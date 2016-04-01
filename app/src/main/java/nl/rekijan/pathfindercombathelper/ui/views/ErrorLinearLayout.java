package nl.rekijan.pathfindercombathelper.ui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
public class ErrorLinearLayout extends LinearLayout {

    public ErrorLinearLayout(final Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.error_layout, this, true);
        Button emailBtn = (Button) this.findViewById(R.id.email_error_button);
        emailBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.getInstance(context).startEmailActivity(context, new String[]{"info@rekijan.nl"},
                        (context.getString(R.string.error_email_subject) + " " + context.getString(R.string.app_name)));
            }
        });
    }

    public void setText(String errorText) {
        TextView tv = (TextView) findViewById(R.id.email_error_textView);
        tv.setText(errorText);
    }
}