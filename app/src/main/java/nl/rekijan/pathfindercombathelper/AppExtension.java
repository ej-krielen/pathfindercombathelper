package nl.rekijan.pathfindercombathelper;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import nl.rekijan.pathfindercombathelper.models.NavItemModel;
import nl.rekijan.pathfindercombathelper.models.QuestionModel;

import static nl.rekijan.pathfindercombathelper.AppConstants.CATEGORY_BULL_RUSH;
import static nl.rekijan.pathfindercombathelper.AppConstants.CATEGORY_DIRTY_TRICK;
import static nl.rekijan.pathfindercombathelper.AppConstants.CATEGORY_DISARM;
import static nl.rekijan.pathfindercombathelper.AppConstants.CATEGORY_DRAG;
import static nl.rekijan.pathfindercombathelper.AppConstants.CATEGORY_GRAPPLE;

/**
 * Class for methods and variables that need to be app-wide
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 6-4-2016
 */
public class AppExtension extends Application {

    private List<NavItemModel> navItems;

    @Override
    public void onCreate() {
        super.onCreate();
        setDefaultValues();
        createOrUpdateNavItems();
    }

    private void setDefaultValues() {
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

    public void createOrUpdateNavItems() {
        navItems = new ArrayList<>();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPref.getBoolean("pref_bull_rush", true))
            navItems.add(new NavItemModel(getString(R.string.cmb_bull_rush), new QuestionModel(getString(R.string.cmb_bull_rush), CATEGORY_BULL_RUSH)));
        if (sharedPref.getBoolean("pref_dirty_trick", true))
            navItems.add(new NavItemModel(getString(R.string.cmb_dirty_trick), new QuestionModel(getString(R.string.cmb_dirty_trick), CATEGORY_DIRTY_TRICK)));
        if (sharedPref.getBoolean("pref_disarm", true))
            navItems.add(new NavItemModel(getString(R.string.cmb_disarm), new QuestionModel(getString(R.string.cmb_disarm), CATEGORY_DISARM)));
        if (sharedPref.getBoolean("pref_drag", true))
            navItems.add(new NavItemModel(getString(R.string.cmb_drag), new QuestionModel(getString(R.string.cmb_drag), CATEGORY_DRAG)));
        if (sharedPref.getBoolean("pref_grapple", true))
            navItems.add(new NavItemModel(getString(R.string.cmb_grapple), new QuestionModel(getString(R.string.grapple_question_start), CATEGORY_GRAPPLE)));
    }

    public List<NavItemModel> getNavItems() {
        return navItems;
    }

    public void setNavItems(List<NavItemModel> navItems) {
        this.navItems = navItems;
    }
}