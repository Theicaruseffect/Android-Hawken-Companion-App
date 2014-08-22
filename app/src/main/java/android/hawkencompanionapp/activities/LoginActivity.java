/*
 * Copyright (c) 2014 "Hawken Companion App"
 *
 * This file is part of Hawken Companion App.
 *
 * Hawken Companion App is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses.
 */

package android.hawkencompanionapp.activities;

import android.content.Intent;
import android.hawkencompanionapp.R;
import android.hawkencompanionapp.asynctasks.AsyncTaskUpdate;
import android.hawkencompanionapp.asynctasks.LoginUserTask;
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

        if (loginDetailsAreCorrectlyFormatted(emailAddress, password)) {
            new LoginUserTask(this).execute(mUserLoginSession);
            displayUIToast("Online mode activated.");
        } else {
            displayUIToast("Offline mode activated.");
            //startUserAccountActivity();
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

    private void startUserAccountActivity() {
        final Intent intent = new Intent(this, UserAccountMainActivity.class);
        //Logger.debug(this, mUserLoginSession.getEmailAddress());
        intent.putExtra(BUNDLE_KEY, mUserLoginSession);
        startActivity(intent);
    }

    @Override
    public void onAsyncPostComplete() {
        if (mUserLoginSession.isUserValid()) {
            startUserAccountActivity();
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
