LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := alg-clx
LOCAL_SRC_FILES := alg-clx.c

include $(BUILD_SHARED_LIBRARY)