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
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class Event extends Common implements Serializable {
    private String description;
    private Handler handler;
    private Bundle data;
    private Common comm;

    public Event(Common comm) {
        this.comm = comm;
    }

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

    public Handler getHandler() {
        return handler;
    }

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
