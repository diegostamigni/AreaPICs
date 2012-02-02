package eu.areamobile.apis.hw.pics.ijones;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;
import eu.areamobile.apis.hw.pics.entity.Common;
import eu.areamobile.apis.hw.pics.entity.json.HWJSonIOSpecs;
import eu.areamobile.apis.hw.pics.entity.json.JSonFactory;
import eu.areamobile.apis.hw.pics.utils.NetUtils;

import java.io.IOException;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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
    private List<Common> mCommonCollection;
    private DatagramSocket socket;
    private JSonFactory mJSonFactory;
    private Context mContext;
    private int port = -1;

    /**
     * It's a receiver interface, not fully implemented yet.
     */
    public interface DiscoveryReceiver {
        void addAnnouncedServers(InetAddress[] host, int port[]);
    }

    /**
     * Blank constructor. You've to set every fields:<br></br>
     * <ol>
     * <li>Context</li>
     * <li>WifiManager</li>
     * <li>Discoverer port: where to find</li>
     * </ol>
     *
     * @see Discoverer#setContext(android.content.Context)
     * @see Discoverer#setWifiManager(android.net.wifi.WifiManager)
     * @see Discoverer#setDiscovererPort(int)
     */
    public Discoverer() {
        mJSonFactory = new JSonFactory();
    }

    /**
     * Half constructor. You've to set:<br></br>
     * <ol>
     * <li>WifiManager</li>
     * </ol>
     *
     * @see Discoverer#setWifiManager(android.net.wifi.WifiManager)
     */
    public Discoverer(Context ctx, int port) throws SocketException {
        this.mContext = ctx;
        mJSonFactory = new JSonFactory();
        this.port = port;
        initSocket();
    }

    /**
     * Half constructor. You've to set:<br></br>
     * <ol>
     * <li>Context</li>
     * </ol>
     *
     * @see Discoverer#setContext(android.content.Context)
     */
    public Discoverer(android.net.wifi.WifiManager wifi, int port) throws SocketException {
        this.mWifi = wifi;
        mJSonFactory = new JSonFactory();
        this.port = port;
        initSocket();
    }

    /**
     * It is an adventure obj. Initialize it and use scan function to find any Common on the current net.
     *
     * @param ctx  is the context
     * @param wifi is the android.net.wifi.WifiManager
     * @param port is the discoverer port
     * @throws SocketException Something goes wrong in the init of the socket. Are you connected ?
     * @see Discoverer#scan()
     */
    public Discoverer(Context ctx, android.net.wifi.WifiManager wifi, int port) throws SocketException {
        this.mContext = ctx;
        mWifi = wifi;
        mJSonFactory = new JSonFactory();
        this.port = port;
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
        if (socket != null && !socket.isClosed())
            socket.close();

        socket = new DatagramSocket(this.port);
        socket.setBroadcast(true);
        socket.setSoTimeout(TIMEOUT_MS);
    }

    /**
     * Get the current socket, created by Discoverer
     *
     * @return the socket
     * @uml.property name="socket"
     * @see DatagramSocket
     */
    public DatagramSocket getSocket() {
        return socket;
    }

    /**
     * @param socket
     * @uml.property name="socket"
     */
    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    private HWJSonIOSpecs createNetworkScanMessage() {
        HWJSonIOSpecs sayHiAll = new HWJSonIOSpecs();
        HWJSonIOSpecs.ExecValue exec = new HWJSonIOSpecs.ExecValue();
        HWJSonIOSpecs.Argv[] argv = new HWJSonIOSpecs.Argv[0];

        exec.setAck(true);
        exec.setDevice("");
        exec.setArgv(argv);
        exec.setGroup(HWJSonIOSpecs.GROUP_ALL);
        exec.setOp(HWJSonIOSpecs.OPCODE_SCAN);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis());
        sayHiAll.setExec(exec);
        return sayHiAll;
    }

    public synchronized List<Common> scan() throws IOException {
        byte[] buf = new byte[1024];
        DatagramPacket packet;
        String s;

        this.sendMessage(createNetworkScanMessage());

        try {
            this.mCommonCollection = new ArrayList<Common>();
            while (true) {
                Common mCommon = new Common(mContext);
                packet = new DatagramPacket(buf, buf.length);

                socket.receive(packet);
                mCommon.setIPAddress(packet.getAddress().getHostAddress());

                s = new String(packet.getData(), 0, packet.getLength());

                HWJSonIOSpecs ioSpecs = mJSonFactory.parseFromStream(s);

                //                if (Common.isCommon(s)) {
                mCommon.setMacAddress(ioSpecs.getExec().getDevice());
                mCommon.setDescription(ioSpecs);

                mCommonCollection.add(mCommon);

                //we need to get/set Events
                //                }
            }
        } catch (SocketTimeoutException e) {
            Log.d(TAG, "Receive timed out.");
        }

        return mCommonCollection;
    }

    /**
     * Send a broadcast UDP packet containing a request for service to
     * announce themselves. It use the inner socket, created by Discoverer.
     * <br></br><br></br>
     * Be careful: you're developing you're own message, it means that it needs to be parsed as the example below:<br></br>
     * <br></br>
     * <ul>
     * <li><b>LOREM</b> + <u>Common.SEPARATOR</u> + <b>IPSUM</b></li>
     * </ul>
     *
     * @param stream the data you'd like to send throw the socket
     * @throws IOException something goes wrong
     * @see Discoverer#sendMessage(eu.areamobile.apis.hw.pics.entity.json.HWJSonIOSpecs)
     */
    public void sendMessage(HWJSonIOSpecs stream) throws IOException {
        DatagramSocket socket = this.getSocket();
        String msg = mJSonFactory.transfertStream(stream);
        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), NetUtils.getBroadcastAddress(mWifi), getDiscovererPort());
        socket.send(packet);
    }

    /**
     * Send a broadcast UDP packet containing a request for service to
     * announce themselves. The destCommon is the destination Common device. It use the inner socket, created by Discoverer.
     * <br></br><br></br>
     * Actually I send a message in this format: <b>LOREM</b> + <u>Common.SEPARATOR</u> + <b>IPSUM</b>
     *
     * @param destCommon is the Common you would send the data
     * @param stream     the data you'd like to send throw the socket
     * @throws IOException something goes wrong
     */
