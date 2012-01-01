package it.areamobile.apis.hw.areafly.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by AreaMobile
 * Date: 31/12/11
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class Updater extends Service {
    private Timer timer;
    private int PERIOD = -1;

    public Updater(int period) {
        timer = new Timer();
    }

    //TODO code this one
    @Override
    public void onCreate() {
        super.onCreate();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Stuff here
            }
        }, 0, PERIOD);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
