package eu.areamobile.apis.hw.pics.entity.json;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by AreaMobile
 * Date: 23/01/12
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class HWJSonIOSpecs implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// PIN NUMBERS
	public final static short NUMBER_PIN_1 = 1;
	public final static short NUMBER_PIN_2 = 2;
	public final static short NUMBER_PIN_3 = 3;
	public final static short NUMBER_PIN_4 = 4;
	public final static short NUMBER_PIN_5 = 5;
	public final static short NUMBER_PIN_6 = 6;
	public final static short NUMBER_PIN_7 = 7;
	public final static short NUMBER_PIN_8 = 8;
	public final static short NUMBER_PIN_9 = 9;
	public final static short NUMBER_PIN_10 = 10;
	public final static short NUMBER_PIN_11 = 11;
	public final static short NUMBER_PIN_12 = 12;
	public final static short NUMBER_PIN_13 = 13;
	public final static short NUMBER_PIN_14 = 14;
	public final static short NUMBER_PIN_15 = 15;
	public final static short NUMBER_PIN_16 = 16;
	public final static short NUMBER_PIN_17 = 17;
	public final static short NUMBER_PIN_18 = 18;
	public final static short NUMBER_PIN_19 = 19;
	public final static short NUMBER_PIN_20 = 20;
	public final static short NUMBER_PIN_21 = 21;
	public final static short NUMBER_PIN_22 = 22;
	public final static short NUMBER_PIN_23 = 23;
	public final static short NUMBER_PIN_24 = 24;
	public final static short NUMBER_PIN_25 = 25;
	public final static short NUMBER_PIN_26 = 26;
	
	// PORT NUMBERS
	public final static short NUMBER_PORT_1 = 1;
	public final static short NUMBER_PORT_2 = 2;
	public final static short NUMBER_PORT_3 = 3;
	public final static short NUMBER_PORT_4 = 4;
	
	// CHANNEL NUMBER
	public final static short NUMBER_CHANNEL_1 = 1;
	public final static short NUMBER_CHANNEL_2 = 2;
	public final static short NUMBER_CHANNEL_3 = 3;
	public final static short NUMBER_CHANNEL_4 = 4;
	public final static short NUMBER_CHANNEL_5 = 5;
	public final static short NUMBER_CHANNEL_6 = 6;
	public final static short NUMBER_CHANNEL_7 = 7;
	public final static short NUMBER_CHANNEL_8 = 8;
	public final static short NUMBER_CHANNEL_9 = 9;

    // GROUPs
    public final static short GROUP_SINGLE = 0;
    public final static short GROUP_ALL = 255;

    // IOPut Value
    public final static byte VALUE_IOPUT_ON = 1;
    public final static byte VALUE_IOPUT_OFF = 0;
    public final static byte VALUE_IOPUT_TOGGLE = 2;
    
    // IOInit Value
    public final static byte VALUE_IOINIT_OUT = 0;
    public final static byte VALUE_IOINIT_IN = 1;
    public final static byte VALUE_IOINIT_INUP = 2;
    public final static byte VALUE_IOINIT_INDOWN = 3;
    public final static byte VALUE_IOINIT_UART1RX = 5;
    public final static byte VALUE_IOINIT_UART1CTS = 6;
    public final static byte VALUE_IOINIT_UART2RX = 7;
    public final static byte VALUE_IOINIT_UART2CTS = 8;
    public final static byte VALUE_IOINIT_UART3RX = 9;
    public final static byte VALUE_IOINIT_UART3CTS = 10;
    public final static byte VALUE_IOINIT_UART4RX = 11;
    public final static byte VALUE_IOINIT_UART4CTS = 12;
    public final static byte VALUE_IOINIT_EXT_INT2 = 13;
    public final static byte VALUE_IOINIT_EXT_INT3 = 14;
    public final static byte VALUE_IOINIT_EXT_INT4 = 15;
    public final static byte VALUE_IOINIT_SPICLKIN = 16;
    public final static byte VALUE_IOINIT_SPI_IN = 17;
    public final static byte VALUE_IOINIT_SPI_SS = 18;
    public final static byte VALUE_IOINIT_TIM_4_CLK = 19;
    public final static byte VALUE_IOINIT_UART1TX = 31;
    public final static byte VALUE_IOINIT_UART1RTS = 32;
    public final static byte VALUE_IOINIT_UART2TX = 33;
    public final static byte VALUE_IOINIT_UART2RTS = 34;
    public final static byte VALUE_IOINIT_UART3TX = 35;
    public final static byte VALUE_IOINIT_UART3RTS = 36;
    public final static byte VALUE_IOINIT_UART4TX = 37;
    public final static byte VALUE_IOINIT_UART4RTS = 38;
    public final static byte VALUE_IOINIT_SPICLKOUT = 39;
    public final static byte VALUE_IOINIT_SPI_OUT = 40;
    public final static byte VALUE_IOINIT_SPI_SS_OUT = 41;

    // OPCODE
    public final static byte OPCODE_SCAN = 0;
    public final static byte OPCODE_IOPUT = 1;
    public final static byte OPCODE_IOINIT = 2;
    public final static byte OPCODE_IOGET = 3;
    public final static byte OPCODE_IOBUTTONSTATE = 4;
    public final static byte OPCODE_ADCINIT = 5;
    public final static byte OPCODE_ADCVAL = 6;
    public final static byte OPCODE_UARTINIT = 7;
    public final static byte OPCODE_UART_ON = 8;
    public final static byte OPCODE_UART_OFF = 9;
    public final static byte OPCODE_UART_FLUSH = 10;
    public final static byte OPCODE_UART_BUFFER_SIZE = 11;
    public final static byte OPCODE_UART_READ = 12;
    public final static byte OPCODE_UART_WRITE = 13;
    public final static byte OPCODE_UART_WRITE_CH = 14;
    public final static byte OPCODE_PWM_INIT = 15;
    public final static byte OPCODE_PWM_ON = 16;
    public final static byte OPCODE_PWM_DUTY = 17;
    public final static byte OPCODE_PWM_OFF = 18;
    
    // COMMON BAUD RATE
    public final static int VALUE_BAUD_LOW = 9600;
    public final static int VALUE_BAUD_MEDIUMLOW = 19200;
    public final static int VALUE_BAUD_MEDIUM = 38400;
    public final static int VALUE_BAUD_MEDIUMHIGH = 57600;
    public final static int VALUE_BAUD_HIGH = 115200;


    @SerializedName("exec")
    private Exec exec;

    @SerializedName("status")
    private Status status;

    private Exec getExec() {
        return exec;
    }

    public void setExec(Exec exec) {
        this.exec = exec;
    }

    private Status setStatys() {
        return status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return this.getExec().getDevice() + ":" + this.getExec().getGroup();
    }

    /**
     * The exec value
	 * @author Diego Stamigni
	 */
    public static class Exec implements Serializable {
        private String dev;
        private long time;
        private String pwd;
        private short grp;
        private byte op;
        private byte argc;
        private Argv[] argv;
        private boolean ack;

        public String getDevice() {
            return dev;
        }

        public void setDevice(String dev) {
            this.dev = dev;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public byte getArgc() {
			return argc;
		}

		public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public short getGroup() {
            return grp;
        }

        public void setGroup(short grp) {
            this.grp = grp;
        }

        public byte getOp() {
            return op;
        }

        public void setOp(byte op) {
            this.op = op;
        }

        public Argv[] getArgv() {
            return argv;
        }

        public void setArgv(Argv[] argv) {
            this.argc = (byte) argv.length;
            this.argv = argv;
        }

        /**
		 * @return
		 * @uml.property  name="ack"
		 */
        public boolean isAck() {
            return ack;
        }

        /**
		 * @param ack
		 * @uml.property  name="ack"
		 */
        public void setAck(boolean ack) {
            this.ack = ack;
        }
    }

    /**
     * The status value
	 * @author Diego Stamigni
	 */
    public static class Status implements Serializable {
        private String dev;
        private long time;
        private String type;
        private String subt;
        private byte argc;
        private Argv[] argv;

        public String getDevice() {
            return dev;
        }

        public void setDevice(String dev) {
            this.dev = dev;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public byte getArgc() {
			return argc;
		}

        public Argv[] getArgv() {
            return argv;
        }

        public void setArgv(Argv[] argv) {
            this.argc = (byte) argv.length;
            this.argv = argv;
        }
    }

    //The argv: it is dynamic, depends on {@link ExecValue#argc}
    public static class Argv implements Serializable {
    	String type;
    	int value;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

        @Override
        public String toString() {
            return '[' + type + ", " + value + ']';
        }
    }
}
