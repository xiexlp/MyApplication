package android.js.com.myapplication.feature.fragmentmarket.submood;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.js.com.myapplication.feature.R;
import android.js.com.myapplication.feature.TopicDetailActivity;
import android.js.com.myapplication.feature.beanmarket.CoinMarketCapBean;
import android.js.com.myapplication.feature.utils.Constants;
import android.js.com.myapplication.feature.utils.HttpsCert;
import android.js.com.myapplication.feature.utils.PageList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by root on 2019/3/3.
 * 最开始写这个
 */

public class WatchListFragment extends Fragment {

    public WatchListFragment() {
        // Required empty public constructor
    }


    int boardId = 0;
    String boardName;
    ListView coinMarketCapListView;

    private PullToRefreshListView coinMarketCapListMPullRefreshListView;
    private LinkedList<CoinMarketCapBean.DataListBean> mListItems;

    private BaseAdapter mAdapter;

    List<CoinMarketCapBean.DataListBean> coinMarketCapDataList=new ArrayList<>();
    PageList<CoinMarketCapBean.DataListBean> coinMarketCapPageList;
    CoinMarketCapBean.DataListBean[] coinMarketCapDataBeans={};
    int pageNo=0;

    Map<Integer,CoinMarketCapBean.DataListBean> coinMarketCapDetailBeanMap;

