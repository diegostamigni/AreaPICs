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
    private String macAddress, mNetBiosName;
    private AreaFlyJSonFactory.AreaFlyIOStream areaFlyIOStream;
    private AreaFlyJSonFactory jsonFact;

    public AreaFly(String mNetBiosName, String wifiMacAddress, AreaFlyJSonFactory jsonFact) {
        this.mNetBiosName = mNetBiosName;
        this.macAddress = wifiMacAddress;
        this.jsonFact = jsonFact;
    }

    public AreaFly(String macDevice) {
        this.macAddress = macDevice;
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
    public AreaFlyJSonFactory io_put(int pin, int value) {
        return this.io_put(pin, value, false);
    }

    @Override
    public AreaFlyJSonFactory io_put(int pin, int value, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;
        int second_type = HWOperations.TYPE_INT;

        AreaFlyJSonFactory ioput = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[2];

        argv[0] = new AreaFlyJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(pin);

        argv[1] = new AreaFlyJSonFactory.Argv();
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
    public AreaFlyJSonFactory io_init(int pin, int value) {
        return this.io_init(pin, value, false);
    }

    @Override
    public AreaFlyJSonFactory io_init(int pin, int value, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;
        int second_type = HWOperations.TYPE_INT;

        AreaFlyJSonFactory ioinit = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[2];

        argv[0] = new AreaFlyJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(pin);

        argv[1] = new AreaFlyJSonFactory.Argv();
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
    public AreaFlyJSonFactory io_get(int pin) {
        return this.io_get(pin, false);
    }

    @Override
    public AreaFlyJSonFactory io_get(int pin, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        AreaFlyJSonFactory ioget = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[1];

        argv[0] = new AreaFlyJSonFactory.Argv();
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
    public AreaFlyJSonFactory io_button_state(int pin) {
        return this.io_button_state(pin, false);
    }

    @Override
    public AreaFlyJSonFactory io_button_state(int pin, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        AreaFlyJSonFactory iobuttonstate = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[1];

        argv[0] = new AreaFlyJSonFactory.Argv();
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
    public AreaFlyJSonFactory adc_init() {
        return this.adc_init(false);
    }

    @Override
    public AreaFlyJSonFactory adc_init(boolean acknowledge) {
        AreaFlyJSonFactory adcinit = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[0];

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
    public AreaFlyJSonFactory adc_val(int channel) {
        return this.adc_val(channel, false);
    }

    @Override
    public AreaFlyJSonFactory adc_val(int channel, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        AreaFlyJSonFactory adcval = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[1];

        argv[0] = new AreaFlyJSonFactory.Argv();
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
    public AreaFlyJSonFactory uart_init(int port, long baud) {
        return this.uart_init(port, baud, false);
    }

    @Override
    public AreaFlyJSonFactory uart_init(int port, long baud, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;
        int second_type = HWOperations.TYPE_LONG;

        AreaFlyJSonFactory uartinit = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[2];

        argv[0] = new AreaFlyJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(port);

        argv[1] = new AreaFlyJSonFactory.Argv();
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
    public AreaFlyJSonFactory uart_on(int port) {
        return this.uart_on(port, false);
    }

    @Override
    public AreaFlyJSonFactory uart_on(int port, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        AreaFlyJSonFactory uarton = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[1];

        argv[0] = new AreaFlyJSonFactory.Argv();
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
    public AreaFlyJSonFactory uart_off(int port) {
        return this.uart_off(port, false);
    }

    @Override
    public AreaFlyJSonFactory uart_off(int port, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        AreaFlyJSonFactory uartoff = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[1];

        argv[0] = new AreaFlyJSonFactory.Argv();
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
    public AreaFlyJSonFactory uart_flush(int port) {
        return this.uart_flush(port, false);
    }

    @Override
    public AreaFlyJSonFactory uart_flush(int port, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        AreaFlyJSonFactory uartflush = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[1];

        argv[0] = new AreaFlyJSonFactory.Argv();
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
    public AreaFlyJSonFactory uart_buffer_size(int port) {
        return this.uart_buffer_size(port, false);
    }

    @Override
    public AreaFlyJSonFactory uart_buffer_size(int port, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        AreaFlyJSonFactory uartbs = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[1];

        argv[0] = new AreaFlyJSonFactory.Argv();
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
    public AreaFlyJSonFactory uart_read(int port, int n) {
        return this.uart_read(port, n, false);
    }

    @Override
    public AreaFlyJSonFactory uart_read(int port, int n, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;
        int second_type = HWOperations.TYPE_INT;

        AreaFlyJSonFactory uartread = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[2];

        argv[0] = new AreaFlyJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(port);

        argv[1] = new AreaFlyJSonFactory.Argv();
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
    public AreaFlyJSonFactory uart_write(int port, String str) {
        return this.uart_write(port, str, false);
    }

    @Override
    public AreaFlyJSonFactory uart_write(int port, String str, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;
        int second_type = HWOperations.TYPE_STRING;

        AreaFlyJSonFactory uartwrite = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[2];

        argv[0] = new AreaFlyJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(port);

        argv[1] = new AreaFlyJSonFactory.Argv();
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
    public AreaFlyJSonFactory uart_write_ch(int port, char ch) {
        return this.uart_write_ch(port, ch, false);
    }

    @Override
    public AreaFlyJSonFactory uart_write_ch(int port, char ch, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;
        int second_type = HWOperations.TYPE_CHAR;

        AreaFlyJSonFactory uartwritech = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[2];

        argv[0] = new AreaFlyJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(port);

        argv[1] = new AreaFlyJSonFactory.Argv();
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
    public AreaFlyJSonFactory pwm_init(byte n, float freq, float duty) {
        return this.pwm_init(n, freq, duty, false);
    }

    @Override
    public AreaFlyJSonFactory pwm_init(byte n, float freq, float duty, boolean acknowledge) {
        int first_type = HWOperations.TYPE_BYTE;
        int second_type = HWOperations.TYPE_FLOAT;
        int third_type = HWOperations.TYPE_FLOAT;

        AreaFlyJSonFactory pwminit = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[3];

        argv[0] = new AreaFlyJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(n);

        argv[1] = new AreaFlyJSonFactory.Argv();
        argv[1].setType(second_type); argv[1].setValue(freq);

        argv[2] = new AreaFlyJSonFactory.Argv();
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
    public AreaFlyJSonFactory pwm_on(byte pin, byte pwm) {
        return this.pwm_on(pin, pwm, false);
    }

    @Override
    public AreaFlyJSonFactory pwm_on(byte pin, byte pwm, boolean acknowledge) {
        int first_type = HWOperations.TYPE_BYTE;
        int second_type = HWOperations.TYPE_BYTE;

        AreaFlyJSonFactory pwmon = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[2];

        argv[0] = new AreaFlyJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(pin);

        argv[1] = new AreaFlyJSonFactory.Argv();
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
    public AreaFlyJSonFactory pwm_duty(float duty, byte pwm) {
        return this.pwm_duty(duty, pwm, false);
    }

    @Override
    public AreaFlyJSonFactory pwm_duty(float duty, byte pwm, boolean acknowledge) {
        int first_type = HWOperations.TYPE_FLOAT;
        int second_type = HWOperations.TYPE_BYTE;

        AreaFlyJSonFactory pwmduty = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[2];

        argv[0] = new AreaFlyJSonFactory.Argv();
        argv[0].setType(first_type); argv[0].setValue(duty);

        argv[1] = new AreaFlyJSonFactory.Argv();
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
    public AreaFlyJSonFactory pwm_off(byte pwm) {
        return this.pwm_off(pwm, false);
    }

    @Override
    public AreaFlyJSonFactory pwm_off(byte pwm, boolean acknowledge) {
        int first_type = HWOperations.TYPE_BYTE;

        AreaFlyJSonFactory pwmoff = new AreaFlyJSonFactory();
        AreaFlyJSonFactory.Exec exec = new AreaFlyJSonFactory.Exec();
        AreaFlyJSonFactory.Argv[] argv = new AreaFlyJSonFactory.Argv[1];

        argv[0] = new AreaFlyJSonFactory.Argv();
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