package nl.rekijan.pathfindercombathelper.ui.activities;

import android.app.Activity;
import android.os.Bundle;

import nl.rekijan.pathfindercombathelper.ui.fragments.SettingsFragment;

/**
 * Activity for Settings
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 6-4-2016
 */
public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}