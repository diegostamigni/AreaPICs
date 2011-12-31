package it.areamobile.apis.hw.areafly.ijones;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import it.areamobile.apis.hw.areafly.entity.AreaFly;
import it.areamobile.apis.hw.areafly.utils.NetUtils;

import java.io.IOException;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by AreaMobile
 * Date: 29/12/11
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class Discoverer extends Thread {
    public final static String EVENT_DESCRIPTION = "EVENT_TYPE_TAG";
    public static final String TAG = Discoverer.class.getName();
    private static final String REMOTE_KEY = "";

    private final static int DISCOVERY_PORT = AreaFly.PORT;
    private static final int TIMEOUT_MS = 500;
    private final WifiManager mWifi;
    private Collection<AreaFly> areaFlyCollection;
    private DatagramSocket socket;

    //TODO Only for test right now
    private final String FAKE_DATA = "D";

    //TODO write javadoc
    public interface DiscoveryReceiver {
        void addAnnouncedServers(InetAddress[] host, int port[]);
    }

    /**
     * It is an adventure obj. Initialize it and use scan function to find any AreaFly on the current net.
     *
     * @param wifi is the android.net.wifi.WifiManager
     * @throws SocketException Something goes wrong in the init of the socket. Are you connected ?
     * @see Discoverer#scan()
     */
    public Discoverer(android.net.wifi.WifiManager wifi) throws SocketException {
        mWifi = wifi;
        initSocket();
    }

    /**
     * @throws SocketException something goes wrong declaring the socket
     */
    private void initSocket() throws SocketException {
        // PORT: final static field taken by AreaFly.PORT
        socket = new DatagramSocket(DISCOVERY_PORT);
        socket.setBroadcast(true);
        socket.setSoTimeout(TIMEOUT_MS);
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    /**
     * Scan the current wifi network to search any kind of AreaFly.
     * This method is synchronized and set the local collection of AreaFlies.
     *
     * @return the collection of AreaFly available
     * @throws IOException something goes wrong
     * @see it.areamobile.apis.hw.areafly.ijones.Discoverer#getAreaFlyCollection()
     */
    public synchronized Collection<AreaFly> scan() throws IOException {
        this.sendMessage(socket, FAKE_DATA);
        this.areaFlyCollection = this.listenForAllResponses(socket);
        Log.e(TAG, "Could not send request.");
        return areaFlyCollection;
    }

    /**
     * Send a broadcast UDP packet containing a request for service to
     * announce themselves.
     *
     * @param socket the socket
     * @param msg    the data you'd like to send throw the socket
     * @throws IOException something goes wrong
     */
    public void sendMessage(DatagramSocket socket, String msg) throws IOException {
        Log.d(TAG, "Sending data: " + msg + " to address (broadcast): " + socket.getBroadcast());

        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), NetUtils.getBroadcastAddress(mWifi), DISCOVERY_PORT);
        socket.send(packet);
    }

    /**
     * Send a broadcast UDP packet containing a request for service to
     * announce themselves. The destAreaFly is the destination AreaFly device.
     *
     * @param socket      the socket
     * @param destAreaFly is the AreaFly you would send the data
     * @param msg         the data you'd like to send throw the socket
     * @throws IOException something goes wrong
     */
    public void sendMessage(DatagramSocket socket, AreaFly destAreaFly, String msg) throws IOException {
        String af_address = destAreaFly.getIPAddress();
        String af_netbios = destAreaFly.getNetBiosName();
        String af_macaddress = destAreaFly.getMacAddress();

        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), NetUtils.getBroadcastAddress(mWifi), DISCOVERY_PORT);
        socket.send(packet);
    }

    /**
     * Listen on socket for responses, timing out after TIMEOUT_MS
     *
     * @param socket socket on which the announcement request was sent
     * @return the collection of found areaFlies
     * @throws IOException something goes wrong
     */
    private Collection<AreaFly> listenForAllResponses(DatagramSocket socket) throws IOException {
        byte[] buf = new byte[1024];
        DatagramPacket packet;
        String s;
        AreaFly areaFly;
        Collection<AreaFly> areaFliesList = null;

        try {
            areaFliesList = new ArrayList<AreaFly>();
            while (true) {
                areaFly = new AreaFly();
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                areaFly.setIPAddress(packet.getAddress().getHostAddress());

                //TODO We need a parser here for the data that I receive
                s = new String(packet.getData(), 0, packet.getLength());
//                String expr = "\\*";
//                String[] parsed = s.split(expr);
                String parsed = s;

                Log.d(TAG, "Received response: " + parsed + " by address (broadcast): " + socket.getBroadcast());
                String mNetBiosName = parsed;
                String mMacAddress = parsed;
                String mEventDescription = parsed;
                //////

                if (AreaFly.isAreaFly(s)) {
                    areaFly.setNetBiosName(mNetBiosName);
                    areaFly.setMacAddress(mMacAddress);
                    areaFliesList.add(areaFly);

                    //we need to get/set Events
                    setEventDescription(areaFly, mEventDescription);
                }
            }
        } catch (SocketTimeoutException e) {
            Log.d(TAG, "Receive timed out.");
        }

        return areaFliesList;
    }

    /**
     * Get the AreaFly collection, already scanned.<br></br>
     * <b>Note: </b>This collection may be old, you've to rescan you're collection of AreaFlies.
     *
     * @return the collection of AreaFly already scanned before with this.scan()
     * @see Discoverer#scan()
     */
    public Collection<AreaFly> getAreaFlyCollection() {
        return areaFlyCollection;
    }

    /**
     * Listen on socket for responses, timing out after TIMEOUT_MS.
     *
     * @param socket  socket on which the announcement request was sent
     * @param areaFly specify what kind of AreaFly you'd like to listen for response
     * @return the packet received (Event) set to the passed areaFly.
     * @throws IOException something goes wrong
     * @see java.net.DatagramPacket
     * @see it.areamobile.apis.hw.areafly.entity.AreaFly#getEvent()
     */
    public synchronized DatagramPacket listenForData(DatagramSocket socket, AreaFly areaFly) throws IOException {
        String af_address = areaFly.getIPAddress();
        String af_netbios = areaFly.getNetBiosName();
        String af_macaddress = areaFly.getMacAddress();
        String s;

        byte[] buf = new byte[1024];
        DatagramPacket packet = null;

        try {
            while (true) {
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                //TODO We need a parser here for the data that I receive
                s = new String(packet.getData(), 0, packet.getLength());
//                String expr = "\\*";
//                String[] parsed = s.split(expr);
                String parsed = s;
                String mMacAddress = parsed;
                String mEventDescription = parsed;
                //////

                if (AreaFly.isAreaFly(s) && mMacAddress.equalsIgnoreCase(areaFly.getMacAddress())) {
                    //we need to get/set Events, if we're talking with the same areaFly we passed
                    setEventDescription(areaFly, mEventDescription);
                }
            }
        } catch (SocketTimeoutException e) {
            Log.d(TAG, "Receive timed out.");
        }

        return packet;
    }

    /**
     * Listen on socket for responses, timing out after TIMEOUT_MS.
     *
     * @param socket socket on which the announcement request was sent
     * @return the packet received from the socket. <br></br><b>Note</b>: it will contains all the network data.
     *         May need to be parsed.
     * @throws IOException something goes wrong
     * @see java.net.DatagramPacket
     */
    public synchronized DatagramPacket listenForData(DatagramSocket socket) throws IOException {
        byte[] buf = new byte[1024];
        DatagramPacket packet = null;
        Collection<AreaFly> areaFliesList = null;
        String s;

        try {
            areaFliesList = this.getAreaFlyCollection();
            while (true) {
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                /*
                 * TODO packed need to be parsed and divided. Read below.
                 * The packet will contains a lot of data. This method will return all data in a single packet,
                 * came from Broadcast. This one contains the information about all AreaFlies. But we're listening
                 * for Events, and every event will be connected to his specific AreaFly. We got the collection of
                 * AreaFly with getAreaFlyCollection() and on every AreaFly we can use getMacAddress().
                 *
                 * Every packet contains also the MAC_ADDRESS that need to be compared with the internal mac_address
                 * in the packet; so, we can compare this data to update every AreaFly with his specific Event.
                 */
                s = new String(packet.getData(), 0, packet.getLength());
//                String expr = "\\*";
//                String[] parsed = s.split(expr);
                String parsed = s;
                String mMacAddress = parsed;
                String mEventDescription = parsed;
            }
        } catch (SocketTimeoutException e) {
            Log.d(TAG, "Receive timed out.");
        }

        return packet;
    }

    private void setEventDescription(AreaFly areaFly, String eventDescription) {
        Handler handler = areaFly.getEvent().getHandler();
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(EVENT_DESCRIPTION, eventDescription);
        msg.setData(bundle);
        handler.sendMessage(msg);
    }

    /**
     * Calculate the signature we need to send with the request. It is a string
     * containing the hex md5sum of the challenge and remotekey.
     *
     * @param challenge to compare
     * @return signature string
     */
    private String getSignature(String challenge) throws NoSuchAlgorithmException {
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
}