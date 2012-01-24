LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := alg-clx
LOCAL_SRC_FILES := alg-clx.c
LOCAL_CFLAGS := -Werror -Wall

include $(BUILD_SHARED_LIBRARY)