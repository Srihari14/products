package com.pagination.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.Random;

/*
 * Created by Rajkumar.
 */
public class CommonUtils {

    public static boolean checkNetworkConnection(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public static void showToastMessage(Context context, String s) {
        if (context != null)
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();

    }

    public static void showToastMessageLong(Context context, String s) {
        if (context != null)
            Toast.makeText(context, s, Toast.LENGTH_LONG).show();

    }

    public static Drawable getDrawable(final String string) {

        /*
         * final String[] colors = { "#faafbe", "#9AFEFF", "#95B9C7", "#737CA1",
         * "#736AFF", "#659EC7", "#B7CEEC", "#7FFFD4", "#3EA055", "#6CBB3C",
         * "#99C68E", "#89C35C", "#8BB381", "#9CB071", "#EDE275", "#FFF380",
         * "#F5F5DC", "#FFE5B4", "#F9966B", "#FDD7E4", "#FCDFFF", "#FFDFDD",
         * "#FBBBB9", "#C6AEC7" };
         */
        final String[] colors = {"#00ffffff", "#00ffffff"};

        final Paint paint = new Paint();
        paint.setColor(Color
                .parseColor(colors[getrandomNumber(colors.length - 1)]));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        final Paint textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStrokeCap(Paint.Cap.ROUND);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.parseColor("#11000000"));
        textPaint.setTextSize(40);

        return new Drawable() {

            @Override
            public void setColorFilter(ColorFilter cf) {
                // TODO Auto-generated method stub

            }

            @Override
            public void setAlpha(int alpha) {
                // TODO Auto-generated method stub

            }

            @Override
            public int getOpacity() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public void draw(@NonNull Canvas canvas) {
                Rect rect = getBounds();
                canvas.drawRect(rect, paint);
                int x = rect.centerX();
                int y = rect.centerY();
                canvas.drawText(string, x, y, textPaint);

            }
        };
    }

    private static int getrandomNumber(int max_count) {

        int min = 1;

        Random r = new Random();

        return r.nextInt(max_count - min + 1) + min;
    }

}
