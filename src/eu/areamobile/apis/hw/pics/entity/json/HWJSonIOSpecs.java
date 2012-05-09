package eu.areamobile.apis.hw.pics.entity.json;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * Created by AreaMobile
 * Date: 23/01/12
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class HWJSonIOSpecs implements Serializable {
    // EXEC SerializedName
    private static final String EXEC = "exec";
    private static final String EXEC_SDR = "sdr";
    private static final String EXEC_RCV = "rcv";
    private static final String EXEC_TIME = "time";
    private static final String EXEC_OP = "op";
    private static final String EXEC_GROUP = "grp";
    private static final String EXEC_PWD = "pwd";
    private static final String EXEC_ACK = "ack";

    // STAT SerializedName
    private static final String STAT = "stat";
    private static final String STAT_SDR = "sdr";
    private static final String STAT_RCV = "rcv";
    private static final String STAT_TIME = "time";
    private static final String STAT_TYPE = "type";
    private static final String STAT_SUBT = "subt";

    // ARGV SerializedName
    private static final String ARGV = "argv";
    private static final String ARGV_TYPE = "type";
    private static final String ARGV_VAL = "val";

    private static final long serialVersionUID = 1L;

    @SerializedName(EXEC)
    private Exec exec;

    @SerializedName(STAT)
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
     * The exec val
	 * @author Diego Stamigni
	 */
    public static class Exec implements Serializable {
        // android wifi mac address
        @SerializedName(EXEC_SDR)
        private String sdr;

        // areafly mac address
        @SerializedName(EXEC_RCV)
        private String rcv;

        @SerializedName(EXEC_TIME)
        private String time;

        @SerializedName(EXEC_PWD)
        private String pwd;

        @SerializedName(EXEC_GROUP)
        private short grp;

        @SerializedName(EXEC_OP)
        private byte op;

        @SerializedName(ARGV)
        private Argv[] argv;

        @SerializedName(EXEC_ACK)
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

        public boolean isAck() {
            return ack;
        }

        public void setAck(boolean ack) {
            this.ack = ack;
        }
    }

    /**
     * The status val
	 * @author Diego Stamigni
	 */
    public static class Status implements Serializable {
        // areafly wifi mac address
        @SerializedName(STAT_SDR)
        private String sdr;

        // android mac address
        @SerializedName(STAT_RCV)
        private String rcv;

        @SerializedName(STAT_TIME)
        private String time;

        @SerializedName(STAT_TYPE)
        private int type;

        @SerializedName(STAT_SUBT)
        private int subt;

        @SerializedName(ARGV)
        private Argv[] argv;

        public String getDevice() {
            return sdr;
        }

        public void setDevice(String dev) {
            this.sdr = dev;
        }

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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
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
        @SerializedName(ARGV_TYPE)
        int type;

        @SerializedName(ARGV_VAL)
    	Object val;

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public Object getValue() {
			return val;
		}

		public void setValue(Object value) {
			this.val = value;
		}

        @Override
        public String toString() {
            return '[' + type + ", " + val + ']';
        }
    }

    /* Exec sample
        { "exec":	{
                "sdr":	"mac_sender",
                "rcv":	"mac_receiver",
                "time":	"1234567890",
                "pwd":	"xxx",
                "grp":	0,
                "op":	1,
                "ack":	true,
                "argv":	[{
                        "type":	1,
                        "val":	19
                    }, {
                        "type":	1,
                        "val":	0
                ]}
         }
    */
    class HWExecJsonSerializer implements JsonSerializer<HWJSonIOSpecs> {
        @Override
        public JsonElement serialize(HWJSonIOSpecs jsonSpecs, Type type, JsonSerializationContext context) {
            JsonObject exec = new JsonObject();
            JsonObject execBody = new JsonObject();
            JsonArray argvBody = new JsonArray();

            Exec jsonSpecsExec = jsonSpecs.getExec();
            Argv[] argv = jsonSpecsExec.getArgv();

            execBody.add(EXEC_SDR, context.serialize(jsonSpecsExec.getSender()));
            execBody.add(EXEC_RCV, context.serialize(jsonSpecsExec.getReceiver()));
            execBody.add(EXEC_TIME, context.serialize(jsonSpecsExec.getTime()));
            execBody.add(EXEC_PWD, context.serialize(jsonSpecsExec.getPwd()));
            execBody.add(EXEC_GROUP, context.serialize(jsonSpecsExec.getGroup()));
            execBody.add(EXEC_OP, context.serialize(jsonSpecsExec.getOp()));
            execBody.add(EXEC_ACK, context.serialize(jsonSpecsExec.isAck()));


            for (int i = 0; i < argv.length; i++) {
                JsonObject argvElement = new JsonObject();
                argvElement.add(ARGV_TYPE, context.serialize(argv[i].getType()));
                argvElement.add(ARGV_VAL, context.serialize(argv[i].getValue()));
                argvBody.add(argvElement);
            }

            execBody.add(ARGV, argvBody);
            exec.add(EXEC, execBody);
            return exec;
        }
    }

    /* STAT Example
        {
            "stat":	{
                "sdr":	"00:1E:C0:06:AF:DD",
                "rcv":	"a0:0b:ba:c4:01:9c",
                "time":	"1336032382003",
                "type":	1,
                "subt":	0,
                "argv":	[{
                        "type":	6,
                        "val":	"PICUS2DOT2     \r\n",
                        "type":	6,
                        "val":	"00:1E:C0:06:AF:DD",
                        "type":	6,
                        "val":	"W",
                        "type":	1,
                        "val":	0
                    }]
            }
        }
    */
    class HWStatJsonDeserializer implements JsonDeserializer<HWJSonIOSpecs> {
        @Override
        public HWJSonIOSpecs deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
            return null;
        }
    }
}
