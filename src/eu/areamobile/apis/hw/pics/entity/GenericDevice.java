package eu.areamobile.apis.hw.pics.entity;

import eu.areamobile.apis.hw.pics.HWSpecs;
import eu.areamobile.apis.hw.pics.entity.json.HWJSonIOSpecs;
import eu.areamobile.apis.hw.pics.entity.json.JSonFactory;

import java.io.Serializable;

/**
 * Created by AreaMobile
 * Date: 30/12/11
 * <p/>
 * Class contains general purpose, like connection and other stuff.
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public abstract class GenericDevice implements Serializable, HWSpecs, HWOperations {
    public static int SOCK_PORT = 50000;
    private String ip_address;

    private HWJSonIOSpecs mCommonIOStream;
    private JSonFactory mJSonFactory;
    private String macDevice;

    public GenericDevice() {
    }

    public GenericDevice(String macDevice) {
        this.macDevice = macDevice;
    }

    public GenericDevice(JSonFactory jsonFact) {
        this.mJSonFactory = jsonFact;
    }

    public void setDevice(String device) {
        this.macDevice = device;
    };

    @Override
    public String getIPAddress() {
        return ip_address;
    }

    @Override
    public void setIPAddress(String ip_address) {
        this.ip_address = ip_address;
    }

    @Override
    public void setDescription(HWJSonIOSpecs fromJson) {
        this.mCommonIOStream = fromJson;
    }

    @Override
    public HWJSonIOSpecs getDescription() {
        return this.mCommonIOStream;
    }

    /**
     * Check if is a response (Status). This method already check if stream is not null
     * @param stream json parsed string from stream
     * @return true if is a response
     */
    public static boolean isCommon(HWJSonIOSpecs stream) {
        return stream != null && stream.getStatus() != null;
    }

    public JSonFactory getJSonFactory() {
        return this.mJSonFactory;
    }

    public void setJSonFactory(JSonFactory factory) {
        this.mJSonFactory = factory;
    }

    public int getSocketPort() {
        return SOCK_PORT;
    }

    public String getDevice() {
        return this.macDevice;
    }
}
