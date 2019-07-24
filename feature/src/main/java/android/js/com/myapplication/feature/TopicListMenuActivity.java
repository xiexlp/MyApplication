package android.js.com.myapplication.feature;

import android.js.com.myapplication.feature.fragment.topiclist.ContentFragment;
import android.js.com.myapplication.feature.fragment.topiclist.LeftMenuFragment;
import android.js.com.myapplication.feature.utils.DensityUtil;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class TopicListMenuActivity extends SlidingFragmentActivity {

    private static final String MAIN_CONTENT_TAG = "MAIN_CONTENT_TAG";
    private static final String LEFTMENU_TAG = "LEFTMENU_TAG";
    private int screeHeight;
    private int screeWidth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置没有标题
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list_menu);

        //设置左侧菜单
        setBehindContentView(R.layout.fragment_topic_list_leftmenu);


        //设置右侧菜单
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setSecondaryMenu(R.layout.fragment_topic_list_rightmenu);


        //设置模式显示的模式 左侧菜单+主页 左侧菜单+主页+右侧菜单 主页面+右侧菜单
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);

        //设置滑动模式 边缘，全屏滑动，不可以滑动
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置主页占据的宽度
        slidingMenu.setBehindOffset(DensityUtil.dip2px(TopicListMenuActivity.this,200));

        DisplayMetrics outmetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outmetrics);
        screeWidth = outmetrics.widthPixels;
        screeHeight = outmetrics.heightPixels;
        //6.设置主页占据的宽度
//        slidingMenu.setBehindOffset(DensityUtil.dip2px(MainActivity.this, 200));
        slidingMenu.setBehindOffset((int) (screeWidth*0.625));


        initFragment();
    }


    private void initFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        LeftMenuFragment leftMenuFragment = new LeftMenuFragment();
        leftMenuFragment.setContext(this);

        ft.replace(R.id.fl_main_content,new ContentFragment(), MAIN_CONTENT_TAG);//主页
        ft.replace(R.id.fl_main_leftmenu, leftMenuFragment, LEFTMENU_TAG);//左侧菜单
        //4.提交
        ft.commit();



    }
}
