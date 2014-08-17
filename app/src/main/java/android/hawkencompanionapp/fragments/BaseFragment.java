package android.hawkencompanionapp.fragments;

import android.app.Fragment;
import android.hawkencompanionapp.activities.UserAccountMainActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Icarus on 14/08/2014.
 */
public abstract class BaseFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        final Bundle bundle = getArguments();

        if (bundle != null) {

            final String fragmentTitle =
                    bundle.getString(UserAccountMainActivity.FRAGMENT_TITLE_BUNDLE_KEY);

            if (fragmentTitle != null) {
                getActivity().getActionBar().setTitle(fragmentTitle);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle args) {
        return inflater.inflate(getFragmentLayoutId(), container, false);
    }

    protected abstract int getFragmentLayoutId();
}
