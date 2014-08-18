package android.hawkencompanionapp.models;
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
import java.util.ArrayList;

/**
 * Created by Phillip Adam Nash on 16/08/2014.
 */
public final class NavBarCategory {
    final String mCategory;
    final ArrayList<String> mCategoryItemsList;

    public NavBarCategory(String category) {
        mCategory = category;
        mCategoryItemsList = new ArrayList<String>();
    }

    public void addCategoryItem(String item) {
        mCategoryItemsList.add(item);
    }

    public String getCategoryItem(int pos) {
        return mCategoryItemsList.get(pos);
    }

    public ArrayList<String> getCategoryItemList() {
        return mCategoryItemsList;
    }


    public int getCategoryItemsCount() {
        return mCategoryItemsList.size();
    }

    public String getCategoryName() {
        return mCategory;
    }
}
