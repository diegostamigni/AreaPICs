/**
 * Created by AreaMobile
 * Date: 03/01/12
 *
 * This will contains useful library and functions.
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

#ifndef ALG_CLX_H
#define ALG_CLX_H
#include <jni.h>

/* Enum on false, true for generating Java Boolean (that's for @gianky, obv):
 * 0: false
 * 1: true
 */
typedef enum {false, true} boolean;

jboolean it_areamobile_apis_hw_areafly_utils_attaching(JNIEnv* env, jobject thiz);

#endif /* End */
