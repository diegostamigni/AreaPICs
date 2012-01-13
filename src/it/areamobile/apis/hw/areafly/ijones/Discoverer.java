package it.areamobile.apis.hw.areafly.ijones;

import android.content.Context;
import android.net.wifi.WifiManager;
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
 * This class help you to search AreaFlies and manage those.
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class Discoverer extends Thread {
    public static final String TAG = Discoverer.class.getName();
    private static final String REMOTE_KEY = "";

    public static final int TIMEOUT_MS = 500;
    private WifiManager mWifi;
    private Collection<AreaFly> areaFlyCollection;
    private DatagramSocket socket;

    private Context mContext;

    /**
     * It's a receiver interface, not fully implemented yet.
     */
    //TODO to code
    public interface DiscoveryReceiver {
        void addAnnouncedServers(InetAddress[] host, int port[]);
    }

    /**
     * Blank constructor. You've to set every fields:<br></br>
     * <ol>
     *     <li>Context</li>
     *     <li>WifiManager</li>
     * </ol>
     * @see Discoverer#setContext(android.content.Context)
     * @see Discoverer#setWifiManager(android.net.wifi.WifiManager)
     */
    public Discoverer() {}

    /**
     * Half constructor. You've to set:<br></br>
     * <ol>
     *     <li>WifiManager</li>
     * </ol>
     *
     * @see Discoverer#setWifiManager(android.net.wifi.WifiManager)
     */
    public Discoverer(Context ctx) throws SocketException {
        this.mContext = ctx;
        initSocket();
    }

    /**
     * Half constructor. You've to set:<br></br>
     * <ol>
     *     <li>Context</li>
     * </ol>
     *
     * @see Discoverer#setContext(android.content.Context)
     */
    public Discoverer(android.net.wifi.WifiManager wifi) throws SocketException {
        this.mWifi = wifi;
        initSocket();
    }

    /**
     * It is an adventure obj. Initialize it and use scan function to find any AreaFly on the current net.
     *
     * @param ctx is the context
     * @param wifi is the android.net.wifi.WifiManager
     * @throws SocketException Something goes wrong in the init of the socket. Are you connected ?
     * @see Discoverer#scan()
     */
    public Discoverer(Context ctx, android.net.wifi.WifiManager wifi) throws SocketException {
        this.mContext = ctx;
        mWifi = wifi;
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
     * @throws SocketException something goes wrong declaring the socket
     */
    private void initSocket() throws SocketException {
        // PORT: final static field taken by AreaFly.PORT
        if (socket != null && !socket.isClosed())
            socket.close();

        socket = new DatagramSocket(AreaFly.PORT);
        socket.setBroadcast(true);
        socket.setSoTimeout(TIMEOUT_MS);
    }

    /**
     * Get the current socket, created by Discoverer
     * @return the socket
     * @see DatagramSocket
     */
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
        this.sendMessage(socket, AreaFly.WELCOME);
        return this.areaFlyCollection = this.listenForAllResponses(socket);
    }

    /**
     * Send a broadcast UDP packet containing a request for service to
     * announce themselves. It use the inner socket, created by Discoverer.
     * <br></br><br></br>
     * Be careful: you're developing you're own message, it means that it needs to be parsed as the example below:<br></br>
     * <br></br>
     * <ul>
     *     <li><b>LOREM</b> + <u>AreaFly.SEPARATOR</u> + <b>IPSUM</b></li>
     * </ul>
     * @param msg    the data you'd like to send throw the socket
     * @throws IOException something goes wrong
     * @see AreaFly#SEPARATOR
     * @see Discoverer#sendMessage(java.net.DatagramSocket, String)
     */
    public void sendMessage(String msg) throws IOException {
        DatagramSocket socket = this.getSocket();

        Log.d(TAG, "Sending data: " + msg + " to address (broadcast): " + socket.getInetAddress().getHostAddress());
        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), NetUtils.getBroadcastAddress(mWifi), AreaFly.PORT);
        socket.send(packet);
    }

    /**
     * Send a broadcast UDP packet containing a request for service to
     * announce themselves.
     * <br></br><br></br>
     * Be careful: you're developing you're own message, it means that it needs to be parsed as the example below:<br></br>
     * <br></br>
     * <ul>
     *     <li><b>LOREM</b> + <u>AreaFly.SEPARATOR</u> + <b>IPSUM</b></li>
     * </ul>
     * @param socket the socket
     * @param msg    the data you'd like to send throw the socket
     * @throws IOException something goes wrong
     * @see AreaFly#SEPARATOR
     */
    public void sendMessage(DatagramSocket socket, String msg) throws IOException {
        Log.d(TAG, "Sending data: " + msg + " to address (broadcast): " + socket.getInetAddress().getHostAddress());

        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), NetUtils.getBroadcastAddress(mWifi), AreaFly.PORT);
        socket.send(packet);
    }

    /**
     * Send a broadcast UDP packet containing a request for service to
     * announce themselves. The destAreaFly is the destination AreaFly device.
     * <br></br><br></br>
     * Actually I send a message in this format: <b>LOREM</b> + <u>AreaFly.SEPARATOR</u> + <b>IPSUM</b>
     * @param socket      the socket
     * @param destAreaFly is the AreaFly you would send the data
     * @param msg         the data you'd like to send throw the socket
     * @throws IOException something goes wrong
     * @see AreaFly#SEPARATOR
     */
    public void sendMessage(DatagramSocket socket, AreaFly destAreaFly, String msg) throws IOException {
        String af_address = destAreaFly.getIPAddress();
        String af_netbios = destAreaFly.getNetBiosName();
        String af_macaddress = destAreaFly.getMacAddress();

        String message = destAreaFly.getMacAddress() + AreaFly.SEPARATOR + "D";
        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), NetUtils.getBroadcastAddress(mWifi), AreaFly.PORT);

        Log.d(TAG, "Sending data: " + message + " to address (broadcast): " + socket.getInetAddress().getHostAddress());
        socket.send(packet);
    }

    /**
     * Send a broadcast UDP packet containing a request for service to
     * announce themselves. The destAreaFly is the destination AreaFly device. It use the inner socket, created by Discoverer.
     * <br></br><br></br>
     * Actually I send a message in this format: <b>LOREM</b> + <u>AreaFly.SEPARATOR</u> + <b>IPSUM</b>
     * @param destAreaFly is the AreaFly you would send the data
     * @param msg         the data you'd like to send throw the socket
     * @throws IOException something goes wrong
     * @see AreaFly#SEPARATOR
     */
    public void sendMessage(AreaFly destAreaFly, String msg) throws IOException {
        DatagramSocket socket = this.getSocket();

        String af_address = destAreaFly.getIPAddress();
        String af_netbios = destAreaFly.getNetBiosName();
        String af_macaddress = destAreaFly.getMacAddress();

        String message = destAreaFly.getMacAddress() + AreaFly.SEPARATOR + "D";
        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), NetUtils.getBroadcastAddress(mWifi), AreaFly.PORT);

        Log.d(TAG, "Sending data: " + message + " to address (broadcast): " + socket.getInetAddress().getHostAddress());
        socket.send(packet);
    }

    //TODO review
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
                areaFly = new AreaFly(mContext);
                packet = new DatagramPacket(buf, buf.length);

                socket.receive(packet);

                //TODO it set the broadcast ip instead of a parsing. Check it out
                areaFly.setIPAddress(packet.getAddress().getHostAddress());

                //TODO We need a parser here for the data that I receive
                s = new String(packet.getData(), 0, packet.getLength());
