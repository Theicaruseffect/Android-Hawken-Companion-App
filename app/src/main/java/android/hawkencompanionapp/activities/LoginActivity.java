package android.hawkencompanionapp.activities;

import android.content.Intent;
import android.hawkencompanionapp.R;
import android.hawkencompanionapp.asynctasks.AsyncTaskUpdate;
import android.hawkencompanionapp.asynctasks.LoginUserTask;
import android.hawkencompanionapp.logger.Logger;
import android.hawkencompanionapp.models.UserLoginSession;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class LoginActivity extends BaseActivity implements AsyncTaskUpdate {

    private UserLoginSession mUserLoginSession;
    public static final String BUNDLE_KEY = "android.hawkencompanionapp.models.UserLoginSession";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.login_activity;
    }

    public void loginButtonOnTap(View v) {
        final EditText editTextEmailAddress = (EditText)
                findViewById(R.id.email_address_edit_text);
        final EditText editTextPassword = (EditText)
                findViewById(R.id.password_edit_text);
        final String emailAddress = editTextEmailAddress.getText().toString();
        final String password = editTextPassword.getText().toString();

        mUserLoginSession = new UserLoginSession(emailAddress,password);
        new LoginUserTask(this).execute(mUserLoginSession);

        if (loginDetailsAreCorrectlyFormatted(emailAddress, password)) {
            displayUIToast("User Valid");
        } else {
            displayUIToast("User Invalid");
        }
    }

    /**
     * This method starts a new activity for the user request a password reset.
     *
     * @param textView the text view associated with the onTap() callback method.
     * */
    public void forgotPasswordOnTap(View textView) {
        Intent i = new Intent(LoginActivity.this,ForgotPasswordActvity.class);
        startActivity(i);
    }

    /**
     * This method validates the user's email address and password, to insure they are
     * either not empty and contain valid data (i.e '@' in the email address).
     *
     * TODO: Use a regex to fully check that the email address being entered is correctly formatted.
     *
     * @return True is the details entered are valid, false otherwise;
     * */
    private boolean loginDetailsAreCorrectlyFormatted(String emailAddress, String password) {
        if (emailAddress.isEmpty() || password.isEmpty()) {
            return false;
        } else if (!emailAddress.contains("@")){ //Should use regex
            return false;
        }
        return true;
    }

    @Override
    public void onAsyncPostComplete() {
        if (mUserLoginSession.isUserValid()) {
            final Intent intent = new Intent(this, UserAccountMainActivity.class);
            Logger.debug(this, mUserLoginSession.getEmailAddress());
            intent.putExtra(BUNDLE_KEY, mUserLoginSession);
            startActivity(intent);
        } else {
            displayUIToast(getString(R.string.login_details_invalid));
        }
        dismissUILoadingSpinner();
    }

    @Override
    public void onAsyncPreComplete() {
        displayUILoadingSpinner(getString(R.string.spinner_msg_logging_in));
    }
}
