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
    private AreaFlyIOStream areaFlyIOStream;
    private Gson gson;

    public abstract static class AreaFlyIOStream extends HWJSonIOSpecs {}
}
