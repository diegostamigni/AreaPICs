package eu.areamobile.apis.hw.areafly.entity;

import android.content.Context;
import android.content.Intent;
import eu.areamobile.apis.hw.areafly.HWSpecs;
import eu.areamobile.apis.hw.areafly.services.Updater;

/**
 * Created by AreaMobile
 * Date: 28/12/11
 * <p/>
 * AreaFly, the derivation of FlyPort, this is the class you've to use for connection on it.
 * It already instance a connection between {@link eu.areamobile.apis.hw.areafly.entity.AreaFly}
 * -> {@link eu.areamobile.apis.hw.areafly.entity.Event}.
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 * @see eu.areamobile.apis.hw.areafly.services.Updater
 */

public class AreaFly extends Common implements Comparable<AreaFly>, HWSpecs {
    public final static String WELCOME = "D";
    public final static String AREAFLY_NETBIOS_NAME = "PICUS";

    /**
     * Used as a separator for parsing stuffs
     */
    public static final String SEPARATOR = Common.ATTR_SEPARATOR;

    //
    private final String TAG = this.getClass().getName();
    private final Context mContext;
    //    private final int BEFORE = -1;
//    private final int EQUAL = 0;
//    private final int AFTER = 1;
    private Updater updaterService;
    private Event event;
    private OnAreaFlyEventListener listener = null;

    public AreaFly(Context ctx) {
        super();

        this.mContext = ctx;

        event = new Event(this);
        this.setEvent(event);
    }

//    public AreaFly(Context ctx, int period) {
//        super();
//
//        this.mContext = ctx;
//        // Set in listening for events, every millis
//
//        event = new Event(this, period);
//        this.setEvent(event);
//
//        try {
//            listener = (OnAreaFlyEventListener) ctx;
//        } catch (ClassCastException e) {}
//    }

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

    public static boolean isAreaFly(String s) {
        return s.equalsIgnoreCase(AREAFLY_NETBIOS_NAME);
    }

    @Override
    public Context getContext() {
        super.getContext();
        return mContext;
    }

    public void setEventDescription(String eventDescription) {
        event.setDescription(eventDescription);

        //TODO check this form of listener, works when you set the EventDescription
        if (listener == null) listener = getOnAreaFlyEventListener();
        if (listener != null) this.listener.OnEventReceived(AreaFly.this);
    }

    /**
     * Service Updater enabler.<br></br>
     * Be careful to have added these line to your manifest:<br></br><br></br>
     * <pre>
     * &lt;service android:name="eu.areamobile.apis.hw.areafly.services.Updater" /&gt;
     * </pre>
     *
     *
     * @see eu.areamobile.apis.hw.areafly.entity.Event#isUpdaterEnabled()
     * @see eu.areamobile.apis.hw.areafly.services.Updater
     */
    public void enableUpdater(Context mContext) {
        final Intent intent;
        updaterService = new Updater(AreaFly.this);
        intent = new Intent(mContext, Updater.class);
        mContext.startService(intent);
    }

    /**
     * Is is important to get the UpdaterService variable used by enableUpdater(Context, Areafly)
     *
     * @return
     * @see AreaFly#enableUpdater(android.content.Context)
     */
    public Updater getUpdaterService() {
        return updaterService;
    }

    /**
     * @return the event
     * @see Event
     */
    Event getEvent() {
        return event;
    }

    void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public void setOnAreaFlyEventListener(OnAreaFlyEventListener listener) {
        super.setOnAreaFlyEventListener(listener);
    }

    public String getEventDescription() {
        return event.getDescription();
    }
}
