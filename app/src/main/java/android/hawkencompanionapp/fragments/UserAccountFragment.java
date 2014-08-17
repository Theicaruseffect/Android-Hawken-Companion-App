package android.hawkencompanionapp.fragments;

import android.hawkencompanionapp.R;
import android.hawkencompanionapp.activities.LoginActivity;
import android.hawkencompanionapp.activities.UserAccountMainActivity;
import android.hawkencompanionapp.asynctasks.AsyncTaskUpdate;
import android.hawkencompanionapp.asynctasks.LoadUserDetailsTask;
import android.hawkencompanionapp.logger.Logger;
import android.hawkencompanionapp.models.UserLoginSession;
import android.hawkencompanionapp.models.UserProfile;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Icarus on 14/08/2014.
 */
public class UserAccountFragment extends BaseFragment implements AsyncTaskUpdate {

    private UserProfile mUserProfile;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        loadUserDetails();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.user_account_fragment;
    }

    private void loadUserDetails() {
        final UserLoginSession mUserLoginSession = getArguments().getParcelable(LoginActivity.BUNDLE_KEY);
        Logger.debug(this,mUserLoginSession.getPhpAccessId());
        mUserProfile = new UserProfile();
        new LoadUserDetailsTask(this,mUserProfile).execute(mUserLoginSession);
    }

    @Override
    public void onAsyncPostComplete() {
        final UserAccountMainActivity mainActivity = (UserAccountMainActivity) getActivity();
        setUIAccountInfo();
        setUIPersonalInfo();
        setUIRegionalInfo();
        mainActivity.dismissUILoadingSpinner();
    }

    @Override
    public void onAsyncPreComplete() {
        final UserAccountMainActivity mainActivity = (UserAccountMainActivity) getActivity();
        mainActivity.displayUILoadingSpinner(getString(R.string.spinner_msg_obtaining_info));
    }

    private void setUIAccountInfo() {
        final TextView callsignTxtView = (TextView)
                getView().findViewById(R.id.callsign_txt_view);
        final TextView emailEditTxtView = (TextView)
                getView().findViewById(R.id.email_addr_edit_txt);
        final TextView meteorCreditsTxtView = (TextView)
                getView().findViewById(R.id.meteor_credits_txt_view);
        final TextView hawkenCreditsTxtView = (TextView)
                getView().findViewById(R.id.hawken_credits_txt_view);
        callsignTxtView.setText(mUserProfile.getCallsign());
        emailEditTxtView.setText(mUserProfile.getEmailAddress());
        meteorCreditsTxtView.setText(mUserProfile.getMeteorCredits());
        hawkenCreditsTxtView.setText(mUserProfile.getHawkenCredits());
    }

    private void setUIPersonalInfo() {
        final TextView firstNameTxtView = (TextView)
                getView().findViewById(R.id.first_name_txt_view);
        final TextView lastNameTxtView = (TextView)
                getView().findViewById(R.id.last_name_txt_view);
        final TextView genderTxtView = (TextView)
                getView().findViewById(R.id.gender_txt_view);
        final TextView dateOfBirthTxtView = (TextView)
                getView().findViewById(R.id.data_of_birth_txt_view);
        final int len = mUserProfile.getDateOfBirth().length;
        String dateOfBirth = "";

        for (int i = 0; i < len; i++) {
            dateOfBirth += mUserProfile.getDateOfBirth()[i];

            if (i < 2) {
                dateOfBirth += "/";
            }
        }

        firstNameTxtView.setText(mUserProfile.getFirstName());
        lastNameTxtView.setText(mUserProfile.getLastName());
        genderTxtView.setText(mUserProfile.getGender());
        dateOfBirthTxtView.setText(dateOfBirth);
    }

    private void setUIRegionalInfo() {
        final TextView countryTxtView = (TextView)
                getView().findViewById(R.id.country_txt_view);
        final TextView languageTxtView = (TextView)
                getView().findViewById(R.id.language_txt_view);
        final TextView postalCodeTxtView = (TextView)
                getView().findViewById(R.id.postal_code_txt_view);

        countryTxtView.setText(mUserProfile.getCountry());
        languageTxtView.setText(mUserProfile.getLanguage());
        postalCodeTxtView.setText(mUserProfile.getPostalCode());
    }
}
