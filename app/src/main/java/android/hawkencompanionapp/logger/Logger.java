package android.hawkencompanionapp.logger;
/**
 *   Copyright (c) 2014 "Hawken Companion App"
 *
 *   This file is part of Hawken Companion App.
 *
 *   Hawken Companion App is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import android.util.Log;

/**
 * Created by Phillip Adam Nash on 08/08/2014.
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
