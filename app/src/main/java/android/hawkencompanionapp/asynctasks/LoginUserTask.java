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
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phillip Adam Nash on 10/08/2014.
 */
public class LoginUserTask extends AsyncTask<UserLoginSession, Void, Void> {
    private final String mLoginUrl = "https://www.playhawken.com/storm/user/loginUser.php";
    private AsyncTaskUpdate mAsyncTaskUpdate;

    public LoginUserTask(AsyncTaskUpdate asyncTaskUpdate) {
        mAsyncTaskUpdate = asyncTaskUpdate;
    }

    protected Void doInBackground(UserLoginSession... userLoginSession) {
        getUserSession(userLoginSession[0]);
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

    private void getUserSession(UserLoginSession userLoginSession) {
        final HttpPost httpPost = new HttpPost(mLoginUrl);
        final List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        final DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse;

        try {
            Logger.debug(this, "Logging in user: " + userLoginSession.getEmailAddress());
            nameValuePairs.add(new BasicNameValuePair("EmailAddress", userLoginSession.getEmailAddress()));
            nameValuePairs.add(new BasicNameValuePair("Password", userLoginSession.getUserPassword()));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            httpResponse = httpClient.execute(httpPost);

            if (isUserLoginValid(httpResponse)) {
                final List<Cookie> cookies = httpClient.getCookieStore().getCookies();
                Logger.debug(this,cookies.toString());
                userLoginSession.setUserSession(cookies);
                userLoginSession.setUserIsValid();
            }
        } catch (IOException e) {
            Logger.error(this, e.getMessage());
        }
    }

    /**
     * This method looks at what the server returns (a JSON message) if the credentials entered by the user are valid.
     *
     * @param httpResponse The response from the server.
     * @return True if the credentials entered by the user are valid, false otherwise.
     * */
    private boolean isUserLoginValid(HttpResponse httpResponse) throws IOException {
        final String responseStr = EntityUtils.toString(httpResponse.getEntity());
        //Invalid password or invalid username.
        if (responseStr.contains("401") || responseStr.contains("404")) {
            return false;
        }
        return true;
    }
}
