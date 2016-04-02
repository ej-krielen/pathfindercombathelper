package nl.rekijan.pathfindercombathelper.ui.views;

import android.app.Activity;
import android.content.Context;
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

import nl.rekijan.pathfindercombathelper.R;
import nl.rekijan.pathfindercombathelper.ui.adapters.NavItemAdapter;
import nl.rekijan.pathfindercombathelper.utilities.CommonUtil;

/**
 * Custom View to handle navigating the app
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 28-3-2016
 */
public class SearchableNavigationView extends LinearLayout {
    public interface OnNavItemPressedListener {
        void onNavItemPressed(String newFragment);
    }

    private EditText mSearchView;
    private ListView mNavigationList;
    private NavItemAdapter mAdapter;
    private Activity mOwner;
    private OnNavItemPressedListener mListener;

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

        //TODO replace with array of navItems (text + string of first question)
        setNavItems(new String[]{mOwner.getString(R.string.cmb_bull_rush), mOwner.getString(R.string.cmb_dirty_trick), mOwner.getString(R.string.cmb_disarm), mOwner.getString(R.string.cmb_drag), mOwner.getString(R.string.cmb_grapple)});
    }

    public void setNavItems(String[] navItems) {
        mAdapter = new NavItemAdapter(getContext(), R.layout.navitem_list_item, navItems, mNavigationList);
        mNavigationList.setAdapter(mAdapter);
    }

    private class onNavItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            NavItemAdapter adapter = (NavItemAdapter) adapterView.getAdapter();
            String navItem = adapter.getItem(position).toString();
            if (!TextUtils.isEmpty(navItem)) {
                if (mListener != null) {
                    mListener.onNavItemPressed(navItem);
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