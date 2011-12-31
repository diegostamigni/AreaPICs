package it.areamobile.apis.hw.areafly;

import it.areamobile.apis.hw.areafly.entity.Event;

//TODO improve javadoc

/**
 * Created by AreaMobile
 * Date: 30/12/11
 * <p/>
 * Class contains general purpose, like connection and other stuff.
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public abstract class Common {
    private OnAreaFlyEventListener listener;
    private Event event;

    public static interface OnAreaFlyEventListener {
        /**
         * Event received listener.
         *
         * @param comm is the Common about the Event
         * @see Common
         * @see Common#setOnAreaFlyEventListener(it.areamobile.apis.hw.areafly.Common.OnAreaFlyEventListener, int)
         */
        public void OnEventReceived(Common comm);
    }

    /**
     * Set the obj listening for events
     *
     * @param listener instance of OnAreaFlyEventListener
     * @param period in millis, means the delay between every areafly event update
     * @see OnAreaFlyEventListener
     */
    public void setOnAreaFlyEventListener(OnAreaFlyEventListener listener, int period) {
        this.listener = listener;
        event = new Event(this, period);
        event.init(this.listener);
    }

    //TODO improve javadoc
    /**
     * @return the event
     * @see Event
     */
    public Event getEvent() {
        return event;
    }

    //TODO code this and improve javadoc
    /**
     * When an event is received ...
     */
    public void notifyEventReceived() {
    }
}
