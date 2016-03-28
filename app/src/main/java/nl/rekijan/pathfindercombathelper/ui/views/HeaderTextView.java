package nl.rekijan.pathfindercombathelper.ui.views;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.widget.TextView;

import nl.rekijan.pathfindercombathelper.utilities.CommonUtil;

/**
 * Custom class to create custom layout
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 27-3-2016
 */
public class HeaderTextView extends TextView {

    public HeaderTextView(Context context) {
        super(context);
        this.setTypeface(Typeface.DEFAULT_BOLD);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.setTextAppearance(android.R.style.TextAppearance_Large);
        } else {
            this.setTextAppearance(context, android.R.style.TextAppearance_Large);
        }
        int spacing = (int) CommonUtil.getInstance(context).convertDpToPixel(8, context);
        this.setPadding(spacing, spacing, spacing, spacing);
    }
}