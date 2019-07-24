package android.js.com.myapplication.feature.fragment;


import android.js.com.myapplication.feature.fragment.sub.EntFragment;
import android.js.com.myapplication.feature.fragment.sub.FriendsFragment;
import android.js.com.myapplication.feature.fragment.sub.TechnologyFragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.js.com.myapplication.feature.R;

//import android.app.Fragment;

//import com.astuetz.PagerSlidingTabStrip;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsChannelFragment extends Fragment {



    ViewPager viewPager;

    /**
     * PagerSlidingTabStrip的实例
     */
    //PagerSlidingTabStrip tabs;

    EntFragment entFragment;
    FriendsFragment sub2Fragment;
    TechnologyFragment sub3Fragment;
    /**
     * 获取当前屏幕的密度
     */
    private DisplayMetrics dm;

    private TabLayout tabLayout;

    private final String[] titles = { "娱乐", "好友", "科技" };


    public NewsChannelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view= inflater.inflate(R.layout.fragment_news_channel, container, false);
        initView(view);
        return  view;
    }

    private void initView(View view){
        ViewPager pager = (ViewPager) view.findViewById(R.id.news_pager);
        //tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        pager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        //tabs.setViewPager(pager);


        // Bind the tabs to the ViewPager
//        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
//        tabs.setViewPager(pager);

        // Bind the tabs to the ViewPager
//       PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
//        tabs.setViewPager(pager);


        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(pager,false);

        for(int i=0;i<titles.length;i++){
            //fragments.add(new TabFragment());
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.getTabAt(i).setText(titles[i]);
        }
    }


    public class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        //private final String[] titles = { "同学", "同事", "好友" };

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:

                    if (null == entFragment) {
                        entFragment = new EntFragment();
                    }

                    return entFragment;

                case 1:

                    if (null == sub2Fragment) {
                        sub2Fragment = new FriendsFragment();
                    }

                    return sub2Fragment;
                case 2:

                    if (null == sub3Fragment) {
                        sub3Fragment = new TechnologyFragment();
                    }
                    entFragment = new EntFragment();
                    return sub3Fragment;
                default:
                    return null;
            }
        }

    }

}
