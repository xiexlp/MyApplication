package android.js.com.myapplication.feature;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.js.com.myapplication.feature.bean.TopicDetailBean;
import android.js.com.myapplication.feature.customize.MyListView;
import android.js.com.myapplication.feature.orm.Topic;
import android.js.com.myapplication.feature.utils.Constants;
import android.js.com.myapplication.feature.utils.HttpRequestUrl;
import android.js.com.myapplication.feature.utils.HttpsCert;
import android.js.com.myapplication.feature.utils.PageList;
import android.js.com.myapplication.feature.utils.SizeUtils;
import android.js.com.myapplication.feature.utils.SpUtils;
import android.js.com.myapplication.feature.utils.ToastUtils;
import android.js.com.myapplication.feature.utils.Utils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrPosition;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

public class TopicDetailActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView usernameTextView;
    private TextView contentTextView;


    private MyListView replyListView;

    private TextView topic_title_text_view_toolbar;
    private Toolbar toolbar;


    int topciId;
    int boardId;
    Topic topic;

    private String Topic_Relative_Url="/boot/topic/topicattach?topicId="+topciId+"&boardId=14";


    private SlidrConfig mConfig;

    private EditText etContent;
    private Button btnSend;
    private ImageView ivMore;

    private int userId;
    private String username;

    //声明，放在声明中
//TextView et_main_message;
    TextView boardIdTv;

    //声明，放在声明中
