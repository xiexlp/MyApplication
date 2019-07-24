package android.js.com.myapplication.feature.fragment.topiclist;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.js.com.myapplication.feature.R;
import android.js.com.myapplication.feature.TopicListActivity;
import android.js.com.myapplication.feature.TopicListMenuActivity;
import android.js.com.myapplication.feature.config.ConstantSetting;
import android.js.com.myapplication.feature.fragment.HomeFragment;
import android.js.com.myapplication.feature.orm.Board;
import android.js.com.myapplication.feature.utils.BitmapCache;
import android.js.com.myapplication.feature.utils.Constants;
import android.js.com.myapplication.feature.utils.HttpsCert;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeftMenuFragment extends Fragment {


    private static final String TAG = "LeftMenuFragment";
    private static final String MAIN_CONTENT_TAG = "LeftMenuFragment main";


    //这个listView修改为可以上下拉刷新的版本
    ListView listView;
    String boardListStr;
    List<Board> boardList;

    TopicListMenuActivity context;

    public LeftMenuFragment(){

    }


    @Nullable
    @Override
    public TopicListMenuActivity getContext() {
        return context;
    }

    public void setContext(TopicListMenuActivity context) {
        this.context = context;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_left_menu2, container, false);

        String url = ConstantSetting.LOCAL_IP_URL+"/boot/board/boards_json";
        //String url = "https://10.0.46.50/boot/board/boards_json";
        Log.d(TAG, "onCreateView: url"+url);
        listView = view.findViewById(R.id.board_list_view);

        getNetWorkString(url);



        return view;
    }


    public void getNetWorkString(String url) {
        //String   url  = "https://192.168.191.1/boot/board/boards_json";//带https的网址
        final Request request = new Request.Builder().url(url).build();
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        boardList= new ArrayList<>();
                        boardListStr = res;
                        boardList = JSON.parseArray(boardListStr,Board.class);
                        listView.setAdapter(new LeftMenuFragment.MyAdapter(getActivity()));

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Board board = boardList.get(i);
                                int boardId = board.getBoardId();

                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction ft = fragmentManager.beginTransaction();


                                //收起菜单
                                getContext().getSlidingMenu().toggle();

                                ContentFragment contentFragment = new ContentFragment();
                                contentFragment.setBoardId(boardId);
                                contentFragment.setContext(getActivity());
                                contentFragment.initData(boardId);


                                ft.replace(R.id.fl_main_content,contentFragment, "内容页");//主页
                                //ft.replace(R.id.fl_main_leftmenu, new LeftMenuFragment(), "left");//左侧菜单
                                //4.提交
                                ft.commit();




//                                Intent intent = new Intent();
//                                intent.setClass(getActivity(),TopicListActivity.class);
//                                intent.putExtra("string","canava");
//                                intent.putExtra("boardId",boardId);
//                                intent.putExtra("boardName",board.getBoardName());
//                                startActivity(intent);
                                //Toast.makeText(ListViewActivity.this,book.toString(),Toast.LENGTH_LONG).show();
                            }
                        });


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


    class MyAdapter extends BaseAdapter {
        private Context context = null;
        public MyAdapter(Context context) {
            this.context = context;
        }
        @Override
        public int getCount() {
            return boardList.size();
        }
        @Override
        public Object getItem(int position) {
            return boardList.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final LeftMenuFragment.MyAdapter.ViewHolder mHolder;
            if (convertView == null) {
                mHolder = new LeftMenuFragment.MyAdapter.ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.item_listview_main_boardlist, null, true);
                mHolder.text_item_listview_boardname = (TextView) convertView.findViewById(R.id.text_item_listview_boardname);
                mHolder.text_item_listview_brief = (TextView) convertView.findViewById(R.id.text_item_listview_brief);
                mHolder.imageView_item_listview_boardpic = (ImageView) convertView.findViewById(R.id.imageView_item_listview_boardpic);
                convertView.setTag(mHolder);
            } else {
                mHolder = (LeftMenuFragment.MyAdapter.ViewHolder) convertView.getTag();
            }
            String boardname = boardList.get(position).getBoardName().toString();
            String brief = boardList.get(position).getBrief().toString();
            //int picId = Integer.parseInt(boardList.get(position).get("headpic").toString());

            String picStr= boardList.get(position).getIconFile();
            String picStrSlash = picStr.replace("\\","/");

            mHolder.text_item_listview_boardname.setText(boardname);
            mHolder.text_item_listview_brief.setText(brief);
            //mHolder.imageView_item_listview_headpic
            System.out.println("glide pic url:"+picStr);
            //glide不能显示https

            String url1="https://ad.12306.cn/res/delivery/0001/2017/08/31/201708311634229711.jpg";
            String url = Constants.URL_PRE+picStrSlash;
            System.out.println("下载图片的url:"+url);


            //String   url  = "https://192.168.191.1/boot/board/boards_json";//带https的网址
            /**
            final Request request = new Request.Builder().url(url).build();
            Call  call  = new HttpsCert().setCard(getActivity()).newCall(request);

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
                        throws IOException
                {
                    final InputStream res = response.body().byteStream();
                    //Log.e("joker",res);
                    System.out.println("收到图片消息:"+res);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(MainActivity.this,res,Toast.LENGTH_LONG).show();
                            Bitmap bitmap = BitmapFactory.decodeStream(res);
                            mHolder.imageView_item_listview_boardpic.setImageBitmap(bitmap);

                        }
                    });
                }
            });

             **/
            return convertView;
        }



        public void getNetWorkString(String url) {
            //String   url  = "https://192.168.191.1/boot/board/boards_json";//带https的网址
            final Request request = new Request.Builder().url(url).build();
            Call  call  = setCard().newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                    Log.i("joker", e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response)
                        throws IOException
                {
                    final InputStream res = response.body().byteStream();
                    //Log.e("joker",res);
                    System.out.println("收到图片消息:"+res);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(MainActivity.this,res,Toast.LENGTH_LONG).show();
                            Bitmap bitmap = BitmapFactory.decodeStream(res);

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



        class ViewHolder {
            private TextView text_item_listview_boardname;
            private TextView text_item_listview_brief;
            private ImageView imageView_item_listview_boardpic;
        }
    }

}
