package eu.areamobile.apis.hw.pics;

import eu.areamobile.apis.hw.pics.proto.HWJSonIOSpecs;

/**
 * Created by AreaMobile Date: 29/12/11 This interface is a group of HardWare specifications. It's developed around the generically stuffs about it, and what we usually need.
 * @author  Diego Stamigni (diegostamigni@areamobile.eu)
 */


public interface HWSpecs {
    abstract String getDevice();
//    abstract void setDevice(String macAddress);

    abstract String getNetBiosName();
    abstract void setNetBiosName(String netBiosName);

    abstract String getIPAddress();

    /**
	 * @param ip_address
	 */
    abstract void setIPAddress(String ip_address);

    /**
	 * @param fromJson
	 */
    abstract void setDescription(HWJSonIOSpecs fromJson);

    abstract HWJSonIOSpecs getDescription();
}