//TextView et_main_message;
    Button backToBoardBtn;




    List<TopicDetailBean.ReplyListBean> replyListBeanList;
    MyReplyAdapter myReplyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_topic_detail3);




        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //隐藏标题栏,这个必须隐藏
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        //导航栏颜色
        getWindow().setNavigationBarColor(getResources().getColor(R.color.cardview_light_background));

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) actionBar.hide();
        //透明导航栏
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        titleTextView = findViewById(R.id.topic_title_text_view);
        usernameTextView = findViewById(R.id.username_text_view);
        contentTextView = findViewById(R.id.topic_content_text_view);

        topic_title_text_view_toolbar =findViewById(R.id.topic_title_text_view_toolbar);
        toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //toolbar.setVisible(View.GONE);
        etContent = findViewById(R.id.etContent);
        btnSend = findViewById(R.id.btnSend);
        ivMore = findViewById(R.id.ivMore);

        userId = Integer.parseInt(SpUtils.getKeyValue(getApplicationContext(),"userInfo","userId"));

        //4). 得到intent对象
        Intent intent = getIntent();
        //5). 通过intent读取额外数据
        topciId = intent.getIntExtra("topicId", 0);
        boardId = intent.getIntExtra("boardId",0);
        System.out.println("从ListActivity中得到的boardId:"+boardId);

        //实例化，放在onCreate方法中
        boardIdTv=findViewById(R.id.boardIdTv);
        boardIdTv.setText(Integer.toString(boardId));
        //实例化，放在onCreate方法中
        backToBoardBtn=findViewById(R.id.backToBoardBtn);

        initListener();
        slidrEffectStrength();

        String Topic_Relative_Url= Constants.URL_PRE+"/boot/topic/topicattach_json?topicId="+topciId+"&boardId="+boardId;

        getNetWorkString(Topic_Relative_Url);


    }

    private void initListener(){



        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etContent.getText().toString().trim().length() > 0) {
                    btnSend.setVisibility(View.VISIBLE);
                    ivMore.setVisibility(View.GONE);
                } else {
                    btnSend.setVisibility(View.GONE);
                    ivMore.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("快速回复主题");
                System.out.println("当前的主题id:"+topciId);

                TopicDetailBean.ReplyListBean replyListBean = new TopicDetailBean.ReplyListBean();
                replyListBean.setTopicId(topciId);
                replyListBean.setBoardId(boardId);
                replyListBean.setTitle("re:"+titleTextView.getText().toString());
                replyListBean.setCreateTime(System.currentTimeMillis());
                replyListBean.setUpdateTime(System.currentTimeMillis());
                replyListBean.setUsername(usernameTextView.getText().toString());
                replyListBean.setUserId(userId);
                replyListBean.setContent(etContent.getText().toString());
                replyListBean.setAvatar("https://191.168.191.1/111.jpg");

                postNetWorkString(HttpRequestUrl.REPLY_ADD_URL,replyListBean);

            }
        });


        //动作，放在onCreate方法中
        backToBoardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取值
                String boardIdTv_value= boardIdTv.getText().toString();
                int boardId = Integer.parseInt(boardIdTv_value);

                System.out.println("所属论坛Id:"+boardIdTv_value);

                Intent intent = new Intent();
                intent.setClass(TopicDetailActivity.this,TopicListActivity.class);
                intent.putExtra("string","canava");
                intent.putExtra("boardId",boardId);
                //intent.putExtra("boardName",board.getBoardName());
                startActivity(intent);

            }
        });


    }


    /**
     * 左右滑动退出效果增强
     */
    private void slidrEffectStrength(){
        // Get the status bar colors to interpolate between
        int primary = getResources().getColor(R.color.primaryDarkGreen);
        int secondary = getResources().getColor(R.color.red_500);

        // Build the slidr config
        int numPositions = SlidrPosition.values().length;
        SlidrPosition position = SlidrPosition.values()[Utils.getRandom().nextInt(numPositions)];
        //mPosition.setText(position.name());

        mConfig = new SlidrConfig.Builder()
                .primaryColor(primary)
                .secondaryColor(secondary)
                .position(SlidrPosition.HORIZONTAL)
                .velocityThreshold(2400)
//                .distanceThreshold(.25f)
//                .edge(true)
                .touchSize(SizeUtils.dpToPx(this, 32))
                .build();

        // Attach the Slidr Mechanism to this activity
        Slidr.attach(this, mConfig);
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
                System.out.println("收到的消息 具体的topic:" + res);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //Toast.makeText(TopicListActivity.this, res, Toast.LENGTH_LONG).show();
                        final TopicDetailBean topicDetailBean = JSON.parseObject(res, TopicDetailBean.class);

                        System.out.println("topic title:"+topicDetailBean.getTitle());
                        System.out.println("topic content:"+topicDetailBean.getContent());
                        System.out.println("topic reply list size:"+topicDetailBean.getReplies());
                        //topicPageList = JSON.parseObject(res, PageList.class);
                        titleTextView.setText(topicDetailBean.getTitle());
                        contentTextView.setText(Html.fromHtml(topicDetailBean.getContent()));

                        contentTextView.setMovementMethod(LinkMovementMethod.getInstance());
                        usernameTextView.setText(topicDetailBean.getUsername());

                        //topic_title_text_view_toolbar.setText(topicDetailBean.getTitle());
                        //replyListBeans = topicDetailBean.getReplyList();

                        for(TopicDetailBean.ReplyListBean replyListBean:topicDetailBean.getReplyList()){
                            System.out.println("得到的回复:"+replyListBean.getContent());
                        }
                        replyListView = findViewById(R.id.topic_reply_list_view);
                        replyListBeanList = topicDetailBean.getReplyList();
                        myReplyAdapter = new MyReplyAdapter(TopicDetailActivity.this,replyListBeanList);
                        System.out.println("adapter 初始化成功");
                        replyListView.setAdapter(myReplyAdapter);
                        System.out.println("list view 配置好 adapter");
                        //监听动作
                        replyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                TopicDetailBean.ReplyListBean replyListBean = topicDetailBean.getReplyList().get(i);
                                System.out.println("reply list bean content:"+replyListBean.getContent());
                                Toast.makeText(TopicDetailActivity.this,replyListBean.getContent().toString(),Toast.LENGTH_LONG).show();
                            }
                        });

                        setListViewHeightBasedOnChildren(replyListView);
                        System.out.println("是否已经执行？？？");
                        //System.out.println("topic page list size:"+topicPageList.size());
                        //topicListView = findViewById(R.id.topic_list_view);
                        //topicListView.setAdapter(new TopicListActivity.TopicListAdapter(getParent()));

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

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


    public void postNetWorkString(String url,TopicDetailBean.ReplyListBean replyListBean) {
        //String   url  = "https://192.168.191.1/boot/board/boards_json";//带https的网址

        FormBody body = new FormBody.Builder()
                .add("title",replyListBean.getTitle())
                .add("content",replyListBean.getContent())
                .add("avatar",replyListBean.getAvatar())
                .add("userId",Integer.toString(replyListBean.getUserId()))
                .add("username",replyListBean.getUsername())
                .add("boardId",Integer.toString(replyListBean.getBoardId()))
                .add("topicId",Integer.toString(replyListBean.getTopicId()))
                .add("langCode","zh-cn")
                .build();

        String sessionid= SpUtils.getSessionCookie(this,"sessionid");
        System.out.println("取出来的session id:"+sessionid);

        if(!StringUtils.isNoneEmpty(sessionid)){
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();

        }

        final Request request = new Request.Builder().url(url).post(body).addHeader("cookie",sessionid).build();


        Call call  = new HttpsCert().setCard(this).newCall(request);
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
                    throws IOException
            {
                final String res = response.body().string();
                Log.e("joker",res);
                System.out.println("收到的消息:"+res);
                System.out.println("增加回复成功");
                final TopicDetailBean.ReplyListBean replyListBean1=  JSON.parseObject(res, TopicDetailBean.ReplyListBean.class);


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("正常收到回复增加消息");
                        ToastUtils.show(TopicDetailActivity.this,"快速回复成功");
                        replyListBeanList.add(replyListBean1);
                        myReplyAdapter.notifyDataSetChanged();
                        //replyListView
                        // Call onRefreshComplete when the list has been refreshed.
                        //replyListView.onRefreshComplete();
                        //Toast.makeText(TopicDetailActivity.this, "快速回复成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    class MyReplyAdapter extends BaseAdapter {
        private Context context = null;
        private List<TopicDetailBean.ReplyListBean> replyListBeans;

        public MyReplyAdapter(Context context,List<TopicDetailBean.ReplyListBean> replyListBeans) {
            this.context = context;
            this.replyListBeans = replyListBeans;
            System.out.println("adapter 已经初始化");
            System.out.println("reply 的数量:"+replyListBeans.size());

        }
        @Override
        public int getCount() {
            return replyListBeans.size();
        }
        @Override
        public Object getItem(int position) {
            return replyListBeans.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            System.out.println("list view get view");
            MyReplyAdapter.ViewHolder mHolder;
            if (convertView == null) {
                mHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.item_listview_reply_list, null, true);
                mHolder.text_item_reply_title = (TextView) convertView.findViewById(R.id.text_item_reply_title);
                mHolder.text_item_reply_content = (TextView) convertView.findViewById(R.id.text_item_reply_content);

                mHolder.text_item_reply_username = (TextView) convertView.findViewById(R.id.text_item_reply_username);

                //mHolder.imageView_item_listview_headpic = (ImageView) convertView.findViewById(R.id.imageView_item_listview_headpic);
                convertView.setTag(mHolder);
            } else {
                mHolder = (MyReplyAdapter.ViewHolder) convertView.getTag();
            }
            String title = replyListBeans.get(position).getTitle().toString();
            System.out.println("list view item title:"+title);
            String username = replyListBeans.get(position).getUsername().toString();
            System.out.println("list view item username:"+username);
            String content = replyListBeans.get(position).getContent().toString();
            System.out.println("list view item content:"+content);
            //int picId = Integer.parseInt(list.get(position).get("headpic").toString());
            mHolder.text_item_reply_title.setText(username);
            mHolder.text_item_reply_username.setText(title);
            mHolder.text_item_reply_content.setText(content);
            //mHolder.imageView_item_listview_headpic.setImageResource(picId);
            return convertView;
        }

        class ViewHolder {
            private TextView text_item_reply_title;
            private TextView text_item_reply_username;
            private TextView text_item_reply_content;
            //private ImageView imageView_item_listview_headpic;
        }
    }


}
