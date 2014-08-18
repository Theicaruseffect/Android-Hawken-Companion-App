package android.hawkencompanionapp.asynctasks;
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

import android.hawkencompanionapp.models.UserLoginSession;
import android.os.AsyncTask;

/**
 * Created by Phillip Adam Nash on 14/08/2014.
 */
public class LogoutUserTask extends AsyncTask<UserLoginSession, Void, Void> {
    private final String mLoginUrl = "https://www.playhawken.com/storm/user/loginUser.php";
    private AsyncTaskUpdate mAsyncTaskUpdate;

    public LogoutUserTask(AsyncTaskUpdate asyncTaskUpdate) {
        mAsyncTaskUpdate = asyncTaskUpdate;
    }

    @Override
    protected Void doInBackground(UserLoginSession... userLoginSession) {
        logoutUser(userLoginSession[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        mAsyncTaskUpdate.onAsyncPostComplete();
    }

    @Override
    protected void onPreExecute() {
        mAsyncTaskUpdate.onAsyncPreComplete();
    }

    private void logoutUser(UserLoginSession userLoginSession) {

    }
}
