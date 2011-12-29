package it.areamobile.apis.hw.areafly;

/**
 * Created by AreaMobile
 * Date: 29/12/11
 */

public interface Warrior {
    final static int PORT = 30303;

    abstract String getMacAddress();

    abstract void setMacAddress(String mac_address);

    abstract String getIPAddress();

    abstract void setIPAddress(String ip_address);

    abstract int getPort();

    // The port should be final static, not changeable by world
//    public abstract void setPort(int port);

    abstract String getNetBiosName();

    abstract void setNetBiosName(String netbios_name);
};
