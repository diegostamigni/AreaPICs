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
    private ExecValue exec;
    private Args args;

    public ExecValue getExec() {
        return exec;
    }

    public void setExec(ExecValue exec) {
        this.exec = exec;
    }

    public Args getArgs() {
        return args;
    }

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
    public abstract static class ExecValue {
        private String dev;
        private String time;
        private String pwd;
        private byte grp;
        private byte op;
        private byte argc;
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

        public byte getArgc() {
            return argc;
        }

        public void setArgc(byte argc) {
            this.argc = argc;
        }

        public boolean isAck() {
            return ack;
        }

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
