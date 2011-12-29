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
    private String type;
    private OnAreaFlyEventListener eventListener;
    private Handler handler;
    private Bundle data;

    public Event() {}

    private void init() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                data = msg.getData();
                type = data.getString(Discoverer.EVENT_TYPE);

                if (eventListener != null)
                    eventListener.OnEventReceived(Event.this);
            }
        };
    }

    public Handler getHandler() {
        return handler;
    }

    public static interface OnAreaFlyEventListener {
        public void OnEventReceived(Event event);
    }

    @Override
    public String toString() {
        return this.type;
    }

    public void setOnAreaFlyEventListener(OnAreaFlyEventListener listener) {
        this.eventListener = listener;
        init();
    }
};
