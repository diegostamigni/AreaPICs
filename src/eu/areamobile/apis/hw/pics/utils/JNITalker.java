package eu.areamobile.apis.hw.pics.utils;

/**
 * Created by AreaMobile
 * Date: 03/01/12
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class JNITalker {
//    public final static String LIB_NAME = "alg-clx";
//
//    public JNITalker() {}
//
//    /**
//     * From JNI code
//     * @return boolean
//     */
//    public native boolean attaching();

    static {
        System.loadLibrary("alg-clx");
    }

    public native void init (int j, int x);
}