    Button newTopicButton;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_rank_market, container, false);

        //4). 得到intent对象
        Intent intent = getActivity().getIntent();
        //5). 通过intent读取额外数据
        boardId = intent.getIntExtra("boardId", 1);
        boardName = intent.getStringExtra("boardName");
        String url = Constants.URL_PRE + "/boot/lion/currencyPricePartPage_microservice_json?pageNo=" + 1 + "&sort=1&sortBy=priceChange";

        //https://localhost/boot/lion/currencyPricePartPage_microservice?1=1&pageNo=1&sortBy=priceChange&sort=1

        coinMarketCapDetailBeanMap = new HashMap<>();
        getNetWorkString(url);

        return view;
    }


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        //设置状态栏颜色
//
//        StatusBarUtils.setWindowStatusBarColor(getActivity(),R.color.red_light);
//        //setStatusBarTranslucent();
//
//        getActivity().setContentView(R.layout.fragment_newest);
//
//        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
//        //getActivity().setSupportActionBar(toolbar);
//
//        //去掉Activity上面的状态栏
//        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        //隐藏标题栏,这个必须隐藏
//        //透明状态栏
//        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        //透明导航栏
//        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//
//
//        //ActionBar actionBar = getSupportActionBar();
//        //if(actionBar!=null) actionBar.hide();
//        //透明导航栏
//        //Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        //setSupportActionBar(mToolbar);
//
//
//        //4). 得到intent对象
//        Intent intent = getActivity().getIntent();
//        //5). 通过intent读取额外数据
//        boardId = intent.getIntExtra("boardId", 1);
//        boardName = intent.getStringExtra("boardName");
//        String url = Constants.URL_PRE + "/boot/topic/topics_global_json?boardId=" + boardId + "&type=0";
//        topicDetailBeanMap = new HashMap<>();
//        getNetWorkString(url);
//
//
//    }

    public void setStatusBarTranslucent() {
        WindowManager.LayoutParams localLayoutParams = getActivity().getWindow().getAttributes();
        localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
    }




    private class GetDataTask extends AsyncTask<Void, Void, CoinMarketCapBean.DataListBean[]> {

        @Override
        protected CoinMarketCapBean.DataListBean[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return coinMarketCapDataBeans;
        }

        @Override
        protected void onPostExecute(CoinMarketCapBean.DataListBean[] result) {

            if(coinMarketCapListMPullRefreshListView.getMode()== PullToRefreshBase.Mode.PULL_FROM_START){
                //下拉刷新
                //mListItems.addFirst("Added after refresh...");
                //mListItems.addFirst("数据下拉刷新请求的数据....");

                CoinMarketCapBean.DataListBean coinMarketCapBeanItem = new CoinMarketCapBean.DataListBean();

                mListItems.addFirst(coinMarketCapBeanItem);
                coinMarketCapDataList.add(0,coinMarketCapBeanItem);
                int size = coinMarketCapDataList.size();
                for(int i=0;i<size;i++){
                    CoinMarketCapBean.DataListBean detailBean = coinMarketCapDataList.get(i);
                    coinMarketCapDetailBeanMap.put(detailBean.getCurrencyPriceId(),detailBean);
                }
                //mListItems.addLast(topic);

                coinMarketCapListMPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);

            }else if(coinMarketCapListMPullRefreshListView.getMode()== PullToRefreshBase.Mode.PULL_FROM_END){
                //mListItems.addLast("数据上拉最后加入的数据...");

                CoinMarketCapBean.DataListBean coinMarketCapBeanItem = new CoinMarketCapBean.DataListBean();

                mListItems.addFirst(coinMarketCapBeanItem);

                //mListItems.addFirst(topic);
                mListItems.addLast(coinMarketCapBeanItem);
                //增加到最后
                coinMarketCapDataList.add(coinMarketCapBeanItem);
                int size = coinMarketCapDataList.size();
                for(int i=0;i<size;i++){
                    CoinMarketCapBean.DataListBean dataListBean = coinMarketCapDataList.get(i);
                    coinMarketCapDetailBeanMap.put(dataListBean.getCurrencyPriceId(),dataListBean);
                }
                coinMarketCapListMPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
            }

            mAdapter.notifyDataSetChanged();

            // Call onRefreshComplete when the list has been refreshed.
            coinMarketCapListMPullRefreshListView.onRefreshComplete();

            super.onPostExecute(result);
        }
    }


    public void getNetWorkString(String url) {
        //String   url  = "https://192.168.191.1/boot/board/boards_json";//带https的网址
        final Request request = new Request.Builder().url(url).build();
        Call call = new HttpsCert().setCard(getActivity()).newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                getActivity().runOnUiThread(new Runnable() {
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
                    throws IOException {
                final String res = response.body().string();
                Log.e("joker", res);
                System.out.println("收到的消息topic list:" + res);
                //如果不为空则执行，为空则不执行
                if(!StringUtils.isEmpty(res)) {

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

//                        newTopicButton = getActivity().findViewById(R.id.newTopicButton);
//
//                        newTopicButton.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                System.out.println("新增主题");
//                                //增加输入文本框
//                                Intent intent = new Intent(getActivity(),TopicNewActivity.class);
//                                intent.putExtra("boardId",boardId);
//                                intent.putExtra("boardName",boardName);
//                                startActivity(intent);
//                                //intent.putExtra("topicId",topic.getTopicId());
//                                //intent.putExtra("username",)
//
//                            }
//                        });

                            //Toast.makeText(TopicListActivity.this, res, Toast.LENGTH_LONG).show();
                            CoinMarketCapBean coinMarketCapBean = JSON.parseObject(res, CoinMarketCapBean.class);
                            mListItems = new LinkedList<>();
                            int l = coinMarketCapBean.getDataList().size();
                            coinMarketCapDataBeans = new CoinMarketCapBean.DataListBean[l];
                            for (int i = 0; i < l; i++) {
                                coinMarketCapDataBeans[i] = coinMarketCapBean.getDataList().get(i);
                                coinMarketCapDataList.add(coinMarketCapDataBeans[i]);
                                mListItems.add(coinMarketCapDataBeans[i]);
                                coinMarketCapDetailBeanMap.put(coinMarketCapDataBeans[i].getCurrencyPriceId(), coinMarketCapDataBeans[i]);
                            }

                            System.out.println("topic list size:" + coinMarketCapDataList.size());
//                        topicPageList = JSON.parseObject(res, PageList.class);
//                        int l = topicList.size();
//                        for(int i=0;i<l;i++){
//                            topics[i]= topicPageList.get(i);
//                        }
                            //System.out.println("topic page list size:"+topicPageList.size());
                            //topicListView = findViewById(R.id.pull_refresh_list_view_topic_list);
                            coinMarketCapListMPullRefreshListView = view.findViewById(R.id.pull_refresh_list_view_coinmarketcap_list);
                            coinMarketCapListMPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

                                @Override
                                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                                    //没有设置时间
                                    String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                            DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                                    System.out.println("时间:" + label);

                                    Toast.makeText(getActivity(), "下拉刷新Pull Down!", Toast.LENGTH_SHORT).show();

                                    coinMarketCapListMPullRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
                                    new WatchListFragment.GetDataTask().execute();
                                }

                                @Override
                                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                                    System.out.println("上拉得到刷新....");

                                    Toast.makeText(getActivity(), "上拉刷新111Pull Up!", Toast.LENGTH_SHORT).show();
                                    coinMarketCapListMPullRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
                                    new WatchListFragment.GetDataTask().execute();
                                }
                            });


                            ListView actualListView = coinMarketCapListMPullRefreshListView.getRefreshableView();
                            mAdapter = new WatchListFragment.CoinMarketCapListAdapter(getActivity());
                            actualListView.setAdapter(mAdapter);

                            //topicListView.setAdapter(new TopicListAdapter(getParent()));

                            //监听动作
                            actualListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    if (i == 0) i = 0;
                                    if (i > 0) i = i - 1;
                                    CoinMarketCapBean.DataListBean dataListBean = coinMarketCapDataList.get(i);
                                    int currencyPriceId = dataListBean.getCurrencyPriceId();
                                    int currencyId = dataListBean.getCurrencyId();

                                    TextView topic_id_text_view = (TextView) view.findViewById(R.id.topic_id_text_view);
                                    int topicId = Integer.parseInt(topic_id_text_view.getText().toString());
                                    TextView topic_title_text_view = (TextView) view.findViewById(R.id.text_item_listview_title);
                                    String title = topic_title_text_view.getText().toString();

                                    CoinMarketCapBean.DataListBean selectTopicDetail = coinMarketCapDetailBeanMap.get(topicId);
                                    System.out.println("选择的 topic title:" + title);

                                    Intent intent = new Intent((Context) getActivity(), TopicDetailActivity.class);
                                    //Intent intent1 = intent.setClass(TopicListActivity.class, TopicDetailActivity.class);
                                    //intent.putExtra("string","canava");
                                    //intent.putExtra("boardId",boardId);
                                    intent.putExtra("topicId", topicId);
                                    //intent.putExtra("boardId",selectTopicDetail.getBoardId());
                                    startActivity(intent);
                                    //Toast.makeText(ListViewActivity.this,book.toString(),Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    });
                }
            }
        });
    }




    class CoinMarketCapListAdapter extends BaseAdapter {
        private Context context = null;

        public CoinMarketCapListAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return mListItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mListItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final WatchListFragment.CoinMarketCapListAdapter.ViewHolder mHolder;
            if (convertView == null) {
                mHolder = new WatchListFragment.CoinMarketCapListAdapter.ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                convertView = inflater.inflate(R.layout.item_listview_main_topiclist, null, true);
                mHolder.topic_id_text_view = (TextView)convertView.findViewById(R.id.topic_id_text_view);
                mHolder.text_item_listview_title = (TextView) convertView.findViewById(R.id.text_item_listview_title);
                mHolder.text_item_listview_username = (TextView) convertView.findViewById(R.id.text_item_listview_username);
                mHolder.imageView_item_listview_topic_pic = (ImageView) convertView.findViewById(R.id.imageView_item_listview_topicpic);
                convertView.setTag(mHolder);
            } else {
                mHolder = (WatchListFragment.CoinMarketCapListAdapter.ViewHolder) convertView.getTag();
            }

            CoinMarketCapBean.DataListBean dataListBean = mListItems.get(position);
            String title = dataListBean.getName();
            String username = dataListBean.getSymbol();
            //int topicId = topicDetailBean.getTopicId();
            //int picId = Integer.parseInt(boardList.get(position).get("headpic").toString());

            //String username = topicPageList.get(position).getUsername();
            //if(position<=0) return null;

            CoinMarketCapBean.DataListBean dataListBean1
                    = mListItems.get(position);
            String picStrSlash ="";

//            String avatar=dataListBean1.getAvatar();
//            if(avatar!=null) {
//                avatar= avatar.replace("\\", "/");
//            }else avatar="";
            mHolder.text_item_listview_title.setText(title);
            mHolder.text_item_listview_username.setText(username);
            mHolder.topic_id_text_view.setText(title);
            //mHolder.imageView_item_listview_headpic
            //System.out.println("glide pic url:"+picStr);
            //glide不能显示https

//            String url1 = "https://ad.12306.cn/res/delivery/0001/2017/08/31/201708311634229711.jpg";
//            String url = Constants.URL_PRE + avatar;
//            System.out.println("下载图片的url:" + url);
//
//
//            //String   url  = "https://192.168.191.1/boot/board/boards_json";//带https的网址
//            final Request request = new Request.Builder().url(url).build();
//            Call call = new HttpsCert().setCard(getActivity()).newCall(request);
//
//            call.enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, final IOException e) {
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            //Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
//                        }
//                    });
//                    Log.i("下载图片joker出错了:", e.getMessage());
//                }
//
//                @Override
//                public void onResponse(Call call, Response response)
//                        throws IOException {
//                    final InputStream res = response.body().byteStream();
//                    //Log.e("joker",res);
//                    System.out.println("收到图片消息:" + res);
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            //Toast.makeText(MainActivity.this,res,Toast.LENGTH_LONG).show();
//                            Bitmap bitmap = BitmapFactory.decodeStream(res);
//                            if(bitmap==null)return;
//                            mHolder.imageView_item_listview_topic_pic.setImageBitmap(bitmap);
//
//                        }
//                    });
//                }
//            });

            return convertView;
        }

        class ViewHolder {
            private TextView topic_id_text_view;
            private TextView text_item_listview_title;
            private TextView text_item_listview_username;
            private ImageView imageView_item_listview_topic_pic;
        }

    }


}
