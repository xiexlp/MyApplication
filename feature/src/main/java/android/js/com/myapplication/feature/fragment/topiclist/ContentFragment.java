package android.js.com.myapplication.feature.fragment.topiclist;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.js.com.myapplication.feature.R;
import android.js.com.myapplication.feature.TopicDetailActivity;
import android.js.com.myapplication.feature.TopicListActivity;
import android.js.com.myapplication.feature.TopicNewActivity;
import android.js.com.myapplication.feature.bean.TopicDetailBean;
import android.js.com.myapplication.feature.orm.Topic;
import android.js.com.myapplication.feature.utils.Constants;
import android.js.com.myapplication.feature.utils.HttpsCert;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {

    Context context;

    ListView topic_list_view;
    List<TopicDetailBean> topicDetailBeanList;

    Map<Integer,TopicDetailBean> topicDetailBeanMap;

    int boardId = 0;
    TopicDetailBean[] topics={};

    private PullToRefreshListView toppicListMPullRefreshListView;
    private LinkedList<TopicDetailBean> mListItems;
    private Button newTopicButton;

    List<TopicDetailBean> topicList;

    private BaseAdapter mAdapter;

    View view;

    public ContentFragment(){

    }

//    @SuppressLint("ValidFragment")
//    public ContentFragment(AppCompatActivity context) {
//        // Required empty public constructor
//        this.context = context;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_content, container, false);
        toppicListMPullRefreshListView = view.findViewById(R.id.pull_refresh_list_view_topic_list);
        newTopicButton = view.findViewById(R.id.newTopicButton);
        System.out.println("是否已经初始化view");
        return view;
    }


    public void initData(int boardId){
        this.boardId =boardId;
        String url = Constants.URL_PRE + "/boot/topic/topics_json?boardId=" + boardId + "&type=0";
        topicDetailBeanMap = new HashMap<>();
        getNetWorkString(url);
    }



    public void setContext(Context context) {
        this.context = context;
    }

    public void getNetWorkString(String url) {
        //String   url  = "https://192.168.191.1/boot/board/boards_json";//带https的网址
        final Request request = new Request.Builder().url(url).build();
        Call call = new HttpsCert().setCard((Activity) this.context).newCall(request);
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        newTopicButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                System.out.println("新增主题");
                                //增加输入文本框
                                Intent intent = new Intent(getActivity(),TopicNewActivity.class);
                                intent.putExtra("boardId",boardId);
                                //intent.putExtra("boardName",boardName);
                                startActivity(intent);
                                //intent.putExtra("topicId",topic.getTopicId());
                                //intent.putExtra("username",)

                            }
                        });

                        //Toast.makeText(TopicListActivity.this, res, Toast.LENGTH_LONG).show();
                        topicList = JSON.parseArray(res, TopicDetailBean.class);
                        mListItems = new LinkedList<>();
                        int l = topicList.size();
                        topics = new TopicDetailBean[l];
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

                        toppicListMPullRefreshListView= view.findViewById(R.id.pull_refresh_list_view_topic_list);



                        toppicListMPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

                            @Override
                            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                                //没有设置时间

                                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                                System.out.println("时间:"+label);

                                //Toast.makeText(TopicListActivity.this, "下拉刷新Pull Down!", Toast.LENGTH_SHORT).show();

                                toppicListMPullRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
                                new ContentFragment.GetDataTask().execute();


                            }

                            @Override
                            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                                System.out.println("上拉得到刷新....");

                                Toast.makeText(getActivity(), "上拉刷新111Pull Up!", Toast.LENGTH_SHORT).show();
                                toppicListMPullRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
                                new ContentFragment.GetDataTask().execute();
                            }
                        });


                        ListView actualListView = toppicListMPullRefreshListView.getRefreshableView();
                        mAdapter =new ContentFragment.TopicListAdapter(getActivity());
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

                                Intent intent = new Intent(getActivity(), TopicDetailActivity.class);
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

    public OkHttpClient setCard() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore           = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            String certificateAlias = Integer.toString(0);
            keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(getActivity().getAssets().open("server.cer")));//拷贝好的证书
            SSLContext sslContext = SSLContext.getInstance("TLS");
            final TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init
                    (
                            null,
                            trustManagerFactory.getTrustManagers(),
                            new SecureRandom()
                    );
            builder.sslSocketFactory(sslContext.getSocketFactory());
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.build();
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

                TopicDetailBean topic = new TopicDetailBean();
                topic.setTitle("1233下拉刷新");
                topic.setContent("hahah");
                topic.setUserId(1);
                topic.setUsername("canava");
                topic.setAvatar("aaaa.jpg");
                mListItems.addFirst(topic);
                topicList.add(0,topic);
                int size = topicList.size();
                for(int i=0;i<size;i++){
                    TopicDetailBean topicDetailBean = topicList.get(i);
                    topicDetailBeanMap.put(topicDetailBean.getTopicId(),topicDetailBean);
                }
                //mListItems.addLast(topic);

                toppicListMPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);

            }else if(toppicListMPullRefreshListView.getMode()== PullToRefreshBase.Mode.PULL_FROM_END){
                //mListItems.addLast("数据上拉最后加入的数据...");

                TopicDetailBean topic = new TopicDetailBean();
                topic.setTitle("1233上拉刷新加入的数据");
                topic.setContent("hahah");
                topic.setUserId(1);
                topic.setUsername("canava");
                topic.setAvatar("aaaa.jpg");
                //mListItems.addFirst(topic);
                mListItems.addLast(topic);
                //增加到最后
                topicList.add(topic);
                int size = topicList.size();
                for(int i=0;i<size;i++){
                    TopicDetailBean topicDetailBean = topicList.get(i);
                    topicDetailBeanMap.put(topicDetailBean.getTopicId(),topicDetailBean);
                }

                toppicListMPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
            }

            mAdapter.notifyDataSetChanged();

            // Call onRefreshComplete when the list has been refreshed.
            toppicListMPullRefreshListView.onRefreshComplete();

            super.onPostExecute(result);
        }
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
            final ContentFragment.TopicListAdapter.ViewHolder mHolder;
            if (convertView == null) {
                mHolder = new ContentFragment.TopicListAdapter.ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                convertView = inflater.inflate(R.layout.item_listview_main_topiclist, null, true);
                mHolder.topic_id_text_view = (TextView)convertView.findViewById(R.id.topic_id_text_view);
                mHolder.text_item_listview_title = (TextView) convertView.findViewById(R.id.text_item_listview_title);
                mHolder.text_item_listview_username = (TextView) convertView.findViewById(R.id.text_item_listview_username);
                mHolder.imageView_item_listview_topic_pic = (ImageView) convertView.findViewById(R.id.imageView_item_listview_topicpic);
                convertView.setTag(mHolder);
            } else {
                mHolder = (ContentFragment.TopicListAdapter.ViewHolder) convertView.getTag();
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
            Call call = new HttpsCert().setCard(getActivity()).newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {
                    getActivity().runOnUiThread(new Runnable() {
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
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(MainActivity.this,res,Toast.LENGTH_LONG).show();
                            Bitmap bitmap = BitmapFactory.decodeStream(res);
                            if(bitmap!=null)
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



    public List<TopicDetailBean> getTopicDetailBeanList() {
        return topicDetailBeanList;
    }

    public void setTopicDetailBeanList(List<TopicDetailBean> topicDetailBeanList) {
        this.topicDetailBeanList = topicDetailBeanList;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
}
