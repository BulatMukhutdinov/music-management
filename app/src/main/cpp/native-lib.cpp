#include <jni.h>
#include <string>
#include <iostream>

extern "C" JNIEXPORT jstring JNICALL
Java_tat_mukhutdinov_musicmanagement_infrastructure_common_di_CommonInjectionModule_getNativeKey(JNIEnv *env, jobject) {
    return env->NewStringUTF("NWRkMzAyZWIzNGRjNzQ0OTMxOGJmMDgzNzkyNzQyZDk=\n");
}