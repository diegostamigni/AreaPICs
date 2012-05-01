package eu.areamobile.apis.hw.pics.entity.json;

import com.google.gson.Gson;
import eu.areamobile.apis.hw.pics.entity.Common;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;

/**
 * Created by AreaMobile
 * Date: 23/01/12
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class JSonFactory extends HWJSonIOSpecs implements Serializable {
    private Common mCommon;

    public JSonFactory() {
        super();
    }

    public HWJSonIOSpecs parseFromStream(String data, Class<HWJSonIOSpecs> jSonClass) {
        Gson gson = new Gson();
        return gson.fromJson(data, jSonClass);
    }

    public String transfertStream(HWJSonIOSpecs stream, Class<HWJSonIOSpecs> jSonClass) {
        Gson gson = new Gson();
        return gson.toJson(stream, jSonClass);
    }

    public HWJSonIOSpecs parseFromStream(String data) {
        try {
            Gson gson = new Gson();
            Reader r = new InputStreamReader(new ByteArrayInputStream(data.getBytes()));
            HWJSonIOSpecs specs = gson.fromJson(r, HWJSonIOSpecs.class);
            return specs;
        } catch (Exception e) { e.printStackTrace(); return null; }
    }

    public String transfertStream(HWJSonIOSpecs stream) {
        Gson gson = new Gson();
        return gson.toJson(stream, HWJSonIOSpecs.class);
    }
}
