package nl.rekijan.pathfindercombathelper.ui.views;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * //TODO write description of this class
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 28-3-2016
 */
public class NavigationView extends LinearLayout {
    private Activity mOwner;

    public NavigationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        try {
            mOwner = (Activity) context;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Needs to be an activity");
        }
//        LayoutInflater.from(context).inflate(R.layout.navigation_layout, this, true);

    }
}