//    public void sendMessage(Common destCommon, HWJSonIOSpecs stream) throws IOException {
//        DatagramSocket socket = this.getSocket();
//        String dest_macaddress = stream.getExec().getDevice();
//        String message = mJSonFactory.transfertStream(stream);
//        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), NetUtils.getBroadcastAddress(mWifi), getDiscovererPort());
//        socket.send(packet);
//    }

    /**
     * Listen on socket for all responses, timing out after TIMEOUT_MS.
     * <br></br>
     *
     * @return a collection of net raw packet, to be parsed
     * @throws IOException something goes wrong
     * @see java.net.DatagramPacket
     * @see Discoverer#TIMEOUT_MS
     */
    public List<DatagramPacket> listenForResponse() throws IOException {
        byte[] buf = new byte[1024];
        List<DatagramPacket> list = new ArrayList<DatagramPacket>();
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
     * Listen on socket for responses of a specific Common, timing out after TIMEOUT_MS. It use inner socket created
     * by Discoverer.
     * <br></br>
     * <b>NOTE:</b> The Common passed will be updated from data received.
     *
     * @return the Common passed
     * @throws IOException something goes wrong
     * @see java.net.DatagramPacket
     * @see Discoverer#TIMEOUT_MS
     */
//    public Common listenForResponse(Common mCommon) throws IOException {
//        String af_address = mCommon.getIPAddress();
//        String af_macaddress = mCommon.getMacAddress();
//        String s;
//
//        byte[] buf = new byte[1024];
//        DatagramPacket packet = null;
//        DatagramSocket socket = this.getSocket();
//
//        try {
//            while (true) {
//                packet = new DatagramPacket(buf, buf.length);
//                socket.receive(packet);
//
//                //TODO We need a parser here for the data that I receive
//                s = new String(packet.getData(), 0, packet.getLength());
////                String expr = "\\*";
////                String[] parsed = s.split(expr);
//                String parsed = s;
//                String mMacAddress = parsed;
//                //////
//
////                if (Common.isCommon(null) && mMacAddress.equalsIgnoreCase(mCommon.getMacAddress())) {
//                //we need to get/set Events, if we're talking with the same Common we passed
//                mCommon.setDescription(null);
////                }
//            }
//        } catch (SocketTimeoutException e) {
//            Log.d(TAG, "Receive timed out.");
//        }
//
//        return mCommon;
//    }

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

    /**
     * Get the Common collection, already scanned.<br></br>
     * <b>Note: </b>This collection may be old, you've to rescan you're collection of AreaFlies.
     *
     * @return the collection of Common already scanned before with this.scan()
     * @see Discoverer#scan()
     */
    public List<Common> getCommonCollection() {
        return mCommonCollection;
    }

//    private void setCommonCollection(final List<Common> list) {
//        this.mCommonCollection = list;
//    }


    public void setDiscovererPort(int port) {
        this.port = port;
    }

    public int getDiscovererPort() {
        return this.port;
    }
}