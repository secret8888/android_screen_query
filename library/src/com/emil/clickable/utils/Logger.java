package com.emil.clickable.utils;

import android.util.Log;

/**
 * Created by yuym on 13-12-10.
 */
public class Logger {
    private static final boolean SHOW_LOG = true;

    // 大于此等级才会显示
    private static final int LOG_ENABLE_LEVEL = 0;

    private static final int LOG_LEVEL_VERBOSE = 0;

    private static final int LOG_LEVEL_DEBUG = 1;

    private static final int LOG_LEVEL_ERROR = 2;

    private static final int LOG_LEVEL_EXCEPTION = 3;

    public static boolean v(Object clazz, String msg) {
        if (!check(LOG_LEVEL_VERBOSE)) {
            return false;
        }
        log(clazz, msg, LOG_LEVEL_VERBOSE);
        return true;
    }

    public static boolean d(Object clazz, String msg) {
        if (!check(LOG_LEVEL_DEBUG)) {
            return false;
        }
        log(clazz, msg, LOG_LEVEL_DEBUG);
        return true;
    }

    public static boolean e(Object clazz, String msg) {
        if (!check(LOG_LEVEL_ERROR)) {
            return false;
        }
        log(clazz, msg, LOG_LEVEL_ERROR);
        return true;
    }

    private static boolean check(int level) {
        if (!SHOW_LOG) {
            return false;
        }
        if (LOG_ENABLE_LEVEL > level) {
            return false;
        }
        return true;
    }

    private static void log(Object clazz, String msg, int level) {
        String clazzName = clazz.getClass().getName();
        int idx = clazzName.lastIndexOf(".");
        if (idx != -1) {
            clazzName = clazzName.substring(idx + 1);
        }
        switch (level) {
            case LOG_LEVEL_VERBOSE: {
                Log.v(clazzName, msg);
                break;
            }
            case LOG_LEVEL_DEBUG: {
                Log.d(clazzName, msg);
                break;
            }
            case LOG_LEVEL_ERROR: {
                Log.w(clazzName, msg);
                break;
            }
            case LOG_LEVEL_EXCEPTION: {
                Log.e(clazzName, msg);
                break;
            }
            default: {
                Log.v(clazzName, msg);
                break;
            }
        }
    }
}
