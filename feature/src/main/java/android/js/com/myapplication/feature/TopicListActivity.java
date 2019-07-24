package android.js.com.myapplication.feature;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.js.com.myapplication.feature.bean.TopicDetailBean;
import android.js.com.myapplication.feature.fragment.HomeFragment;
import android.js.com.myapplication.feature.orm.Board;
import android.js.com.myapplication.feature.orm.Topic;
import android.js.com.myapplication.feature.pullrefresh.PullToRefreshListActivity;
import android.js.com.myapplication.feature.utils.Constants;
import android.js.com.myapplication.feature.utils.HttpRequestUrl;
import android.js.com.myapplication.feature.utils.HttpsCert;
import android.js.com.myapplication.feature.utils.PageList;
import android.js.com.myapplication.feature.utils.StatusBarUtils;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
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

public class TopicListActivity extends AppCompatActivity {

    int boardId = 0;
    String boardName;
    ListView topicListView;

    private PullToRefreshListView toppicListMPullRefreshListView;
    private LinkedList<TopicDetailBean> mListItems;

    private BaseAdapter mAdapter;

    List<TopicDetailBean> topicList;
    PageList<TopicDetailBean> topicPageList;
    TopicDetailBean[] topics={};
    int pageNo=0;

    Map<Integer,TopicDetailBean> topicDetailBeanMap;

    Button newTopicButton;

    TextView tv_last_update_time;
    //声明，放在声明中
//TextView et_main_message;
    TextView tv_first_update_time;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置状态栏颜色

        StatusBarUtils.setWindowStatusBarColor(TopicListActivity.this,R.color.red_light);
        //setStatusBarTranslucent();

        setContentView(R.layout.activity_topic_list);

        //实例化，放在onCreate方法中
        tv_last_update_time=findViewById(R.id.tv_last_update_time);
        //实例化，放在onCreate方法中
        tv_first_update_time=findViewById(R.id.tv_first_update_time);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //去掉Activity上面的状态栏
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //隐藏标题栏,这个必须隐藏
        //透明状态栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);


        //ActionBar actionBar = getSupportActionBar();
        //if(actionBar!=null) actionBar.hide();
        //透明导航栏
        //Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(mToolbar);


        //4). 得到intent对象
        Intent intent = getIntent();
        //5). 通过intent读取额外数据
        boardId = intent.getIntExtra("boardId", 0);
        boardName = intent.getStringExtra("boardName");
        String url = Constants.URL_PRE + "/boot/topic/topics_json?boardId=" + boardId + "&type=0";
        topicDetailBeanMap = new HashMap<>();

        newTopicButton = findViewById(R.id.newTopicButton);

        newTopicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("新增主题");
                //增加输入文本框
                Intent intent = new Intent(TopicListActivity.this,TopicNewActivity.class);
                intent.putExtra("boardId",boardId);
                intent.putExtra("boardName",boardName);
                startActivity(intent);
                //intent.putExtra("topicId",topic.getTopicId());
                //intent.putExtra("username",)

            }
        });

        getNetWorkString(url);


    }

    public void setStatusBarTranslucent() {
        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
    }




    private class GetDataTask extends AsyncTask<Void, Void, TopicDetailBean[]> {

        @Override
        protected TopicDetailBean[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return topics;
        }

        @Override
        protected void onPostExecute(TopicDetailBean[] result) {

			if(toppicListMPullRefreshListView.getMode()== PullToRefreshBase.Mode.PULL_FROM_START){
				//下拉刷新
				//mListItems.addFirst("Added after refresh...");
				//mListItems.addFirst("数据下拉刷新请求的数据....");


                //取值
                String  tv_last_update_time_value= tv_last_update_time.getText().toString();
                long lastUpdateTime = System.currentTimeMillis();
                if(StringUtils.isEmpty(tv_last_update_time_value)){
                    lastUpdateTime = System.currentTimeMillis();
                }

                lastUpdateTime = Long.parseLong(tv_last_update_time_value);
                String topics_last_update_json_url = HttpRequestUrl.TOPICS_LAST_UPDATE_JSON_URL+"?boardId="+boardId+"&lastUpdateTime="+lastUpdateTime;

                getNewTopicNetWorkString(topics_last_update_json_url,0);



//                TopicDetailBean topic = new TopicDetailBean();
//                topic.setTitle("1233下拉刷新");
//                topic.setContent("hahah");
//                topic.setUserId(1);
//                topic.setUsername("canava");
//                topic.setAvatar("aaaa.jpg");


			}else if(toppicListMPullRefreshListView.getMode()== PullToRefreshBase.Mode.PULL_FROM_END){
				//mListItems.addLast("数据上拉最后加入的数据...");

//                TopicDetailBean topic = new TopicDetailBean();
//                topic.setTitle("1233上拉刷新加入的数据");
//                topic.setContent("hahah");
//                topic.setUserId(1);
//                topic.setUsername("canava");
//                topic.setAvatar("aaaa.jpg");


                //取值
                String  tv_first_update_time_value= tv_first_update_time.getText().toString();
                long firstUpdateTime = Long.parseLong(tv_first_update_time_value);
                String topics_first_update_json_url = HttpRequestUrl.TOPICS_PRE_UPDATE_JSON_URL+"?boardId="+boardId+"&firstUpdateTime="+firstUpdateTime;

                getNewTopicNetWorkString(topics_first_update_json_url,1);


                //mListItems.addFirst(topic);
                //mListItems.addLast(topic);
                //增加到最后
                //topicList.add(topic);
               // int size = topicList.size();
//                for(int i=0;i<size;i++){
//                    TopicDetailBean topicDetailBean = topicList.get(i);
//                    topicDetailBeanMap.put(topicDetailBean.getTopicId(),topicDetailBean);
//                }

                toppicListMPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
			}
            mAdapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            toppicListMPullRefreshListView.onRefreshComplete();
            super.onPostExecute(result);
        }
    }


    public void getNewTopicNetWorkString(String url, final int mode) {
        //String   url  = "https://192.168.191.1/boot/board/boards_json";//带https的网址

        System.out.println("新主题请求地址:"+url);

        final Request request = new Request.Builder().url(url).build();
        Call call = new HttpsCert().setCard(this).newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(TopicListActivity.this, res, Toast.LENGTH_LONG).show();
                        List<TopicDetailBean> newTopicList = JSON.parseArray(res, TopicDetailBean.class);
                        //mListItems = new LinkedList<>();
                        int l = newTopicList.size();
                        //没有新消息，不动
                        if(l<=0) return;
                        //topics = new TopicDetailBean[l];
                        //下拉
                        if(mode==0) {
                            tv_last_update_time.setText(Long.toString(newTopicList.get(0).getUpdateTime()));
                            for (int i = 0; i < l; i++) {
                                TopicDetailBean topic = newTopicList.get(i);
                                mListItems.addFirst(topic);
                                topicDetailBeanMap.put(topic.getTopicId(), topic);
                                topicList.add(0, topic);
                            }
                        }
                        //上拉
                        else if(mode==1){
                            //更新为最后一条记录的时间
                            tv_first_update_time.setText(Long.toString(newTopicList.get(l-1).getUpdateTime()));
                            for (int i = 0; i < l; i++) {
                                TopicDetailBean topic = newTopicList.get(i);
                                mListItems.addLast(topic);
                                topicDetailBeanMap.put(topic.getTopicId(), topic);
                                topicList.add(topic);
                            }
                        }

                        //mListItems.addFirst(topic);

//                        int size = topicList.size();
//                        for(int i=0;i<size;i++){
//                            TopicDetailBean topicDetailBean = topicList.get(i);
//                            topicDetailBeanMap.put(topicDetailBean.getTopicId(),topicDetailBean);
//                        }
                        //mListItems.addLast(topic);


                        mAdapter.notifyDataSetChanged();
                        // Call onRefreshComplete when the list has been refreshed.
                        toppicListMPullRefreshListView.onRefreshComplete();
                        toppicListMPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
                        //super.onPostExecute(result);
                        //mAdapter.notifyDataSetChanged();
                        // Call onRefreshComplete when the list has been refreshed.
                        //toppicListMPullRefreshListView.onRefreshComplete();

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


    public void getNetWorkString(String url) {
        //String   url  = "https://192.168.191.1/boot/board/boards_json";//带https的网址
        final Request request = new Request.Builder().url(url).build();
        Call call = new HttpsCert().setCard(this).newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //Toast.makeText(TopicListActivity.this, res, Toast.LENGTH_LONG).show();
                        topicList = JSON.parseArray(res, TopicDetailBean.class);
                        //第一次
                        mListItems = new LinkedList<>();
                        int l = topicList.size();
                        topics = new TopicDetailBean[l];
                        //如有最新的主题，最后更新时间设置一下
                        if(l>0) {
                            tv_last_update_time.setText(Long.toString(topicList.get(0).getUpdateTime()));
                            //取值
                            tv_first_update_time.setText(Long.toString(topicList.get(l - 1).getUpdateTime()));
                        }
                        for(int i=0;i<l;i++){
                            topics[i]= topicList.get(i);
                            mListItems.add(topicList.get(i));
                            topicDetailBeanMap.put(topics[i].getTopicId(),topics[i]);
                        }

                       System.out.println("topic list size:"+topicList.size());
//                        topicPageList = JSON.parseObject(res, PageList.class);
//
//
//                        int l = topicList.size();
//                        for(int i=0;i<l;i++){
//                            topics[i]= topicPageList.get(i);
//                        }

                        //System.out.println("topic page list size:"+topicPageList.size());
                        //topicListView = findViewById(R.id.pull_refresh_list_view_topic_list);

                        toppicListMPullRefreshListView= findViewById(R.id.pull_refresh_list_view_topic_list);

                        toppicListMPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

                            @Override
                            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                                //没有设置时间

                                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                                System.out.println("时间:"+label);
                                //tv_last_update_time.setText(Long.toString(System.currentTimeMillis()));

                                Toast.makeText(TopicListActivity.this, "下拉刷新Pull Down!", Toast.LENGTH_SHORT).show();

                                toppicListMPullRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
                                new TopicListActivity.GetDataTask().execute();


                            }

                            @Override
                            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                                System.out.println("上拉得到刷新....");

                                Toast.makeText(TopicListActivity.this, "上拉刷新111Pull Up!", Toast.LENGTH_SHORT).show();
                                toppicListMPullRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
                                new TopicListActivity.GetDataTask().execute();
                            }
                        });


                        ListView actualListView = toppicListMPullRefreshListView.getRefreshableView();
                        mAdapter =new TopicListAdapter(getParent());
                        actualListView.setAdapter(mAdapter);

                        //topicListView.setAdapter(new TopicListAdapter(getParent()));

                        //监听动作
                        actualListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                                if(i==0) i=0;
                                if(i>0) i=i-1;
                                TopicDetailBean topic = topicList.get(i);
                                int boardId = topic.getBoardId();


                                TextView topic_id_text_view = (TextView) view.findViewById(R.id.topic_id_text_view);
                                int topicId = Integer.parseInt(topic_id_text_view.getText().toString());
                                TextView topic_title_text_view = (TextView)view.findViewById(R.id.text_item_listview_title);
                                String title = topic_title_text_view.getText().toString();


                                TopicDetailBean selectTopicDetail= topicDetailBeanMap.get(topicId);
                                System.out.println("选择的 topic title:"+title);

                                Intent intent = new Intent((Context) TopicListActivity.this, TopicDetailActivity.class);
                                //Intent intent1 = intent.setClass(TopicListActivity.class, TopicDetailActivity.class);
                                //intent.putExtra("string","canava");
                                //intent.putExtra("boardId",boardId);
                                intent.putExtra("topicId",topicId);
                                intent.putExtra("boardId",selectTopicDetail.getBoardId());

                                startActivity(intent);
                                //Toast.makeText(ListViewActivity.this,book.toString(),Toast.LENGTH_LONG).show();
                            }
                        });




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




    class TopicListAdapter extends BaseAdapter {
        private Context context = null;

        public TopicListAdapter(Context context) {
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
            final TopicListAdapter.ViewHolder mHolder;
            if (convertView == null) {
                mHolder = new TopicListAdapter.ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                convertView = inflater.inflate(R.layout.item_listview_main_topiclist, null, true);
                mHolder.topic_id_text_view = (TextView)convertView.findViewById(R.id.topic_id_text_view);
                mHolder.text_item_listview_title = (TextView) convertView.findViewById(R.id.text_item_listview_title);
                mHolder.text_item_listview_username = (TextView) convertView.findViewById(R.id.text_item_listview_username);
                mHolder.imageView_item_listview_topic_pic = (ImageView) convertView.findViewById(R.id.imageView_item_listview_topicpic);
                convertView.setTag(mHolder);
            } else {
                mHolder = (TopicListAdapter.ViewHolder) convertView.getTag();
            }

            TopicDetailBean topicDetailBean = mListItems.get(position);
            String title = topicDetailBean.getTitle().toString();
            String username = topicDetailBean.getUsername().toString();
            int topicId = topicDetailBean.getTopicId();
            //int picId = Integer.parseInt(boardList.get(position).get("headpic").toString());

            //String username = topicPageList.get(position).getUsername();
            //if(position<=0) return null;

            TopicDetailBean topicDetailBean1
             = mListItems.get(position);
            String picStrSlash ="";

            String avatar=topicDetailBean1.getAvatar();
            if(avatar!=null) {
             avatar= avatar.replace("\\", "/");
            }else avatar="";
            mHolder.text_item_listview_title.setText(title);
            mHolder.text_item_listview_username.setText(username);
            mHolder.topic_id_text_view.setText(Integer.toString(topicId));
            //mHolder.imageView_item_listview_headpic
            //System.out.println("glide pic url:"+picStr);
            //glide不能显示https

            String url1 = "https://ad.12306.cn/res/delivery/0001/2017/08/31/201708311634229711.jpg";
            String url = Constants.URL_PRE + avatar;
            System.out.println("下载图片的url:" + url);


            //String   url  = "https://192.168.191.1/boot/board/boards_json";//带https的网址
            final Request request = new Request.Builder().url(url).build();
            Call call = new HttpsCert().setCard(getParent()).newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            //Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                    Log.i("下载图片joker出错了:", e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response)
                        throws IOException {
                    final InputStream res = response.body().byteStream();
                    //Log.e("joker",res);
                    System.out.println("收到图片消息:" + res);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(MainActivity.this,res,Toast.LENGTH_LONG).show();
                            Bitmap bitmap = BitmapFactory.decodeStream(res);
                            mHolder.imageView_item_listview_topic_pic.setImageBitmap(bitmap);

                        }
                    });
                }
            });

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
