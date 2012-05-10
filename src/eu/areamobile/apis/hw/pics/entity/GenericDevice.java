package eu.areamobile.apis.hw.pics.entity;

import eu.areamobile.apis.hw.pics.HWSpecs;
import eu.areamobile.apis.hw.pics.Operation;
import eu.areamobile.apis.hw.pics.proto.HWJSonIOSpecs;
import eu.areamobile.apis.hw.pics.proto.JSonFactory;

import java.io.Serializable;

/**
 * Created by AreaMobile
 * Date: 30/12/11
 * <p/>
 * Class contains general purpose, like connection and other stuff.
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public abstract class GenericDevice implements Serializable, HWSpecs {
    public static int SOCK_PORT = 50000;
    private String ip_address;

    private HWJSonIOSpecs mCommonIOStream;
    private JSonFactory mJSonFactory;
    private String macDevice;
    private String netBiosName;
    private Operation mOperation;

    public GenericDevice() {}

    public GenericDevice(String macDevice) {
        this.macDevice = macDevice;
    }

    public GenericDevice(String macDevice, String netBiosName) {
        this.macDevice = macDevice;
        this.netBiosName = netBiosName;
    }

    public GenericDevice(String macDevice, JSonFactory jsonFact) {
        this.mJSonFactory = jsonFact;
        this.macDevice = macDevice;
    }

    public GenericDevice(String netBiosName, String macDevice, JSonFactory jsonFact, Operation mOperation) {
        this.netBiosName = netBiosName;
        this.mJSonFactory = jsonFact;
        this.macDevice = macDevice;
        this.mOperation = mOperation;
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
    public void setDescription(HWJSonIOSpecs fromJson) {
        this.mCommonIOStream = fromJson;
    }

    @Override
    public HWJSonIOSpecs getDescription() {
        return this.mCommonIOStream;
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

    @Override
    public String getDevice() {
        return this.macDevice;
    }

//    @Override
//    public void setDevice(String mac) {
//         this.macDevice = mac;
//    }

    @Override
    public void setNetBiosName(String netBiosName) {
        this.netBiosName = netBiosName;
    }

    @Override
    public String getNetBiosName() {
        return netBiosName;
    }

    @Override
    public boolean equals(Object o) {
        try {
            return this.getDescription().getStatus().getDevice().equalsIgnoreCase(((GenericDevice) o).getDescription().getStatus().getDevice());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setOperation(Operation operation) {
        this.mOperation = operation;
    }

    public Operation getOperation() {
        return this.mOperation;
    }
}
