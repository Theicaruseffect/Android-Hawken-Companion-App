package android.hawkencompanionapp.logger;

import android.util.Log;

/**
 * Created by Icarus on 08/08/2014.
 *
 * A simple helper class for logging messages (Log.d). This may be useful if we to send these messages
 * via email to a developer.
 */
public class Logger {

    //Use this to suppress logging
    private static final boolean LOG_ENABLED = true;

    public static void debug(Object klass, String msg) {
        if (LOG_ENABLED)
            Log.d(klass.getClass().getName(),msg);
    }

    public static void error(Object klass, String msg) {
        if (LOG_ENABLED)
            Log.e(klass.getClass().getName(),msg);
    }

    public static void info(Object klass, String msg) {
        if (LOG_ENABLED)
            Log.i(klass.getClass().getName(),msg);
    }
}