//                String expr = "\\*";
//                String[] parsed = s.split(expr);
                String parsed = s;

                Log.d(TAG, "Received response: " + parsed + " by address (broadcast): " + socket.getInetAddress().getHostAddress());
                String mNetBiosName = parsed;
                String mMacAddress = parsed;
                String mEventDescription = parsed;
                //////

                if (AreaFly.isAreaFly(s)) {
                    areaFly.setNetBiosName(mNetBiosName);
                    areaFly.setMacAddress(mMacAddress);
                    areaFliesList.add(areaFly);

                    //we need to get/set Events
                    areaFly.setEventDescription(mEventDescription);
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

    private void setAreaFlyCollection(Collection<AreaFly> list) {
        this.areaFlyCollection = list;
    }

    //TODO review
    /**
     * Listen on socket for all responses, timing out after TIMEOUT_MS.
     * <br></br>
     *
     * @return a collection of net packet
     * @throws IOException something goes wrong
     * @see java.net.DatagramPacket
     * @see Discoverer#TIMEOUT_MS
     */
    public synchronized Collection<DatagramPacket> listenForResponse() throws IOException {
        byte[] buf = new byte[1024];
        Collection<DatagramPacket> list = new ArrayList<DatagramPacket>();
        DatagramPacket packet = null;

        try {
            while (true) {
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                list.add(packet);
            }
        } catch (SocketTimeoutException e) {
            Log.d(TAG, "Receive timed out.");
        }

        return list;
    }

        //TODO review
    /**
     * Listen on socket for responses of a specific AreaFly, timing out after TIMEOUT_MS. It use inner socket created
     * by Discoverer.
     * <br></br>
     * <b>NOTE:</b> The AreaFly passed will be updated from data received.
     *
     * @return the AreaFly passed
     * @throws IOException something goes wrong
     * @see java.net.DatagramPacket
     * @see it.areamobile.apis.hw.areafly.entity.AreaFly#getEvent()
     * @see Discoverer#TIMEOUT_MS
     */
    public AreaFly listenForData(AreaFly areaFly) throws IOException {
        String af_address = areaFly.getIPAddress();
        String af_netbios = areaFly.getNetBiosName();
        String af_macaddress = areaFly.getMacAddress();
        String s;

        byte[] buf = new byte[1024];
        DatagramPacket packet = null;
        DatagramSocket socket = this.getSocket();

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
                    areaFly.setEventDescription(mEventDescription);
                }
            }
        } catch (SocketTimeoutException e) {
            Log.d(TAG, "Receive timed out.");
        }

        return areaFly;
    }

    //TODO review
    /**
     * Listen on socket for responses of a specific AreaFly, timing out after TIMEOUT_MS.
     * <br></br>
     * <b>NOTE:</b> The AreaFly passed will be updated from data received.
     *
     * @param socket  socket on which the announcement request was sent
     * @param areaFly specify what kind of AreaFly you'd like to listen for response
     * @return the AreaFly passed
     * @throws IOException something goes wrong
     * @see java.net.DatagramPacket
     * @see it.areamobile.apis.hw.areafly.entity.AreaFly#getEvent()
     * @see Discoverer#TIMEOUT_MS
     */
    public AreaFly listenForData(DatagramSocket socket, AreaFly areaFly) throws IOException {
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
                    areaFly.setEventDescription(mEventDescription);
                }
            }
        } catch (SocketTimeoutException e) {
            Log.d(TAG, "Receive timed out.");
        }

        return areaFly;
    }

    //TODO review
    /**
     * Listen on socket for responses of all AreaFly connected, timing out after TIMEOUT_MS.
     *
     * @param socket socket on which the announcement request was sent
     * @return the packet received from the socket. <br></br><b>Note</b>: it will contains all the network data.
     *         May need to be parsed.
     * @throws IOException something goes wrong
     * @see java.net.DatagramPacket
     * @see Discoverer#TIMEOUT_MS
     */
    public Collection<AreaFly> listenForData(DatagramSocket socket) throws IOException {
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

        this.setAreaFlyCollection(areaFliesList);
        return this.getAreaFlyCollection();
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