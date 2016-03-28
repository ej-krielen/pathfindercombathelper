package nl.rekijan.pathfindercombathelper.utilities;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

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
}