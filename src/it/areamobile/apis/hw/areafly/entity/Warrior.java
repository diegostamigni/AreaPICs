package it.areamobile.apis.hw.areafly.entity;

import java.io.Serializable;

/**
 * Created by AreaMobile
 * Date: 29/12/11
 */

public abstract class Warrior implements Serializable {
    public Warrior() {};

    public abstract String getMacAddress();

    public abstract void setMacAddress(String mac_address);

    public abstract String getIp_address();

    public abstract void setIPAddress(String ip_address);

    public abstract int getPort();

    public abstract void setPort(int port);

    public abstract String getNetBiosName();

    public abstract void setNetBiosName(String netbios_name);
};
