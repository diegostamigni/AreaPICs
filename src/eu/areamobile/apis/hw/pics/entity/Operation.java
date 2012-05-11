package eu.areamobile.apis.hw.pics.entity;

import eu.areamobile.apis.hw.pics.HWOperations;
import eu.areamobile.apis.hw.pics.proto.HWJSonIOSpecs;
import eu.areamobile.apis.hw.pics.proto.JSonFactory;

import java.io.Serializable;

/**
 * Created by AreaMobile
 * Date: 10/05/12
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class Operation implements HWOperations, Serializable {
    private String macAddress, macReceiver = HWOperations.MA_TO_BROADCAST;
//    private GenericDevice genericDevice;
    private HWJSonIOSpecs jsonSpecs;

    /**
     * Define the operation you want to do to your board.
     * @param yourNetMacAddress is your network mac address, of your client
     */
    public Operation(String yourNetMacAddress) {
        this.macAddress = yourNetMacAddress;
    }

//    public Operation(GenericDevice genericDevice, String yourNetMacAddress) {
//        this.genericDevice = genericDevice;
//        this.macAddress = yourNetMacAddress;
//    }

    /* ###################################################
    *                  OPERATIONS
    * ################################################### */

    @Override
    public void io_put(int pin, int value) {
        this.io_put(pin, value, false);
    }

    @Override
    public void io_put(int pin, int value, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;
        int second_type = HWOperations.TYPE_INT;

        JSonFactory ioput = new JSonFactory();
        JSonFactory.Exec exec = new JSonFactory.Exec();
        JSonFactory.Argv[] argv = new JSonFactory.Argv[2];

        argv[0] = new JSonFactory.Argv();
        argv[0].setType(first_type);
        argv[0].setValue(pin);

        argv[1] = new JSonFactory.Argv();
        argv[1].setType(second_type);
        argv[1].setValue(value);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(macReceiver);
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_IOPUT);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis() + "");
        ioput.setExec(exec);
        this.setJsonSpecs(ioput);
    }

    @Override
    public void io_init(int pin, int value) {
        this.io_init(pin, value, false);
    }

    @Override
    public void io_init(int pin, int value, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;
        int second_type = HWOperations.TYPE_INT;

        JSonFactory ioinit = new JSonFactory();
        JSonFactory.Exec exec = new JSonFactory.Exec();
        JSonFactory.Argv[] argv = new JSonFactory.Argv[2];

        argv[0] = new JSonFactory.Argv();
        argv[0].setType(first_type);
        argv[0].setValue(pin);

        argv[1] = new JSonFactory.Argv();
        argv[1].setType(second_type);
        argv[1].setValue(value);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(macReceiver);
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_IOINIT);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis() + "");
        ioinit.setExec(exec);
        this.setJsonSpecs(ioinit);
    }

    @Override
    public void io_get(int pin) {
        this.io_get(pin, false);
    }

    @Override
    public void io_get(int pin, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        JSonFactory ioget = new JSonFactory();
        JSonFactory.Exec exec = new JSonFactory.Exec();
        JSonFactory.Argv[] argv = new JSonFactory.Argv[1];

        argv[0] = new JSonFactory.Argv();
        argv[0].setType(first_type);
        argv[0].setValue(pin);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(macReceiver);
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_IOGET);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis() + "");
        ioget.setExec(exec);
        this.setJsonSpecs(ioget);
    }

    @Override
    public void io_button_state(int pin) {
        this.io_button_state(pin, false);
    }

    @Override
    public void io_button_state(int pin, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        JSonFactory iobuttonstate = new JSonFactory();
        JSonFactory.Exec exec = new JSonFactory.Exec();
        JSonFactory.Argv[] argv = new JSonFactory.Argv[1];

        argv[0] = new JSonFactory.Argv();
        argv[0].setType(first_type);
        argv[0].setValue(pin);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(macReceiver);
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_IOBUTTONSTATE);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis() + "");
        iobuttonstate.setExec(exec);
        this.setJsonSpecs(iobuttonstate);
    }

    @Override
    public void adc_init() {
        this.adc_init(false);
    }

    @Override
    public void adc_init(boolean acknowledge) {
        JSonFactory adcinit = new JSonFactory();
        JSonFactory.Exec exec = new JSonFactory.Exec();
        JSonFactory.Argv[] argv = new JSonFactory.Argv[0];

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(macReceiver);
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_ADCINIT);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis() + "");
        adcinit.setExec(exec);
        this.setJsonSpecs(adcinit);
    }

    @Override
    public void adc_val(int channel) {
        this.adc_val(channel, false);
    }

    @Override
    public void adc_val(int channel, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        JSonFactory adcval = new JSonFactory();
        JSonFactory.Exec exec = new JSonFactory.Exec();
        JSonFactory.Argv[] argv = new JSonFactory.Argv[1];

        argv[0] = new JSonFactory.Argv();
        argv[0].setType(first_type);
        argv[0].setValue(channel);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(macReceiver);
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_ADCVAL);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis() + "");
        adcval.setExec(exec);
        this.setJsonSpecs(adcval);
    }

    @Override
    public void uart_init(int port, long baud) {
        this.uart_init(port, baud, false);
    }

    @Override
    public void uart_init(int port, long baud, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;
        int second_type = HWOperations.TYPE_LONG;

        JSonFactory uartinit = new JSonFactory();
        JSonFactory.Exec exec = new JSonFactory.Exec();
        JSonFactory.Argv[] argv = new JSonFactory.Argv[2];

        argv[0] = new JSonFactory.Argv();
        argv[0].setType(first_type);
        argv[0].setValue(port);

        argv[1] = new JSonFactory.Argv();
        argv[1].setType(second_type);
        argv[1].setValue(baud);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(macReceiver);
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_UART_INIT);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis() + "");
        uartinit.setExec(exec);
        this.setJsonSpecs(uartinit);
    }

    @Override
    public void uart_on(int port) {
        this.uart_on(port, false);
    }

    @Override
    public void uart_on(int port, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        JSonFactory uarton = new JSonFactory();
        JSonFactory.Exec exec = new JSonFactory.Exec();
        JSonFactory.Argv[] argv = new JSonFactory.Argv[1];

        argv[0] = new JSonFactory.Argv();
        argv[0].setType(first_type);
        argv[0].setValue(port);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(macReceiver);
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_UART_ON);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis() + "");
        uarton.setExec(exec);
        this.setJsonSpecs(uarton);
    }

    @Override
    public void uart_off(int port) {
        this.uart_off(port, false);
    }

    @Override
    public void uart_off(int port, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        JSonFactory uartoff = new JSonFactory();
        JSonFactory.Exec exec = new JSonFactory.Exec();
        JSonFactory.Argv[] argv = new JSonFactory.Argv[1];

        argv[0] = new JSonFactory.Argv();
        argv[0].setType(first_type);
        argv[0].setValue(port);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(macReceiver);
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_UART_OFF);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis() + "");
        uartoff.setExec(exec);
        this.setJsonSpecs(uartoff);
    }

    @Override
    public void uart_flush(int port) {
        this.uart_flush(port, false);
    }

    @Override
    public void uart_flush(int port, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        JSonFactory uartflush = new JSonFactory();
        JSonFactory.Exec exec = new JSonFactory.Exec();
        JSonFactory.Argv[] argv = new JSonFactory.Argv[1];

        argv[0] = new JSonFactory.Argv();
        argv[0].setType(first_type);
        argv[0].setValue(port);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(macReceiver);
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_UART_FLUSH);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis() + "");
        uartflush.setExec(exec);
        this.setJsonSpecs(uartflush);
    }

    @Override
    public void uart_buffer_size(int port) {
        this.uart_buffer_size(port, false);
    }

    @Override
    public void uart_buffer_size(int port, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;

        JSonFactory uartbs = new JSonFactory();
        JSonFactory.Exec exec = new JSonFactory.Exec();
        JSonFactory.Argv[] argv = new JSonFactory.Argv[1];

        argv[0] = new JSonFactory.Argv();
        argv[0].setType(first_type);
        argv[0].setValue(port);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(macReceiver);
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_UART_BUFFER_SIZE);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis() + "");
        uartbs.setExec(exec);
        this.setJsonSpecs(uartbs);
    }

    @Override
    public void uart_read(int port, int n) {
        this.uart_read(port, n, false);
    }

    @Override
    public void uart_read(int port, int n, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;
        int second_type = HWOperations.TYPE_INT;

        JSonFactory uartread = new JSonFactory();
        JSonFactory.Exec exec = new JSonFactory.Exec();
        JSonFactory.Argv[] argv = new JSonFactory.Argv[2];

        argv[0] = new JSonFactory.Argv();
        argv[0].setType(first_type);
        argv[0].setValue(port);

        argv[1] = new JSonFactory.Argv();
        argv[1].setType(second_type);
        argv[1].setValue(n);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(macReceiver);
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_UART_READ);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis() + "");
        uartread.setExec(exec);
        this.setJsonSpecs(uartread);
    }

    @Override
    public void uart_write(int port, String str) {
        this.uart_write(port, str, false);
    }

    @Override
    public void uart_write(int port, String str, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;
        int second_type = HWOperations.TYPE_STRING;

        JSonFactory uartwrite = new JSonFactory();
        JSonFactory.Exec exec = new JSonFactory.Exec();
        JSonFactory.Argv[] argv = new JSonFactory.Argv[2];

        argv[0] = new JSonFactory.Argv();
        argv[0].setType(first_type);
        argv[0].setValue(port);

        argv[1] = new JSonFactory.Argv();
        argv[1].setType(second_type);
        argv[1].setValue(str);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(macReceiver);
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_UART_WRITE);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis() + "");
        uartwrite.setExec(exec);
        this.setJsonSpecs(uartwrite);
    }

    @Override
    public void uart_write_ch(int port, char ch) {
        this.uart_write_ch(port, ch, false);
    }

    @Override
    public void uart_write_ch(int port, char ch, boolean acknowledge) {
        int first_type = HWOperations.TYPE_INT;
        int second_type = HWOperations.TYPE_CHAR;

        JSonFactory uartwritech = new JSonFactory();
        JSonFactory.Exec exec = new JSonFactory.Exec();
        JSonFactory.Argv[] argv = new JSonFactory.Argv[2];

        argv[0] = new JSonFactory.Argv();
        argv[0].setType(first_type);
        argv[0].setValue(port);

        argv[1] = new JSonFactory.Argv();
        argv[1].setType(second_type);
        argv[1].setValue(ch);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(macReceiver);
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_UART_WRITE_CH);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis() + "");
        uartwritech.setExec(exec);
        this.setJsonSpecs(uartwritech);
    }

    @Override
    public void pwm_init(byte n, float freq, float duty) {
        this.pwm_init(n, freq, duty, false);
    }

    @Override
    public void pwm_init(byte n, float freq, float duty, boolean acknowledge) {
        int first_type = HWOperations.TYPE_BYTE;
        int second_type = HWOperations.TYPE_FLOAT;
        int third_type = HWOperations.TYPE_FLOAT;

        JSonFactory pwminit = new JSonFactory();
        JSonFactory.Exec exec = new JSonFactory.Exec();
        JSonFactory.Argv[] argv = new JSonFactory.Argv[3];

        argv[0] = new JSonFactory.Argv();
        argv[0].setType(first_type);
        argv[0].setValue(n);

        argv[1] = new JSonFactory.Argv();
        argv[1].setType(second_type);
        argv[1].setValue(freq);

        argv[2] = new JSonFactory.Argv();
        argv[2].setType(third_type);
        argv[2].setValue(duty);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(macReceiver);
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_PWM_INIT);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis() + "");
        pwminit.setExec(exec);
        this.setJsonSpecs(pwminit);
    }

    @Override
    public void pwm_on(byte pin, byte pwm) {
        this.pwm_on(pin, pwm, false);
    }

    @Override
    public void pwm_on(byte pin, byte pwm, boolean acknowledge) {
        int first_type = HWOperations.TYPE_BYTE;
        int second_type = HWOperations.TYPE_BYTE;

        JSonFactory pwmon = new JSonFactory();
        JSonFactory.Exec exec = new JSonFactory.Exec();
        JSonFactory.Argv[] argv = new JSonFactory.Argv[2];

        argv[0] = new JSonFactory.Argv();
        argv[0].setType(first_type);
        argv[0].setValue(pin);

        argv[1] = new JSonFactory.Argv();
        argv[1].setType(second_type);
        argv[1].setValue(pwm);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(macReceiver);
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_PWM_ON);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis() + "");
        pwmon.setExec(exec);
        this.setJsonSpecs(pwmon);
    }

    @Override
    public void pwm_duty(float duty, byte pwm) {
        this.pwm_duty(duty, pwm, false);
    }

    @Override
    public void pwm_duty(float duty, byte pwm, boolean acknowledge) {
        int first_type = HWOperations.TYPE_FLOAT;
        int second_type = HWOperations.TYPE_BYTE;

        JSonFactory pwmduty = new JSonFactory();
        JSonFactory.Exec exec = new JSonFactory.Exec();
        JSonFactory.Argv[] argv = new JSonFactory.Argv[2];

        argv[0] = new JSonFactory.Argv();
        argv[0].setType(first_type);
        argv[0].setValue(duty);

        argv[1] = new JSonFactory.Argv();
        argv[1].setType(second_type);
        argv[1].setValue(pwm);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(macReceiver);
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_PWM_DUTY);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis() + "");
        pwmduty.setExec(exec);
        this.setJsonSpecs(pwmduty);
    }

    @Override
    public void pwm_off(byte pwm) {
        this.pwm_off(pwm, false);
    }

    @Override
    public void pwm_off(byte pwm, boolean acknowledge) {
        int first_type = HWOperations.TYPE_BYTE;

        JSonFactory pwmoff = new JSonFactory();
        JSonFactory.Exec exec = new JSonFactory.Exec();
        JSonFactory.Argv[] argv = new JSonFactory.Argv[1];

        argv[0] = new JSonFactory.Argv();
        argv[0].setType(first_type);
        argv[0].setValue(pwm);

        exec.setAck(acknowledge);
        exec.setSender(macAddress);
        exec.setReceiver(macReceiver);
        exec.setArgv(argv);
        exec.setGroup(HWOperations.GROUP_SINGLE);
        exec.setOp(HWOperations.OPCODE_PWM_OFF);
        exec.setPwd("xxx");
        exec.setTime(System.currentTimeMillis() + "");
        pwmoff.setExec(exec);
        this.setJsonSpecs(pwmoff);
    }

    public HWJSonIOSpecs getJsonSpecs() {
        return jsonSpecs;
    }

    public void setJsonSpecs(HWJSonIOSpecs jsonSpecs) {
        this.jsonSpecs = jsonSpecs;
    }

    public String getMacReceiver() {
        return macReceiver;
    }

    public void setMacReceiver(String macReceiver) {
        this.macReceiver = macReceiver;
        this.getJsonSpecs().getExec().setReceiver(macReceiver);
    }
}
