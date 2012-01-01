package it.areamobile.apis.hw.areafly.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import it.areamobile.apis.hw.areafly.entity.AreaFly;
import it.areamobile.apis.hw.areafly.entity.Event;
import it.areamobile.apis.hw.areafly.ijones.Discoverer;
import it.areamobile.apis.hw.areafly.utils.NetUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by AreaMobile
 * Date: 31/12/11
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class Updater extends Service {
    private final Timer timer;
    private int PERIOD = -1;
    private DatagramSocket socket;
    private final AreaFly areaFly;
    private final WifiManager mWifi;
    private final String STATUS_MSG = "STATUS";
    private DatagramPacket packet;

    public Updater(AreaFly areaFly) {
        timer = new Timer();
        this.areaFly = areaFly;
        mWifi = (WifiManager) areaFly.getContext().getSystemService(Context.WIFI_SERVICE);
    }

    public Updater(AreaFly areaFly, int period) {
        PERIOD = period;
        timer = new Timer();
        this.areaFly = areaFly;
        mWifi = (WifiManager) areaFly.getContext().getSystemService(Context.WIFI_SERVICE);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            socket = new DatagramSocket(AreaFly.PORT);
            socket.setBroadcast(true);
            socket.setSoTimeout(Discoverer.TIMEOUT_MS);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Stuff here
                try {
                    byte[] buf = new byte[1024];
                    sendMessage(socket, STATUS_MSG);
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
                            setEventDescription(areaFly, mEventDescription);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, PERIOD);
    }

    private void sendMessage(DatagramSocket socket, String msg) throws IOException {
        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), NetUtils.getBroadcastAddress(mWifi), AreaFly.PORT);
        socket.send(packet);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void setEventDescription(AreaFly common, String eventDescription) {
        Handler handler = common.getEvent().getHandler();
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(Event.EVENT_DESCRIPTION, eventDescription);
        msg.setData(bundle);
        handler.sendMessage(msg);
    }

}
