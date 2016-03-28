package nl.rekijan.pathfindercombathelper.ui.views;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.widget.TextView;

import nl.rekijan.pathfindercombathelper.R;
import nl.rekijan.pathfindercombathelper.utilities.Measurements;

/**
 * Custom class to create custom layout
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 27-3-2016
 */
public class QuestionTextView extends TextView {

    public QuestionTextView(Context context) {
        super(context);
        this.setBackgroundResource(R.drawable.back_question);
        this.setTypeface(Typeface.DEFAULT_BOLD);
        int spacing = (int) Measurements.getInstance(context).convertDpToPixel(8, context);
        this.setPadding(spacing, spacing, spacing, spacing);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(spacing, spacing, spacing, spacing);
        this.setLayoutParams(params);
    }
}