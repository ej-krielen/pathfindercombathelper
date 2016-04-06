package nl.rekijan.pathfindercombathelper.ui.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import nl.rekijan.pathfindercombathelper.R;

/**
 * Fragment for Settings
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 6-4-2016
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}