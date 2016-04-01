package nl.rekijan.pathfindercombathelper.utilities;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import nl.rekijan.pathfindercombathelper.ui.dialogs.CustomDialogFragment;

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

    public AlertDialog createDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message);
        builder.setPositiveButton(context.getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        return builder.create();
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
}