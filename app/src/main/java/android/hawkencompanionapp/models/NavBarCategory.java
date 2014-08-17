package android.hawkencompanionapp.models;

import java.util.ArrayList;

/**
 * Created by Icarus on 16/08/2014.
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
