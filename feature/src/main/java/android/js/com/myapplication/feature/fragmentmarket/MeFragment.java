package android.js.com.myapplication.feature.fragmentmarket;


import android.js.com.myapplication.feature.R;
import android.js.com.myapplication.feature.utils.ToastUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {


    // Content View Elements

    private ScrollView mScrollview;
    private ImageButton mIb_user_icon_avator;
    private TextView mTv_username;
    private TextView mTv_all_order;
    private TextView mTv_user_pay;
    private TextView mTv_user_receive;
    private TextView mTv_user_finish;
    private TextView mTv_user_drawback;
    private RelativeLayout mRl_collection;
    private ImageView mIv_collec;
    private RelativeLayout mRl_setting;
    private ImageView mIv_setting;


    private TextView mTv_setting;
    private TextView mTv_user_location;
    private TextView mTv_user_collect;
    private TextView mTv_user_coupon;
    private TextView mTv_user_score;
    private TextView mTv_user_prize;
    private TextView mTv_user_ticket;
    private TextView mTv_user_invitation;
    private TextView mTv_user_callcenter;
    private TextView mTv_user_feedback;
    private TextView mTv_usercenter;
    private ImageButton mIb_user_setting;
    private ImageButton mIb_user_message;

    // End Of Content View Elements

    private void bindViews(View view) {

        mScrollview = (ScrollView) view.findViewById(R.id.scrollview);
        mIb_user_icon_avator = (ImageButton) view.findViewById(R.id.ib_user_icon_avator);
        mTv_username = (TextView) view.findViewById(R.id.tv_username);
        mTv_all_order = (TextView) view.findViewById(R.id.tv_all_order);
        mTv_user_pay = (TextView) view.findViewById(R.id.tv_user_pay);
        mTv_user_receive = (TextView)view.findViewById(R.id.tv_user_receive);
        mTv_user_finish = (TextView) view.findViewById(R.id.tv_user_finish);
        mTv_user_drawback = (TextView) view.findViewById(R.id.tv_user_drawback);
        mRl_collection = (RelativeLayout) view.findViewById(R.id.rl_collection);
        mIv_collec = (ImageView) view.findViewById(R.id.iv_collect);
        mRl_setting = (RelativeLayout) view.findViewById(R.id.rl_setting);
        mIv_setting = (ImageView) view.findViewById(R.id.iv_setting);

        mTv_setting = (TextView) view.findViewById(R.id.tv_setting);

        mTv_user_location = (TextView) view.findViewById(R.id.tv_user_location);
        mTv_user_collect = (TextView) view.findViewById(R.id.tv_user_collect);
        mTv_user_coupon = (TextView) view.findViewById(R.id.tv_user_coupon);
        mTv_user_score = (TextView) view.findViewById(R.id.tv_user_score);
        mTv_user_prize = (TextView) view.findViewById(R.id.tv_user_prize);
        mTv_user_ticket = (TextView) view.findViewById(R.id.tv_user_ticket);
        mTv_user_invitation = (TextView) view.findViewById(R.id.tv_user_invitation);
        mTv_user_callcenter = (TextView) view.findViewById(R.id.tv_user_callcenter);
        mTv_user_feedback = (TextView) view.findViewById(R.id.tv_user_feedback);
        mTv_usercenter = (TextView) view.findViewById(R.id.tv_usercenter);
        mIb_user_setting = (ImageButton) view.findViewById(R.id.ib_user_setting);
        mIb_user_message = (ImageButton) view.findViewById(R.id.ib_user_message);
    }


    private void bindListeners(){
        mIv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("点击设置");
                //ToastUtils.show(null,"点击设置");
            }
        });


        mTv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("点击文本设置选项");
                //ToastUtils.show(null,"点击设置");
            }
        });

        mTv_user_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("点击用户收藏");
                //ToastUtils.show(null,"点击用户收藏");
            }
        });


        mTv_all_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("所有订单");
                //ToastUtils.show(null,"点击所有订单");
            }
        });
    }




    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_me, container, false);
        bindViews(view);
        bindListeners();
        return view;
    }

}
