package eu.areamobile.apis.hw.pics.entity.areafly.json;

import com.google.gson.Gson;
import eu.areamobile.apis.hw.pics.entity.json.HWJSonIOSpecs;
import eu.areamobile.apis.hw.pics.entity.json.JSonFactory;

/**
 * Created by AreaMobile
 * Date: 23/01/12
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class AreaFlyJSonFactory extends JSonFactory {
    //JSon Value
    /**
	 * @uml.property  name="areaFlyIOStream"
	 * @uml.associationEnd  readOnly="true"
	 */
    private AreaFlyIOStream areaFlyIOStream;
    /**
	 * @uml.property  name="gson"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    private Gson gson;

    public abstract static class AreaFlyIOStream extends HWJSonIOSpecs {
    }

    public AreaFlyJSonFactory() {
        super();
        gson = new Gson();
    }

    @Override
    public AreaFlyIOStream parseFromStream(String data) {
        super.parseFromStream(data);
        return gson.fromJson(data, AreaFlyIOStream.class);
    }

    @Override
    public String transfertStream(HWJSonIOSpecs stream) {
        super.transfertStream(stream);
        return gson.toJson(stream, AreaFlyIOStream.class);

    }

    public Gson getGSonObject() {
        return gson;
    }
}
