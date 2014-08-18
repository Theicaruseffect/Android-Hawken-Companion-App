package android.hawkencompanionapp.fragments;
/**
 *   Copyright (c) 2014 "Hawken Companion App"
 *
 *   This file is part of Hawken Companion App.
 *
 *   Hawken Companion App is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.hawkencompanionapp.activities.UserAccountMainActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Phillip Adam Nash on 14/08/2014.
 */
public abstract class BaseFragment extends android.support.v4.app.Fragment {

    private OnFragmentInflated mOnFragmentInflated;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        final Bundle bundle = getArguments();
        final ActionBar actionBar = getActivity().getActionBar();

        if (bundle != null) {
            final String fragmentTitle =
                    bundle.getString(UserAccountMainActivity.FRAGMENT_TITLE_BUNDLE_KEY);
            if (fragmentTitle != null) {
                actionBar.setTitle(fragmentTitle);
            }
        }

        if (!(this instanceof MechGuideFragment)) {
            if (actionBar != null) {
                actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle args) {
        final View view = inflater.inflate(getFragmentLayoutId(), container, false);

        if (mOnFragmentInflated != null) {
            mOnFragmentInflated.onFragmentInflated(view);
        }

        return view;
    }

    protected void setOnFragmentInflated(OnFragmentInflated onFragmentInflated) {
        this.mOnFragmentInflated = onFragmentInflated;
    }

    protected abstract int getFragmentLayoutId();
}
