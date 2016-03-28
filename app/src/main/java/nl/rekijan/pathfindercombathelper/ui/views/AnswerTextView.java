package nl.rekijan.pathfindercombathelper.ui.views;

import android.content.Context;
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
public class AnswerTextView extends TextView {

    public interface OnAnswerPressedListener {
        void onAnswerPressed(String newFragment);
    }

    private OnAnswerPressedListener mListener;

    public AnswerTextView(Context context) {
        super(context);
        if (context instanceof OnAnswerPressedListener) {
            mListener = (OnAnswerPressedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAnswerPressedListener");
        }
        this.setBackgroundResource(R.drawable.back_answer);
        int spacing = (int) CommonUtil.getInstance(context).convertDpToPixel(8, context);
        this.setPadding(spacing, spacing, spacing, spacing);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(spacing, spacing, spacing, spacing);
        this.setLayoutParams(params);
    }

    public void setNavigationClickListener(final String newFragment) {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onAnswerPressed(newFragment);
                }
            }
        });
    }
}