package nl.rekijan.pathfindercombathelper.utilities;

import android.content.Context;
import android.os.Bundle;

import nl.rekijan.pathfindercombathelper.R;
import nl.rekijan.pathfindercombathelper.models.QuestionModel;
import nl.rekijan.pathfindercombathelper.ui.dialogs.CustomDialogFragment;

import static nl.rekijan.pathfindercombathelper.AppConstants.CATEGORY_GRAPPLE;
import static nl.rekijan.pathfindercombathelper.AppConstants.DIALOG_MESSAGE;
import static nl.rekijan.pathfindercombathelper.AppConstants.DIALOG_TITLE;

/**
 * Class for generic handling of the navigation on Answers and Notes
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 28-3-2016
 */
public class NavigationHandler {

    private static final String TAG = "NavigationHandler";

    private static NavigationHandler sInstance = null;

    public static synchronized NavigationHandler getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new NavigationHandler();
        }
        return sInstance;
    }

    /**
     * Method to make dialogFragment easier.
     *
     * @param title   of dialog
     * @param message content of dialog
     * @return CustomDialogFragment
     */
    public CustomDialogFragment createDialogFragment(String title, String message) {
        CustomDialogFragment dialogFragment = new CustomDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DIALOG_TITLE, title);
        bundle.putString(DIALOG_MESSAGE, message);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    /**
     * Check if the String is a navItem, if so get the corresponding question
     * //TODO replace with NavItem class that has text + questionText
     * @param context
     * @param navItem String to check
     * @return corresponding question string if their is one, original navItem param if not
     */
    public QuestionModel convertNavItemToQuestionModel(Context context, String navItem) {
        if (navItem.equals(context.getString(R.string.cmb_grapple))) {
            String question =  context.getString(R.string.grapple_question_start);
            return new QuestionModel(question, CATEGORY_GRAPPLE);
        }
        return null;
    }
}