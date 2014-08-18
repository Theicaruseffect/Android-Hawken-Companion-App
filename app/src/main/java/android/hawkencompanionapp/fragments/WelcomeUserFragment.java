package android.hawkencompanionapp.fragments;

import android.hawkencompanionapp.R;
import android.os.Bundle;

/**
 * Created by Phillip Adam Nash on 16/08/2014.
 *
 * This fragment simply displays a welcome screen (default screen upon logging in) to the user.
 */
public class WelcomeUserFragment extends BaseFragment {
    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.welcome_user_fragment;
    }
}
