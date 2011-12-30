package it.areamobile.apis.hw.areafly.ijones;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import it.areamobile.apis.hw.areafly.entity.AreaFly;
import it.areamobile.apis.hw.areafly.utils.NetUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by AreaMobile
 * Date: 29/12/11
 */

public class Discoverer extends Thread {
    public final static String EVENT_TYPE = "EVENT_TYPE_TAG";
    public static final String TAG = Discoverer.class.getName();
    private static final String REMOTE_KEY = "";

    private final static int DISCOVERY_PORT = AreaFly.PORT;
    private static final int TIMEOUT_MS = 500;
    private final WifiManager mWifi;
    private Collection<AreaFly> areaFlyCollection;

    //TODO Only for test right now
    private final String FAKE_DATA = "D";

    public interface DiscoveryReceiver {
        void addAnnouncedServers(InetAddress[] host, int port[]);
    }

    public Discoverer(WifiManager wifi) {
        mWifi = wifi;
    }

    /**
     * Scan the current wifi network to search any kind of AreaFly.
     *
     * @return the collection of AreaFly available
     */
    public Collection<AreaFly> scan() {
        try {
            DatagramSocket socket = new DatagramSocket(DISCOVERY_PORT);
            socket.setBroadcast(true);
            socket.setSoTimeout(TIMEOUT_MS);

            this.sendDiscoveryRequest(socket, FAKE_DATA);
            this.areaFlyCollection = this.listenForResponses(socket);
        } catch (IOException e) {
            Log.e(TAG, "Could not send request.");
            e.printStackTrace();
        }

        return areaFlyCollection;
    }

    /**
     * Send a broadcast UDP packet containing a request for service to
     * announce themselves.
     *
     * @param socket the socket
     * @param data   the data you'd like to send throw the socket
     * @throws IOException something goes wrong
     */
    private void sendDiscoveryRequest(DatagramSocket socket, String data) throws IOException {
        Log.d(TAG, "Sending data: " + data);

        DatagramPacket packet = new DatagramPacket(data.getBytes(), data.length(), NetUtils.getBroadcastAddress(mWifi), DISCOVERY_PORT);
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

                Log.d(TAG, "Received response: " + parsed);
                String mNetBiosName = parsed;
                String mMacAddress = parsed;
                String mEvent = parsed;
                //////

                areaFly.setNetBiosName(mNetBiosName);
                areaFly.setMacAddress(mMacAddress);
                areaFliesList.add(areaFly);

                //we need to set Event
                Handler handler = areaFly.getEvent().getHandler();
                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putString(EVENT_TYPE, mEvent);
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        } catch (SocketTimeoutException e) {
            Log.d(TAG, "Receive timed out.");
        }

        return areaFliesList;
    }

    /**
     * Calculate the signature we need to send with the request. It is a string
     * containing the hex md5sum of the challenge and remotekey.
     *
     * @param challenge to compare
     * @return signature string
     */
    private String getSignature(String challenge) {
        MessageDigest digest;
        byte[] md5sum = null;
        try {
            digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(challenge.getBytes());
            digest.update(REMOTE_KEY.getBytes());
            md5sum = digest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

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
