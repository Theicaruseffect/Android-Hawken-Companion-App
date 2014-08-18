package android.hawkencompanionapp.adapters;
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

import android.content.Context;
import android.database.DataSetObserver;
import android.hawkencompanionapp.R;
import android.hawkencompanionapp.models.NavBarCategory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Phillip Adam Nash on 14/08/2014.
 */
public final class NavBarExpandableListAdapter implements ExpandableListAdapter {
    private final Context mContext;
    private final List<NavBarCategory> mNavBarCategoryList;
    private final LayoutInflater mLayoutInflator;

    static class ViewHolder {
        TextView groupTxtView;
        TextView groupItemTxtView;
    }


    public NavBarExpandableListAdapter(Context context, List<NavBarCategory> navBarCategoryList) {
        mContext = context;
        mNavBarCategoryList = navBarCategoryList;
        mLayoutInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public boolean areAllItemsEnabled() {
        return true;
    }

    public String getChild(int groupPosition, int childPosition) {
        return mNavBarCategoryList.get(groupPosition).getCategoryItem(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public int getChildrenCount(int groupPosition) {
        return mNavBarCategoryList.get(groupPosition).getCategoryItemsCount();
    }

    public long getCombinedChildId(long groupId, long childId) {
        return childId;
    }

    public long getCombinedGroupId(long groupId) {
        return groupId;
    }

    public String getGroup(int groupPosition) {
        return mNavBarCategoryList.get(groupPosition).getCategoryName();
    }

    public int getGroupCount() {
        return mNavBarCategoryList.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isEmpty() {
        return mNavBarCategoryList.isEmpty();
    }


    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void onGroupCollapsed(int groupPosition) {}
    public void onGroupExpanded(int groupPosition) {}

    public void registerDataSetObserver(DataSetObserver observer) {}
    public void unregisterDataSetObserver(DataSetObserver observer) {}

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolder vh = null;

        if (convertView == null) {
            convertView = mLayoutInflator.inflate(R.layout.nav_drawer_group_item,null);
        }

        if (vh == null) {
            vh = new ViewHolder();
            vh.groupTxtView = (TextView) convertView.findViewById(R.id.nav_drawer_group_item);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.groupTxtView.setText(mNavBarCategoryList.get(groupPosition).getCategoryName());
        return convertView;
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)  {

        ViewHolder vh = null;

        if (convertView == null) {
            convertView = mLayoutInflator.inflate(R.layout.nav_drawer_group_child_item,null);
        }

        if (vh == null) {
            vh = new ViewHolder();
            vh.groupItemTxtView = (TextView) convertView.findViewById(R.id.nav_drawer_group_child_item);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.groupItemTxtView.setText(mNavBarCategoryList.get(groupPosition).getCategoryItem(childPosition));
        return convertView;
    }

}
