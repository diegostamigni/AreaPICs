#include <jni.h>
#include <stdio.h>

/* Enum on false, true for generating Java Boolean (that's for @gianky, obv):
 * 0: false
 * 1: true
 */
typedef enum {false, true} boolean;

jboolean it_areamobile_apis_hw_areafly_utils_attaching(JNIEnv* env, jobject thiz) {
    return true;
}
