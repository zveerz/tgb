#include <jni.h>
#include <stdio.h>
#include <string.h>
#include "ping.h"

JNIEXPORT jstring JNICALL Java_org_makeriga_tgbot_features_ping_PingFeature_ping (JNIEnv *env, jobject self, jstring value) {
	const char * valueC = (*env)->GetStringUTFChars(env, value, NULL);
	if (valueC == NULL)
		return NULL;
	if (strcmp(valueC, "ping") == 0)
		return (*env)->NewStringUTF(env, "pong");
	return (*env)->NewStringUTF(env, "nepong");
}
