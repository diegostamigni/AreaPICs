package eu.areamobile.apis.hw.pics.entity.json;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;

import com.google.gson.Gson;

/**
 * Created by AreaMobile
 * Date: 23/01/12
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class JSonFactory extends HWJSonIOSpecs implements Serializable {
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
        Gson gson = new Gson();
        Reader r = new InputStreamReader(new ByteArrayInputStream(data.getBytes()));
        HWJSonIOSpecs specs = gson.fromJson(r, HWJSonIOSpecs.class);
        return specs;
//        return gson.fromJson(r, HWJSonIOSpecs.class);
    }

    public String transfertStream(HWJSonIOSpecs stream) {
        Gson gson = new Gson();
        return gson.toJson(stream, HWJSonIOSpecs.class);
    }
}
