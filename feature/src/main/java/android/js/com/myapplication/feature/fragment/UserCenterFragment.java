package android.js.com.myapplication.feature.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.js.com.myapplication.feature.LoginActivity;
import android.js.com.myapplication.feature.MessageCenterActivity;
import android.js.com.myapplication.feature.fragment.eventbus.LoginEvent;
import android.js.com.myapplication.feature.utils.SPkey;
import android.js.com.myapplication.feature.utils.SpUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.js.com.myapplication.feature.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserCenterFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "UserCenterFragment";
    public Context mContext;

    private ScrollView scrollview;
    private ImageButton ibUserIconAvator;
    private TextView tvUsername;
    private TextView tvAllOrder;
    private TextView tvUserPay;
    private TextView tvUserReceive;
    private TextView tvUserFinish;
    private TextView tvUserDrawback;
    private TextView tvUserLocation;
    private TextView tvUserCollect;
    private TextView tvUserCoupon;
    private TextView tvUserScore;
    private TextView tvUserPrize;
    private TextView tvUserTicket;
    private TextView tvUserInvitation;
    private TextView tvUserCallcenter;
    private TextView tvUserFeedback;
    private TextView tvUsercenter;
    private ImageButton ibUserSetting;
    private ImageButton ibUserMessage;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-02-28 09:27:27 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View v) {
        scrollview = (ScrollView)v.findViewById( R.id.scrollview );
        ibUserIconAvator = (ImageButton)v.findViewById( R.id.ib_user_icon_avator );
        tvUsername = (TextView)v.findViewById( R.id.tv_username );
        tvAllOrder = (TextView)v.findViewById( R.id.tv_all_order );
        tvUserPay = (TextView)v.findViewById( R.id.tv_user_pay );
        tvUserReceive = (TextView)v.findViewById( R.id.tv_user_receive );
        tvUserFinish = (TextView)v.findViewById( R.id.tv_user_finish );
        tvUserDrawback = (TextView)v.findViewById( R.id.tv_user_drawback );
        tvUserLocation = (TextView)v.findViewById( R.id.tv_user_location );
        tvUserCollect = (TextView)v.findViewById( R.id.tv_user_collect );
        tvUserCoupon = (TextView)v.findViewById( R.id.tv_user_coupon );
        tvUserScore = (TextView)v.findViewById( R.id.tv_user_score );
        tvUserPrize = (TextView)v.findViewById( R.id.tv_user_prize );
        tvUserTicket = (TextView)v.findViewById( R.id.tv_user_ticket );
        tvUserInvitation = (TextView)v.findViewById( R.id.tv_user_invitation );
        tvUserCallcenter = (TextView)v.findViewById( R.id.tv_user_callcenter );
        tvUserFeedback = (TextView)v.findViewById( R.id.tv_user_feedback );
        tvUsercenter = (TextView)v.findViewById( R.id.tv_usercenter );
        ibUserSetting = (ImageButton)v.findViewById( R.id.ib_user_setting );
        ibUserMessage = (ImageButton)v.findViewById( R.id.ib_user_message );

        ibUserIconAvator.setOnClickListener( this );
        ibUserSetting.setOnClickListener( this );
        ibUserMessage.setOnClickListener( this );

    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-02-28 09:27:27 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == ibUserIconAvator ) {
            //登录
            Intent intent = new Intent(mContext, LoginActivity.class);
//            startActivityForResult(intent, 0);
            startActivity(intent);

            // Handle clicks for ibUserIconAvator
        } else if ( v == ibUserSetting ) {
            Toast.makeText(mContext, "设置", Toast.LENGTH_SHORT).show();
            // Handle clicks for ibUserSetting
        } else if ( v == ibUserMessage ) {
            Intent intent = new Intent(mContext, MessageCenterActivity.class);
            startActivity(intent);
            // Handle clicks for ibUserMessage
        }
    }


    public UserCenterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        System.out.println("fragment创建调用Activity方法");
//        //去除title
//        getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //去掉Activity上面的状态栏
//        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        //隐藏标题栏
//        ActionBar actionBar =((AppCompatActivity) getActivity()).getSupportActionBar();
//        actionBar.hide();
        mContext = getActivity();
        //event bus使用
        //注册
    }

    //加上Subscribe注解,这个方法会在事件发出后收到回调,方法名是自定义的
//    @Subscribe
//    public void onLoingEvent(LoginEvent event) {
//        Log.i(TAG, "onLoingEvent: ");
//        System.out.println("用户名:"+event.getUsername()+" userId:"+event.getUserId());
//        tvUsername.setText(event.getUsername());
//    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyLoginEvent(LoginEvent event) {
        System.out.println("收到stick消息粘性消息用户名:"+event.getUsername()+" userId:"+event.getUserId());

        if(tvUsername==null) {
            System.out.println("还没初始化图像");
        }
        tvUsername.setText(event.getUsername());
    }



//    public static class MessageEvent {
//  /* Additional fields if needed */
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //mContext = getActivity();
        System.out.println("fragment创建调用Activity方法");

        View view= inflater.inflate(R.layout.fragment_user_center, container, false);

        findViews(view);
        EventBus.getDefault().register(this);

        //查看是否已经登录
        String username= SpUtils.getKeyValue(getActivity().getApplicationContext(),"userInfo","username");
        System.out.println("取出来的用户名:"+username);
        String pwd = SpUtils.getKeyValue(getActivity().getApplicationContext(),"userInfo","pwd");
        System.out.println("取出来的密码:"+pwd);
        String autoLoginStr= SpUtils.getKeyValue(getActivity().getApplicationContext(), SPkey.USERINFO,SPkey.AUTOLOGIN);
        System.out.println("是否自动登录:"+autoLoginStr);
        String rememberUserStr = SpUtils.getKeyValue(getActivity().getApplicationContext(),SPkey.REMEMBERUSER,SPkey.REMEMBERUSER);
        System.out.println("是否记住用户:"+rememberUserStr);
        boolean rememberUser = Boolean.getBoolean(rememberUserStr);
        boolean autoLogin = Boolean.getBoolean(autoLoginStr);

        Toast.makeText(getActivity(), "用户名:" + username + " 密码:" + pwd, Toast.LENGTH_SHORT).show();

        Toast.makeText(getActivity(), "自动登录:" + autoLoginStr + " 是否记录用户:" + rememberUserStr, Toast.LENGTH_SHORT).show();

        tvUsername.setText(username);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("activity created 活动创建");
        //去除title
        //getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        //getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //隐藏标题栏
        ActionBar actionBar =((AppCompatActivity) getActivity()).getSupportActionBar();
        if(actionBar!=null)  actionBar.hide();

        initData();
    }

    private void initData(){

    }

}
