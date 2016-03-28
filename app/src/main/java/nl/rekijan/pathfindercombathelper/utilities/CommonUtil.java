package nl.rekijan.pathfindercombathelper.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.util.DisplayMetrics;

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

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
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
}