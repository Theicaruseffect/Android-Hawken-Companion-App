package android.hawkencompanionapp.activities;
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
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.hawkencompanionapp.R;
import android.hawkencompanionapp.adapters.NavBarExpandableListAdapter;
import android.hawkencompanionapp.fragments.BaseFragment;
import android.hawkencompanionapp.fragments.MechGuideFragment;
import android.hawkencompanionapp.fragments.UserAccountFragment;
import android.hawkencompanionapp.fragments.WelcomeUserFragment;
import android.hawkencompanionapp.models.NavBarCategory;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Phillip Adam Nash on 11/08/2014.
 *
 * This activity is shown to the user when they have successfully logging into their account.
 * This is also the main activity that handles and switches between the various UI fragments
 * and performs most of the functionality within the app.
 */
public class UserAccountMainActivity extends BaseActivity implements OnChildClickListener {

    private Bundle mUserAccountBundle;
    //This will tie the navigation bar (selectable) items with actual fragments.
    private final Map<String,BaseFragment> mFragmentMap = new HashMap<String, BaseFragment>();
    private List<NavBarCategory> mNavBarCategoryList;
    public static final String FRAGMENT_TITLE_BUNDLE_KEY = "FragmentTitleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserAccountBundle = getIntent().getExtras();
        mNavBarCategoryList = getCategoriesWithItems();
        displayWelcomeFragment();
        populateNavBarFragmentMap();
        setupNavDrawer();
    }

    private void displayWelcomeFragment() {
        //Default fragment, upon logging in
        final Bundle b = new Bundle();
        b.putString(FRAGMENT_TITLE_BUNDLE_KEY, getString(R.string.welcome_fragment_title));
        swapCurrentFragment(new WelcomeUserFragment(),b);
    }

    private void populateNavBarFragmentMap() {
        final BaseFragment[] fragmentsArr = {new UserAccountFragment(), new MechGuideFragment()};
        final ArrayList<String> items = new ArrayList<String>();

        for (int j = 0; j < mNavBarCategoryList.size(); j++) {
            final NavBarCategory category = mNavBarCategoryList.get(j);
            final int len = category.getCategoryItemList().size();

            for (int k = 0; k < len; k++) {
                final String item = category.getCategoryItemList().get(k);
                items.add(item);
            }
        }

        for (int i = 0; i < fragmentsArr.length; i++) {
            mFragmentMap.put(items.get(i), fragmentsArr[i]);
        }
    }

    private void swapCurrentFragment(Fragment currentFragment, Bundle bundle) {
        final FragmentManager fragmentManager = getFragmentManager();

        if (bundle != null && currentFragment.getArguments() == null) {
            currentFragment.setArguments(bundle);
        }

        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_frame, currentFragment);
        transaction.commit();
    }

    private List<NavBarCategory> getCategoriesWithItems() {
        final String[] categoryItemsArr = getResources().getStringArray(R.array.nav_drawer_categories_and_items);
        final int len = categoryItemsArr.length;
        final List<NavBarCategory> navBarCategoryList = new ArrayList<NavBarCategory>();

        for (int j = 0; j < len; j++) {
            final String arrStr = categoryItemsArr[j];

            if (arrStr.contains("Category: ")) {
                final NavBarCategory navCat = new NavBarCategory(arrStr.split(" ",2)[1]);

                for (int k = j + 1; k < len
                        && !categoryItemsArr[k].contains("Category: "); k++) {
                    navCat.addCategoryItem(categoryItemsArr[k]);
                }

                navBarCategoryList.add(navCat);
            }
        }

        return navBarCategoryList;
    }

    private void setupNavDrawer() {
        final ExpandableListView lv = (ExpandableListView) findViewById(R.id.nav_drawer_expandable_list_view);
        lv.setAdapter(new NavBarExpandableListAdapter(this,mNavBarCategoryList));
        lv.setOnChildClickListener(this);
    }

    @Override
    public boolean onChildClick (ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        onNavDrawerItemClick(v);
        return true;
    }

    private void onNavDrawerItemClick(View view) {
        final TextView navItem = (TextView) view;
        final String navItemStr = navItem.getText().toString();
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer_layout);
        final Fragment selectedFragment = mFragmentMap.get(navItemStr);

        if (selectedFragment == null) {
            displayUIToast("Need to implement");
        } else if (selectedFragment instanceof UserAccountFragment) {
            //Only pass the account details to fragments that actually need it
            mUserAccountBundle.putString(FRAGMENT_TITLE_BUNDLE_KEY, navItemStr);
            swapCurrentFragment(selectedFragment, mUserAccountBundle);
        } else {
            final Bundle b = new Bundle();
            b.putString(FRAGMENT_TITLE_BUNDLE_KEY,navItemStr);
            swapCurrentFragment(selectedFragment, b);
        }

        //Close the navigation drawer
        drawerLayout.closeDrawers();
    }


    @Override
    public int getLayoutResourceId() {
        return R.layout.user_account_main_activity;
    }
}
