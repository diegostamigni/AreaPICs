package eu.areamobile.apis.hw.pics.entity.dooip;

import eu.areamobile.apis.hw.pics.HWSpecs;
import eu.areamobile.apis.hw.pics.entity.GenericDevice;
import eu.areamobile.apis.hw.pics.entity.HWOperations;
import eu.areamobile.apis.hw.pics.entity.dooip.json.DooIPJSonFactory;

/**
 * Created by AreaMobile
 * Date: 28/12/11
 * <p/>
 * AreaFly, the derivation of FlyPort, this is the class you've to use for connection on it.
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class DooIP extends GenericDevice implements Comparable<DooIP>, HWSpecs, DooIPHWOperations {
    private final String TAG = this.getClass().getName();
    private String macAddress, mNetBiosName;
    private DooIPJSonFactory.AreaFlyIOStream areaFlyIOStream;
    private DooIPJSonFactory jsonFact;

    public DooIP(String mNetBiosName, String wifiMacAddress, DooIPJSonFactory jsonFact) {
        this.mNetBiosName = mNetBiosName;
        this.macAddress = wifiMacAddress;
        this.jsonFact = jsonFact;
    }

    public DooIP(String macDevice) {
        this.macAddress = macDevice;
    }

    @Override
    public int compareTo(DooIP dooIP) {
        return this.compareTo(dooIP);
    }

    @Override
    public DooIPJSonFactory getJSonFactory() {
        return this.jsonFact;
    }

    public void setJSonFactory(DooIPJSonFactory factory) {
        this.jsonFact = factory;
    }

    /* ###################################################
     *                  OPERATIONS
     * ################################################### */

    @Override
    public DooIPJSonFactory io_put(int pin, int value) {
        return this.io_put(pin, value, false);
    }

    @Override
    public DooIPJSonFactory io_put(int pin, int value, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;
        int second_type = HWOperations.TYPE_INT;

        DooIPJSonFactory ioput = new DooIPJSonFactory();
        DooIPJSonFactory.Exec exec = new DooIPJSonFactory.Exec();
        DooIPJSonFactory.Argv[] argv = new DooIPJSonFactory.Argv[2];

        argv[0] = new DooIPJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(pin);

        argv[1] = new DooIPJSonFactory.Argv();
        argv[1].setType(second_type); argv[1].setValue(value);

        exec.setAck(acknowledge);
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
    public DooIPJSonFactory io_init(int pin, int value) {
        return this.io_init(pin, value, false);
    }

    @Override
    public DooIPJSonFactory io_init(int pin, int value, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;
        int second_type = HWOperations.TYPE_INT;

        DooIPJSonFactory ioinit = new DooIPJSonFactory();
        DooIPJSonFactory.Exec exec = new DooIPJSonFactory.Exec();
        DooIPJSonFactory.Argv[] argv = new DooIPJSonFactory.Argv[2];

        argv[0] = new DooIPJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(pin);

        argv[1] = new DooIPJSonFactory.Argv();
        argv[1].setType(second_type); argv[1].setValue(value);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(this.getDescription().getStatus().getDevice());
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_IOINIT);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis()+"");
        ioinit.setExec(exec);
        return ioinit;
    }

    @Override
    public DooIPJSonFactory io_get(int pin) {
        return this.io_get(pin, false);
    }

    @Override
    public DooIPJSonFactory io_get(int pin, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        DooIPJSonFactory ioget = new DooIPJSonFactory();
        DooIPJSonFactory.Exec exec = new DooIPJSonFactory.Exec();
        DooIPJSonFactory.Argv[] argv = new DooIPJSonFactory.Argv[1];

        argv[0] = new DooIPJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(pin);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(this.getDescription().getStatus().getDevice());
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_IOGET);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis()+"");
        ioget.setExec(exec);
        return ioget;
    }

    @Override
    public DooIPJSonFactory io_button_state(int pin) {
        return this.io_button_state(pin, false);
    }

    @Override
    public DooIPJSonFactory io_button_state(int pin, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        DooIPJSonFactory iobuttonstate = new DooIPJSonFactory();
        DooIPJSonFactory.Exec exec = new DooIPJSonFactory.Exec();
        DooIPJSonFactory.Argv[] argv = new DooIPJSonFactory.Argv[1];

        argv[0] = new DooIPJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(pin);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(this.getDescription().getStatus().getDevice());
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_IOBUTTONSTATE);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis()+"");
        iobuttonstate.setExec(exec);
        return iobuttonstate;
    }

    @Override
    public DooIPJSonFactory adc_init() {
        return this.adc_init(false);
    }

    @Override
    public DooIPJSonFactory adc_init(boolean acknowledge) {
        DooIPJSonFactory adcinit = new DooIPJSonFactory();
        DooIPJSonFactory.Exec exec = new DooIPJSonFactory.Exec();
        DooIPJSonFactory.Argv[] argv = new DooIPJSonFactory.Argv[0];

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(this.getDescription().getStatus().getDevice());
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_ADCINIT);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis()+"");
        adcinit.setExec(exec);
        return adcinit;
    }

    @Override
    public DooIPJSonFactory adc_val(int channel) {
        return this.adc_val(channel, false);
    }

    @Override
    public DooIPJSonFactory adc_val(int channel, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        DooIPJSonFactory adcval = new DooIPJSonFactory();
        DooIPJSonFactory.Exec exec = new DooIPJSonFactory.Exec();
        DooIPJSonFactory.Argv[] argv = new DooIPJSonFactory.Argv[1];

        argv[0] = new DooIPJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(channel);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(this.getDescription().getStatus().getDevice());
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_ADCVAL);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis()+"");
        adcval.setExec(exec);
        return adcval;
    }

    @Override
    public DooIPJSonFactory uart_init(int port, long baud) {
        return this.uart_init(port, baud, false);
    }

    @Override
    public DooIPJSonFactory uart_init(int port, long baud, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;
        int second_type = HWOperations.TYPE_LONG;

        DooIPJSonFactory uartinit = new DooIPJSonFactory();
        DooIPJSonFactory.Exec exec = new DooIPJSonFactory.Exec();
        DooIPJSonFactory.Argv[] argv = new DooIPJSonFactory.Argv[2];

        argv[0] = new DooIPJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(port);

        argv[1] = new DooIPJSonFactory.Argv();
        argv[1].setType(second_type); argv[1].setValue(baud);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(this.getDescription().getStatus().getDevice());
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_UART_INIT);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis()+"");
        uartinit.setExec(exec);
        return uartinit;
    }

    @Override
    public DooIPJSonFactory uart_on(int port) {
        return this.uart_on(port, false);
    }

    @Override
    public DooIPJSonFactory uart_on(int port, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        DooIPJSonFactory uarton = new DooIPJSonFactory();
        DooIPJSonFactory.Exec exec = new DooIPJSonFactory.Exec();
        DooIPJSonFactory.Argv[] argv = new DooIPJSonFactory.Argv[1];

        argv[0] = new DooIPJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(port);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(this.getDescription().getStatus().getDevice());
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_UART_ON);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis()+"");
        uarton.setExec(exec);
        return uarton;
    }

    @Override
    public DooIPJSonFactory uart_off(int port) {
        return this.uart_off(port, false);
    }

    @Override
    public DooIPJSonFactory uart_off(int port, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        DooIPJSonFactory uartoff = new DooIPJSonFactory();
        DooIPJSonFactory.Exec exec = new DooIPJSonFactory.Exec();
        DooIPJSonFactory.Argv[] argv = new DooIPJSonFactory.Argv[1];

        argv[0] = new DooIPJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(port);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(this.getDescription().getStatus().getDevice());
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_UART_OFF);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis()+"");
        uartoff.setExec(exec);
        return uartoff;
    }

    @Override
    public DooIPJSonFactory uart_flush(int port) {
        return this.uart_flush(port, false);
    }

    @Override
    public DooIPJSonFactory uart_flush(int port, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        DooIPJSonFactory uartflush = new DooIPJSonFactory();
        DooIPJSonFactory.Exec exec = new DooIPJSonFactory.Exec();
        DooIPJSonFactory.Argv[] argv = new DooIPJSonFactory.Argv[1];

        argv[0] = new DooIPJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(port);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(this.getDescription().getStatus().getDevice());
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_UART_FLUSH);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis()+"");
        uartflush.setExec(exec);
        return uartflush;
    }

    @Override
    public DooIPJSonFactory uart_buffer_size(int port) {
        return this.uart_buffer_size(port, false);
    }

    @Override
    public DooIPJSonFactory uart_buffer_size(int port, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        DooIPJSonFactory uartbs = new DooIPJSonFactory();
        DooIPJSonFactory.Exec exec = new DooIPJSonFactory.Exec();
        DooIPJSonFactory.Argv[] argv = new DooIPJSonFactory.Argv[1];

        argv[0] = new DooIPJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(port);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(this.getDescription().getStatus().getDevice());
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_UART_BUFFER_SIZE);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis()+"");
        uartbs.setExec(exec);
        return uartbs;
    }

    @Override
    public DooIPJSonFactory uart_read(int port, int n) {
        return this.uart_read(port, n, false);
    }

    @Override
    public DooIPJSonFactory uart_read(int port, int n, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;
        int second_type = HWOperations.TYPE_INT;

        DooIPJSonFactory uartread = new DooIPJSonFactory();
        DooIPJSonFactory.Exec exec = new DooIPJSonFactory.Exec();
        DooIPJSonFactory.Argv[] argv = new DooIPJSonFactory.Argv[2];

        argv[0] = new DooIPJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(port);

        argv[1] = new DooIPJSonFactory.Argv();
        argv[1].setType(second_type); argv[1].setValue(n);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(this.getDescription().getStatus().getDevice());
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_UART_READ);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis()+"");
        uartread.setExec(exec);
        return uartread;
    }

    @Override
    public DooIPJSonFactory uart_write(int port, String str) {
        return this.uart_write(port, str, false);
    }

    @Override
    public DooIPJSonFactory uart_write(int port, String str, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;
        int second_type = HWOperations.TYPE_STRING;

        DooIPJSonFactory uartwrite = new DooIPJSonFactory();
        DooIPJSonFactory.Exec exec = new DooIPJSonFactory.Exec();
        DooIPJSonFactory.Argv[] argv = new DooIPJSonFactory.Argv[2];

        argv[0] = new DooIPJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(port);

        argv[1] = new DooIPJSonFactory.Argv();
        argv[1].setType(second_type); argv[1].setValue(str);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(this.getDescription().getStatus().getDevice());
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_UART_WRITE);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis()+"");
        uartwrite.setExec(exec);
        return uartwrite;
    }

    @Override
    public DooIPJSonFactory uart_write_ch(int port, char ch) {
        return this.uart_write_ch(port, ch, false);
    }

    @Override
    public DooIPJSonFactory uart_write_ch(int port, char ch, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;
        int second_type = HWOperations.TYPE_CHAR;

        DooIPJSonFactory uartwritech = new DooIPJSonFactory();
        DooIPJSonFactory.Exec exec = new DooIPJSonFactory.Exec();
        DooIPJSonFactory.Argv[] argv = new DooIPJSonFactory.Argv[2];

        argv[0] = new DooIPJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(port);

        argv[1] = new DooIPJSonFactory.Argv();
        argv[1].setType(second_type); argv[1].setValue(ch);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(this.getDescription().getStatus().getDevice());
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_UART_WRITE_CH);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis()+"");
        uartwritech.setExec(exec);
        return uartwritech;
    }

    @Override
    public DooIPJSonFactory pwm_init(byte n, float freq, float duty) {
        return this.pwm_init(n, freq, duty, false);
    }

    @Override
    public DooIPJSonFactory pwm_init(byte n, float freq, float duty, boolean acknowledge) {
        int first_type = HWOperations.TYPE_BYTE;
        int second_type = HWOperations.TYPE_FLOAT;
        int third_type = HWOperations.TYPE_FLOAT;

        DooIPJSonFactory pwminit = new DooIPJSonFactory();
        DooIPJSonFactory.Exec exec = new DooIPJSonFactory.Exec();
        DooIPJSonFactory.Argv[] argv = new DooIPJSonFactory.Argv[3];

        argv[0] = new DooIPJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(n);

        argv[1] = new DooIPJSonFactory.Argv();
        argv[1].setType(second_type); argv[1].setValue(freq);

        argv[2] = new DooIPJSonFactory.Argv();
        argv[2].setType(third_type); argv[2].setValue(duty);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(this.getDescription().getStatus().getDevice());
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_PWM_INIT);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis()+"");
        pwminit.setExec(exec);
        return pwminit;
    }

    @Override
    public DooIPJSonFactory pwm_on(byte pin, byte pwm) {
        return this.pwm_on(pin, pwm, false);
    }

    @Override
    public DooIPJSonFactory pwm_on(byte pin, byte pwm, boolean acknowledge) {
        int first_type = HWOperations.TYPE_BYTE;
        int second_type = HWOperations.TYPE_BYTE;

        DooIPJSonFactory pwmon = new DooIPJSonFactory();
        DooIPJSonFactory.Exec exec = new DooIPJSonFactory.Exec();
        DooIPJSonFactory.Argv[] argv = new DooIPJSonFactory.Argv[2];

        argv[0] = new DooIPJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(pin);

        argv[1] = new DooIPJSonFactory.Argv();
        argv[1].setType(second_type); argv[1].setValue(pwm);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(this.getDescription().getStatus().getDevice());
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_PWM_ON);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis()+"");
        pwmon.setExec(exec);
        return pwmon;
    }

    @Override
    public DooIPJSonFactory pwm_duty(float duty, byte pwm) {
        return this.pwm_duty(duty, pwm, false);
    }

    @Override
    public DooIPJSonFactory pwm_duty(float duty, byte pwm, boolean acknowledge) {
        int first_type = HWOperations.TYPE_FLOAT;
        int second_type = HWOperations.TYPE_BYTE;

        DooIPJSonFactory pwmduty = new DooIPJSonFactory();
        DooIPJSonFactory.Exec exec = new DooIPJSonFactory.Exec();
        DooIPJSonFactory.Argv[] argv = new DooIPJSonFactory.Argv[2];

        argv[0] = new DooIPJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(duty);

        argv[1] = new DooIPJSonFactory.Argv();
        argv[1].setType(second_type); argv[1].setValue(pwm);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(this.getDescription().getStatus().getDevice());
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_PWM_DUTY);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis()+"");
        pwmduty.setExec(exec);
        return pwmduty;
    }

    @Override
    public DooIPJSonFactory pwm_off(byte pwm) {
        return this.pwm_off(pwm, false);
    }

    @Override
    public DooIPJSonFactory pwm_off(byte pwm, boolean acknowledge) {
        int first_type = HWOperations.TYPE_BYTE;

        DooIPJSonFactory pwmoff = new DooIPJSonFactory();
        DooIPJSonFactory.Exec exec = new DooIPJSonFactory.Exec();
        DooIPJSonFactory.Argv[] argv = new DooIPJSonFactory.Argv[1];

        argv[0] = new DooIPJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(pwm);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(this.getDescription().getStatus().getDevice());
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_PWM_OFF);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis()+"");
        pwmoff.setExec(exec);
        return pwmoff;
    }
}