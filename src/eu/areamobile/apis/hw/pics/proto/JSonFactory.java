package eu.areamobile.apis.hw.pics.proto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.areamobile.apis.hw.pics.entity.GenericDevice;

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
    private GenericDevice mGenericDevice;

    public JSonFactory() {
        super();
    }

    public HWJSonIOSpecs parseFromStream(String data, Class<? extends HWJSonIOSpecs> jSonClass) {
        Gson gson = new Gson();
        return gson.fromJson(data, jSonClass);
    }

    public HWJSonIOSpecs parseFromStream(String data) {
        try {
            Gson gson = new Gson();
            Reader r = new InputStreamReader(new ByteArrayInputStream(data.getBytes()));
            HWJSonIOSpecs specs = gson.fromJson(r, HWJSonIOSpecs.class);
            return specs;
        } catch (Exception e) { e.printStackTrace(); return null; }
    }

    public String transfertStream(HWJSonIOSpecs stream, Class<? extends HWJSonIOSpecs> jSonClass) {
        Gson gson = new GsonBuilder().registerTypeAdapter(HWJSonIOSpecs.class, new HWExecJsonSerializer()).create();
        return gson.toJson(stream, jSonClass);
    }

    public String transfertStream(HWJSonIOSpecs stream) {
        Gson gson = new GsonBuilder().registerTypeAdapter(HWJSonIOSpecs.class, new HWExecJsonSerializer()).create();
        return gson.toJson(stream, HWJSonIOSpecs.class);
    }
}
