package eu.areamobile.apis.hw.pics.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import eu.areamobile.apis.hw.pics.entity.GenericDevice;
import eu.areamobile.apis.hw.pics.entity.json.HWJSonIOSpecs;
import eu.areamobile.apis.hw.pics.ijones.Discoverer;

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
 * This service is developed to work as an updater for  a single Common\'s events.
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 * @see Service
 */
@Deprecated
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
    private final GenericDevice mGenericDevice;
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
     * @param mGenericDevice is the Common where you'd like to allow the event service auto updater
     * @see Updater( eu.areamobile.apis.hw.pics.entity.GenericDevice , int)
     */
    public Updater(GenericDevice mGenericDevice) {
        timer = new Timer();
        discoverer = new Discoverer();

        this.mGenericDevice = mGenericDevice;
    }

    /**
     * Updater constructor.
     *
     * @param mGenericDevice is the Common where you'd like to allow the event service auto updater
     * @param period is the time in millis used by the service as a delay between every update
     * @see Updater( eu.areamobile.apis.hw.pics.entity.GenericDevice )
     */
    public Updater(GenericDevice mGenericDevice, int period) {
        timer = new Timer();
        discoverer = new Discoverer();

        PERIOD = period;
        this.mGenericDevice = mGenericDevice;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mWifi = (WifiManager) mGenericDevice.getContext().getSystemService(Context.WIFI_SERVICE);
        discoverer.setWifiManager(mWifi);
        discoverer.setContext(mGenericDevice.getContext());


        try {
            socket = new DatagramSocket(mGenericDevice.getSocketPort());
            socket.setBroadcast(true);
            socket.setSoTimeout(Discoverer.TIMEOUT_MS);
            discoverer.setSocketDiscoverer(socket);
        } catch (SocketException e) { e.printStackTrace(); }

        final HWJSonIOSpecs sayHiAll = mGenericDevice.getDescription();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Stuff here
                try {
                    byte[] buf = new byte[1024];

                    discoverer.sendMessage(sayHiAll, new Discoverer.OnResponseListener() {
                        @Override
                        public void onMessageReceived(HWJSonIOSpecs.Status status) {
                            //TODO review
                            mGenericDevice.setDescription(null);
                        }
                    });
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
