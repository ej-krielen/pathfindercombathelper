package nl.rekijan.pathfindercombathelper.ui.views;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import nl.rekijan.pathfindercombathelper.R;
import nl.rekijan.pathfindercombathelper.ui.dialogs.CustomDialogFragment;

/**
 * Custom class to create custom layout
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 27-3-2016
 */
public class NoteLinearLayout extends LinearLayout {
    public interface OnNotePressedListener {
        void onNotePressed(CustomDialogFragment dialogFragment);
    }

    private OnNotePressedListener mListener;

    public NoteLinearLayout(Context context) {
        super(context);
        if (context instanceof OnNotePressedListener) {
            mListener = (OnNotePressedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnNotePressedListener");
        }
        LayoutInflater.from(context).inflate(R.layout.note_layout, this, true);
    }

    public void setNavigationClickListener(final CustomDialogFragment dialogFragment) {
        LinearLayout ll = (LinearLayout) findViewById(R.id.note_linearLayout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ll.setBackgroundColor(getContext().getColor(R.color.colorAccent));
        } else {
            ll.setBackgroundColor(getContext().getResources().getColor(R.color.colorAccent));
        }
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onNotePressed(dialogFragment);
                }
            }
        });
    }

    public void setText(String questionText) {
        TextView tv = (TextView) findViewById(R.id.note_textView);
        tv.setText(questionText);
    }
}