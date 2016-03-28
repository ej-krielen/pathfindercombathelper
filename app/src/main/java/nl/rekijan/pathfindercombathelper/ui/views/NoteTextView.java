package nl.rekijan.pathfindercombathelper.ui.views;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import nl.rekijan.pathfindercombathelper.R;
import nl.rekijan.pathfindercombathelper.utilities.CommonUtil;

/**
 * Custom class to create custom layout
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 27-3-2016
 */
public class NoteTextView extends TextView {

    public NoteTextView(Context context) {
        super(context);
        this.setBackgroundResource(R.drawable.back_note);
        int spacing = (int) CommonUtil.getInstance(context).convertDpToPixel(8, context);
        this.setPadding(spacing, spacing, spacing, spacing);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(spacing, spacing, spacing, spacing);
        this.setLayoutParams(params);
    }

    public void setNavigationClickListener(final AlertDialog dialog) {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }
}