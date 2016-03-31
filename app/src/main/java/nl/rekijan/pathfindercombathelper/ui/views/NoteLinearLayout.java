package nl.rekijan.pathfindercombathelper.ui.views;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import nl.rekijan.pathfindercombathelper.R;

/**
 * Custom class to create custom layout
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 27-3-2016
 */
public class NoteLinearLayout extends LinearLayout {

    public NoteLinearLayout(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.note_layout, this, true);
    }

    public void setNavigationClickListener(final AlertDialog dialog) {
        LinearLayout ll = (LinearLayout) findViewById(R.id.note_linearLayout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ll.setBackgroundColor(getContext().getColor(R.color.colorAccent));
        } else {
            ll.setBackgroundColor(getContext().getResources().getColor(R.color.colorAccent));
        }
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    public void setText(String questionText) {
        TextView tv = (TextView) findViewById(R.id.note_textView);
        tv.setText(questionText);
    }
}