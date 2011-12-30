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

    /**
     * Time out of the socket
     *
     * @return the timeout of the socket in mills
     */
    public static int getTimeOutInMs() {
        return TIMEOUT_MS;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    /**
     * Scan the current wifi network to search any kind of AreaFly.
     * This method is synchronized.
     *
     * @return the collection of AreaFly available
     */
    public synchronized Collection<AreaFly> scan() throws IOException {
        this.sendMessage(socket, FAKE_DATA);
        this.areaFlyCollection = this.listenForResponses(socket);
        Log.e(TAG, "Could not send request.");
        return areaFlyCollection;
    }

    /**
     * Send a broadcast UDP packet containing a request for service to
     * announce themselves.
     *
     * @param socket the socket
     * @param msg   the data you'd like to send throw the socket
     * @throws IOException something goes wrong
     */
    private void sendMessage(DatagramSocket socket, String msg) throws IOException {
        Log.d(TAG, "Sending data: " + msg + " to address (broadcast): " + socket.getBroadcast());

        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), NetUtils.getBroadcastAddress(mWifi), DISCOVERY_PORT);
        socket.send(packet);
    }
    
    /**
     * Send a broadcast UDP packet containing a request for service to
     * announce themselves.
     *
     * @param socket the socket
     * @param msg   the data you'd like to send throw the socket
     * @throws IOException something goes wrong
     */
    private void sendMessage(AreaFly destAreaFly, DatagramSocket socket, String msg) throws IOException {
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
     * @return the collection of found areafly
     * @throws IOException something goes wrong
     */
    private Collection<AreaFly> listenForResponses(DatagramSocket socket) throws IOException {
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
                    Handler handler = areaFly.getEvent().getHandler();
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString(EVENT_DESCRIPTION, mEventDescription);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }
        } catch (SocketTimeoutException e) {
            Log.d(TAG, "Receive timed out.");
        }

        return areaFliesList;
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
    public DatagramPacket listenForData(DatagramSocket socket) throws IOException {
        byte[] buf = new byte[1024];
        DatagramPacket packet = null;

        try {
            while (true) {
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
            }
        } catch (SocketTimeoutException e) {
            Log.d(TAG, "Receive timed out.");
        }

        return packet;
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
