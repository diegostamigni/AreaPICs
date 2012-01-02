package it.areamobile.apis.hw.areafly.entity;

import android.content.Context;
import it.areamobile.apis.hw.areafly.HWSpecs;

//TODO improve javadoc
/**
 * Created by AreaMobile
 * Date: 28/12/11
 *
 * Your AreaFly obj reference.
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
}
