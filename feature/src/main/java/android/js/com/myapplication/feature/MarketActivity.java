package android.js.com.myapplication.feature;

import android.content.Intent;
import android.js.com.myapplication.feature.R;
import android.js.com.myapplication.feature.fragment.HomeFragment;
import android.js.com.myapplication.feature.fragment.NewsChannelFragment;
import android.js.com.myapplication.feature.fragment.ProductListFragment;
import android.js.com.myapplication.feature.fragment.SearchFragment;
import android.js.com.myapplication.feature.fragment.SocialFragment;
import android.js.com.myapplication.feature.fragment.UserCenterFragment;
import android.js.com.myapplication.feature.fragmentmarket.DeepFragment;
import android.js.com.myapplication.feature.fragmentmarket.MeFragment;
import android.js.com.myapplication.feature.fragmentmarket.MoodFragment;
import android.js.com.myapplication.feature.fragmentmarket.NewsFragment;
import android.js.com.myapplication.feature.fragmentmarket.PlatformFragment;
import android.js.com.myapplication.feature.orm.Section;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MarketActivity extends AppCompatActivity {


    private ArrayList<Fragment> fragments;

    private FrameLayout fl_type;
    RadioGroup rgMain;
    int position;

    private Fragment mContext;

    MoodFragment moodFragment;
    PlatformFragment platformFragment;
    NewsFragment newsFragment;
    DeepFragment deepFragment;
    MeFragment meFragment;


//    HomeFragment homeFragment;
//    ProductListFragment productListFragment;
//    SocialFragment socialFragment;
//    UserCenterFragment userCenterFragment;
//    SearchFragment searchFragment;
//    NewsChannelFragment newsChannelFragment;

    String boardListStr;
    List<Section> sectionList;
    //int checkId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        fl_type = findViewById(R.id.frameMarketLayout);
        rgMain = findViewById(R.id.rg_market_main);


        //4). 得到intent对象
        Intent intent = getIntent();
        //5). 通过intent读取额外数据
        boardListStr = intent.getStringExtra("MESSAGE");


        initFragment();
        initListener();

//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.frameLayout,homeFragment).commit();
    }


    private void initFragment() {
        fragments = new ArrayList<>();

        //homeFragment=new HomeFragment();

        moodFragment = new MoodFragment();
        platformFragment = new PlatformFragment();
        newsFragment =new NewsFragment();
        deepFragment = new DeepFragment();
        meFragment =new MeFragment();
        fragments.add(moodFragment);
        fragments.add(platformFragment);
        fragments.add(newsFragment);
        fragments.add(deepFragment);
        fragments.add(meFragment);

        //传入参数
        //homeFragment.setBoardListStr(boardListStr);
        //productListFragment= new ProductListFragment();
        //productListFragment.setContext(MarketActivity.this);
        //searchFragment = new SearchFragment();
        //socialFragment= new SocialFragment();
        //userCenterFragment= new UserCenterFragment();

        //newsChannelFragment = new NewsChannelFragment();

//        fragments.add(homeFragment);
//        fragments.add(productListFragment);
//        fragments.add(searchFragment);
//        fragments.add(socialFragment);
//        fragments.add(searchFragment);
//        fragments.add(userCenterFragment);
//        fragments.add(newsChannelFragment);
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_mood) {
                    position = 0;
                    System.out.println("position :"+position);
//                        dismissAnmiation();
//                        typeFragment.hideFragment();

                } else if (checkedId == R.id.rb_platform) {
                    position = 1;
                    System.out.println("position :"+position);
//                        dismissAnmiation();
                    //初始化数据
//                        int currentTab = typeFragment.getCurrentTab();
//                        if (currentTab == 0) {
//                            if (typeFragment.listFragment != null) {
//                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                                transaction.show(typeFragment.listFragment).commit();
//                            }
//                        } else {
//                            if (typeFragment.tagFragment != null) {
//                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                                transaction.show(typeFragment.tagFragment).commit();
//                            }
//                        }

                }else if (checkedId == R.id.rb_find) {
                    position = 2;
                    System.out.println("position :"+position);
//                        dismissAnmiation();
                    //初始化数据
//                        int currentTab = typeFragment.getCurrentTab();
//                        if (currentTab == 0) {
//                            if (typeFragment.listFragment != null) {
//                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                                transaction.show(typeFragment.listFragment).commit();
//                            }
//                        } else {
//                            if (typeFragment.tagFragment != null) {
//                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                                transaction.show(typeFragment.tagFragment).commit();
//                            }
//                        }

                }else if (checkedId == R.id.rb_deep) {
                    position = 3;
                    System.out.println("position :"+position);
//                        dismissAnmiation();
                    //初始化数据
//                        int currentTab = typeFragment.getCurrentTab();
//                        if (currentTab == 0) {
//                            if (typeFragment.listFragment != null) {
//                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                                transaction.show(typeFragment.listFragment).commit();
//                            }
//                        } else {
//                            if (typeFragment.tagFragment != null) {
//                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                                transaction.show(typeFragment.tagFragment).commit();
//                            }
//                        }

                }else if (checkedId == R.id.rb_me) {
                    position = 4;
                    System.out.println("position :"+position);
//                        dismissAnmiation();
                    //初始化数据
//                        int currentTab = typeFragment.getCurrentTab();
//                        if (currentTab == 0) {
//                            if (typeFragment.listFragment != null) {
//                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                                transaction.show(typeFragment.listFragment).commit();
//                            }
//                        } else {
//                            if (typeFragment.tagFragment != null) {
//                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                                transaction.show(typeFragment.tagFragment).commit();
//                            }
//                        }

                }

