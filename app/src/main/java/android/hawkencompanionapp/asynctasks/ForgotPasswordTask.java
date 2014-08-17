package android.hawkencompanionapp.asynctasks;

import android.hawkencompanionapp.logger.Logger;
import android.hawkencompanionapp.models.UserForgottenPassword;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Icarus on 08/08/2014.
 */
public class ForgotPasswordTask extends AsyncTask<UserForgottenPassword, Void, Void> {

    private final String mResetUrl = "https://www.playhawken.com/storm/user/requestPasswordReset.php";
    private final AsyncTaskUpdate mAsyncTaskUpdate;
    private boolean mEmailAddressNotFound;

    public ForgotPasswordTask(AsyncTaskUpdate aSyncTaskUpdate) {
        mAsyncTaskUpdate = aSyncTaskUpdate;
    }

    protected Void doInBackground(UserForgottenPassword... userForgottenPasswords) {
        Logger.debug(this,"inDoBackground");
        sendPasswordResetRequest(userForgottenPasswords[0]);
        return null;
    }

    @Override
    protected void onPreExecute() {
        mAsyncTaskUpdate.onAsyncPreComplete();
    }

    @Override
    protected void onPostExecute(Void v) {
        mAsyncTaskUpdate.onAsyncPostComplete();
    }

    private void sendPasswordResetRequest(UserForgottenPassword userForgottenPasswords) {
        final HttpPost httpPost = new HttpPost(mResetUrl);
        final HttpClient httpClient = new DefaultHttpClient();
        final List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        HttpResponse httpResponse;

        try {
            nameValuePairs.add(new BasicNameValuePair("EmailAddress", userForgottenPasswords.getEmailAddress()));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            httpResponse = httpClient.execute(httpPost);
            final String responseStr = EntityUtils.toString(httpResponse.getEntity());
            if (!responseStr.contains("404")) {
                userForgottenPasswords.setAccountValid(true);
            }
            Logger.debug(this,"Email address not found?: " + String.valueOf(mEmailAddressNotFound));
        } catch (IOException e) {
            Logger.error(this,e.getMessage());
        }
    }
}
