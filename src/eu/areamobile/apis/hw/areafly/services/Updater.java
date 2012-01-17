package eu.areamobile.apis.hw.areafly.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import eu.areamobile.apis.hw.areafly.entity.AreaFly;
import eu.areamobile.apis.hw.areafly.ijones.Discoverer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by AreaMobile
 * Date: 31/12/11
 * <p/>
 * This service is developed to work as an updater for areafly's events.
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 * @see Service
 */

public class Updater extends Service {
    private final Timer timer;
    private int PERIOD = 10000;
    private DatagramSocket socket;
    private final AreaFly areaFly;
    private WifiManager mWifi;
    private final String STATUS_MSG = "STATUS";
    private DatagramPacket packet;
    private Discoverer discoverer;

    /**
     * Updater constructor. Without period parameter, it's by default set on 10000 mills.
     *
     * @param areaFly is the AreaFly where you'd like to allow the event service auto updater
     * @see Updater(AreaFly, int)
     */
    public Updater(AreaFly areaFly) {
        timer = new Timer();
        discoverer = new Discoverer();

        this.areaFly = areaFly;
    }

    /**
     * Updater constructor.
     *
     * @param areaFly is the AreaFly where you'd like to allow the event service auto updater
     * @param period is the time in millis used by the service as a delay between every update
     * @see Updater(AreaFly)
     */
    public Updater(AreaFly areaFly, int period) {
        timer = new Timer();
        discoverer = new Discoverer();

        PERIOD = period;
        this.areaFly = areaFly;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mWifi = (WifiManager) areaFly.getContext().getSystemService(Context.WIFI_SERVICE);
        discoverer.setWifiManager(mWifi);


        try {
            socket = new DatagramSocket(AreaFly.PORT);
            socket.setBroadcast(true);
            socket.setSoTimeout(Discoverer.TIMEOUT_MS);
            discoverer.setSocket(socket);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Stuff here
                try {
                    byte[] buf = new byte[1024];
                    discoverer.sendMessage(socket, STATUS_MSG);
                    String s;

                    while (true) {
                        packet = new DatagramPacket(buf, buf.length);
                        socket.receive(packet);

                        //TODO We need a parser here for the data that I receive
                        s = new String(packet.getData(), 0, packet.getLength());
//                      String expr = "\\*";
//                      String[] parsed = s.split(expr);
                        String parsed = s;
                        String mMacAddress = parsed;
                        String mEventDescription = parsed;

                        if (AreaFly.isAreaFly(s) && mMacAddress.equalsIgnoreCase(areaFly.getMacAddress())) {
                            //we need to get/set Events, if we're talking with the same common we passed
                            areaFly.setEventDescription(mEventDescription);
                        }
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

    public Discoverer getDiscoverer() {
        return discoverer;
    }

    /**
     * Return the socket used by this service.
     * @return socket
     * @see DatagramSocket
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
