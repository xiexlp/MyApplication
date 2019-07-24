package android.js.com.myapplication.feature.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by root on 2019/3/6.
 */

public class SearchResultBean {


    @JSONField(name = "pageInfo")
    private PageInfoBean _$PageInfo181; // FIXME check this code
    private List<HitListBean> hitList;

    public PageInfoBean get_$PageInfo181() {
        return _$PageInfo181;
    }

    public void set_$PageInfo181(PageInfoBean _$PageInfo181) {
        this._$PageInfo181 = _$PageInfo181;
    }

    public List<HitListBean> getHitList() {
        return hitList;
    }

    public void setHitList(List<HitListBean> hitList) {
        this.hitList = hitList;
    }

    public static class PageInfoBean {
        /**
         * total : 12
         * pageTotal : 1
         * pageNo : 1
         * actionUrl : /boot/lion/searchmicroservice?query=intent
         * pageSize : 20
         */

        private int total;
        private int pageTotal;
        private int pageNo;
        private String actionUrl;
        private int pageSize;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageTotal() {
            return pageTotal;
        }

        public void setPageTotal(int pageTotal) {
            this.pageTotal = pageTotal;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public String getActionUrl() {
            return actionUrl;
        }

        public void setActionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }

    public static class HitListBean {
        /**
         * postionList : [{"fieldname":"content","query":"intent","wordPos":null,"posList":[2967,6046,6063,6070,6108,6139,6193,11296,11303,11316,11403,11471,11557,11600,11681,16062,16069,16082,16185,16192,16202,16305,16375,16443,16511,16616,16823,16830,16843,16922,17009,17129]}]
         * score : 48.0
         * hitCount : 0
         * distance : 0.0
         * docId : 713
         * query : null
         * titleCount : 0
         * title : TopicListActivity完美的解决了沉浸式的布局
         * body : 非常好看 <?xml version="1.0" encoding="utf-8"?> <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"     xmlns:app="http://schemas.android.com/apk/res-auto"     xmlns:tools="http://schemas.android.com/tools"     android:layout_width="match_parent"     android:layout_height="match_parent"     android:orientation="vertical"     android:clipToPadding="true"     android:fitsSystemWindows="true"     tools:context="android.js.com.myapplication.feature.TopicListActivity">     <android.support.v7.widget.Toolbar         android:id="@+id/toolbar"         android:layout_height="wrap_content"         android:layout_width="match_parent"         android:minHeight="?attr/actionBarSize">         <Button             android:id="@+id/newTopicButton"             android:layout_width="wrap_content"             android:layout_height="wrap_content"             android:text="新增主题"             tools:layout_editor_absoluteX="300dp"             tools:layout_editor_absoluteY="200dp" />     </android.support.v7.widget.Toolbar>     <!--<android.support.v7.widget.Toolbar-->         <!--android:id="@+id/toolbar"-->         <!--android:layout_width="match_parent"-->         <!--app:title="App Title"-->         <!--app:subtitle="Sub Title"-->         <!--app:navigationIcon="@android:drawable/ic_input_add"-->         <!--android:layout_height="wrap_content"-->         <!--android:background="?attr/colorPrimary"-->         <!--android:fitsSystemWindows="true"-->         <!--android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"-->         <!--app:popupTheme="@style/ThemeOverlay.AppCompat.Light">-->         <!--<Button-->             <!--android:id="@+id/newTopicButton"-->             <!--android:layout_width="wrap_content"-->             <!--android:layout_height="wrap_content"-->             <!--android:text="新增主题"-->             <!--tools:layout_editor_absoluteX="300dp"-->             <!--tools:layout_editor_absoluteY="200dp" />-->     <!--</android.support.v7.widget.Toolbar>-->     <!--<ListView-->         <!--android:id="@+id/topic_list_view"-->         <!--android:layout_width="match_parent"-->         <!--android:layout_height="match_parent"></ListView>-->     <com.handmark.pulltorefresh.library.PullToRefreshListView         xmlns:ptr="http://schemas.android.com/apk/res-auto"         android:id="@+id/pull_refresh_list_view_topic_list"         android:layout_width="fill_parent"         android:layout_height="fill_parent"         android:cacheColorHint="#00000000"         android:divider="#19000000"         android:dividerHeight="4dp"         android:fadingEdge="none"         android:fastScrollEnabled="false"         android:footerDividersEnabled="false"         android:headerDividersEnabled="false"         android:smoothScrollbar="true"         ptr:ptrMode="both"         />     </LinearLayout> package android.js.com.myapplication.feature; import android.app.Activity; import android.content.Context; import android.content.<span style="color:red;">intent</span>; import android.graphics.Bitmap; import android.graphics.BitmapFactory; import android.js.com.myapplication.feature.bean.TopicDetailBean; import android.js.com.myapplication.feature.fragment.HomeFragment; import android.js.com.myapplication.feature.orm.Board; import android.js.com.myapplication.feature.orm.Topic; import android.js.com.myapplication.feature.pullrefresh.PullToRefreshListActivity; import android.js.com.myapplication.feature.utils.Constants; import android.js.com.myapplication.feature.utils.HttpsCert; import android.js.com.myapplication.feature.utils.PageList; import android.os.AsyncTask; import android.support.v7.app.ActionBar; import android.support.v7.app.AppCompatActivity; import android.os.Bundle; import android.support.v7.widget.Toolbar; import android.text.format.DateUtils; import android.util.Log; import android.view.LayoutInflater; import android.view.View; import android.view.ViewGroup; import android.view.WindowManager; import android.widget.AdapterView; import android.widget.ArrayAdapter; import android.widget.BaseAdapter; import android.widget.Button; import android.widget.ImageView; import android.widget.ListAdapter; import android.widget.ListView; import android.widget.TextView; import android.widget.Toast; import com.alibaba.fastjson.JSON; import com.handmark.pulltorefresh.library.PullToRefreshBase; import com.handmark.pulltorefresh.library.PullToRefreshListView; import java.io.IOException; import java.io.InputStream; import java.util.ArrayList; import java.util.HashMap; import java.util.LinkedList; import java.util.List; import java.util.Map; import okhttp3.Call; import okhttp3.Callback; import okhttp3.Request; import okhttp3.Response; public class TopicListActivity extends AppCompatActivity {     int boardId = 0;     String boardName;     ListView topicListView;     private PullToRefreshListView toppicListMPullRefreshListView;     private LinkedList<TopicDetailBean> mListItems;     private BaseAdapter mAdapter;     List<TopicDetailBean> topicList;     PageList<TopicDetailBean> topicPageList;     TopicDetailBean[] topics={};     int pageNo=0;     Map<Integer,TopicDetailBean> topicDetailBeanMap;     Button newTopicButton;     @Override     protected void onCreate(Bundle savedInstanceState) {         super.onCreate(savedInstanceState);         setContentView(R.layout.activity_topic_list);         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);         setSupportActionBar(toolbar);         //去掉Activity上面的状态栏         //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);         //隐藏标题栏,这个必须隐藏         //透明状态栏         getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);         //透明导航栏         getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);         //ActionBar actionBar = getSupportActionBar();         //if(actionBar!=null) actionBar.hide();         //透明导航栏         //Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);         //setSupportActionBar(mToolbar);         //4). 得到<span style="color:red;">intent</span>对象         <span style="color:red;">intent</span> <span style="color:red;">intent</span> = get<span style="color:red;">intent</span>();         //5). 通过<span style="color:red;">intent</span>读取额外数据         boardId = <span style="color:red;">intent</span>.getIntExtra("boardId", 0);         boardName = <span style="color:red;">intent</span>.getStringExtra("boardName");         String url = Constants.URL_PRE + "/boot/topic/topics_json?boardId=" + boardId + "&type=0";         topicDetailBeanMap = new HashMap<>();         getNetWorkString(url);     }     private class GetDataTask extends AsyncTask<Void, Void, TopicDetailBean[]> {         @Override         protected TopicDetailBean[] doInBackground(Void... params) {             // Simulates a background job.             try {                 Thread.sleep(2000);             } catch (InterruptedException e) {             }             return topics;         }         @Override         protected void onPostExecute(TopicDetailBean[] result) {          if(toppicListMPullRefreshListView.getMode()== PullToRefreshBase.Mode.PULL_FROM_START){             //下拉刷新             //mListItems.addFirst("Added after refresh...");             //mListItems.addFirst("数据下拉刷新请求的数据....");                 TopicDetailBean topic = new TopicDetailBean();                 topic.setTitle("1233下拉刷新");                 topic.setContent("hahah");                 topic.setUserId(1);                 topic.setUsername("canava");                 topic.setAvatar("aaaa.jpg");                 mListItems.addFirst(topic);                 topicList.add(0,topic);                 int size = topicList.size();                 for(int i=0;i<size;i++){                     TopicDetailBean topicDetailBean = topicList.get(i);                     topicDetailBeanMap.put(topicDetailBean.getTopicId(),topicDetailBean);                 }                 //mListItems.addLast(topic);                 toppicListMPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);          }else if(toppicListMPullRefreshListView.getMode()== PullToRefreshBase.Mode.PULL_FROM_END){             //mListItems.addLast("数据上拉最后加入的数据...");                 TopicDetailBean topic = new TopicDetailBean();                 topic.setTitle("1233上拉刷新加入的数据");                 topic.setContent("hahah");                 topic.setUserId(1);                 topic.setUsername("canava");                 topic.setAvatar("aaaa.jpg");                 //mListItems.addFirst(topic);                 mListItems.addLast(topic);                 //增加到最后                 topicList.add(topic);                 int size = topicList.size();                 for(int i=0;i<size;i++){                     TopicDetailBean topicDetailBean = topicList.get(i);                     topicDetailBeanMap.put(topicDetailBean.getTopicId(),topicDetailBean);                 }                 toppicListMPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);          } //            TopicDetailBean topic = new TopicDetailBean(); //            topic.setTitle("1233"); //            topic.setContent("hahah"); //            topic.setUserId(1); //            topic.setUsername("canava"); //            topic.setAvatar("aaaa.jpg"); //            mListItems.addFirst(topic); //            mListItems.addLast(topic);             //mListItems.addAll(Arrays.asList(result));             //mListItems.addFirst("Added after refresh 添加的数据...");             //mListItems.addAll(Arrays.asList(result));             //mListItems.addFirst("数据刷新请求的数据....");             //mListItems.addLast("数据下拉刷新得到的数据................");             //mListItems.addAll(Arrays.asList(result));             mAdapter.notifyDataSetChanged();             // Call onRefreshComplete when the list has been refreshed.             toppicListMPullRefreshListView.onRefreshComplete();             super.onPostExecute(result);         }     }     public void getNetWorkString(String url) {         //String   url  = "https://192.168.191.1/boot/board/boards_json";//带https的网址         final Request request = new Request.Builder().url(url).build();         Call call = new HttpsCert().setCard(this).newCall(request);         call.enqueue(new Callback() {             @Override             public void onFailure(Call call, final IOException e) {                 runOnUiThread(new Runnable() {                     @Override                     public void run() {                         System.out.println("数据取得失败");                         //Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();                     }                 });                 Log.i("joker", e.getMessage());             }             @Override             public void onResponse(Call call, Response response)                     throws IOException {                 final String res = response.body().string();                 Log.e("joker", res);                 System.out.println("收到的消息topic list:" + res);                 runOnUiThread(new Runnable() {                     @Override                     public void run() {                         newTopicButton = findViewById(R.id.newTopicButton);                         newTopicButton.setOnClickListener(new View.OnClickListener() {                             @Override                             public void onClick(View v) {                                 System.out.println("新增主题");                                 //增加输入文本框                                 <span style="color:red;">intent</span> <span style="color:red;">intent</span> = new <span style="color:red;">intent</span>(TopicListActivity.this,TopicNewActivity.class);                                 <span style="color:red;">intent</span>.putExtra("boardId",boardId);                                 <span style="color:red;">intent</span>.putExtra("boardName",boardName);                                 startActivity(<span style="color:red;">intent</span>);                                 //<span style="color:red;">intent</span>.putExtra("topicId",topic.getTopicId());                                 //<span style="color:red;">intent</span>.putExtra("username",)                             }                         });                         //Toast.makeText(TopicListActivity.this, res, Toast.LENGTH_LONG).show();                         topicList = JSON.parseArray(res, TopicDetailBean.class);                         mListItems = new LinkedList<>();                         int l = topicList.size();                         topics = new TopicDetailBean[l];                         for(int i=0;i<l;i++){                             topics[i]= topicList.get(i);                             mListItems.add(topicList.get(i));                             topicDetailBeanMap.put(topics[i].getTopicId(),topics[i]);                         }                        System.out.println("topic list size:"+topicList.size()); //                        topicPageList = JSON.parseObject(res, PageList.class); // // //                        int l = topicList.size(); //                        for(int i=0;i<l;i++){ //                            topics[i]= topicPageList.get(i); //                        }                         //System.out.println("topic page list size:"+topicPageList.size());                         //topicListView = findViewById(R.id.pull_refresh_list_view_topic_list);                         toppicListMPullRefreshListView= findViewById(R.id.pull_refresh_list_view_topic_list);                         toppicListMPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {                             @Override                             public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {                                 //没有设置时间                                 String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),                                         DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);                                 System.out.println("时间:"+label);                                 Toast.makeText(TopicListActivity.this, "下拉刷新Pull Down!", Toast.LENGTH_SHORT).show();                                 toppicListMPullRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);                                 new TopicListActivity.GetDataTask().execute();                             }                             @Override                             public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {                                 System.out.println("上拉得到刷新....");                                 Toast.makeText(TopicListActivity.this, "上拉刷新111Pull Up!", Toast.LENGTH_SHORT).show();                                 toppicListMPullRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);                                 new TopicListActivity.GetDataTask().execute();                             }                         });                         ListView actualListView = toppicListMPullRefreshListView.getRefreshableView();                         mAdapter =new TopicListAdapter(getParent());                         actualListView.setAdapter(mAdapter);                         //topicListView.setAdapter(new TopicListAdapter(getParent()));                         //监听动作                         actualListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {                             @Override                             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {                                 if(i==0) i=0;                                 if(i>0) i=i-1;                                 TopicDetailBean topic = topicList.get(i);                                 int boardId = topic.getBoardId();                                 TextView topic_id_text_view = (TextView) view.findViewById(R.id.topic_id_text_view);                                 int topicId = Integer.parseInt(topic_id_text_view.getText().toString());                                 TextView topic_title_text_view = (TextView)view.findViewById(R.id.text_item_listview_title);                                 String title = topic_title_text_view.getText().toString();                                 TopicDetailBean selectTopicDetail= topicDetailBeanMap.get(topicId);                                 System.out.println("选择的 topic title:"+title);                                 <span style="color:red;">intent</span> <span style="color:red;">intent</span> = new <span style="color:red;">intent</span>((Context) TopicListActivity.this, TopicDetailActivity.class);                                 //<span style="color:red;">intent</span> <span style="color:red;">intent</span>1 = <span style="color:red;">intent</span>.setClass(TopicListActivity.class, TopicDetailActivity.class);                                 //<span style="color:red;">intent</span>.putExtra("string","canava");                                 //<span style="color:red;">intent</span>.putExtra("boardId",boardId);                                 <span style="color:red;">intent</span>.putExtra("topicId",topicId);                                 <span style="color:red;">intent</span>.putExtra("boardId",selectTopicDetail.getBoardId());                                 startActivity(<span style="color:red;">intent</span>);                                 //Toast.makeText(ListViewActivity.this,book.toString(),Toast.LENGTH_LONG).show();                             }                         });                         //<span style="color:red;">intent</span> <span style="color:red;">intent</span> = new <span style="color:red;">intent</span>(MainActivity.this, HomeActivity.class);                         //2). 通过<span style="color:red;">intent</span>携带额外数据 //                        String message = res; //                        <span style="color:red;">intent</span>.putExtra("MESSAGE", message); //                        //3). 启动Activity //                        startActivity(<span style="color:red;">intent</span>);                     }                 });             }         });     }     class TopicListAdapter extends BaseAdapter {         private Context context = null;         public TopicListAdapter(Context context) {             this.context = context;         }         @Override         public int getCount() {             return mListItems.size();         }         @Override         public Object getItem(int position) {             return mListItems.get(position);         }         @Override         public long getItemId(int position) {             return position;         }         @Override         public View getView(int position, View convertView, ViewGroup parent) {             final TopicListAdapter.ViewHolder mHolder;             if (convertView == null) {                 mHolder = new TopicListAdapter.ViewHolder();                 LayoutInflater inflater = LayoutInflater.from(getApplicationContext());                 convertView = inflater.inflate(R.layout.item_listview_main_topiclist, null, true);                 mHolder.topic_id_text_view = (TextView)convertView.findViewById(R.id.topic_id_text_view);                 mHolder.text_item_listview_title = (TextView) convertView.findViewById(R.id.text_item_listview_title);                 mHolder.text_item_listview_username = (TextView) convertView.findViewById(R.id.text_item_listview_username);                 mHolder.imageView_item_listview_topic_pic = (ImageView) convertView.findViewById(R.id.imageView_item_listview_topicpic);                 convertView.setTag(mHolder);             } else {                 mHolder = (TopicListAdapter.ViewHolder) convertView.getTag();             }             TopicDetailBean topicDetailBean = mListItems.get(position);             String title = topicDetailBean.getTitle().toString();             String username = topicDetailBean.getUsername().toString();             int topicId = topicDetailBean.getTopicId();             //int picId = Integer.parseInt(boardList.get(position).get("headpic").toString());             //String username = topicPageList.get(position).getUsername();             String picStrSlash = mListItems.get(position).getAvatar().replace("\\", "/");             mHolder.text_item_listview_title.setText(title);             mHolder.text_item_listview_username.setText(username);             mHolder.topic_id_text_view.setText(Integer.toString(topicId));             //mHolder.imageView_item_listview_headpic             //System.out.println("glide pic url:"+picStr);             //glide不能显示https             String url1 = "https://ad.12306.cn/res/delivery/0001/2017/08/31/201708311634229711.jpg";             String url = Constants.URL_PRE + picStrSlash;             System.out.println("下载图片的url:" + url);             //String   url  = "https://192.168.191.1/boot/board/boards_json";//带https的网址             final Request request = new Request.Builder().url(url).build();             Call call = new HttpsCert().setCard(getParent()).newCall(request);             call.enqueue(new Callback() {                 @Override                 public void onFailure(Call call, final IOException e) {                     runOnUiThread(new Runnable() {                         @Override                         public void run() {                             //Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();                         }                     });                     Log.i("下载图片joker出错了:", e.getMessage());                 }                 @Override                 public void onResponse(Call call, Response response)                         throws IOException {                     final InputStream res = response.body().byteStream();                     //Log.e("joker",res);                     System.out.println("收到图片消息:" + res);                     runOnUiThread(new Runnable() {                         @Override                         public void run() {                             //Toast.makeText(MainActivity.this,res,Toast.LENGTH_LONG).show();                             Bitmap bitmap = BitmapFactory.decodeStream(res);                             mHolder.imageView_item_listview_topic_pic.setImageBitmap(bitmap);                         }                     });                 }             });             return convertView;         }         class ViewHolder {             private TextView topic_id_text_view;             private TextView text_item_listview_title;             private TextView text_item_listview_username;             private ImageView imageView_item_listview_topic_pic;         }     } }
         * shotStartPosList : []
         * contentCount : 0
         * url : https://localhost/boot/topic/topicattach?topicId=674
         */

