package nl.rekijan.pathfindercombathelper;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nl.rekijan.pathfindercombathelper.models.NavItemModel;
import nl.rekijan.pathfindercombathelper.models.QuestionModel;

import static nl.rekijan.pathfindercombathelper.AppConstants.CATEGORY_BULL_RUSH;
import static nl.rekijan.pathfindercombathelper.AppConstants.CATEGORY_DIRTY_TRICK;
import static nl.rekijan.pathfindercombathelper.AppConstants.CATEGORY_DISARM;
import static nl.rekijan.pathfindercombathelper.AppConstants.CATEGORY_DRAG;
import static nl.rekijan.pathfindercombathelper.AppConstants.CATEGORY_GRAPPLE;
import static nl.rekijan.pathfindercombathelper.AppConstants.CATEGORY_POISON;

/**
 * Class for methods and variables that need to be app-wide
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 6-4-2016
 */
public class AppExtension extends Application {

    /**
     * Strings for the categories of items in the ExpendableListView
     */
    private List<String> mListDataHeader;
    /**
     * Items for under the categories in the ExpendableListView<br>
     * Keys: categories from {@link #mListDataHeader}<br>
     * Values: Lists of {@link NavItemModel}<br>
     */
    private HashMap<String, List<NavItemModel>> mListDataChild;

    @Override
    public void onCreate() {
        super.onCreate();
        setDefaultValues();
        createOrUpdateNavigation();
    }

    /**
     * Sets the default values from an XML preference file by reading the values defined
     * by each {@link Preference} item's {@code android:defaultValue} attribute.
     */
    private void setDefaultValues() {
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

    /**
     * Creates or updates {@link #mListDataHeader} and {@link #mListDataChild} with items for the navigation drawer, visibility based on preferences
     */
    public void createOrUpdateNavigation() {
        mListDataHeader = new ArrayList<>();
        mListDataChild = new HashMap<>();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        //Combat maneuvers
        if (sharedPref.getBoolean("pref_nc_cmb", true)) {
            mListDataHeader.add(getString(R.string.navigation_category_cmb));
            List<NavItemModel> cmb = new ArrayList<>();
            if (sharedPref.getBoolean("pref_bull_rush", true))
                cmb.add(new NavItemModel(getString(R.string.cmb_bull_rush), new QuestionModel(getString(R.string.cmb_bull_rush), CATEGORY_BULL_RUSH)));
            if (sharedPref.getBoolean("pref_dirty_trick", true))
                cmb.add(new NavItemModel(getString(R.string.cmb_dirty_trick), new QuestionModel(getString(R.string.cmb_dirty_trick), CATEGORY_DIRTY_TRICK)));
            if (sharedPref.getBoolean("pref_disarm", true))
                cmb.add(new NavItemModel(getString(R.string.cmb_disarm), new QuestionModel(getString(R.string.cmb_disarm), CATEGORY_DISARM)));
            if (sharedPref.getBoolean("pref_drag", true))
                cmb.add(new NavItemModel(getString(R.string.cmb_drag), new QuestionModel(getString(R.string.cmb_drag), CATEGORY_DRAG)));
            if (sharedPref.getBoolean("pref_grapple", true))
                cmb.add(new NavItemModel(getString(R.string.cmb_grapple), new QuestionModel(getString(R.string.grapple_question_start), CATEGORY_GRAPPLE)));
            mListDataChild.put(getString(R.string.navigation_category_cmb), cmb);
        }
        //End Combat maneuvers

        //Others
        if (sharedPref.getBoolean("pref_nc_other", true)) {
            mListDataHeader.add(getString(R.string.navigation_category_other));
            List<NavItemModel> other = new ArrayList<>();
            if (sharedPref.getBoolean("pref_poison", true))
                other.add(new NavItemModel(getString(R.string.poison), new QuestionModel(getString(R.string.poison), CATEGORY_POISON)));
            mListDataChild.put(getString(R.string.navigation_category_other), other);
        }
        //End Others
    }

    public List<String> getHeaders() {
        return mListDataHeader;
    }

    public void setHeaders(List<String> mListDataHeader) {
        this.mListDataHeader = mListDataHeader;
    }

    public HashMap<String, List<NavItemModel>> getNavItems() {
        return mListDataChild;
    }

    public void setNavItems(HashMap<String, List<NavItemModel>> mListDataChild) {
        this.mListDataChild = mListDataChild;
    }
}