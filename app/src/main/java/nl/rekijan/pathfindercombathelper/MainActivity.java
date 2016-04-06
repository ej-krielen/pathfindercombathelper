package nl.rekijan.pathfindercombathelper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import nl.rekijan.pathfindercombathelper.models.QuestionModel;
import nl.rekijan.pathfindercombathelper.surveys.Surveys;
import nl.rekijan.pathfindercombathelper.ui.adapters.NavItemAdapter;
import nl.rekijan.pathfindercombathelper.ui.dialogs.CustomDialogFragment;
import nl.rekijan.pathfindercombathelper.ui.fragments.StartFragment;
import nl.rekijan.pathfindercombathelper.ui.fragments.SurveyFragment;
import nl.rekijan.pathfindercombathelper.ui.views.AnswerLinearLayout;
import nl.rekijan.pathfindercombathelper.ui.views.NoteLinearLayout;
import nl.rekijan.pathfindercombathelper.ui.views.SearchableNavigationView;
import nl.rekijan.pathfindercombathelper.utilities.CommonUtil;

import static nl.rekijan.pathfindercombathelper.AppConstants.DIALOG_TAG;
import static nl.rekijan.pathfindercombathelper.AppConstants.START_FRAGMENT_TAG;

public class MainActivity extends AppCompatActivity
        implements AnswerLinearLayout.OnAnswerPressedListener, NoteLinearLayout.OnNotePressedListener, SearchableNavigationView.OnNavItemPressedListener, Surveys.OnSurveyCreatedListener {

    private CustomDialogFragment mDialogFragment;
    /**
     * Per the design guidelines, you should show the drawer on launch until the user manually
     * uses it. This shared preference tracks this.
     */
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    private SharedPreferences sharedPreferences;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerGroupedLayout;
    private ListView mNavItemListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Layout that containts the navigation drawer and the main contain
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //Container that holds all elements of the navigation drawer
        mDrawerGroupedLayout = (RelativeLayout) findViewById(R.id.drawer_left_group);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (mDrawerLayout != null) {
            mDrawerLayout.addDrawerListener(toggle);
        }
        toggle.syncState();

        // Read in the flag indicating whether or not the user has demonstrated awareness of the
        // drawer. See PREF_USER_LEARNED_DRAWER for details.
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean mUserLearnedDrawer = sharedPreferences.getBoolean(PREF_USER_LEARNED_DRAWER, false);
        if (!mUserLearnedDrawer) {
            mDrawerLayout.openDrawer(mDrawerGroupedLayout);
        }

        mDrawerLayout.setDrawerListener(new DrawerListener());
        mNavItemListView = (ListView) findViewById(R.id.navigation_listView);

        if (findViewById(R.id.fragment_container) != null) {
            //if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, StartFragment.newInstance())
                    .addToBackStack(START_FRAGMENT_TAG)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    getSupportFragmentManager().popBackStackImmediate();
                } else {
                    this.finish();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment newFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onAnswerPressed(QuestionModel questionModel) {
        replaceFragment(SurveyFragment.newInstance(questionModel));
    }

    @Override
    public void onNavItemPressed(QuestionModel questionModel) {
        replaceFragment(SurveyFragment.newInstance(questionModel));
        if (mDrawerLayout.isDrawerOpen(mDrawerGroupedLayout)) {
            mDrawerLayout.closeDrawer(mDrawerGroupedLayout);
        }
    }

    @Override
    public void onSurveyCreated(String navItem) {
        NavItemAdapter adapter = (NavItemAdapter) mNavItemListView.getAdapter();
        adapter.setSelectedNavItem(navItem);
    }

    @Override
    public void onNotePressed(CustomDialogFragment dialogFragment) {
        //Hide previous dialog if there is one
        if (mDialogFragment != null && mDialogFragment.getDialog() != null && mDialogFragment.getDialog().isShowing())
            mDialogFragment.dismiss();
        //Show new dialog
        mDialogFragment = dialogFragment != null ? dialogFragment : null;
        if (mDialogFragment != null)
            mDialogFragment.show(getSupportFragmentManager(), DIALOG_TAG);
    }

    private class DrawerListener implements DrawerLayout.DrawerListener {

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
        }

        @Override
        public void onDrawerOpened(View drawerView) {
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            //If the drawer is closed the user has interacted with it
            //Store that the user has learned to use the navigation drawer
            //so it doesn't start open on start up
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(PREF_USER_LEARNED_DRAWER, true);
            editor.apply();
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            CommonUtil.getInstance(MainActivity.this).hideSoftKeyboard(MainActivity.this);
        }
    }
}