package it.areamobile.apis.hw.areafly.entity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import it.areamobile.apis.hw.areafly.HWSpecs;

/**
 * Created by AreaMobile
 * Date: 28/12/11
 *
 * AreaFly, the derivation of FlyPort, this is the class you've to use for connection on it.
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class AreaFly extends Common implements Comparable<AreaFly>, HWSpecs, Common.OnAreaFlyEventListener {
    private final String TAG = this.getClass().getName();
    private final Context mContext;

    public AreaFly(Context ctx) {
        super();

        this.mContext = ctx;
        // Set in listening for events, every millis
        this.setOnAreaFlyEventListener(this, 1000);
    }

    @Override
    public int compareTo(AreaFly areaFly) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;
        if (areaFly.toString().toLowerCase().equals(this.toString().toLowerCase()))
            return EQUAL;
        else if (areaFly.toString().toLowerCase().length() > this.toString().toLowerCase().length())
            return AFTER;
        else
            return BEFORE;
    }

    @Override
    public void OnEventReceived(Common areaFly) {}

    public static boolean isAreaFly(String s) {
        return s.equalsIgnoreCase(FLYPORT_ID);
    }

    @Override
    public Context getContext() {
        super.getContext();
        return mContext;
    }

    public void setEventDescription(String eventDescription) {
        Handler handler = getEvent().getHandler();
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(Event.EVENT_DESCRIPTION, eventDescription);
        msg.setData(bundle);
        handler.sendMessage(msg);
    }
}
