package android.hawkencompanionapp.asynctasks;

import android.hawkencompanionapp.models.UserLoginSession;
import android.os.AsyncTask;

/**
 * Created by Icarus on 14/08/2014.
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
