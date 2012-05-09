package eu.areamobile.apis.hw.pics.ijones;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;
import eu.areamobile.apis.hw.pics.entity.GenericDevice;
import eu.areamobile.apis.hw.pics.entity.HWOperations;
import eu.areamobile.apis.hw.pics.entity.json.HWJSonIOSpecs;
import eu.areamobile.apis.hw.pics.entity.json.JSonFactory;
import eu.areamobile.apis.hw.pics.exceptions.UnknownDeviceException;
import eu.areamobile.apis.hw.pics.utils.NetUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by AreaMobile
 * Date: 29/12/11
 * <p/>
 * This class help you to search AreaFlies and manage those.
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class Discoverer extends Thread {
    public static final String TAG = Discoverer.class.getName();
    private static final String REMOTE_KEY = "";

    public static final int TIMEOUT_MS = 500;
    private WifiManager mWifi;
    private DatagramSocket mainSocket;
    private JSonFactory mJSonFactory;
    private Context mContext;
    private int socketPort = -1;

    public interface OnResponseListener {
        void onMessageReceived(HWJSonIOSpecs.Status status);
    }

    public interface OnScanResponseListener<T> {
        void onScanFinished(List<T> list);
        void onScanInProgress(T device, int position);
    }

    /**
     * Blank constructor. You've to set every fields:<br></br>
     * <ol>
     * <li>Context</li>
     * <li>WifiManager</li>
     * <li>Discoverer socketPort: where to listen</li>
     * <li>Discoverer senderPort: where to send</li>
     * </ol>
     *
     * @see Discoverer#setContext(android.content.Context)
     * @see Discoverer#setWifiManager(android.net.wifi.WifiManager)
     * @see Discoverer#setSocketPort
     */
    public Discoverer() {
        mJSonFactory = new JSonFactory();
    }

    /**
     * It is an adventure obj. Initialize it and use scan function to find any Common on the current net.
     *
     * @param ctx  is the context
     * @param wifi is the android.net.wifi.WifiManager
     * @param socketPort is the discoverer socketPort where it listen
     * @throws SocketException Something goes wrong in the init of the mainSocket. Are you connected ?
     * @see Discoverer#scan(Class, eu.areamobile.apis.hw.pics.ijones.Discoverer.OnScanResponseListener)
     */
    public Discoverer(Context ctx, android.net.wifi.WifiManager wifi, int socketPort) throws SocketException {
        this.mContext = ctx;
        mWifi = wifi;
        mJSonFactory = new JSonFactory();
        this.socketPort = socketPort;
        initSocket();
    }

    public WifiManager getWifiManager() {
        return mWifi;
    }

    public Context getContext() {
        return mContext;
    }

    public void setWifiManager(WifiManager mWifi) {
        this.mWifi = mWifi;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
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
        exec.setSender(((WifiManager) mContext.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getMacAddress());
        exec.setReceiver("ff:ff:ff:ff:ff:ff");
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
        List<T> mGenericDeviceCollection = new CopyOnWriteArrayList<T>();
        byte[] buf = new byte[1024];
        DatagramPacket packet;
        String s;

        DatagramSocket socket = this.getSocketDiscoverer();
        String msg = mJSonFactory.transfertStream(createNetworkScanMessage());
        packet = new DatagramPacket(msg.getBytes(), msg.length(), NetUtils.getBroadcastAddress(mWifi), getSocketPort());
        socket.send(packet);

        int position = 0;
        try {
            while (true) {
                mCurrentGenericDevice = type.getConstructor(String.class).newInstance(((WifiManager) mContext.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getMacAddress());

                packet = new DatagramPacket(buf, buf.length, NetUtils.getBroadcastAddress(mWifi), getSocketDiscoverer().getLocalPort());

                mainSocket.receive(packet);
                ((GenericDevice) mCurrentGenericDevice).setIPAddress(packet.getAddress().getHostAddress());

                s = new String(packet.getData(), 0, packet.getLength());

                HWJSonIOSpecs ioSpecs = mJSonFactory.parseFromStream(s);

                // check if already exist
                if (ioSpecs != null && ioSpecs.getStatus() != null) {
                    if (mGenericDeviceCollection.size() > 0) {
                        for (T genericDevice : mGenericDeviceCollection) {
                            if (!(ioSpecs.getStatus().getDevice().equalsIgnoreCase(((GenericDevice)genericDevice).getDescription().getStatus().getDevice()))) {
                                ((GenericDevice) mCurrentGenericDevice).setDescription(ioSpecs);
                                ((GenericDevice) mCurrentGenericDevice).setNetBiosName(((String) ioSpecs.getStatus().getArgv()[0].getValue()).trim());
                                mGenericDeviceCollection.add(mCurrentGenericDevice);
                                if (listener != null) listener.onScanInProgress(genericDevice, position);
                                position++;
                            }
                        }
                    } else {
                        HWJSonIOSpecs.Argv[] argv = ioSpecs.getStatus().getArgv();
                        if (argv.length > 0) {
                            ((GenericDevice) mCurrentGenericDevice).setNetBiosName(((String) ioSpecs.getStatus().getArgv()[0].getValue()).trim());
                            ((GenericDevice) mCurrentGenericDevice).setDescription(ioSpecs);
                            mGenericDeviceCollection.add(mCurrentGenericDevice);
                            if (listener != null) listener.onScanInProgress(mCurrentGenericDevice, position);
                            position++;
                        }
                    }
                }
            }
        } catch (SocketTimeoutException e) {
            Log.d(TAG, "Receive timed out.");
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
     * @param stream the data you'd like to send throw the mainSocket
     * @param listener return from the stream
     * @throws IOException something goes wrong
     * @see eu.areamobile.apis.hw.pics.entity.json.HWJSonIOSpecs
     */
    public void sendMessage(HWJSonIOSpecs stream, OnResponseListener listener) throws IOException {
        DatagramSocket socket = this.getSocketDiscoverer();
        String msg = mJSonFactory.transfertStream(stream);
        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), NetUtils.getBroadcastAddress(mWifi), getSocketPort());
        socket.send(packet);

        try {
            mainSocket.receive(packet);
            String s = new String(packet.getData(), 0, packet.getLength());
            HWJSonIOSpecs ioSpecs = mJSonFactory.parseFromStream(s);

            if (listener != null && ioSpecs != null && ioSpecs.getStatus() != null)
                listener.onMessageReceived(ioSpecs.getStatus());

        } catch (SocketTimeoutException e) { Log.d(TAG, "Receive timed out."); }
    }

    /**
     * Calculate the signature we need to send with the request. It is a string
     * containing the hex md5sum of the challenge and remotekey.
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
}