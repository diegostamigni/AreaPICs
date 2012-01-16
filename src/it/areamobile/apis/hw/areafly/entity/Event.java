package it.areamobile.apis.hw.areafly.entity;

import it.areamobile.apis.hw.areafly.services.Updater;

import java.io.Serializable;

/**
 * Created by AreaMobile
 * Date: 29/12/11
 * <p/>
 * Event of an AreaFly; include service usage.
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 * @see Updater
 */

class Event implements Serializable {
    public final static String EVENT_DESCRIPTION = "EVENT_TYPE_TAG";
    private String description;
//    private Handler handler;
//    private Bundle data;
    private final AreaFly areaFly;

    // Default period value
    private int UPDATE_EVENT_DELAY = 10000;
    private boolean isUpdaterEnabled;

    public Event(AreaFly areaFly) {
        this.areaFly = areaFly;
    }

    public Event(AreaFly areaFly, int period) {
        this.areaFly = areaFly;
        this.UPDATE_EVENT_DELAY = period;
    }

    /**
     * Get the service period of updating.
     *
     * @return update period in millis
     */
    public int getUpdatePeriod() {
        return UPDATE_EVENT_DELAY;
    }

    public void setUpdateDelay(int period) {
        this.UPDATE_EVENT_DELAY = period;
    }

    /**
     * Init and set Event. It has to be protected
     * @param eventListener is the listener to use for the connection
     */
    protected void init(final Common.OnAreaFlyEventListener eventListener) {
//        handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//
//                data = msg.getData();
//                String description = data.getString(EVENT_DESCRIPTION);
//                setDescription(description);

                if (eventListener != null) {
                    eventListener.OnEventReceived(areaFly);

                    // Re join listening
//                    init(eventListener);
                }
//            }
//        };
    }

    /**
     * Return the handler used for handling connectino between AreaFly -> Event
     * @return the handler used by Event
     */
//    public Handler getHandler() {
//        return handler;
//    }

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