        private double score;
        private int hitCount;
        private double distance;
        private int docId;
        private Object query;
        private int titleCount;
        private String title;
        private String body;
        private int contentCount;
        private String url;
        private List<PostionListBean> postionList;
        private List<?> shotStartPosList;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getHitCount() {
            return hitCount;
        }

        public void setHitCount(int hitCount) {
            this.hitCount = hitCount;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public int getDocId() {
            return docId;
        }

        public void setDocId(int docId) {
            this.docId = docId;
        }

        public Object getQuery() {
            return query;
        }

        public void setQuery(Object query) {
            this.query = query;
        }

        public int getTitleCount() {
            return titleCount;
        }

        public void setTitleCount(int titleCount) {
            this.titleCount = titleCount;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public int getContentCount() {
            return contentCount;
        }

        public void setContentCount(int contentCount) {
            this.contentCount = contentCount;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<PostionListBean> getPostionList() {
            return postionList;
        }

        public void setPostionList(List<PostionListBean> postionList) {
            this.postionList = postionList;
        }

        public List<?> getShotStartPosList() {
            return shotStartPosList;
        }

        public void setShotStartPosList(List<?> shotStartPosList) {
            this.shotStartPosList = shotStartPosList;
        }

        public static class PostionListBean {
            /**
             * fieldname : content
             * query : intent
             * wordPos : null
             * posList : [2967,6046,6063,6070,6108,6139,6193,11296,11303,11316,11403,11471,11557,11600,11681,16062,16069,16082,16185,16192,16202,16305,16375,16443,16511,16616,16823,16830,16843,16922,17009,17129]
             */

            private String fieldname;
            private String query;
            private Object wordPos;
            private List<Integer> posList;

            public String getFieldname() {
                return fieldname;
            }

            public void setFieldname(String fieldname) {
                this.fieldname = fieldname;
            }

            public String getQuery() {
                return query;
            }

            public void setQuery(String query) {
                this.query = query;
            }

            public Object getWordPos() {
                return wordPos;
            }

            public void setWordPos(Object wordPos) {
                this.wordPos = wordPos;
            }

            public List<Integer> getPosList() {
                return posList;
            }

            public void setPosList(List<Integer> posList) {
                this.posList = posList;
            }
        }
    }
}
