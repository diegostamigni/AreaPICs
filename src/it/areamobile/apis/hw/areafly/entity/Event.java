package it.areamobile.apis.hw.areafly.entity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import it.areamobile.apis.hw.areafly.ijones.Discoverer;

import java.io.Serializable;

/**
 * Created by AreaMobile
 * Date: 29/12/11
 */

public class Event implements Serializable {
    private String description;
    private OnAreaFlyEventListener eventListener;
    private Handler handler;
    private Bundle data;

    public Event() {
    }

    private void init() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                data = msg.getData();
                description = data.getString(Discoverer.EVENT_DESCRIPTION);
                setDescription(description);

                if (eventListener != null)
                    eventListener.OnEventReceived(Event.this);
            }
        };
    }

    public Handler getHandler() {
        return handler;
    }

    /**
     * Return the <b>last</b> description event occurred.
     * @return event description
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static interface OnAreaFlyEventListener {
        public void OnEventReceived(Event event);
    }

    @Override
    public String toString() {
        return this.description;
    }

    public void setOnAreaFlyEventListener(OnAreaFlyEventListener listener) {
        this.eventListener = listener;
        init();
    }
}
