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

import android.app.ProgressDialog;
import android.content.Intent;
import android.hawkencompanionapp.R;
import android.hawkencompanionapp.logger.Logger;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Phillip Adam Nash on 08/08/2014.
 *
 * A base class for all of the activities use in the application.
 * Common functionalities between all of the activities should be
 * added here.
 */
public abstract class BaseActivity extends FragmentActivity {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
    }

    public void displayUILoadingSpinner(String msg) {
        mProgressDialog = new ProgressDialog(this,ProgressDialog.THEME_HOLO_DARK);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setTitle(getString(R.string.spinner_title_loading));
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    public void dismissUILoadingSpinner() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        } else {
            Logger.error(this,"Unable to dismiss a loading dialog that doesn't exist");
        }
    }

    public void displayUIToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        final MenuItem menuItemSignOut = menu.findItem(R.id.action_sign_out);
        final MenuItem menuItemOfflineMode = menu.findItem(R.id.action_offline_mode);

        //Disable the About option if the current activity is the About activity
        if (this instanceof AboutActivity) {
            final MenuItem about = menu.findItem(R.id.action_about);
            final int len = menu.size();
            //Disable all menu items when the about activity is being shown.
            for (int i = 0; i < len; i++) {
                final MenuItem item = menu.getItem(i);
                item.setVisible(false);
            }
        } else if (this instanceof ForgotPasswordActvity) { //Forgot Password activity is shown
            menuItemSignOut.setVisible(false);
            menuItemOfflineMode.setVisible(false);
        } else if (this instanceof LoginActivity) { //Login activity is shown
            menuItemSignOut.setVisible(false);
        } else if (this instanceof UserAccountMainActivity) {
            menuItemOfflineMode.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        boolean actionHandled = false;

        switch (id) {
            case R.id.action_about:
                actionHandled = true;
                startAboutActivity();
            break;
            case R.id.action_sign_out:
                //Handle user signing out
                displayUIToast("Signing user out.");
                actionHandled = true;
            break;
            case R.id.action_offline_mode:
                //Handle user signing out
                displayUIToast("Enabling offline mode.");
                actionHandled = true;
                break;
        }

        if (actionHandled) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void startAboutActivity() {
        final Intent i = new Intent(this,AboutActivity.class);
        startActivity(i);
    }

    /**
     * Sub-classes must return their resource id so the content can be added.
     *
     * @return A resource id pointing to a valid XML layout for an activity
     * */
    abstract public int getLayoutResourceId();
}
