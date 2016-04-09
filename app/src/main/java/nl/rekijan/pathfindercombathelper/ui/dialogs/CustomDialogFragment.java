package nl.rekijan.pathfindercombathelper.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import nl.rekijan.pathfindercombathelper.R;

import static nl.rekijan.pathfindercombathelper.AppConstants.DIALOG_MESSAGE;
import static nl.rekijan.pathfindercombathelper.AppConstants.DIALOG_TITLE;

/**
 * Custom class for DialogFragment
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 1-4-2016
 */
public class CustomDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.Dialog);
        builder.setTitle(args.getString(DIALOG_TITLE))
                .setMessage(args.getString(DIALOG_MESSAGE))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                });
        return builder.create();
    }
}