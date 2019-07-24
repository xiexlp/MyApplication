package android.js.com.myapplication.feature.fragmentmarket;


import android.js.com.myapplication.feature.R;
import android.js.com.myapplication.feature.fragment.topic.LikestFragment;
import android.js.com.myapplication.feature.fragment.topic.NewestFragment;
import android.js.com.myapplication.feature.fragment.topic.RepliestFragment;
import android.js.com.myapplication.feature.fragmentmarket.submood.RankDownFragment;
import android.js.com.myapplication.feature.fragmentmarket.submood.RankMarketFragment;
import android.js.com.myapplication.feature.fragmentmarket.submood.RankUpFragment;
import android.js.com.myapplication.feature.fragmentmarket.submood.WatchListFragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoodFragment extends Fragment {

   // ViewPager forum_topics_pager;

    private final String[] titles = { "关注", "市值排行", "涨幅榜","跌幅榜" };

    TabLayout coins_tablayout;


    NewestFragment newestFragment;
    RepliestFragment repliestFragment;
    LikestFragment likestFragment;


    WatchListFragment watchListFragment;
    RankMarketFragment rankMarketFragment;
    RankDownFragment rankDownFragment;
    RankUpFragment rankUpFragment;



    public MoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mood, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        ViewPager market_coins_pager = view.findViewById(R.id.market_coins_pager);

        coins_tablayout = (TabLayout) view.findViewById(R.id.coins_tablayout);
        for(int i=0;i<titles.length;i++){
            //fragments.add(new TabFragment());
            coins_tablayout.addTab(coins_tablayout.newTab());
           // topics_tablayout.getTabAt(i).setText(titles[i]);
        }


        market_coins_pager.setAdapter(new ForumPagerAdapter(getChildFragmentManager()));
        coins_tablayout.setupWithViewPager(market_coins_pager,false);
        market_coins_pager.setOffscreenPageLimit(3);

        for(int i=0;i<titles.length;i++){
            //fragments.add(new TabFragment());
           // topics_tablayout.addTab(new TabFragment());
            coins_tablayout.getTabAt(i).setText(titles[i]);
        }


        coins_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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


        //topics_tablayout.setTabMode(TabLayout.MODE_FIXED);
       // topics_tablayout.setTabGravity(TabLayout.GRAVITY_FILL);

       // topics_tablayout.setTabMode(TabLayout.GRAVITY_FILL);



//        //重新设置点击事件
//        for (int i = 0; i < topics_tablayout.getTabCount(); i++) {
//            TabLayout.Tab tab=topics_tablayout.getTabAt(i);
//            if (tab!=null){
//                tab.setCustomView((View) forum_topics_pager.getTag(i));
//                if (tab.getCustomView()!=null){
//                    View tabView=  (View)tab.getCustomView().getParent();
//                    tabView.setTag(i);
//                    tabView.setOnClickListener(mTabOnClickListener);
//                }
//            }
//        }


    }

//    private View.OnClickListener mTabOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            System.out.println("点击Tab");
//            int position= (int) v.getTag();
//            if (position==0 &&topics_tablayout.getTabAt(position).isSelected()==true){
//                Toast.makeText(getActivity(), "点击了第一个tab", Toast.LENGTH_SHORT).show();
//            }else if (position==1 && topics_tablayout.getTabAt(position).isSelected()==true){
//                Toast.makeText(getActivity(), "点击了第二个tab", Toast.LENGTH_SHORT).show();
//            }else {
//                TabLayout.Tab tab = topics_tablayout.getTabAt(position);
//                if (tab != null) {
//                    tab.select();
//                }
//            }
//        }
//    };




    public class ForumPagerAdapter extends FragmentStatePagerAdapter {

        public ForumPagerAdapter(FragmentManager fm) {
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
                    if (null == watchListFragment) {
                        watchListFragment = new WatchListFragment();
                    }
                    return watchListFragment;
                case 1:
                    if (null == rankMarketFragment) {
                        rankMarketFragment = new RankMarketFragment();
                    }
                    return rankMarketFragment;
                case 2:
                    if (null == rankDownFragment) {
                        rankDownFragment = new RankDownFragment();
                    }
                   // entFragment = new EntFragment();
                    return rankDownFragment;
                case 3:
                    if (null == rankUpFragment) {
                        rankUpFragment = new RankUpFragment();
                    }
                    // entFragment = new EntFragment();
                    return rankUpFragment;

                default:
                    return watchListFragment;
            }
        }

    }


}
