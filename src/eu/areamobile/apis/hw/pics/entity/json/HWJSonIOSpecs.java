package eu.areamobile.apis.hw.pics.entity.json;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by AreaMobile
 * Date: 23/01/12
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

//TODO review
public class HWJSonIOSpecs implements Serializable {
	private static final long serialVersionUID = 1L;

    @SerializedName("exec")
    private Exec exec;

    @SerializedName("stat")
    private Status status;

    private Exec getExec() {
        return exec;
    }

    public void setExec(Exec exec) {
        this.exec = exec;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return this.getExec().getSender() + ":" + this.getExec().getGroup();
    }

    /**
     * The exec value
	 * @author Diego Stamigni
	 */
    public static class Exec implements Serializable {
        // android wifi mac address
        private String sdr;
        // areafly mac address
        private String rcv;
        private String time;
        private String pwd;
        private short grp;
        private byte op;
        private Argv[] argv;
        private boolean ack;

        public String getSender() {
            return sdr;
        }

        public void setReceiver(String value) {
            this.rcv = value;
        }

        public String getReceiver() {
            return rcv;
        }

        public void setSender(String dev) {
            this.sdr = dev;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
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
        // areafly wifi mac address
        private String sdr;
        // android mac address
        private String rcv;
        private long time;
        private int type;
        private int subt;
        private Argv[] argv;

        public String getDevice() {
            return sdr;
        }

        public void setDevice(String dev) {
            this.sdr = dev;
        }

//        public void setReceiver(String value) {
//            this.rcv = value;
//        }

        public void setType(int value) {
            this.type = value;
        }

        public void setSubType(int value) {
            this.subt = value;
        }

        public int getType() {
            return type;
        }

        public int getSubType() {
            return this.subt;
        }

//        public String getReceiver() {
//            return rcv;
//        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public Argv[] getArgv() {
            return argv;
        }

        public void setArgv(Argv[] argv) {
            this.argv = argv;
        }
    }

    //The argv: it is dynamic, depends on {@link ExecValue#argc}
    public static class Argv implements Serializable {
    	int type;
        @SerializedName("val")
    	Object value;

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

        @Override
        public String toString() {
            return '[' + type + ", " + value + ']';
        }
    }
}
