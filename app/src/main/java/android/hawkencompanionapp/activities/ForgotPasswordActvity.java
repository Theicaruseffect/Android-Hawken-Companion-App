package android.hawkencompanionapp.activities;

import android.content.Intent;
import android.hawkencompanionapp.R;
import android.hawkencompanionapp.asynctasks.AsyncTaskUpdate;
import android.hawkencompanionapp.asynctasks.ForgotPasswordTask;
import android.hawkencompanionapp.models.UserForgottenPassword;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Icarus on 08/08/2014.
 */
public class ForgotPasswordActvity extends BaseActivity implements AsyncTaskUpdate {

    private UserForgottenPassword mUserForgottenPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.forgotten_password_activity;
    }

    public void sendEmailButtonOnTap(View v) {
        final EditText emailEditTxt = (EditText) findViewById(R.id.forgotten_pw_email_edit_txt);
        final String emailEditTxtStr = emailEditTxt.getText().toString();

        mUserForgottenPassword = new UserForgottenPassword(emailEditTxtStr);

        //Not empty and atlease contain an the at symbol
        if (!emailEditTxtStr.isEmpty() && emailEditTxtStr.contains("@")) {
            new ForgotPasswordTask(this).execute(mUserForgottenPassword);
        } else {
            Toast.makeText(this,R.string.invalid_email,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAsyncPostComplete() {
        if (mUserForgottenPassword.accountIsValid()) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            displayUIToast(getString(R.string.password_reset_success_msg));
        } else {
            displayUIToast(getString(R.string.password_reset_unsuccess_msg));
        }
        dismissUILoadingSpinner();
    }

    @Override
    public void onAsyncPreComplete() {
        displayUILoadingSpinner(getString(R.string.spinner_msg_sending_password_reset_email));
    }
}
