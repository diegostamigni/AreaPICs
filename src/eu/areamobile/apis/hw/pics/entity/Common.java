package eu.areamobile.apis.hw.pics.entity;

import android.content.Context;
import eu.areamobile.apis.hw.pics.HWSpecs;
import eu.areamobile.apis.hw.pics.entity.json.HWJSonIOSpecs;
import eu.areamobile.apis.hw.pics.entity.json.JSonFactory;

/**
 * Created by AreaMobile
 * Date: 30/12/11
 * <p/>
 * Class contains general purpose, like connection and other stuff.
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class Common implements HWSpecs {
    /**
	 * @uml.property  name="pORT"
	 */
    public int PORT = 30303;
    /**
	 * @uml.property  name="mContext"
	 * @uml.associationEnd  readOnly="true"
	 */
    private Context mContext;
    /**
	 * @uml.property  name="mac_address"
	 */
    private String mac_address;
    /**
	 * @uml.property  name="ip_address"
	 */
    private String ip_address;
    private final static String ATTR_SEPARATOR = ":";
    /**
	 * @uml.property  name="listener"
	 * @uml.associationEnd  
	 */
    private OnCommonEventListener listener;
    /**
	 * @uml.property  name="mCommonIOStream"
	 * @uml.associationEnd  
	 */
    private HWJSonIOSpecs mCommonIOStream;
    /**
	 * @uml.property  name="mJSonFactory"
	 * @uml.associationEnd  
	 */
    private JSonFactory mJSonFactory;

    public Common(JSonFactory jsonFact) {
        this.mJSonFactory = jsonFact;
    }

    public Common(Context mContext) {}

    public static interface OnCommonEventListener {
        /**
         * Event received listener.
         *
         * @param comm is the Common about the Event
         * @see Common
         * @see Common#setOnAreaFlyEventListener(eu.areamobile.apis.hw.pics.entity.Common.OnCommonEventListener)
         */
        public void OnEventReceived(Common comm);
    }

    /**
     * Set the obj listening for events
     *
     * @param listener instance of OnAreaFlyEventListener
     * @param period   in millis, means the delay between every pics event update
     * @see eu.areamobile.apis.hw.pics.entity.Common.OnCommonEventListener
     */
//    public void setOnAreaFlyEventListener(OnAreaFlyEventListener listener, int period) {}

    /**
     * Set the obj listening for events, need the service
     *
     * @param listener instance of OnAreaFlyEventListener
     * @see eu.areamobile.apis.hw.pics.entity.Common.OnCommonEventListener
     */
    public void setOnAreaFlyEventListener(OnCommonEventListener listener) {
        this.listener = listener;
    }

    //TODO code this and improve javadoc

    /**
     * When an event is received ...
     */
    void notifyEventReceived() {
    }

    public Context getContext() {
        return mContext;
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
    public String toString() {
        return this.mac_address + ATTR_SEPARATOR + this.ip_address;
    }

    /**
	 * @return
	 * @uml.property  name="listener"
	 */
    protected OnCommonEventListener getListener() {
        return listener;
    }

    @Override
    public void setDescription(HWJSonIOSpecs fromJson) {
        this.mCommonIOStream = fromJson;
    }

    @Override
    public HWJSonIOSpecs getDescription() {
        return this.mCommonIOStream;
    }

    //TODO modify in a real control..
    public static boolean isCommon(HWJSonIOSpecs s) {
        if (s.getStatus() != null)
            return true;
        return false;
    }

    public int getSocketPort() {
        return PORT;
    }

    public void setSocketPort(int port) {
        this.PORT = PORT;
    }

    public JSonFactory getJSonFactory() {
        return this.mJSonFactory;
    }

    public void setJSonFactory(JSonFactory factory) {
        this.mJSonFactory = factory;
    }
}
