package eu.areamobile.apis.hw.pics.entity.areafly;

import eu.areamobile.apis.hw.pics.HWSpecs;
import eu.areamobile.apis.hw.pics.entity.GenericDevice;
import eu.areamobile.apis.hw.pics.entity.HWOperations;
import eu.areamobile.apis.hw.pics.entity.areafly.json.AreaFlyJSonFactory;

/**
 * Created by AreaMobile
 * Date: 28/12/11
 * <p/>
 * AreaFly, the derivation of FlyPort, this is the class you've to use for connection on it.
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class AreaFly extends GenericDevice implements Comparable<AreaFly>, HWSpecs, AreaFlyHWOperations {
    private final String TAG = this.getClass().getName();
    private String macAddress;
    private AreaFlyJSonFactory.AreaFlyIOStream areaFlyIOStream;
    private AreaFlyJSonFactory jsonFact;

    public AreaFly(String wifiMacAddress, AreaFlyJSonFactory jsonFact) {
        super(jsonFact);
        this.macAddress = wifiMacAddress;
        this.jsonFact = jsonFact;
    }

    public AreaFly(String wifiMacAddress) {
        this.macAddress = wifiMacAddress;
    }

    @Override
    public int compareTo(AreaFly areaFly) {
        return this.compareTo(areaFly);
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
    public AreaFlyJSonFactory io_put(int pin, Object value) {
        int first_type = HWOperations.TYPE_INT;
        int second_type = -1;

        if (value instanceof Byte) second_type = HWOperations.TYPE_BYTE;
        else if (value instanceof Integer) second_type = HWOperations.TYPE_INT;
        else if (value instanceof Float) second_type = HWOperations.TYPE_FLOAT;
        else if (value instanceof Double) second_type = HWOperations.TYPE_DOUBLE;
        else if (value instanceof Long) second_type = HWOperations.TYPE_LONG;
        else if (value instanceof Character) second_type = HWOperations.TYPE_CHAR;
        else if (value instanceof String) second_type = HWOperations.TYPE_STRING;

        AreaFlyJSonFactory ioput = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[2];

        argv[0] = new AreaFlyJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(pin);

        argv[1] = new AreaFlyJSonFactory.Argv();
        argv[1].setType(second_type); argv[1].setValue(value);

        exec.setAck(true);
        exec.setSender(macAddress);
        exec.setReceiver(this.getDescription().getStatus().getDevice());
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_IOPUT);
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