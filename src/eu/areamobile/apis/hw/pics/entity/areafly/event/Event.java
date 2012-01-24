package eu.areamobile.apis.hw.pics.entity.areafly.event;

import android.os.Handler;
import eu.areamobile.apis.hw.pics.entity.areafly.AreaFly;
import eu.areamobile.apis.hw.pics.services.Updater;

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

@Deprecated
class Event implements Serializable {
    public final static String EVENT_DESCRIPTION = "EVENT_TYPE_TAG";
    private String description;
    private Handler handler;
    //    private Bundle data;
    private final AreaFly areaFly;

    // Default period value
    private int UPDATE_EVENT_DELAY = 10000;
    private boolean isUpdaterEnabled;
    protected final static int __ID_OK = 0;
    protected final static String NOTIFY_EVENT = "NOTIFY_EVENT";

    Event(AreaFly areaFly) {
        this.areaFly = areaFly;
    }

    Event(AreaFly areaFly, int period) {
        this.areaFly = areaFly;
        this.UPDATE_EVENT_DELAY = period;
    }

    /**
     * Get the service period of updating.
     *
     * @return update period in millis
     */
    int getUpdatePeriod() {
        return UPDATE_EVENT_DELAY;
    }

    void setUpdateDelay(int period) {
        this.UPDATE_EVENT_DELAY = period;
    }

    /**
     * Return the handler used for handling connectino between AreaFly -> Event
     *
     * @return the handler used by Event
     */
    Handler getHandler() {
        return handler;
    }

    /**
     * Return the <b>last</b> description event occurred.
     *
     * @return event description
     */
    String getDescription() {
        return description;
    }

    /**
     * Set the description event.
     */
    void setDescription(String description) {
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

    boolean isUpdaterEnabled() {
        return isUpdaterEnabled;
    }

    AreaFly getAreaFly() {
        return areaFly;
    }
}