//                else if (checkedId == R.id.rb_type) {
//                    position = 2;
//                    System.out.println("position :"+position);
////                        dismissAnmiation();
//                    //初始化数据
////                        int currentTab = typeFragment.getCurrentTab();
////                        if (currentTab == 0) {
////                            if (typeFragment.listFragment != null) {
////                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
////                                transaction.show(typeFragment.listFragment).commit();
////                            }
////                        } else {
////                            if (typeFragment.tagFragment != null) {
////                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
////                                transaction.show(typeFragment.tagFragment).commit();
////                            }
////                        }
//
//                } else if (checkedId == R.id.rb_community) {
//                    position = 3;
//                    System.out.println("position :"+position);
////                        typeFragment.hideFragment();
//
//
//                } else if (checkedId == R.id.rb_cart) {
//                    //这个是搜索
//                    position = 4;
//                    System.out.println("position :"+position);
//
////                        fragments.remove(fragments.get(3));
////                        ShoppingCartFragment cartFragment = new ShoppingCartFragment();
////                        fragments.add(3, cartFragment);
////
////                        typeFragment.hideFragment();
//
//                } else if (checkedId == R.id.rb_user) {
//                    position = 5;
//                    System.out.println("position :"+position);
////                        dismissAnmiation();
////                        typeFragment.hideFragment();
//
//                }
////                else if(checkedId== R.id.rb_channel){
////                    position =6;
////                }

                Fragment baseFragment = getFragment(position);
                switchFragment(mContext, baseFragment);
            }
        });

        rgMain.check(R.id.rb_mood);

    }

    /**
     *
     * @param position
     * @return
     */
    private Fragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            Fragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    private void switchFragment(Fragment fromFragment, Fragment nextFragment) {
        System.out.println("inside switch");
        if (mContext != nextFragment) {
            mContext = nextFragment;
            if (nextFragment != null) {
                System.out.println("next fragment is not null");
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    System.out.println("not is added ");
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    System.out.println("add");
                    transaction.add(R.id.frameMarketLayout, nextFragment).commit();
                    System.out.println("finish add");
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

}
