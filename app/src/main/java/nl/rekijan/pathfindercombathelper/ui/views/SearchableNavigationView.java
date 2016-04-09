package nl.rekijan.pathfindercombathelper.ui.views;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.List;

import nl.rekijan.pathfindercombathelper.AppExtension;
import nl.rekijan.pathfindercombathelper.R;
import nl.rekijan.pathfindercombathelper.models.NavItemModel;
import nl.rekijan.pathfindercombathelper.models.QuestionModel;
import nl.rekijan.pathfindercombathelper.ui.adapters.NavItemAdapter;
import nl.rekijan.pathfindercombathelper.utilities.CommonUtil;

/**
 * Custom View to handle navigating the app
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 28-3-2016
 */
public class SearchableNavigationView extends LinearLayout implements SharedPreferences.OnSharedPreferenceChangeListener,
        SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    public interface OnNavItemPressedListener {
        void onNavItemPressed(QuestionModel questionModel);
    }

    private ExpandableListView mNavigationList;
    private NavItemAdapter mAdapter;
    private Activity mOwner;
    private OnNavItemPressedListener mListener;
    private final AppExtension mApp;

    public SearchableNavigationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        try {
            mOwner = (Activity) context;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Needs to be an activity");
        }
        if (context instanceof OnNavItemPressedListener) {
            mListener = (OnNavItemPressedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnNavItemPressedListener");
        }
        mApp = (AppExtension) context.getApplicationContext();
        PreferenceManager.getDefaultSharedPreferences(context).registerOnSharedPreferenceChangeListener(this);

        LayoutInflater.from(context).inflate(R.layout.navigation_layout, this, true);

        mNavigationList = (ExpandableListView) findViewById(R.id.navigation_expandableListView);
        mNavigationList.setOnChildClickListener(new onNavItemClickListener());
        mNavigationList.setTextFilterEnabled(false);

        SearchManager searchManager = (SearchManager) mOwner.getSystemService(Context.SEARCH_SERVICE);
        SearchView mSearchView = (SearchView) findViewById(R.id.navigation_searchView);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(mOwner.getComponentName()));
        mSearchView.setIconified(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setOnCloseListener(this);

        mSearchView.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    InputMethodManager imm = (InputMethodManager) mOwner.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                }
            }
        });

        setNavItems(mApp.getHeaders(), mApp.getNavItems());
        expandAll();
    }

    private void expandAll() {
        int count = mAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            mNavigationList.expandGroup(i);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        mApp.createOrUpdateNavigation();
        setNavItems(mApp.getHeaders(), mApp.getNavItems());
    }

    public void setNavItems(List<String> headers, HashMap<String, List<NavItemModel>> navItems) {
        mAdapter = new NavItemAdapter(getContext(), headers, navItems);
        mNavigationList.setAdapter(mAdapter);
        expandAll();
    }

    @Override
    public boolean onClose() {
        mAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        mAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mAdapter.filterData(query);
        expandAll();
        return false;
    }

    private class onNavItemClickListener implements ExpandableListView.OnChildClickListener {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            final NavItemModel navItem = (NavItemModel) mAdapter.getChild(groupPosition, childPosition);
            if (!TextUtils.isEmpty(navItem.getTitle())) {
                if (mListener != null) {
                    mListener.onNavItemPressed(navItem.getQuestionModel());
                    CommonUtil.getInstance(mOwner).hideSoftKeyboard(mOwner);
                }
            }
            return false;
        }
    }
}