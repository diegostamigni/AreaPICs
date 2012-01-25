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
    public static class ExecValue {
        private String dev;
        private String time;
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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
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
    public static class Argv {
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
