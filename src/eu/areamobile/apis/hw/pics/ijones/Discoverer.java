package eu.areamobile.apis.hw.pics.ijones;

import eu.areamobile.apis.hw.pics.HWOperations;
import eu.areamobile.apis.hw.pics.entity.GenericDevice;
import eu.areamobile.apis.hw.pics.entity.Operation;
import eu.areamobile.apis.hw.pics.exceptions.UnknownDeviceException;
import eu.areamobile.apis.hw.pics.proto.HWJSonIOSpecs;
import eu.areamobile.apis.hw.pics.proto.JSonFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by AreaMobile
 * Date: 29/12/11
 * <p/>
 * This class help you to search DooIP and manage those.
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class Discoverer {
    private static final String REMOTE_KEY = "";

    public static final int TIMEOUT_MS = 500;
    private DatagramSocket mainSocket;
    private JSonFactory mJSonFactory;
    private int socketPort = -1;
    private String macAddress;
    private InetAddress broadcastAddress;

    public interface OnResponseListener {
        void onMessageReceived(HWJSonIOSpecs.Status status);
    }

    public interface OnScanResponseListener<T> {
        void onScanFinished(Set<T> mGenericDeviceCollection);
        void onScanInProgress(T device, int position);
    }

    /**
     * It is an adventurer obj. Initialize it and use scan function to find any Common on the current net.
     *
     * @param broadcastAddress is you ip broadcast address
     * @param yourNetMacAddress is your net-mac-address
     * @param socketPort is the discoverer socketPort where it listen
     * @throws SocketException Something goes wrong in the init of the mainSocket. Are you connected ?
     * @see Discoverer#scan(Class, eu.areamobile.apis.hw.pics.ijones.Discoverer.OnScanResponseListener)
     */
    public Discoverer(InetAddress broadcastAddress, String yourNetMacAddress, int socketPort) throws SocketException {
        mJSonFactory = new JSonFactory();
        this.socketPort = socketPort;
        this.broadcastAddress = broadcastAddress;
        macAddress = yourNetMacAddress;
        initSocket();
    }

    /**
     * @throws SocketException something goes wrong declaring the mainSocket
     */
    private void initSocket() throws SocketException {
        if (mainSocket != null && !mainSocket.isClosed()) mainSocket.close();
        mainSocket = new DatagramSocket(this.socketPort);
        mainSocket.setBroadcast(true);
        mainSocket.setSoTimeout(TIMEOUT_MS);
    }

    /**
     * Get the current socket, used by Discoverer
     *
     * @return the receiverSocket
     * @see DatagramSocket
     */
    public DatagramSocket getSocketDiscoverer() {
        return mainSocket;
    }

    /**
     * Set the current receiverSocket, used by Discoverer
     * @param mainSocket
     */
    public void setSocketDiscoverer(DatagramSocket mainSocket) {
        this.mainSocket = mainSocket;
    }

    private HWJSonIOSpecs createNetworkScanMessage() {
        HWJSonIOSpecs sayHiAll = new HWJSonIOSpecs();
        HWJSonIOSpecs.Exec exec = new HWJSonIOSpecs.Exec();
        HWJSonIOSpecs.Argv[] argv = new HWJSonIOSpecs.Argv[0];

        exec.setAck(false);
        exec.setSender(this.macAddress);
        exec.setReceiver(HWOperations.MA_TO_BROADCAST);
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_ALL);
        exec.setOp(HWOperations.OPCODE_SCAN);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis()+"");
        sayHiAll.setExec(exec);
        return sayHiAll;
    }

    /**
     * Scan the specified pics
     * @param type the type of the device to be scanned
     * @param listener return from the stream
     * @throws IOException
     */
    public <T> void scan(Class<T> type, OnScanResponseListener<T> listener) throws IOException, UnknownDeviceException {
        T mCurrentGenericDevice = null;
        Set<T> mGenericDeviceCollection = new CopyOnWriteArraySet<T>();
        byte[] buf = new byte[1024];
        DatagramPacket packet;
        String s;

        DatagramSocket socket = this.getSocketDiscoverer();
        String msg = mJSonFactory.transfertStream(createNetworkScanMessage());
        packet = new DatagramPacket(msg.getBytes(), msg.length(), this.broadcastAddress, this.getSocketPort());
        socket.send(packet);

        int position = 0;
        try {
            while (true) {
                mCurrentGenericDevice = type.getConstructor(String.class).newInstance(this.macAddress);
                packet = new DatagramPacket(buf, buf.length, this.broadcastAddress, this.getSocketDiscoverer().getLocalPort());

                mainSocket.receive(packet);

                ((GenericDevice) mCurrentGenericDevice).setIPAddress(packet.getAddress().getHostAddress());

                s = new String(packet.getData(), 0, packet.getLength());

                HWJSonIOSpecs ioSpecs = mJSonFactory.parseFromStream(s);

                // check if already exist
                if (ioSpecs != null && ioSpecs.getStatus() != null && ioSpecs.getStatus().getArgv().length > 0) {
                    ((GenericDevice) mCurrentGenericDevice).setNetBiosName(((String) ioSpecs.getStatus().getArgv()[0].getValue()).trim());
                    ((GenericDevice) mCurrentGenericDevice).setDescription(ioSpecs);
                    mGenericDeviceCollection.add(mCurrentGenericDevice);
                    if (listener != null) listener.onScanInProgress(mCurrentGenericDevice, position);
                    position++;
                }
            }
        } catch (SocketTimeoutException e) {
            System.out.print("Receive timed out.");
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new UnknownDeviceException("What kind of device I've to scan for you?");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new UnknownDeviceException("What kind of device I've to scan for you?");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new UnknownDeviceException("What kind of device I've to scan for you?");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new UnknownDeviceException("What kind of device I've to scan for you?");
        }

        if (listener != null) listener.onScanFinished(mGenericDeviceCollection);
    }

    /**
     * Send a broadcast UDP packet containing a request for service to
     * announce themselves. It use the inner mainSocket, created by Discoverer.
     *
     * @param genericDevice At who do you want to send?
     * @param listener return from the stream
     * @throws IOException something goes wrong
     * @see eu.areamobile.apis.hw.pics.proto.HWJSonIOSpecs
     */
    public void sendMessage(GenericDevice genericDevice, OnResponseListener listener) throws IOException {
        this.sendMessage(genericDevice, listener, 250);
    }

    public void sendMessage(GenericDevice genericDevice, OnResponseListener listener, long value) throws IOException {
        byte[] buf = new byte[1024];
        String msg = mJSonFactory.transfertStream(genericDevice.getOperation().getJsonSpecs());
        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), this.broadcastAddress, this.getSocketPort());
        mainSocket.send(packet);

        try {
            while(true) {
                DatagramPacket receivePacket = new DatagramPacket(buf, buf.length, this.broadcastAddress, this.getSocketPort());
                mainSocket.receive(receivePacket);

                String s = new String(receivePacket.getData(), 0, receivePacket.getLength());
                HWJSonIOSpecs ioSpecs = mJSonFactory.parseFromStream(s);

                if (listener != null && ioSpecs != null && ioSpecs.getStatus() != null && (ioSpecs.getStatus().getDevice().equalsIgnoreCase(genericDevice.getDescription().getStatus().getDevice())))
                    listener.onMessageReceived(ioSpecs.getStatus());
            }
        } catch (SocketTimeoutException e) {
            System.out.print("Receive timed out.");
        }

        waitForComplete(value);
    }

    /**
     * Send a broadcast UDP packet to-all containing a request for service to
     * announce themselves. It use the inner mainSocket, created by Discoverer.
     *
     * @param operation the operation you'd like to send throw the mainSocket
     * @throws IOException something goes wrong
     * @see eu.areamobile.apis.hw.pics.proto.HWJSonIOSpecs
     */
    public void sendMessageToAll(Operation operation) throws IOException {
        this.sendMessageToAll(operation, 250);
    }

    public void sendMessageToAll(Operation operation, long millisToWait) throws IOException {
        String msg = mJSonFactory.transfertStream(operation.getJsonSpecs());
        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), this.broadcastAddress, getSocketPort());
        mainSocket.send(packet);

        waitForComplete(millisToWait);
    }

    private void waitForComplete(long value) {
        try {
            Thread.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculate the signature we need to send with the request. It is a string
     * containing the hex md5sum of the challenge and remote key.
     *
     * @param challenge to compare
     * @return signature string
     */
    public String getSignature(final String challenge) throws NoSuchAlgorithmException {
        MessageDigest digest;
        byte[] md5sum;

        digest = java.security.MessageDigest.getInstance("MD5");
        digest.update(challenge.getBytes());
        digest.update(REMOTE_KEY.getBytes());
        md5sum = digest.digest();

        StringBuilder hexString = new StringBuilder();
        if (md5sum != null)
            for (byte aMd5sum : md5sum) {
                String s = Integer.toHexString((int) aMd5sum & 0xFF);
                if (s.length() == 1)
                    hexString.append('0');
                hexString.append(s);
            }
        return hexString.toString();
    }

    public void setSocketPort(int port) {
        this.socketPort = port;
    }

    public int getSocketPort() {
        return this.socketPort;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public InetAddress getBroadcastAddress() {
        return broadcastAddress;
    }

    public void setBroadcastAddress(InetAddress broadcastAddress) {
        this.broadcastAddress = broadcastAddress;
    }
}
