package android.js.com.myapplication.feature.fragment;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.js.com.myapplication.feature.SearchResultActivity;
import android.js.com.myapplication.feature.TopicDetailActivity;
import android.js.com.myapplication.feature.TopicListActivity;
import android.js.com.myapplication.feature.TopicSearchListActivity;
import android.js.com.myapplication.feature.bean.KeySearchBean;
import android.js.com.myapplication.feature.bean.SearchResultBean;
import android.js.com.myapplication.feature.bean.TopicDetailBean;
import android.js.com.myapplication.feature.orm.Board;
import android.js.com.myapplication.feature.sqlite.RecordSQLiteOpenHelper;
import android.js.com.myapplication.feature.utils.Constants;
import android.js.com.myapplication.feature.utils.HttpRequestUrl;
import android.js.com.myapplication.feature.utils.HttpsCert;
import android.js.com.myapplication.feature.utils.SPkey;
import android.js.com.myapplication.feature.utils.SpUtils;
import android.js.com.myapplication.feature.utils.ToastUtils;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.js.com.myapplication.feature.R;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.InputStream;
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
//import scut.carson_ho.searchview.SearchView;
//import scut.carson_ho.searchview.RecordSQLiteOpenHelper;
import scut.carson_ho.searchview.bCallBack;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";
    // 1. 初始化搜索框变量
    private SearchView search_view;
    private Context mContext;
    private TextView tv_clear;  // 删除搜索记录按键


    ListView search_result_list_view;
    ListView auto_list_view;
    RecyclerView query_history_list_view;
    RecyclerviewAdapter recyclerviewAdapter;
    List<SearchResultBean.HitListBean> hitListBeanList=new ArrayList<>();
    Map<Integer,SearchResultBean.HitListBean> hitListBeanMap=new HashMap<>();

    // 数据库变量
    // 用于存放历史搜索记录
    private RecordSQLiteOpenHelper helper ;
    private SQLiteDatabase db;
    private BaseAdapter adapter;


    public SearchFragment() {
        // Required empty public constructor
        mContext= getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("查询的fragment");
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search2, container, false);
        search_view = view.findViewById(R.id.search_view);
       // search_result_list_view = view.findViewById(R.id.search_result_list_view);
       query_history_list_view = view.findViewById(R.id.query_history_list_view);

        GridLayoutManager layoutManage = new GridLayoutManager(getContext(), 2);
        layoutManage.setAutoMeasureEnabled(true);
        query_history_list_view.setLayoutManager(layoutManage);
