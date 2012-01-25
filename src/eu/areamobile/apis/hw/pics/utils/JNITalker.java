package eu.areamobile.apis.hw.pics.utils;


/**
 * Created by AreaMobile
 * Date: 03/01/12
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class JNITalker {
    public final static String LIB_NAME = "alg-clx";

    public JNITalker() {}
    
    static {
        System.loadLibrary(LIB_NAME);
    }
    
    public native boolean attaching();
}
