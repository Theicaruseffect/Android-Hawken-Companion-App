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

package android.hawkencompanionapp.fragments;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.hawkencompanionapp.R;
import android.hawkencompanionapp.asynctasks.AsyncTaskUpdate;
import android.hawkencompanionapp.tabs.HeavyMechsGuideTab;
import android.hawkencompanionapp.tabs.LightMechsGuideTab;
import android.hawkencompanionapp.tabs.MediumMechsGuideTab;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.View;

/**
 * Created by Phillip Adam Nash on 14/08/2014.
 */
public class MechGuideFragment extends BaseFragment implements AsyncTaskUpdate,
        ActionBar.TabListener, OnFragmentInflated {
    private FragmentTabHost mTabHost;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setOnFragmentInflated(this);
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

    @Override
    public void onFragmentInflated(View v) {
        createFragmentTabs(v);
    }

    private void createFragmentTabs(View v) {
        mTabHost = (FragmentTabHost) v.findViewById(R.id.tabhost);
        mTabHost.setup(getActivity(),getChildFragmentManager(),R.id.tabFrameLayout);
        mTabHost.addTab(
                mTabHost.newTabSpec("light").setIndicator("Light",
                        getResources().getDrawable(android.R.drawable.star_on)),
                LightMechsGuideTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("medium").setIndicator("Medium",
                        getResources().getDrawable(android.R.drawable.star_on)),
                MediumMechsGuideTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("heavy").setIndicator("Heavy",
                        getResources().getDrawable(android.R.drawable.star_on)),
                HeavyMechsGuideTab.class, null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}
