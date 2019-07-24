package android.js.com.myapplication.feature.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.js.com.myapplication.feature.TopicListActivity;
import android.js.com.myapplication.feature.bean.ResultBean;
import android.js.com.myapplication.feature.fragment.adapter.ProductListRecycleAdapter;
import android.js.com.myapplication.feature.orm.Board;
import android.js.com.myapplication.feature.utils.Constants;
import android.js.com.myapplication.feature.utils.HttpsCert;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.js.com.myapplication.feature.R;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment {

    private ResultBean resultBean;
    private RecyclerView rvHome;
    private ImageView ib_top;
    private ProductListRecycleAdapter adapter;


    private Context context;


    //public Context mContext;


    public ProductListFragment() {
        // Required empty public constructor
    }


    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getActivity();
        System.out.println("产品列表11111");
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_product_list, container, false);

        initView(view);
        initData();

        return  view;
    }

    private void initView(View view){
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageView) view.findViewById(R.id.ib_top);
    }

    private void initData(){
        getNetWorkString(Constants.HOME_URL);
    }


    public void getNetWorkString(String url) {
        //String   url  = "https://192.168.191.1/boot/board/boards_json";//带https的网址
        final Request request = new Request.Builder().url(url).build();
        Call call  = new HttpsCert().setCard(getActivity()).newCall(request);
        final AppCompatActivity appCompatActivity = (AppCompatActivity)context;
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                appCompatActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("数据取得失败");
                        //Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
                Log.i("joker", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response)
                    throws IOException
            {
                final String res = response.body().string();
                Log.e("joker",res);
                System.out.println("收到的消息:"+res);
                appCompatActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("正常收到消息");

                        processData(res);
                        adapter = new ProductListRecycleAdapter(context, resultBean);
                        rvHome.setAdapter(adapter);

                        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
                        //设置滑动到哪个位置了的监听
                        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                            @Override
                            public int getSpanSize(int position) {
                                if (position <= 3) {
                                    ib_top.setVisibility(View.GONE);
                                } else {
                                    ib_top.setVisibility(View.VISIBLE);
                                }
                                return 1;
                            }
                        });
                        //设置网格布局
                        rvHome.setLayoutManager(manager);
                        initListener();

//                        boardList= new ArrayList<>();
//                        boardListStr = res;
//                        boardList = JSON.parseArray(boardListStr,Board.class);
//                        listView.setAdapter(new HomeFragment.MyAdapter(getContext()));
//
//                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                Board board = boardList.get(i);
//                                int boardId = board.getBoardId();
//                                Intent intent = new Intent();
//                                intent.setClass(getActivity(),TopicListActivity.class);
//                                intent.putExtra("string","canava");
//                                intent.putExtra("boardId",boardId);
//                                startActivity(intent);
//                                //Toast.makeText(ListViewActivity.this,book.toString(),Toast.LENGTH_LONG).show();
//                            }
//                        });


                        //Toast.makeText(MainActivity.this,res,Toast.LENGTH_LONG).show();

                        //Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        //2). 通过intent携带额外数据
//                        String message = res;
//                        intent.putExtra("MESSAGE", message);
//                        //3). 启动Activity
//                        startActivity(intent);


                    }
                });
            }
        });
    }


    private void processData(String res) {
        if (!TextUtils.isEmpty(res)) {
            //得到resultBean的数据

            JSONObject jsonObject = JSON.parseObject(res);
            //得到状态码
            String code = jsonObject.getString("code");
            String msg = jsonObject.getString("msg");
            String result = jsonObject.getString("result");

            //得到resultBean的数据
            resultBean = JSON.parseObject(result, ResultBean.class);
            System.out.println("res22222:"+res);
            //ResultBean resultBean = JSON.parseObject(res, ResultBean.class);
            //resultBean = new ResultBean();

            //设置BannerInfoBean数据
            List<ResultBean.BannerInfoBean> bannerInfoBeans =  resultBean.getBanner_info();

            System.out.println("bannerInfobeans size:"+bannerInfoBeans.size());
            //ResultBean.BannerInfoBean.ValueBean valueBean1= resultBean.getBanner_info().get(0).getValue();
            //设置actInfoBeans数据
            List<ResultBean.ActInfoBean> actInfoBeans = resultBean.getAct_info();

            //设置channelInfoBeans的数据
            List<ResultBean.ChannelInfoBean> channelInfoBeans = resultBean.getChannel_info();
            //设置hotInfoBeans的数据
            List<ResultBean.HotInfoBean> hotInfoBeans =resultBean.getHot_info();

            //设置recommendInfoBeans的数据
            List<ResultBean.RecommendInfoBean> recommendInfoBeans = resultBean.getRecommend_info();

            //设置seckillInfoBean的数据
            ResultBean.SeckillInfoBean seckillInfoBean = resultBean.getSeckill_info();
        }

    }


    private void initListener() {
        //置顶的监听
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvHome.scrollToPosition(0);
            }
        });

//        //搜素的监听
//        tv_search_home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        //消息的监听
//        tv_message_home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, MessageCenterActivity.class);
//                mContext.startActivity(intent);
//            }
//        });

    }


}