//        LinearLayoutManager layoutManager2=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
//        query_history_list_view.setLayoutManager(layoutManager2);


        //搜索图标按钮的点击事件
        search_view.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "打开搜索框", Toast.LENGTH_SHORT).show();
            }
        });

        // 2. 实例化数据库SQLiteOpenHelper子类对象
        helper = new RecordSQLiteOpenHelper(getActivity());



        // 5. 删除历史搜索记录 按钮
        tv_clear = (TextView) view.findViewById(scut.carson_ho.searchview.R.id.tv_clear);
        tv_clear.setVisibility(View.INVISIBLE);

        /**
         * "清空搜索历史"按钮
         */
        tv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 清空数据库->>关注2
                deleteData();
                // 模糊搜索空字符 = 显示所有的搜索历史（此时是没有搜索记录的）
                queryData("");
            }
        });


        search_view.onActionViewExpanded();
        //设置最大宽度
        search_view.setMaxWidth(1000);
        //设置是否显示搜索框展开时的提交按钮
        search_view.setSubmitButtonEnabled(true);
        //设置输入框提示语
        search_view.setQueryHint("");
        //search_button = (Button) findViewById(R.id.search_button);
        auto_list_view= (ListView) view.findViewById(R.id.auto_list_view);

        // 3. 第1次进入时查询所有的历史搜索记录
        queryData("");

        // 设置搜索文本监听
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {//点击提交按钮时
                Toast.makeText(getActivity(), "Submit---提交", Toast.LENGTH_SHORT).show();
                System.out.println("提交的搜索句子:"+query);

                //提交的时候插入查询词
                insertData(query);

                //搜索结果
                //String query = string;
                String username= SpUtils.getKeyValue(getActivity(),"userInfo","username");
                System.out.println("取出来的用户名:"+username);
                int userId = Integer.parseInt(SpUtils.getKeyValue(getActivity(),"userInfo","userId"));
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


//        query_history_list_view.set(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //SQLiteCursor cursor=(SQLiteCursor)parent.getItemAtPosition(position);
//               // String query = cursor.getString(1);
//
//                // 获取用户点击列表里的文字,并自动填充到搜索框内
//                TextView textView = (TextView) view.findViewById(android.R.id.text1);
//                String name = textView.getText().toString();
//
//
//                System.out.println("您选择了:"+name);
//                search_view.setQuery(name,false);
//            }
//        });


        // 4. 设置点击搜索按键后的操作（通过回调接口）
        // 参数 = 搜索框输入的内容
//        searchView.setOnClickSearch(new ICallBack() {
//            @Override
//            public void SearchAciton(String string) {
//                System.out.println("我收到了" + string);
//
//                //搜索结果
//                String query = string;
//                String username= SpUtils.getKeyValue(getContext(),"userInfo","username");
//                System.out.println("取出来的用户名:"+username);
//                int userId = Integer.parseInt(SpUtils.getKeyValue(getContext(),"userInfo","userId"));
//                System.out.println("取出来的用户Id:"+userId);
//
//                postNetWorkString(query,username,userId,HttpRequestUrl.SEARCH_URL);
//
//            }
//        });

        // 5. 设置点击返回按键后的操作（通过回调接口）
//        searchView.setOnClickBack(new bCallBack() {
//            @Override
//            public void BackAciton() {
//                getActivity().finish();
//            }
//        });

        return view;
    }


    /**
     * 关注1
     * 模糊查询数据 & 显示到ListView列表上
     */
    private void queryData(String tempName) {

        // 1. 模糊搜索
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);

        System.out.println("查询出的结果个数为:"+cursor.getCount());
        List<KeySearchBean> nameList=new ArrayList<>();
        while (cursor.moveToNext()){
            System.out.println("查询出的词汇:"+cursor.getString(1));
            KeySearchBean keySearchBean = new KeySearchBean(cursor.getInt(0),cursor.getString(1));
            nameList.add(keySearchBean);
        }

        recyclerviewAdapter = new RecyclerviewAdapter(getActivity(),nameList);

        // 2. 创建adapter适配器对象 & 装入模糊搜索的结果
