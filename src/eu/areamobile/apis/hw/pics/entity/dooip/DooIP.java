package eu.areamobile.apis.hw.pics.entity.dooip;

import eu.areamobile.apis.hw.pics.entity.Operation;
import eu.areamobile.apis.hw.pics.entity.GenericDevice;
import eu.areamobile.apis.hw.pics.proto.JSonFactory;

/**
 * Created by AreaMobile
 * Date: 28/12/11
 * <p/>
 * DooIP, the derivation of FlyPort, this is the class you've to use for staffs on it.
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class DooIP extends GenericDevice {
    private String macAddress, mNetBiosName;
    private JSonFactory jsonFact;
    private Operation mOperation;

    public DooIP(String mNetBiosName, String wifiMacAddress, JSonFactory jsonFact, Operation operation) {
        this.mNetBiosName = mNetBiosName;
        this.macAddress = wifiMacAddress;
        this.jsonFact = jsonFact;
        this.mOperation = operation;
    }

    public DooIP(String macDevice) {
        this.macAddress = macDevice;
    }

    @Override
    public JSonFactory getJSonFactory() {
        return this.jsonFact;
    }

    public void setJSonFactory(JSonFactory factory) {
        this.jsonFact = factory;
    }

    @Override
    public void setOperation(Operation operation) {
        this.mOperation = operation;
        try {
            this.mOperation.setMacReceiver(this.getDescription().getStatus().getDevice());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Operation getOperation() {
        return this.mOperation;
    }
}