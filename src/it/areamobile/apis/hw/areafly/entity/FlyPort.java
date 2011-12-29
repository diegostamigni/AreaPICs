package it.areamobile.apis.hw.areafly.entity;

import android.util.Log;
import it.areamobile.apis.hw.areafly.Warrior;

/**
 * Created by AreaMobile
 * Date: 28/12/11
 */
public abstract class FlyPort implements Comparable, Warrior, Event.OnAreaFlyEventListener {
    private String mac_address;
    private String ip_address;
    private String netbios_name;
    private final static String ATTR_SEPARETOR = "*";
    private final String TAG = this.getClass().getName();
    private Event event;

    public FlyPort() {
        super();
        event = new Event();
        event.setOnAreaFlyEventListener(this);
    };

    @Override
    public void OnEventReceived(Event event) {
        Log.i(TAG, event.toString());
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
    public int getPort() {
        return PORT;
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
    public int compareTo(Object o) {
        return this.compareTo((FlyPort) o);
    }

    @Override
    public String toString() {
        return this.netbios_name + this.ATTR_SEPARETOR + this.mac_address + this.ATTR_SEPARETOR;
    }
};
