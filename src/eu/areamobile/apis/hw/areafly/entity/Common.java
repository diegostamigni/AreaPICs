package eu.areamobile.apis.hw.areafly.entity;

import android.content.Context;
import eu.areamobile.apis.hw.areafly.HWSpecs;

/**
 * Created by AreaMobile
 * Date: 30/12/11
 * <p/>
 * Class contains general purpose, like connection and other stuff.
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public abstract class Common implements HWSpecs {
    private OnAreaFlyEventListener listener;
    private Event event;
    private Context mContext;
    private String mac_address;
    private String ip_address;
    private String netbios_name;
    public final static String ATTR_SEPARATOR = "*";

    public static interface OnAreaFlyEventListener {
        /**
         * Event received listener.
         *
         * @param areaFly is the Common about the Event
         * @see Common
         * @see Common#setOnAreaFlyEventListener(Common.OnAreaFlyEventListener, int)
         */
        public void OnEventReceived(AreaFly areaFly);
    }

    /**
     * Set the obj listening for events
     *
     * @param listener instance of OnAreaFlyEventListener
     * @param period   in millis, means the delay between every areafly event update
     * @see OnAreaFlyEventListener
     */
    public void setOnAreaFlyEventListener(OnAreaFlyEventListener listener, int period) {}

    /**
     * Set the obj listening for events
     *
     * @param listener instance of OnAreaFlyEventListener
     * @see OnAreaFlyEventListener
     */
    public void setOnAreaFlyEventListener(OnAreaFlyEventListener listener) {}

    //TODO code this and improve javadoc
    /**
     * When an event is received ...
     */
    public void notifyEventReceived() {}

    public Context getContext() {
        return mContext;
    }

    @Override
    public String getMacAddress() {
        return mac_address;
    }

    @Override
    public void setMacAddress(String mac_address) {
        this.mac_address = mac_address;
    }

    @Override
    public String getIPAddress() {
        return ip_address;
    }

    @Override
    public void setIPAddress(String ip_address) {
        this.ip_address = ip_address;
    }

    @Override
    public String getNetBiosName() {
        return netbios_name;
    }

    @Override
    public void setNetBiosName(String netbios_name) {
        this.netbios_name = netbios_name;
    }

    @Override
    public String toString() {
        return this.netbios_name + ATTR_SEPARATOR + this.mac_address + ATTR_SEPARATOR;
    }
}
