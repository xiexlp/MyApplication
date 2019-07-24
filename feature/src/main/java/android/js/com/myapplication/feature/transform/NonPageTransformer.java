package android.js.com.myapplication.feature.transform;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by zhy on 16/5/7.
 */
public class NonPageTransformer implements ViewPager.PageTransformer
{
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void transformPage(View page, float position)
    {
        page.setScaleX(0.8f);//hack
    }

    public static final ViewPager.PageTransformer INSTANCE = new NonPageTransformer();
}
