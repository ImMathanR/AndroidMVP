package me.immathan.biryanipoints.utils;

import android.content.Context;

/**
 * Created by Mathan-GG on 02-Mar-17.
 */

public class DisplayUtils {

    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

}
