package it.areamobile.apis.hw.areafly;

/**
 * Created by AreaMobile
 * Date: 29/12/11
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

//TODO improve javadoc to all class
public interface HWSpecs {
    final static int PORT = 30303;

    abstract String getMacAddress();

    abstract void setMacAddress(String mac_address);

    abstract String getIPAddress();

    abstract void setIPAddress(String ip_address);
    
    abstract String getNetBiosName();

    abstract void setNetBiosName(String netbios_name);
}