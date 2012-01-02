package it.areamobile.apis.hw.areafly.entity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import it.areamobile.apis.hw.areafly.services.Updater;

import java.io.Serializable;

/**
 * Created by AreaMobile
 * Date: 29/12/11
 * <p/>
 * Event of an AreaFly.
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class Event implements Serializable {
    public final static String EVENT_DESCRIPTION = "EVENT_TYPE_TAG";
    private String description;
    private Handler handler;
    private Bundle data;
    private final AreaFly areaFly;
    private Updater updater;
    private Intent intent;
    private final Context mContext;

    // Default period value
    private int UPDATE_EVENT_DELAY = 10000;
    private boolean isUpdaterEnabled;

    public Event(AreaFly areaFly) {
        this.areaFly = areaFly;
        mContext = areaFly.getContext();
    }

    public Event(AreaFly areaFly, int period) {
        this.areaFly = areaFly;
        this.UPDATE_EVENT_DELAY = period;
        mContext = areaFly.getContext();
    }

    /**
     * Get the service period of updating.
     * @return update period in millis
     */
    public int getUpdatePeriod() {
        return UPDATE_EVENT_DELAY;
    }

    public void setUpdateDelay(int period) {
        this.UPDATE_EVENT_DELAY = period;
    }

    //TODO write javadoc
    protected void init(final Common.OnAreaFlyEventListener eventListener) {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                data = msg.getData();
                description = data.getString(EVENT_DESCRIPTION);
                setDescription(description);

                if (eventListener != null) {
                    eventListener.OnEventReceived(areaFly);

                    // Re join listening
                    init(eventListener);
                }
            }
        };
    }

    /**
     * Service Updater enabler
     *
     * @param enable if you'd like to be able to get auto update from service
     * @see it.areamobile.apis.hw.areafly.entity.Event#isUpdaterEnabled()
     * @see Updater
     */
    public void enableUpdater(boolean enable) {
        this.isUpdaterEnabled = enable;
        if (isUpdaterEnabled) {
            updater = new Updater(areaFly);
            intent = new Intent(mContext, Updater.class);
            mContext.startService(intent);
        } else {
            if (intent != null && updater != null)
                updater.stopSelf();
        }

    }

    //TODO write javadoc
    public Handler getHandler() {
        return handler;
    }

    /**
     * Return the <b>last</b> description event occurred.
     *
     * @return event description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description event.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Info about this Event
     *
     * @return the description of this event
     */
    @Override
    public String toString() {
        return this.description;
    }

    public boolean isUpdaterEnabled() {
        return isUpdaterEnabled;
    }

    public AreaFly getAreaFly() {
        return areaFly;
    }
}
