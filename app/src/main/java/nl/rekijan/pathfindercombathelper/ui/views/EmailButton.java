package nl.rekijan.pathfindercombathelper.ui.views;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.Button;

import nl.rekijan.pathfindercombathelper.R;
import nl.rekijan.pathfindercombathelper.utilities.CommonUtil;

/**
 * Creates a button with custom layout and OnClickListener set to fire an email intent
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 28-3-2016
 */
public class EmailButton extends Button {
    public EmailButton(final Context context) {
        super(context);

        this.setText(context.getString(R.string.email_button));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.setTextColor(context.getColor(R.color.white));
        } else {
            this.setTextColor(context.getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.setBackgroundColor(context.getColor(R.color.colorPrimary));
        } else {
            this.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.getInstance(context).startEmailActivity(context, new String[]{"info@rekijan.nl"},
                        (context.getString(R.string.error_email_subject) + " " + context.getString(R.string.app_name)));
            }
        });
    }
}