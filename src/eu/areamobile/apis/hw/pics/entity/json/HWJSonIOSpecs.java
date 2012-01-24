package eu.areamobile.apis.hw.pics.entity.json;

import java.io.Serializable;

/**
 * Created by AreaMobile
 * Date: 23/01/12
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public abstract class HWJSonIOSpecs implements Serializable {
    //JSon value
    /**
	 * @uml.property  name="exec"
	 * @uml.associationEnd  
	 */
    private ExecValue exec;
    /**
	 * @uml.property  name="args"
	 * @uml.associationEnd  
	 */
    private Args args;

    /**
	 * @return
	 * @uml.property  name="exec"
	 */
    public ExecValue getExec() {
        return exec;
    }

    /**
	 * @param exec
	 * @uml.property  name="exec"
	 */
    public void setExec(ExecValue exec) {
        this.exec = exec;
    }

    /**
	 * @return
	 * @uml.property  name="args"
	 */
    public Args getArgs() {
        return args;
    }

    /**
	 * @param args
	 * @uml.property  name="args"
	 */
    public void setArgs(Args args) {
        this.args = args;
    }

    /**
     * @return device:group
     * @see ExecValue#getDevice()
     * @see ExecValue#getGroup()
     */
    @Override
    public String toString() {
        return this.getExec().getDevice() + ":" + this.getExec().getGroup();
    }

    //The exec value
    /**
	 * @author  osiris
	 */
    public abstract static class ExecValue {
        private String dev;
        /**
		 * @uml.property  name="time"
		 */
        private String time;
        /**
		 * @uml.property  name="pwd"
		 */
        private String pwd;
        private byte grp;
        /**
		 * @uml.property  name="op"
		 */
        private byte op;
        /**
		 * @uml.property  name="argc"
		 */
        private byte argc;
        /**
		 * @uml.property  name="ack"
		 */
        private boolean ack;

        public String getDevice() {
            return dev;
        }

        public void setDevice(String dev) {
            this.dev = dev;
        }

        /**
		 * @return
		 * @uml.property  name="time"
		 */
        public String getTime() {
            return time;
        }

        /**
		 * @param time
		 * @uml.property  name="time"
		 */
        public void setTime(String time) {
            this.time = time;
        }

        /**
		 * @return
		 * @uml.property  name="pwd"
		 */
        public String getPwd() {
            return pwd;
        }

        /**
		 * @param pwd
		 * @uml.property  name="pwd"
		 */
        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public byte getGroup() {
            return grp;
        }

        public void setGroup(byte grp) {
            this.grp = grp;
        }

        /**
		 * @return
		 * @uml.property  name="op"
		 */
        public byte getOp() {
            return op;
        }

        /**
		 * @param op
		 * @uml.property  name="op"
		 */
        public void setOp(byte op) {
            this.op = op;
        }

        /**
		 * @return
		 * @uml.property  name="argc"
		 */
        public byte getArgc() {
            return argc;
        }

        /**
		 * @param argc
		 * @uml.property  name="argc"
		 */
        public void setArgc(byte argc) {
            this.argc = argc;
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

    //TODO review
    //The args: it is dynamic, depends on {@link ExecValue#argc}
    public abstract static class Args {}

    public abstract HWJSonIOSpecs parseFromStream(String data, Class<HWJSonIOSpecs> jSonClass);

    public abstract String transfertStream(HWJSonIOSpecs stream, Class<HWJSonIOSpecs> jSonClass);

    public abstract HWJSonIOSpecs parseFromStream(String data);

    public abstract String transfertStream(HWJSonIOSpecs stream);
}
