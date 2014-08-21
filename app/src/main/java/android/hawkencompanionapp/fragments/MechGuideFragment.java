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

import android.graphics.Color;
import android.hawkencompanionapp.R;
import android.hawkencompanionapp.asynctasks.AsyncTaskUpdate;
import android.hawkencompanionapp.logger.Logger;
import android.hawkencompanionapp.tabs.HeavyMechsGuideTab;
import android.hawkencompanionapp.tabs.LightMechsGuideTab;
import android.hawkencompanionapp.tabs.MediumMechsGuideTab;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by Phillip Adam Nash on 14/08/2014.
 */
public class MechGuideFragment extends BaseFragment implements AsyncTaskUpdate, OnFragmentInflated, TabHost.OnTabChangeListener,
        View.OnTouchListener {
    private FragmentTabHost mTabHost;
    private final String TAB_SPEC_MECH_LIGHT = "light";
    private final String TAB_SPEC_MECH_MEDIUM = "medium";
    private final String TAB_SPEC_MECH_HEAVY = "heavy";
    private static final float DP_X_SWIPE_EVENT_OFFSET = 20;
    private float mPrevXdp;
    private float mSwipeXPxOffset;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setOnFragmentInflated(this);
        mSwipeXPxOffset = dpToPx(DP_X_SWIPE_EVENT_OFFSET);
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final float xPos = event.getX();

        if (xPos > mSwipeXPxOffset) { //Prevent the nav drawer from registering the event
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                mPrevXdp = xPos;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                if (mPrevXdp < xPos) { //Right swipe
                    switchTab("Right");
                } else { //Left swipe
                    switchTab("Left");
                }
            }
            return true;
        }
        return false;
    }

    private void switchTab(String direction) {
        final int curTab = mTabHost.getCurrentTab();
        final int tabCount = mTabHost.getTabWidget().getChildCount();

        if (direction.equals("Right") && curTab + 1 < tabCount) {
            mTabHost.setCurrentTab(curTab + 1);
        } else if (direction.equals("Left") && curTab - 1 >= 0) {
            mTabHost.setCurrentTab(curTab - 1);
        }
    }

    private void createFragmentTabs(View v) {
        mTabHost = (FragmentTabHost) v.findViewById(R.id.tabhost);
        mTabHost.setOnTouchListener(this);

        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.tabFrameLayout);
        mTabHost.addTab(
                mTabHost.newTabSpec(TAB_SPEC_MECH_LIGHT).setIndicator(getString(R.string.mech_tab_light),
                        getResources().getDrawable(android.R.drawable.star_on)),
                LightMechsGuideTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec(TAB_SPEC_MECH_MEDIUM).setIndicator(getString(R.string.mech_tab_medium),
                        getResources().getDrawable(android.R.drawable.star_on)),
                MediumMechsGuideTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec(TAB_SPEC_MECH_HEAVY).setIndicator(getString(R.string.mech_tab_heavy),
                        getResources().getDrawable(android.R.drawable.star_on)),
                HeavyMechsGuideTab.class, null);
        mTabHost.setOnTabChangedListener(this);

        //Set the text with each tab to white.
        for(int i=0; i < mTabHost.getTabWidget().getChildCount(); i++)
        {
            TextView tv = (TextView)
                    mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.WHITE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }


    @Override
    public void onTabChanged(String id) {
        String selected = "";
    }

    @Override
    public void onDetach() {
        super.onDetach();

        try {
            final Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            Logger.error(this,e.getMessage());
        } catch (IllegalAccessException e) {
            Logger.error(this,e.getMessage());
        }
    }
}
