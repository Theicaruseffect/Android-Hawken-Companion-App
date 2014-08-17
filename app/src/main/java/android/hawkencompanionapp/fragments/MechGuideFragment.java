package android.hawkencompanionapp.fragments;

import android.hawkencompanionapp.R;
import android.hawkencompanionapp.asynctasks.AsyncTaskUpdate;
import android.os.Bundle;

/**
 * Created by Icarus on 14/08/2014.
 */
public class MechGuideFragment extends BaseFragment implements AsyncTaskUpdate {

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.mech_guide_fragment;
    }

    @Override
    public void onAsyncPostComplete() {

    }

    @Override
    public void onAsyncPreComplete() {

    }
}
