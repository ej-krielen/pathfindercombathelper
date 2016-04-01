package nl.rekijan.pathfindercombathelper.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Methods containing ui measurements
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 3-10-2015
 */
public final class CommonUtil {

    private static final String TAG = "CommonUtil";

    private static CommonUtil sInstance = null;

    public static synchronized CommonUtil getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CommonUtil();
        }
        return sInstance;
    }

    public void startEmailActivity(Context context, String[] emailAddresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, emailAddresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    /**
     * Hides keyboard if View lost focus
     * @param activity calling activity
     * @param v View
     */
    public void hideSoftKeyboard(Activity activity, View v) {
        InputMethodManager imm =  (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}