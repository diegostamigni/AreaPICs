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

public abstract class Common implements HWSpecs, Operations {
    public static int SOCK_PORT = 50000;
//    public static int RECEIVER_PORT = 50001;
    private Context mContext;
    private String ip_address;
    private final static String ATTR_SEPARATOR = ":";
    private OnCommonEventListener listener;
    private HWJSonIOSpecs mCommonIOStream;
    private JSonFactory mJSonFactory;
    private String macDevice;

    public Common(JSonFactory jsonFact) {
        this.mJSonFactory = jsonFact;
    }

    public Common(Context mContext) {
        this.mContext = mContext;
    }


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

    public void setDevice(String device) {
        this.macDevice = device;
    };

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
