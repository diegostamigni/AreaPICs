package eu.areamobile.apis.hw.pics;

import eu.areamobile.apis.hw.pics.entity.json.HWJSonIOSpecs;

/**
 * Created by AreaMobile Date: 29/12/11 This interface is a group of HardWare specifications. It's developed around the generically stuffs about it, and what we usually need.
 * @author  Diego Stamigni (diegostamigni@areamobile.eu)
 */


public interface HWSpecs {

    /**
	 * @uml.property  name="macAddress"
	 */
    abstract String getMacAddress();

    /**
	 * @param mac_address
	 * @uml.property  name="macAddress"
	 */
    abstract void setMacAddress(String mac_address);

    /**
	 * @uml.property  name="iPAddress"
	 */
    abstract String getIPAddress();

    /**
	 * @param ip_address
	 * @uml.property  name="iPAddress"
	 */
    abstract void setIPAddress(String ip_address);
    
//    abstract String getNetBiosName();

//    abstract void setNetBiosName(String netbios_name);

    /**
	 * @param fromJson
	 * @uml.property  name="description"
	 */
    abstract void setDescription(HWJSonIOSpecs fromJson);

    /**
	 * @uml.property  name="description"
	 * @uml.associationEnd  
	 */
    abstract HWJSonIOSpecs getDescription();
}