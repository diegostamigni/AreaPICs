package eu.areamobile.apis.hw.pics.entity.json;

import com.google.gson.Gson;

import java.io.Serializable;

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
    private String input;

    public JSonFactory() {
        super();
    }

    public JSonFactory(String toParse) {
        super();
        this.input = toParse;
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
