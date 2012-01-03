package it.areamobile.apis.hw.areafly.utils;

/**
 * Created by AreaMobile
 * Date: 03/01/12
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class JNITalker {
    public final static String LIB_NAME = "alg-clx";

    public JNITalker() {}

    /**
     * From JNI code
     * @return String
     */
    public native String attaching();

    static {
        System.loadLibrary(LIB_NAME);
    }
}
