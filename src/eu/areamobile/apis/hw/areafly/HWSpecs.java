package eu.areamobile.apis.hw.areafly;

/**
 * Created by AreaMobile
 * Date: 29/12/11
 *
 * This interface is a group of HardWare specifications. It's developed
 * around the generically stuffs about it, and what we usually need.
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */


public interface HWSpecs {
    final static int PORT = 30303;

    abstract String getMacAddress();

    abstract void setMacAddress(String mac_address);

    abstract String getIPAddress();

    abstract void setIPAddress(String ip_address);
    
    abstract String getNetBiosName();

    abstract void setNetBiosName(String netbios_name);
}
