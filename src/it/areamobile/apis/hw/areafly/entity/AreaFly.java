package it.areamobile.apis.hw.areafly.entity;

import it.areamobile.apis.hw.areafly.Common;
import it.areamobile.apis.hw.areafly.HWSpecs;

//TODO improve javadoc
/**
 * Created by AreaMobile
 * Date: 28/12/11
 *
 * Your AreaFly obj reference.
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class AreaFly extends Common implements Comparable<AreaFly>, HWSpecs, Event.OnAreaFlyEventListener {
    private String mac_address;
    private String ip_address;
    private String netbios_name;
    private final static String FLYPORT_ID = "PICUS";
    private final static String ATTR_SEPARETOR = "*";
    private final String TAG = this.getClass().getName();

    public AreaFly() {
        super();

        // Set in listening for events, every millis
        this.setOnAreaFlyEventListener(this, 1000);
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
        return this.netbios_name + ATTR_SEPARETOR + this.mac_address + ATTR_SEPARETOR;
    }

    @Override
    public int compareTo(AreaFly areaFly) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;
        if (areaFly.toString().toLowerCase().equals(this.toString().toLowerCase()))
            return EQUAL;
        else if (areaFly.toString().toLowerCase().length() > this.toString().toLowerCase().length())
            return AFTER;
        else
            return BEFORE;
    }

    @Override
    public void OnEventReceived(Common areaFly) {}

    public static boolean isAreaFly(String s) {
        return s.equalsIgnoreCase(FLYPORT_ID);
    }
}
