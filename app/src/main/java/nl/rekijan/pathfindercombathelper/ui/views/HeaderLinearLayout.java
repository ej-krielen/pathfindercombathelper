package nl.rekijan.pathfindercombathelper.ui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import nl.rekijan.pathfindercombathelper.R;

/**
 * Custom class to create custom layout
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 27-3-2016
 */
public class HeaderLinearLayout extends LinearLayout {

    public HeaderLinearLayout(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.header_layout, this, true);
    }

    public void setText(String headerText) {
        TextView tv = (TextView) findViewById(R.id.header_textView);
        tv.setText(headerText);
    }
}