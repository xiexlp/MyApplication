package android.js.com.myapplication.feature;

import android.js.com.myapplication.feature.adaptermarket.ViewPagerAdapter;
import android.js.com.myapplication.feature.fragment.SocialFragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CurrencyActivity extends AppCompatActivity {


    // ViewPager forum_topics_pager;

    private final String[] titles = { "最新", "最热", "最喜欢" };

    private View view1,view2,view3;
    private List<String> mTitleList = new ArrayList<>(); //页卡标题集合
    private List<View> mViewList = new ArrayList<>();   //页卡视图集合
    private LayoutInflater mInflater;
    ViewPager currency_pics_pager;


    TabLayout pics_tablayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        initView();
    }

    private void initView(){
        pics_tablayout = findViewById(R.id.pics_tablayout);

        mInflater = LayoutInflater.from(this);
        view1 = mInflater.inflate(R.layout.view_pic, null);
        view2 = mInflater.inflate(R.layout.view_pic1, null);
        view3 = mInflater.inflate(R.layout.view_pic2, null);

        //添加页卡视图
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);
        //添加页卡标题
        mTitleList.add("图1");
        mTitleList.add("图2");
        mTitleList.add("图3");


        currency_pics_pager = findViewById(R.id.currency_pics_pager);

        pics_tablayout = (TabLayout) findViewById(R.id.pics_tablayout);
        for(int i=0;i<mTitleList.size();i++){
            //fragments.add(new TabFragment());
            pics_tablayout.addTab(pics_tablayout.newTab());
            // topics_tablayout.getTabAt(i).setText(titles[i]);
        }


        ViewPagerAdapter mAdapter = new ViewPagerAdapter(mViewList, mTitleList);
        currency_pics_pager.setAdapter(mAdapter);//给ViewPager设置适配器

        //pics_tablayout.setupWithViewPager(currency_pics_pager,false);
        //currency_pics_pager.setOffscreenPageLimit(3);

        pics_tablayout.setupWithViewPager(currency_pics_pager);  //将TabLayout和ViewPager关联起来。
        pics_tablayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器

        for(int i=0;i<titles.length;i++){
            //fragments.add(new TabFragment());
            // topics_tablayout.addTab(new TabFragment());
            pics_tablayout.getTabAt(i).setText(mTitleList.get(i));
        }


        pics_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                System.out.println("选中的文本Tab:"+tab.getText());
                //Toast.makeText(getActivity(), "选中的"+tab.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                System.out.println("未选中的文本Tab:"+tab.getText());
                //Toast.makeText(getActivity(), "未选中的"+tab.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                System.out.println("复选的文本Tab:"+tab.getText());
                //Toast.makeText(getActivity(), "复选的"+tab.getText(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
