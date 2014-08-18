package android.hawkencompanionapp.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.hawkencompanionapp.R;
import android.hawkencompanionapp.activities.BaseActivity;
import android.hawkencompanionapp.asynctasks.AsyncTaskUpdate;
import android.hawkencompanionapp.tabs.TabsAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Phillip Adam Nash on 14/08/2014.
 */
public class MechGuideFragment extends BaseFragment implements AsyncTaskUpdate,
        ActionBar.TabListener, OnFragmentInflated {
    private ViewPager mViewPager;
    private TabsAdapter mTabsAdapter;

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
        String[] tabs = { "Top Rated", "Games", "Movies" };
        mViewPager = (ViewPager)v.findViewById(R.id.mech_guide_view_pager);
        ActionBar actionBar = getActivity().getActionBar();
        mTabsAdapter = new TabsAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mTabsAdapter);

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        if (actionBar.getTabCount() == 0) {
            // Adding Tabs
            for (String tab_name : tabs) {
                actionBar.addTab(actionBar.newTab().setText(tab_name)
                        .setTabListener(this));
            }
        }
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
