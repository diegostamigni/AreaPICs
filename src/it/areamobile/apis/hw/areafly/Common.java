package it.areamobile.apis.hw.areafly;

import it.areamobile.apis.hw.areafly.entity.Event;

/**
 * Created by AreaMobile
 * Date: 30/12/11
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public abstract class Common {
    private OnAreaFlyEventListener listener;
    private Event event;

    public static interface OnAreaFlyEventListener {
        public void OnEventReceived(Common comm);
    }

    /**
     * Set the obj listening for events
     * @param listener instance of OnAreaFlyEventListener
     */
    public void setOnAreaFlyEventListener(OnAreaFlyEventListener listener) {
        this.listener = listener;
        event = new Event(this);
        event.init(this.listener);
    }

    public Event getEvent() {
        return event;
    }
}
