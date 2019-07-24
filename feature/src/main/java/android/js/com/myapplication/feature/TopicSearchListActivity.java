package android.js.com.myapplication.feature;

import android.content.Context;
import android.content.Intent;
import android.js.com.myapplication.feature.bean.SearchResultBean;
import android.js.com.myapplication.feature.fragment.SearchFragment;
import android.js.com.myapplication.feature.utils.HttpRequestUrl;
import android.js.com.myapplication.feature.utils.HttpsCert;
import android.js.com.myapplication.feature.utils.SpUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.bCallBack;

public class TopicSearchListActivity extends AppCompatActivity {

    private static final String TAG ="TopicSearchListActivity" ;
    SearchView search_view;
    Button search_button;
    ListView auto_list_view;
    private ArrayAdapter mAdapter;
    ListView search_result_list_view;


    List<SearchResultBean.HitListBean> hitListBeanList=new ArrayList<>();
    Map<Integer,SearchResultBean.HitListBean> hitListBeanMap=new HashMap<>();

    String username;
    int userId;
    private String [] data = {"Java","kotlin","C","C++","C#","Python","PHP","JavaScript"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_search_list);

        mAdapter = new ArrayAdapter(TopicSearchListActivity.this, android.R.layout.simple_list_item_1, data);
        search_view = (SearchView) findViewById(R.id.search_view);

        search_view.onActionViewExpanded();
        //设置最大宽度
        search_view.setMaxWidth(1000);
        //设置是否显示搜索框展开时的提交按钮
        search_view.setSubmitButtonEnabled(true);
        //设置输入框提示语
        search_view.setQueryHint("hint");
        //search_button = (Button) findViewById(R.id.search_button);
        auto_list_view= (ListView) findViewById(R.id.auto_list_view);
        search_result_list_view= (ListView) findViewById(R.id.search_result_list_view);

        auto_list_view.setAdapter(mAdapter);
        auto_list_view.setTextFilterEnabled(true);

        auto_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String word =(String)parent.getAdapter().getItem(position);
                System.out.println("您选择了："+word+" 提示");
                search_view.setQuery(word,false);
                auto_list_view.setVisibility(View.GONE);
            }
        });

//        //上来就直接搜索
//        Toast.makeText(TopicSearchListActivity.this, "Submit---提交", Toast.LENGTH_SHORT).show();
//        System.out.println("提交的搜索句子:"+query);
//
//        //搜索结果
//        //String query = string;
//        String username= SpUtils.getKeyValue(TopicSearchListActivity.this,"userInfo","username");
//        System.out.println("取出来的用户名:"+username);
//        int userId = Integer.parseInt(SpUtils.getKeyValue(TopicSearchListActivity.this,"userInfo","userId"));
//        System.out.println("取出来的用户Id:"+userId);
//
//        postNetWorkString(query,username,userId,HttpRequestUrl.SEARCH_URL);


        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        userId = intent.getIntExtra("userId",0);
        String query = intent.getStringExtra("query");
        String resultJson = intent.getStringExtra("result");

        initSearchResult(resultJson);


