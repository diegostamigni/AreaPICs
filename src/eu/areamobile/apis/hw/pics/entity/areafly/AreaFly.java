package eu.areamobile.apis.hw.pics.entity.areafly;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import eu.areamobile.apis.hw.pics.HWSpecs;
import eu.areamobile.apis.hw.pics.entity.Common;
import eu.areamobile.apis.hw.pics.entity.areafly.json.AreaFlyJSonFactory;
import eu.areamobile.apis.hw.pics.services.Updater;

/**
 * Created by AreaMobile
 * Date: 28/12/11
 * <p/>
 * AreaFly, the derivation of FlyPort, this is the class you've to use for connection on it.
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 * @see eu.areamobile.apis.hw.pics.services.Updater
 */

public class AreaFly extends Common implements Comparable<AreaFly>, HWSpecs, AreaFlyHWOperations {
    private final String TAG = this.getClass().getName();
    private final Context mContext;

    private Updater updaterService;
    private OnCommonEventListener listener = null;
    private AreaFlyJSonFactory.AreaFlyIOStream areaFlyIOStream;
    private AreaFlyJSonFactory jsonFact;

    public AreaFly(Context mContext, AreaFlyJSonFactory jsonFact) {
        super(jsonFact);
        this.mContext = mContext;
        this.jsonFact = jsonFact;
    }

    public AreaFly(Context ctx) {
    	super(ctx);
        this.mContext = ctx;
    }

//    public AreaFly(Context ctx, int period) {
//        super();
//
//        this.mContext = ctx;
//        // Set in listening for events, every millis
//
//        event = new Event(this, period);
//        this.setEvent(event);
//
//        try {
//            listener = (OnAreaFlyEventListener) ctx;
//        } catch (ClassCastException e) {}
//    }

    @Override
    public int compareTo(AreaFly areaFly) {
        return this.compareTo(areaFly);
    }

    @Override
    public Context getContext() {
        super.getContext();
        return mContext;
    }

    /**
     * Service Updater enabler.<br></br>
     * Be careful to have added these line to your manifest:<br></br><br></br>
     * <pre>
     * &lt;service android:name="eu.areamobile.apis.hw.pics.services.Updater" /&gt;
     * </pre>
     *
     * @see eu.areamobile.apis.hw.pics.services.Updater
     */
    public void enableUpdater(Context mContext) {
        final Intent intent;
        updaterService = new Updater(AreaFly.this);
        intent = new Intent(mContext, Updater.class);
        mContext.startService(intent);
    }

    /**
	 * Is is important to get the UpdaterService variable used by enableUpdater(Context, Areafly)
	 * @see  AreaFly#enableUpdater(android.content.Context)
	 * @return
	 */
    public Updater getUpdaterService() {
        return updaterService;
    }

    @Override
    public void setOnAreaFlyEventListener(OnCommonEventListener listener) {
        super.setOnAreaFlyEventListener(listener);
    }

    @Override
    public AreaFlyJSonFactory getJSonFactory() {
        return this.jsonFact;
    }

    public void setJSonFactory(AreaFlyJSonFactory factory) {
        this.jsonFact = factory;
    }

    /* ###################################################
     *                  OPERATIONS
     * ################################################### */
    @Override
    public AreaFlyJSonFactory io_put(int first_type, int pin, int second_type, Object value) {
        AreaFlyJSonFactory ioput = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[2];

        argv[0] = new AreaFlyJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(pin);

        argv[1] = new AreaFlyJSonFactory.Argv();
        argv[1].setType(second_type); argv[1].setValue(value);

        exec.setAck(true);
        exec.setSender(((WifiManager) mContext.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getMacAddress());
        exec.setReceiver(this.getDescription().getStatus().getDevice());
        exec.setArgv(argv);
        exec.setGroup(GROUP_SINGLE);
        exec.setOp(OPCODE_IOPUT);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis()+"");
        ioput.setExec(exec);
        return ioput;
    }

    @Override
    public AreaFlyJSonFactory io_init(int pin, int value) {
        return null;
    }

    @Override
    public AreaFlyJSonFactory io_get(int pin) {
        return null;
    }

    @Override
    public AreaFlyJSonFactory io_button_state(int pin) {
        return null;
    }

    @Override
    public AreaFlyJSonFactory adc_init() {
        return null;
    }

    @Override
    public AreaFlyJSonFactory adc_val(int channel) {
        return null;
    }

    @Override
    public AreaFlyJSonFactory uart_init(int port, long baud) {
        return null;
    }

    @Override
    public AreaFlyJSonFactory uart_on(int port) {
        return null;
    }

    @Override
    public AreaFlyJSonFactory uart_off(int port) {
        return null;
    }

    @Override
    public AreaFlyJSonFactory uart_flush(int port) {
        return null;
    }

    @Override
    public AreaFlyJSonFactory uart_buffer_size(int port) {
        return null;
    }

    @Override
    public AreaFlyJSonFactory uart_read(int port, int n) {
        return null;
    }

    @Override
    public AreaFlyJSonFactory uart_write(int port, String str) {
        return null;
    }

    @Override
    public AreaFlyJSonFactory uart_write_ch(int port, char ch) {
        return null;
    }

    @Override
    public AreaFlyJSonFactory pwm_init(byte n, float freq, float duty) {
        return null;
    }

    @Override
    public AreaFlyJSonFactory pwm_on(byte pin, byte pwm) {
        return null;
    }

    @Override
    public AreaFlyJSonFactory pwm_duty(float duty, byte pwm) {
        return null;
    }

    @Override
    public AreaFlyJSonFactory pwm_off(byte pwm) {
        return null;
    }
}