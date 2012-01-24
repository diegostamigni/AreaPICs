package eu.areamobile.apis.hw.pics.entity.areafly;

import android.content.Context;
import android.content.Intent;
import eu.areamobile.apis.hw.pics.HWSpecs;
import eu.areamobile.apis.hw.pics.entity.Common;
import eu.areamobile.apis.hw.pics.entity.areafly.json.AreaFlyJSonFactory;
import eu.areamobile.apis.hw.pics.services.Updater;

/**
 * Created by AreaMobile
 * Date: 28/12/11
 * <p/>
 * AreaFly, the derivation of FlyPort, this is the class you've to use for connection on it.
 * It already instance a connection between {@link AreaFly}
 * -> {@link eu.areamobile.apis.hw.pics.entity.areafly.event.Event}.
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 * @see eu.areamobile.apis.hw.pics.services.Updater
 */

public class AreaFly extends Common implements Comparable<AreaFly>, HWSpecs {
    private int PORT = 30303;
    public final static int DEFAULT_PORT = 30303;
    private final String TAG = this.getClass().getName();
    private final Context mContext;
//    private final int BEFORE = -1;
//    private final int EQUAL = 0;
//    private final int AFTER = 1;
    private Updater updaterService;
    private OnCommonEventListener listener = null;
    private AreaFlyJSonFactory.AreaFlyIOStream areaFlyIOStream;
    private AreaFlyJSonFactory jsonFact;

    public AreaFly(AreaFlyJSonFactory jsonFact, Context mContext) {
        super(jsonFact);
        this.mContext = mContext;
        this.jsonFact = jsonFact;
    }

    public AreaFly(Context ctx) {
        this.mContext = ctx;
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

    @Override
    public Context getContext() {
        super.getContext();
        return mContext;
    }

//    @Override
    public void setDescription(AreaFlyJSonFactory.AreaFlyIOStream fromJson) {
        super.setDescription(fromJson);

        this.areaFlyIOStream = fromJson;

        //TODO check this form of listener, works when you set the EventDescription
        if (listener == null) listener = getListener();
        if (listener != null) this.listener.OnEventReceived(AreaFly.this);
    }

    @Override
    public AreaFlyJSonFactory.AreaFlyIOStream getDescription() {
        super.getDescription();
        return this.areaFlyIOStream;
    }

    /**
     * Service Updater enabler.<br></br>
     * Be careful to have added these line to your manifest:<br></br><br></br>
     * <pre>
     * &lt;service android:name="eu.areamobile.apis.hw.pics.services.Updater" /&gt;
     * </pre>
     *
     * @see eu.areamobile.apis.hw.pics.services.Updater
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

    @Override
    public void setOnAreaFlyEventListener(OnCommonEventListener listener) {
        super.setOnAreaFlyEventListener(listener);
    }

    @Override
    public AreaFlyJSonFactory getJSonFactory() {
        return this.jsonFact;
    }

    public void setJSonFactory(AreaFlyJSonFactory factory) {
        this.jsonFact = factory;
    }
}
