package com.pagination.utils;

import android.util.Log;

import com.pagination.BuildConfig;

/**

 * Created by ${Rajkumar} on ${9/5/16.}.
 */
public class Logger {

    private static Logger instance;

    private String TAG = "xFactory";

    private boolean logVisible = BuildConfig.LOGGER_ENABLE;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
            return instance;
        }
        return instance;
    }

    /**
     * log.i
     *
     * @param msg the msg
     */
    public void i(String msg) {
        if (logVisible) {
            String message = createMessage(msg);
            Log.i(TAG, message);
        }
    }

    /**
     * log.v
     *
     * @param msg the msg
     */
    public void v(String msg) {
        if (logVisible) {
            String message = createMessage(msg);
            Log.v(TAG, message);
        }
    }

    /**
     * log.d
     *
     * @param msg the msg
     */
    public void d(String msg) {
        if (logVisible) {
            String message = createMessage(msg);
            Log.d(TAG, message);
        }
    }

    /**
     * log.e
     *
     * @param msg the msg
     */
    public void e(String msg) {
        if (logVisible) {
            String message = createMessage(msg);
            Log.e(TAG, message);
        }
    }

    private String createMessage(String msg) {
        String functionName = getFunctionName();
        String message = (functionName == null ? msg : (functionName + " - " + msg));
        return message;
    }

    private String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();


        if (sts == null) {
            return null;
        }

        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }

            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }

            if (st.getClassName().equals(this.getClass().getName())) {
                continue;
            }

            return "[" + Thread.currentThread().getName() + "(" + Thread.currentThread().getId() + "): " + st.getFileName() + ":" + st.getLineNumber() + "]";
        }

        return null;
    }

    /**
     * log.error
     *
     * @param e the e
     */
    public void error(Exception e) {
        if (logVisible) {
            StringBuffer sb = new StringBuffer();
            String name = getFunctionName();
            StackTraceElement[] sts = e.getStackTrace();

            if (name != null) {
                sb.append(name + " - " + e + "\r\n");
            } else {
                sb.append(e + "\r\n");
            }
            if (sts != null && sts.length > 0) {
                for (StackTraceElement st : sts) {
                    if (st != null) {
                        sb.append("[ " + st.getFileName() + ":" + st.getLineNumber() + " ]\r\n");
                    }
                }
            }

            Log.e(TAG, sb.toString());
        }
    }

}