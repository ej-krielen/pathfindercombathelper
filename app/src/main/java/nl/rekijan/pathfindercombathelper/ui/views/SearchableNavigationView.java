package nl.rekijan.pathfindercombathelper.ui.views;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.ListView;

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
public class SearchableNavigationView extends LinearLayout implements SharedPreferences.OnSharedPreferenceChangeListener {
    public interface OnNavItemPressedListener {
        void onNavItemPressed(QuestionModel questionModel);
    }

    private EditText mSearchView;
    private ListView mNavigationList;
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

        mNavigationList = (ListView) findViewById(R.id.navigation_listView);
        mNavigationList.setOnItemClickListener(new onNavItemClickListener());
        mNavigationList.setTextFilterEnabled(false);

        mSearchView = (EditText) findViewById(R.id.navigation_editText);
        mSearchView.addTextChangedListener(new onTextChangedListener());

        mSearchView.setOnTouchListener(new rightDrawableClickListener());
        mSearchView.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    InputMethodManager imm = (InputMethodManager) mOwner.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                }
            }
        });

        setNavItems(mApp.getNavItems());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        mApp.createOrUpdateNavItems();
        setNavItems(mApp.getNavItems());
    }

    public void setNavItems(List<NavItemModel> navItems) {
        mAdapter = new NavItemAdapter(getContext(), R.layout.navitem_list_item, navItems, mNavigationList);
        mNavigationList.setAdapter(mAdapter);
    }

    private class onNavItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            NavItemAdapter adapter = (NavItemAdapter) adapterView.getAdapter();
            NavItemModel navItem = adapter.getItem(position);
            if (!TextUtils.isEmpty(navItem.getTitle())) {
                if (mListener != null) {
                    mListener.onNavItemPressed(navItem.getQuestionModel());
                    CommonUtil.getInstance(mOwner).hideSoftKeyboard(mOwner);
                }
            }
        }
    }

    private class rightDrawableClickListener implements OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            final int DRAWABLE_RIGHT = 2;

            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (mSearchView.getRight() - mSearchView.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    mNavigationList.requestFocus();
                    CommonUtil.getInstance(mOwner).hideSoftKeyboard(mOwner);
                    return true;
                }
            }
            return false;
        }
    }

    private class onTextChangedListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s != null && mAdapter != null) {
                Filter filter = mAdapter.getFilter();
                filter.filter(s.toString().toLowerCase());
            }
        }
    }
}