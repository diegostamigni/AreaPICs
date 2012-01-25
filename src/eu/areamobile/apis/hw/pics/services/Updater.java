package eu.areamobile.apis.hw.pics.services;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import eu.areamobile.apis.hw.pics.entity.Common;
import eu.areamobile.apis.hw.pics.entity.json.JSonFactory;
import eu.areamobile.apis.hw.pics.ijones.Discoverer;

/**
 * Created by AreaMobile
 * Date: 31/12/11
 * <p/>
 * This service is developed to work as an updater for  a single Common\'s events.
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 * @see Service
 */

public class Updater extends Service {
    /**
	 * @uml.property  name="timer"
	 */
    private final Timer timer;
    /**
	 * @uml.property  name="pERIOD"
	 */
    private int PERIOD = 10000;
    /**
	 * @uml.property  name="socket"
	 */
    private DatagramSocket socket;
    /**
	 * @uml.property  name="mCommon"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    private final Common mCommon;
    /**
	 * @uml.property  name="mWifi"
	 * @uml.associationEnd  
	 */
    private WifiManager mWifi;
    /**
	 * @uml.property  name="sTATUS_MSG"
	 */
    private final String STATUS_MSG = "STATUS";
    /**
	 * @uml.property  name="packet"
	 */
    private DatagramPacket packet;
    /**
	 * @uml.property  name="discoverer"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    private Discoverer discoverer;

    /**
     * Updater constructor. Without period parameter, it's by default set on 10000 mills.
     *
     * @param mCommon is the Common where you'd like to allow the event service auto updater
     * @see Updater(Common, int)
     */
    public Updater(Common mCommon) {
        timer = new Timer();
        discoverer = new Discoverer();

        this.mCommon = mCommon;
    }

    /**
     * Updater constructor.
     *
     * @param mCommon is the Common where you'd like to allow the event service auto updater
     * @param period is the time in millis used by the service as a delay between every update
     * @see Updater(Common)
     */
    public Updater(Common mCommon, int period) {
        timer = new Timer();
        discoverer = new Discoverer();

        PERIOD = period;
        this.mCommon = mCommon;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mWifi = (WifiManager) mCommon.getContext().getSystemService(Context.WIFI_SERVICE);
        discoverer.setWifiManager(mWifi);
        discoverer.setContext(mCommon.getContext());


        try {
            socket = new DatagramSocket(mCommon.getSocketPort());
            socket.setBroadcast(true);
            socket.setSoTimeout(Discoverer.TIMEOUT_MS);
            discoverer.setSocket(socket);
        } catch (SocketException e) { e.printStackTrace(); }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Stuff here
                try {
                    byte[] buf = new byte[1024];
                    //TODO review
                    discoverer.sendMessage(null);
                    String s;

                    while (true) {
                        packet = new DatagramPacket(buf, buf.length);
                        socket.receive(packet);

                        s = new String(packet.getData(), 0, packet.getLength());
                        JSonFactory factory = mCommon.getJSonFactory();

                        //TODO review
//                        if (Common.isCommon(s)) {
                            //we need to get/set Events, if we're talking with the same common we passed
                            mCommon.setDescription(null);
//                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, PERIOD);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
	 * @return
	 * @uml.property  name="discoverer"
	 */
    public Discoverer getDiscoverer() {
        return discoverer;
    }

    /**
	 * Return the socket used by this service.
	 * @return  socket
	 * @see  DatagramSocket
	 * @uml.property  name="socket"
	 */
    public DatagramSocket getSocket() {
        return socket;
    }

    /**
     * Return the WiFiManager used by this service.
     * @return socket
     * @see android.net.wifi.WifiManager
     */
    public WifiManager getWifiManager() {
        return mWifi;
    }
}
