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

import android.hawkencompanionapp.logger.Logger;
import android.hawkencompanionapp.models.UserLoginSession;
import android.hawkencompanionapp.models.UserProfile;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phillip Adam Nash on 11/08/2014.
 */
public class LoadUserDetailsTask extends AsyncTask<UserLoginSession, Void, Void> {
    private String mAccountFormUrl = "https://www.playhawken.com/account";
    private AsyncTaskUpdate mAsyncTaskUpdate;
    private UserProfile mUserProfile;

    public LoadUserDetailsTask(AsyncTaskUpdate asyncTaskUpdate, UserProfile userProfile) {
        mAsyncTaskUpdate = asyncTaskUpdate;
        mUserProfile = userProfile;
    }

    @Override
    protected void onPostExecute(Void v) {
        mAsyncTaskUpdate.onAsyncPostComplete();
    }

    @Override
    protected void onPreExecute() {
        mAsyncTaskUpdate.onAsyncPreComplete();
    }



    protected Void doInBackground(UserLoginSession... userDetails) {
        final UserLoginSession session = userDetails[0];
        getUserDetails(session);
        return null;
    }

    private void getUserDetails(UserLoginSession userLoginSession) {
        final HttpPost httpPost = new HttpPost(mAccountFormUrl);
        final HttpClient httpClient = new DefaultHttpClient();
        final List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        Logger.debug(this,"Getting user's details");
        try {
            httpPost.setHeader("Cookie", userLoginSession.getSessionCookie());
            final HttpResponse httpResponse = httpClient.execute(httpPost);
            final String responseStr = EntityUtils.toString(httpResponse.getEntity());
            mUserProfile.setHtmlData(responseStr);
            //mUserProfile.set
        } catch (IOException e) {
            Logger.error(this,e.getMessage());
        }
    }
}
