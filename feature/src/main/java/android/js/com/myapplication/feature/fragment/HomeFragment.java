package android.js.com.myapplication.feature.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.js.com.myapplication.feature.HomeActivity;
import android.js.com.myapplication.feature.MainActivity;
import android.js.com.myapplication.feature.TopicListActivity;
import android.js.com.myapplication.feature.customize.CirclePgBar;
import android.js.com.myapplication.feature.orm.Board;
import android.js.com.myapplication.feature.orm.Section;
import android.js.com.myapplication.feature.utils.BitmapCache;
import android.js.com.myapplication.feature.utils.Constants;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import android.js.com.myapplication.feature.R;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.comix.overwatch.HiveProgressView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.https.HttpsUtils;
//import com.zhy.http.okhttp.https.HttpsUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

//    private OkHttpClient okHttpClient = new OkHttpClient();
//    private Response response;
    OkHttpClient okHttpClient = new OkHttpClient();
    TextView textView;


    //这个listView修改为可以上下拉刷新的版本
    ListView listView;

    //private PullToRefreshListView mPullRefreshListView;

    String boardListStr;
    List<Board>  boardList;

    public static final String TAG="HomeFragment";

   // HiveProgressView progressView;
//   ProgressBar progressBar;
   CirclePgBar progressBar;

    public HomeFragment() {
        // Required empty public constructor
    }

    public String getBoardListStr() {
        return boardListStr;
    }

    public void setBoardListStr(String boardListStr) {
        this.boardListStr = boardListStr;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        //textView = view.findViewById(R.id.viewBoard);
        System.out.println("view boards");
        listView = view.findViewById(R.id.fragment_home_board_list_view);

//        List<Section>  sectionList = new ArrayList<>();


        String url = Constants.URL_PRE+"/boot/board/boards_json";
        //String url = "https://10.0.46.50/boot/board/boards_json";
        Log.d(TAG, "onCreateView: url"+url);


//        progressView = (HiveProgressView) view.findViewById(R.id.hive_progress);
//        progressView.setRainbow(false);
//        progressView.setColor(0x000000);

//        progressBar=(ProgressBar) view.findViewById(R.id.hive_progress);

        progressBar=(CirclePgBar) view.findViewById(R.id.hive_progress);


        getNetWorkString(url);

        return view;
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
                        listView.setAdapter(new MyAdapter(getContext()));

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Board board = boardList.get(i);
                                int boardId = board.getBoardId();
                                Intent intent = new Intent();
                                intent.setClass(getActivity(),TopicListActivity.class);
                                intent.putExtra("string","canava");
                                intent.putExtra("boardId",boardId);
                                intent.putExtra("boardName",board.getBoardName());
                                startActivity(intent);
                                //Toast.makeText(ListViewActivity.this,book.toString(),Toast.LENGTH_LONG).show();
                            }
                        });

                        progressBar.setVisibility(ProgressBar.INVISIBLE);


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
            final ViewHolder mHolder;
            if (convertView == null) {
                mHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.item_listview_main_boardlist, null, true);
                mHolder.text_item_listview_boardname = (TextView) convertView.findViewById(R.id.text_item_listview_boardname);
                mHolder.text_item_listview_brief = (TextView) convertView.findViewById(R.id.text_item_listview_brief);
                mHolder.imageView_item_listview_boardpic = (ImageView) convertView.findViewById(R.id.imageView_item_listview_boardpic);
                convertView.setTag(mHolder);
            } else {
                mHolder = (ViewHolder) convertView.getTag();
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



        void volleyImageLoaderPic(ImageView imageView,String picStrSlash){

            String url = Constants.URL_PRE+picStrSlash;
            System.out.println("使用Volley打开图片"+url);


            //生成SSLSocketFactory
            SSLSocketFactory sslSocketFactory = initSSLSocketFactory();

//HurlStack两个参数默认都是null,如果传入SSLSocketFactory，那么会以Https的方式来请求网络
            HurlStack stack = new HurlStack(null, sslSocketFactory);


            // 创建一个请求队列
            //RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

            RequestQueue requestQueue=Volley.newRequestQueue(getActivity(),stack);
            //mQueue.add(request);

            // 创建一个imageloader
//                ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
//                    @Override
//                    public Bitmap getBitmap(String s) {
//                        return null;
//                    }
//
//                    @Override
//                    public void putBitmap(String s, Bitmap bitmap) {
//
//                    }
//                });
            ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());

            // 加载图片
            //String url = "http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg";
            imageView.setVisibility(View.VISIBLE);
            ImageLoader.ImageListener imageListener = imageLoader.getImageListener(imageView, R.drawable.atguigu_logo, R.drawable.atguigu_logo);
            imageLoader.get(url, imageListener);
        }


        class ViewHolder {
            private TextView text_item_listview_boardname;
            private TextView text_item_listview_brief;
            private ImageView imageView_item_listview_boardpic;
        }
    }


    //生成SSLSocketFactory
    private SSLSocketFactory initSSLSocketFactory() {
        //生成证书:Certificate
        CertificateFactory cf = null;
        SSLSocketFactory factory = null;
        try {
            cf = CertificateFactory.getInstance("X.509");
            InputStream caInput = getActivity().getAssets().open("server.crt");
            Certificate ca = null;
            try {
                ca = cf.generateCertificate(caInput);
            } finally {
                try {
                    caInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //初始化公钥:keyStore
            String keyType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            //初始化TrustManagerFactory
            String algorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory managerFactory = TrustManagerFactory.getInstance(algorithm);
            managerFactory.init(keyStore);

            //初始化sslContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, managerFactory.getTrustManagers(), null);
            factory = sslContext.getSocketFactory();

        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return factory;
    }



}
