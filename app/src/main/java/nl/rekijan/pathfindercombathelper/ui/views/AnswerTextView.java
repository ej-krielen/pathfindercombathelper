package nl.rekijan.pathfindercombathelper.ui.views;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import nl.rekijan.pathfindercombathelper.R;
import nl.rekijan.pathfindercombathelper.utilities.Measurements;

/**
 * Custom class to create custom layout
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 27-3-2016
 */
public class AnswerTextView extends TextView {

    public AnswerTextView(Context context) {
        super(context);
        this.setBackgroundResource(R.drawable.back_answer);
        int spacing = (int) Measurements.getInstance(context).convertDpToPixel(8, context);
        this.setPadding(spacing, spacing, spacing, spacing);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(spacing, spacing, spacing, spacing);
        this.setLayoutParams(params);
    }

    public void setNavigationClickListener(final String toast) {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO change to make new Survey with fragment
                Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
            }
        });
    }
}