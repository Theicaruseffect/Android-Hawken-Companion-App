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

import android.app.Fragment;
import android.hawkencompanionapp.activities.UserAccountMainActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Phillip Adam Nash on 14/08/2014.
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
