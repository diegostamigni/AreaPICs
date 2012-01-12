package it.areamobile.apis.hw.areafly.entity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import it.areamobile.apis.hw.areafly.HWSpecs;
import it.areamobile.apis.hw.areafly.services.Updater;

/**
 * Created by AreaMobile
 * Date: 28/12/11
 * <p/>
 * AreaFly, the derivation of FlyPort, this is the class you've to use for connection on it.
 * It already instance a connection between AreaFly -> Event.
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 * @see it.areamobile.apis.hw.areafly.services.Updater
 */

public class AreaFly extends Common implements Comparable<AreaFly>, HWSpecs, Common.OnAreaFlyEventListener {
    public final static String WELCOME = "D";
    public final static String AREAFLY_NETBIOS_NAME = "PICUS";

    /**
     * Used as a separator for parsing stuffs
     */
    public static final String SEPARATOR = Common.ATTR_SEPARATOR;

    //
    private final String TAG = this.getClass().getName();
    private final Context mContext;
    private final int BEFORE = -1;
    private final int EQUAL = 0;
    private final int AFTER = 1;
    private static Updater updaterService;

    public AreaFly(Context ctx) {
        super();

        this.mContext = ctx;
        // Set in listening for events, every millis
        this.setOnAreaFlyEventListener(this, 1000);
    }

    @Override
    public int compareTo(AreaFly areaFly) {
//        if (areaFly.toString().toLowerCase().equals(this.toString().toLowerCase()))
//            return EQUAL;
//        else if (areaFly.toString().toLowerCase().length() > this.toString().toLowerCase().length())
//            return AFTER;
//        else
//            return BEFORE;
        return this.compareTo(areaFly);
    }

    @Override
    public void OnEventReceived(Common areaFly) {}

    public static boolean isAreaFly(String s) {
        return s.equalsIgnoreCase(AREAFLY_NETBIOS_NAME);
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

    /**
     * Service Updater enabler
     *
     * @see it.areamobile.apis.hw.areafly.entity.Event#isUpdaterEnabled()
     * @see it.areamobile.apis.hw.areafly.services.Updater
     */
    // This may not work on an application that use the apis, cause of
    // service in application's manifest missing. need to be done outside
    public static void enableUpdater(Context mContext, AreaFly areaFly) {
        final Intent intent;
        updaterService = new Updater(areaFly);
        intent = new Intent(mContext, Updater.class);
        mContext.startService(intent);
    }

    /**
     * Is is important to get the UpdaterService variable used by enableUpdater(Context, Areafly)
     *
     * @see AreaFly#enableUpdater(android.content.Context, AreaFly)
     * @return
     */
    public static Updater getUpdaterService() {
        return updaterService;
    }
}
