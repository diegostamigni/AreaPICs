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
    /**
     * Scan the current wifi network to search any kind of Common.
     * This method is synchronized and set the local collection of AreaFlies.
     *
     * @return the collection of Common available
     * @throws java.io.IOException something goes wrong
     * @see eu.areamobile.apis.hw.pics.ijones.Discoverer#getCommonCollection()
     */

    // GROUPs
    public final static byte GROUP_ALL = 0;

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


    @SerializedName("exec")
    private ExecValue exec;

    public ExecValue getExec() {
        return exec;
    }

    public void setExec(ExecValue exec) {
        this.exec = exec;
    }

    @Override
    public String toString() {
        return this.getExec().getDevice() + ":" + this.getExec().getGroup();
    }

    //The exec value
    /**
	 * @author Diego Stamigni
	 */
    public static class ExecValue implements Serializable {
        private String dev;
        private long time;
        private String pwd;
        private byte grp;
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

		public void setArgc(byte argc) {
			this.argc = argc;
		}

		public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public byte getGroup() {
            return grp;
        }

        public void setGroup(byte grp) {
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
