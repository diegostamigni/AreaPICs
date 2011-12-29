package it.areamobile.apis.hw.areafly.entity;

import java.io.Serializable;

/**
 * Created by AreaMobile
 * Date: 29/12/11
 */

public class Event implements Serializable {
    private String type;
    private OnAreaFlyEventListener eventListener;

    public interface OnAreaFlyEventListener {
        public void setOnAreaFlyEventListener();
    };
}
