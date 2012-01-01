package it.areamobile.apis.hw.areafly.entity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import it.areamobile.apis.hw.areafly.Common;
import it.areamobile.apis.hw.areafly.ijones.Discoverer;

import java.io.Serializable;

/**
 * Created by AreaMobile
 * Date: 29/12/11
 *
 * Event of an AreaFly.
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class Event extends Common implements Serializable {
    private String description;
    private Handler handler;
    private Bundle data;
    private Common comm;

    // Default period value
    private int UPDATE_EVENT_DELAY = 10000;

    public Event(Common comm) {
        this.comm = comm;
    }

    public Event(Common comm, int period) {
        this.comm = comm;
        this.UPDATE_EVENT_DELAY = period;
    }

    public int getUpdateDelay() {
        return UPDATE_EVENT_DELAY;
    }

    public void setUpdateDelay(int period) {
        this.UPDATE_EVENT_DELAY = period;
    }

    //TODO write javadoc
    public void init(final OnAreaFlyEventListener eventListener) {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                data = msg.getData();
                description = data.getString(Discoverer.EVENT_DESCRIPTION);
                setDescription(description);

                if (eventListener != null) {
                    eventListener.OnEventReceived(comm);

                    // Re join listening
                    init(eventListener);
                }
            }
        };
    }

    //TODO write javadoc
    public Handler getHandler() {
        return handler;
    }

    //TODO write javadoc
    public Common getCommon() {
        return comm;
    }

    /**
     * Return the <b>last</b> description event occurred.
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
     * @return the description of this event
     */
    @Override
    public String toString() {
        return this.description;
    }
}
