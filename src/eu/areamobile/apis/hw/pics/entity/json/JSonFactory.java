package eu.areamobile.apis.hw.pics.entity.json;

import java.io.Serializable;

import com.google.gson.Gson;

/**
 * Created by AreaMobile
 * Date: 23/01/12
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class JSonFactory extends HWJSonIOSpecs implements Serializable {
    /**
	 * @uml.property  name="input"
	 */
    public JSonFactory() {
        super();
    }

    @Override
    public HWJSonIOSpecs parseFromStream(String data, Class<HWJSonIOSpecs> jSonClass) {
        Gson gson = new Gson();
        return gson.fromJson(data, jSonClass);
    }

    @Override
    public String transfertStream(HWJSonIOSpecs stream, Class<HWJSonIOSpecs> jSonClass) {
        Gson gson = new Gson();
        return gson.toJson(stream, jSonClass);
    }

    @Override
    public HWJSonIOSpecs parseFromStream(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, HWJSonIOSpecs.class);
    }

    @Override
    public String transfertStream(HWJSonIOSpecs stream) {
        Gson gson = new Gson();
        return gson.toJson(stream, HWJSonIOSpecs.class);
    }
}