//        search_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("搜索");
//            }
//        });

        //搜索图标按钮的点击事件
        search_view.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TopicSearchListActivity.this, "打开搜索框", Toast.LENGTH_SHORT).show();
            }
        });

        //移除焦点
        search_view.clearFocus();


        // 设置搜索文本监听
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {//点击提交按钮时
                Toast.makeText(TopicSearchListActivity.this, "Submit---提交", Toast.LENGTH_SHORT).show();
                System.out.println("提交的搜索句子:"+query);

                //搜索结果
                //String query = string;
                String username= SpUtils.getKeyValue(TopicSearchListActivity.this,"userInfo","username");
                System.out.println("取出来的用户名:"+username);
                int userId = Integer.parseInt(SpUtils.getKeyValue(TopicSearchListActivity.this,"userInfo","userId"));
                System.out.println("取出来的用户Id:"+userId);

                postNetWorkString(query,username,userId,HttpRequestUrl.SEARCH_URL);

                return true;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    auto_list_view.setFilterText(newText);
                    auto_list_view.setVisibility(View.VISIBLE);
                }else{
                    auto_list_view.clearTextFilter();
                    auto_list_view.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }

    private void initSearchResult(String result) {
        //ToastUtils.show(getContext(),"得到的搜索结果:"+res);
        SearchResultBean searchResultBean = JSON.parseObject(result,SearchResultBean.class);
        // SearchResultBean.PageInfoBean pageInfoBean= searchResultBean.get_$PageInfo181();
        if(searchResultBean==null) return;
        hitListBeanList = searchResultBean.getHitList();
        TopicSearchListActivity.SearchListAdapter searchListAdapter = new TopicSearchListActivity.SearchListAdapter(TopicSearchListActivity.this);
        search_result_list_view.setAdapter(searchListAdapter);

        //监听动作
        search_result_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                if (i == 0) i = 0;
                if (i > 0) i = i - 1;
                SearchResultBean.HitListBean hitListBean = hitListBeanList.get(i);
                //int boardId = hitListBean.
                for (int j = 0; j < hitListBeanList.size(); j++) {
                    SearchResultBean.HitListBean hitListBean1 = hitListBeanList.get(j);
                    hitListBeanMap.put(hitListBean1.getDocId(), hitListBean1);
                }


                TextView doc_id_text_view = (TextView) view.findViewById(R.id.doc_id_text_view);
                int docId = Integer.parseInt(doc_id_text_view.getText().toString());
                //TextView topic_title_text_view = (TextView)view.findViewById(R.id.text_item_listview_title);
                //String title = topic_title_text_view.getText().toString();
                String url = ((TextView) view.findViewById(R.id.doc_url_text_view)).getText().toString();

                SearchResultBean.HitListBean hitListBean1 = hitListBeanMap.get(docId);
                System.out.println("选择的 topic title:" + hitListBean1.getTitle());

                Intent intent = new Intent(TopicSearchListActivity.this, SearchResultActivity.class);
                //Intent intent1 = intent.setClass(TopicListActivity.class, TopicDetailActivity.class);
                //intent.putExtra("string","canava");
                //intent.putExtra("boardId",boardId);
                String url1 = url.replace("localhost", "192.168.191.1");
                intent.putExtra("url", url1);
                //intent.putExtra("boardId",selectTopicDetail.getBoardId());
                startActivity(intent);
                //Toast.makeText(ListViewActivity.this,book.toString(),Toast.LENGTH_LONG).show();

            }
        });
    }

    public void postNetWorkString(String query,String username,int userId,String url) {
        //String   url  = "https://192.168.191.1/boot/board/boards_json";//带https的网址
        //final Request request = new Request.Builder().url(url).build();

        OkHttpClient client = new OkHttpClient();
        FormBody body = new FormBody.Builder()
                .add("username",username)
                .add("userId",Integer.toString(userId))
                .add("langCode","zh-cn")
                .add("query",query)
                .build();
        Request request = new Request.Builder()
                .addHeader("cookie","ss")
                .url(url)
                .post(body)
                .build();

        //Call  call  = setCard().newCall(request);

        //Call call2 = new HttpsCert().setCard(getActivity()).newCall(request);


        Call call  = new HttpsCert().setCard(TopicSearchListActivity.this).newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("数据取得失败");
                        Log.d(TAG, "run: 数据取得失败");
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
                Log.d(TAG, "onResponse: "+res);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //ToastUtils.show(getContext(),"得到的搜索结果:"+res);
                        SearchResultBean searchResultBean = JSON.parseObject(res,SearchResultBean.class);
                        // SearchResultBean.PageInfoBean pageInfoBean= searchResultBean.get_$PageInfo181();
                        hitListBeanList = searchResultBean.getHitList();
                        TopicSearchListActivity.SearchListAdapter searchListAdapter = new TopicSearchListActivity.SearchListAdapter(TopicSearchListActivity.this);
                        search_result_list_view.setAdapter(searchListAdapter);

                        //监听动作
                        search_result_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                                if(i==0) i=0;
                                if(i>0) i=i-1;
                                SearchResultBean.HitListBean hitListBean = hitListBeanList.get(i);
                                //int boardId = hitListBean.
                                for(int j=0;j<hitListBeanList.size();j++){
                                    SearchResultBean.HitListBean hitListBean1 = hitListBeanList.get(j);
                                    hitListBeanMap.put(hitListBean1.getDocId(),hitListBean1);
                                }



                                TextView doc_id_text_view = (TextView) view.findViewById(R.id.doc_id_text_view);
                                int docId = Integer.parseInt(doc_id_text_view.getText().toString());
                                //TextView topic_title_text_view = (TextView)view.findViewById(R.id.text_item_listview_title);
                                //String title = topic_title_text_view.getText().toString();
                                String url = ((TextView) view.findViewById(R.id.doc_url_text_view)).getText().toString();

                                SearchResultBean.HitListBean hitListBean1= hitListBeanMap.get(docId);
                                System.out.println("选择的 topic title:"+hitListBean1.getTitle());

                                Intent intent = new Intent(TopicSearchListActivity.this, SearchResultActivity.class);
                                //Intent intent1 = intent.setClass(TopicListActivity.class, TopicDetailActivity.class);
                                //intent.putExtra("string","canava");
                                //intent.putExtra("boardId",boardId);
                                String url1=url.replace("localhost","192.168.191.1");
                                intent.putExtra("url",url1);
                                //intent.putExtra("boardId",selectTopicDetail.getBoardId());
                                startActivity(intent);
                                //Toast.makeText(ListViewActivity.this,book.toString(),Toast.LENGTH_LONG).show();
                            }
                        });



                    }
                });
            }
        });
    }


    class SearchListAdapter extends BaseAdapter {
        private Context context = null;

        public SearchListAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return hitListBeanList.size();
        }

        @Override
        public Object getItem(int position) {
            return hitListBeanList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final TopicSearchListActivity.SearchListAdapter.ViewHolder mHolder;
            if (convertView == null) {
                mHolder = new TopicSearchListActivity.SearchListAdapter.ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(TopicSearchListActivity.this);
                convertView = inflater.inflate(R.layout.item_listview_main_searchlist, null, true);
                mHolder.topic_title_text_view = (TextView)convertView.findViewById(R.id.topic_title_text_view);
                mHolder.topic_content_text_view = (TextView) convertView.findViewById(R.id.topic_content_text_view);
                mHolder.topic_score_text_view = (TextView)convertView.findViewById(R.id.topic_score_text_view);
                mHolder.doc_id_text_view = (TextView)convertView.findViewById(R.id.doc_id_text_view);
                mHolder.doc_url_text_view = (TextView)convertView.findViewById(R.id.doc_url_text_view);
                //mHolder.text_item_listview_username = (TextView) convertView.findViewById(R.id.text_item_listview_username);
                //mHolder.imageView_item_listview_topic_pic = (ImageView) convertView.findViewById(R.id.imageView_item_listview_topicpic);
                convertView.setTag(mHolder);
            } else {
                mHolder = (TopicSearchListActivity.SearchListAdapter.ViewHolder) convertView.getTag();
            }

            SearchResultBean.HitListBean hitListBean = hitListBeanList.get(position);
            String title = hitListBean.getTitle().toString();

            System.out.println("查询出来的标题:"+title);

            String body = hitListBean.getBody().toString();

            mHolder.topic_score_text_view.setText("得分:"+hitListBean.getScore());

            mHolder.topic_title_text_view.setText(Html.fromHtml(title));
            mHolder.doc_id_text_view.setText(""+hitListBean.getDocId());
            // mHolder.text_item_listview_username.setText(username);
            int len = Math.min(100,body.length());
            mHolder.topic_content_text_view.setText(body.substring(0,len-1));
            mHolder.doc_url_text_view.setText(hitListBean.getUrl());
            //mHolder.imageView_item_listview_headpic
            //System.out.println("glide pic url:"+picStr);
            //glide不能显示https
            return convertView;
        }

        class ViewHolder {
            private TextView doc_url_text_view;
            private TextView doc_id_text_view;
            private TextView topic_title_text_view;
            private TextView topic_content_text_view;
            private TextView topic_score_text_view;
            // private TextView text_item_listview_username;
            // private ImageView imageView_item_listview_topic_pic;
        }

    }

    }
