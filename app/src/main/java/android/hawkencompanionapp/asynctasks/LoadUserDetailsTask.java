package android.hawkencompanionapp.asynctasks;

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
 * Created by Icarus on 11/08/2014.
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