//        adapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, cursor, new String[] { "name" },
//                new int[] { android.R.id.text1 }, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 3. 设置适配器
        query_history_list_view.setAdapter(recyclerviewAdapter);
        recyclerviewAdapter.notifyDataSetChanged();

        System.out.println(cursor.getCount());
        // 当输入框为空 & 数据库中有搜索记录时，显示 "删除搜索记录"按钮
        if (tempName.equals("") && cursor.getCount() != 0){
            tv_clear.setVisibility(View.VISIBLE);
        }
        else {
            tv_clear.setVisibility(View.INVISIBLE);
        };

    }

    /**
     * 关注2：清空数据库
     */
    private void deleteData() {

        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
        tv_clear.setVisibility(View.INVISIBLE);
    }


    private void deleteDataById(int id) {

        db = helper.getWritableDatabase();
        db.execSQL("delete from records where id="+id);
        db.close();
        tv_clear.setVisibility(View.INVISIBLE);
    }

    /**
     * 关注3
     * 检查数据库中是否已经有该搜索记录
     */
    private boolean hasData(String tempName) {
        // 从数据库中Record表里找到name=tempName的id
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //  判断是否有下一个
        return cursor.moveToNext();
    }

    /**
     * 关注4
     * 插入数据到数据库，即写入搜索字段到历史搜索记录
     */
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }

    /**
     * 关注4
     * 插入数据到数据库，即写入搜索字段到历史搜索记录
     */
    private void insertDataNotExist(String tempName) {
        boolean hasData=hasData(tempName);
        if(hasData) return;
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }


    public void postNetWorkString(final String query, final String username, final int userId, String url) {
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


        Call call  = new HttpsCert().setCard(getActivity()).newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                getActivity().runOnUiThread(new Runnable() {
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

                Intent intent = new Intent(getActivity(), TopicSearchListActivity.class);
                intent.putExtra("result",res);
                intent.putExtra("username",username);
                intent.putExtra("userId",userId);
                intent.putExtra("query",query);
                startActivity(intent);

//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //ToastUtils.show(getContext(),"得到的搜索结果:"+res);
//                        SearchResultBean searchResultBean = JSON.parseObject(res,SearchResultBean.class);
//                       // SearchResultBean.PageInfoBean pageInfoBean= searchResultBean.get_$PageInfo181();
//                        hitListBeanList = searchResultBean.getHitList();
//                        SearchListAdapter searchListAdapter = new SearchListAdapter(getActivity());
//                        search_result_list_view.setAdapter(searchListAdapter);
//
//                        //监听动作
//                        search_result_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//
//                                if(i==0) i=0;
//                                if(i>0) i=i-1;
//                                SearchResultBean.HitListBean hitListBean = hitListBeanList.get(i);
//                                //int boardId = hitListBean.
//                                for(int j=0;j<hitListBeanList.size();j++){
//                                    SearchResultBean.HitListBean hitListBean1 = hitListBeanList.get(j);
//                                    hitListBeanMap.put(hitListBean1.getDocId(),hitListBean1);
//                                }
//
//
//
//                                TextView doc_id_text_view = (TextView) view.findViewById(R.id.doc_id_text_view);
//                                int docId = Integer.parseInt(doc_id_text_view.getText().toString());
//                                //TextView topic_title_text_view = (TextView)view.findViewById(R.id.text_item_listview_title);
//                                //String title = topic_title_text_view.getText().toString();
//                                String url = ((TextView) view.findViewById(R.id.doc_url_text_view)).getText().toString();
//
//                                SearchResultBean.HitListBean hitListBean1= hitListBeanMap.get(docId);
//                                System.out.println("选择的 topic title:"+hitListBean1.getTitle());
//
//                                Intent intent = new Intent((Context) getActivity(), SearchResultActivity.class);
//                                //Intent intent1 = intent.setClass(TopicListActivity.class, TopicDetailActivity.class);
//                                //intent.putExtra("string","canava");
//                                //intent.putExtra("boardId",boardId);
//                                String url1=url.replace("localhost","192.168.191.1");
//                                intent.putExtra("url",url1);
//                                //intent.putExtra("boardId",selectTopicDetail.getBoardId());
//                                startActivity(intent);
//                                //Toast.makeText(ListViewActivity.this,book.toString(),Toast.LENGTH_LONG).show();
//                            }
//                        });
//                    }
//                });
            }
        });
    }



    public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

        private Context context;
        private List<KeySearchBean> data;

        public RecyclerviewAdapter(Context context,List<KeySearchBean> data){
            this.context = context;
            this.data = data;

        }

        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //这个问题真是太垃圾了
            View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview,null,false);
           // View view = layoutInflater.inflate(R.layout.list_item_crime, null, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.name.setText(data.get(position).toString());
            //设置条目高度
            ViewGroup.LayoutParams layoutParams = holder.name.getLayoutParams();
            layoutParams.height=80;
            holder.name.setLayoutParams(layoutParams);

            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("这里是点击每一行item的响应事件",""+position);
                    String name = data.get(position).toString();
                    System.out.println("您选择了："+name+" 进行搜索");
                    search_view.setQuery(name,false);
                }
            });

            /**
             * 删除关键词
             */
            holder.remove_word_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("这里是点击每一行item的响应事件",""+position);
                    String name = data.get(position).toString();
                    System.out.println("您选择了："+name+" 进行删除");

                    int id = data.get(position).getId();
                    deleteDataById(id);
                    queryData("");
                    //search_view.setQuery(name,false);
                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            private TextView name;
            private Button remove_word_btn;

            public ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.name111);
                remove_word_btn = (Button) itemView.findViewById(R.id.remove_word_btn);

            }
        }
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
            final SearchFragment.SearchListAdapter.ViewHolder mHolder;
            if (convertView == null) {
                mHolder = new SearchFragment.SearchListAdapter.ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getActivity());
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
                mHolder = (SearchFragment.SearchListAdapter.ViewHolder) convertView.getTag();
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
