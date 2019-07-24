package android.js.com.myapplication.feature;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.js.com.myapplication.feature.dialog.LoginDialog;
import android.js.com.myapplication.feature.jiecaoplayeractivity.JieCaoVideoPlayerMainActivity;
import android.js.com.myapplication.feature.pullrefresh.PullRefreshActivity;
import android.js.com.myapplication.feature.utils.BitmapCache;
import android.js.com.myapplication.feature.utils.Constants;
import android.js.com.myapplication.feature.utils.HttpRequestUrl;
import android.js.com.myapplication.feature.utils.SPkey;
import android.js.com.myapplication.feature.utils.SpUtils;
import android.js.com.myapplication.feature.utils.ToastUtils;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    Button button11;
    Button pullRefreshButton;
    Button toForumButton;

    Button to_pic_button;
    Button universal_video_view_button;
    Button jie_cao_video_view_button;
    Button login_button;

    Button to_login_button;

    Button to_market_button;

    Button to_slidingmenu_button;

    Button button13;

    Button button14;
    Button button15;

    Button button16;
    Button button17;
    Button button18;

    //声明，放在声明中
//TextView et_main_message;
    Button btn_banner;


    TextView textView;
    ImageView imageView;


    NetworkImageView iv_volley_networkimagview;

     LoginDialog loginDialog;
     Button web_view_button;

     private final String TAG="MainActivity";


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    OkHttpClient okHttpClient = new OkHttpClient();

    public static final int GET=1;
    public static final int POST=2;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
               case  GET:
                   System.out.println("get 消息处理");

//                   textView.setText((String)msg.obj);
                   textView.setText("get数据获取成功");
                   break;

                case  POST:
                    System.out.println("post 消息处理");
//                   textView.setText((String)msg.obj);
                    textView.setText("post数据获取成功");
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        imageView = findViewById(R.id.imageView);

        button11 = findViewById(R.id.button11);

        button11.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("to recleview ");
                Intent intent = new Intent(MainActivity.this, RecycleActivity.class);
                startActivity(intent);
                //finish();
            }
        });


        pullRefreshButton = findViewById(R.id.pullRefreshButton);
        pullRefreshButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("pull to refresh ");
                Intent intent = new Intent(MainActivity.this,PullRefreshActivity.class);
                startActivity(intent);
                //finish();
            }
        });

        toForumButton = findViewById(R.id.toForumButton);
        toForumButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("to recleview ");
                //Intent intent = new Intent(MainActivity.this, RecycleActivity.class);
                //startActivity(intent);
                //finish();

               String url = Constants.URL_PRE+"/boot/board/boards_json";
//                String url ="https://www.baidu.com";
                System.out.println("url:"+url);

                try {
                    getNetWorkString(url);
                    //get(url);
                    //System.out.println("board json:"+board_json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        to_pic_button=findViewById(R.id.to_pic_button);
        to_pic_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"拍照上传按钮",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(MainActivity.this,PicTakeActivity.class));
            }
        });


        universal_video_view_button = findViewById(R.id.universal_video_view_button);
        universal_video_view_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"播放视频",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,UniversalVideoActivity.class));
            }
        });


        jie_cao_video_view_button = findViewById(R.id.jie_cao_video_view_button);
        jie_cao_video_view_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"节操播放视频",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,JieCaoVideoPlayerMainActivity.class));
            }
        });


        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                System.out.println("登录对话框出现");
                   try {

                       openCustomerDialog();
                    //post(url,"");
                    //volleyGet();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        to_market_button = findViewById(R.id.to_market_button);
        to_market_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MarketActivity.class);
                startActivity(intent);
            }
        });


        to_login_button = findViewById(R.id.to_login_button);
        to_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        to_slidingmenu_button = findViewById(R.id.to_slidingmenu_button);
        to_slidingmenu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TopicListMenuActivity.class);
                startActivity(intent);
            }
        });



        button14 = findViewById(R.id.button14);
        button14.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                String url ="https://www.baidu.com/s?wd=baidu%20post&rsv_spt=1&rsv_iqid=0x965cc1320001503f&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=10&rsv_sug2=0&inputT=2053&rsv_sug4=2054";
                try {
                    //post(url,"");
                    volleyGet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        button15 = findViewById(R.id.button15);
        button15.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                String url ="https://www.baidu.com/s?wd=baidu%20post&rsv_spt=1&rsv_iqid=0x965cc1320001503f&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=10&rsv_sug2=0&inputT=2053&rsv_sug4=2054";
                try {
                    // post(url,"");
                    volleyPost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        button16 = findViewById(R.id.button16);
        button16.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                String url ="https://www.baidu.com/s?wd=baidu%20post&rsv_spt=1&rsv_iqid=0x965cc1320001503f&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=10&rsv_sug2=0&inputT=2053&rsv_sug4=2054";
                try {
                   // post(url,"");
                    volleyImageRequestPic();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        button17 = findViewById(R.id.button17);
        button17.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String url ="https://www.baidu.com/s?wd=baidu%20post&rsv_spt=1&rsv_iqid=0x965cc1320001503f&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=10&rsv_sug2=0&inputT=2053&rsv_sug4=2054";
                try {
                    // post(url,"");
                    volleyImageLoaderPic();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        iv_volley_networkimagview = findViewById(R.id.iv_volley_networkimagview);
        button18 = findViewById(R.id.button18);
        button18.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String url ="https://www.baidu.com/s?wd=baidu%20post&rsv_spt=1&rsv_iqid=0x965cc1320001503f&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=10&rsv_sug2=0&inputT=2053&rsv_sug4=2054";
                try {
                    // post(url,"");
                    volleyNetworkPic();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        web_view_button =findViewById(R.id.web_view_button);
        web_view_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WebViewTestActivity.class);
                startActivity(intent);
            }
        });


        btn_banner=findViewById(R.id.btn_banner);
        btn_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BannerActivity.class);
                startActivity(intent);
            }
        });
    }


    public void openCustomerDialog(){
         loginDialog= new LoginDialog(getContext());
        loginDialog.setShowTitle(true);
        loginDialog.setLoginListener(new LoginDialog.onLoginListener() {
             EditText editUserName = loginDialog.getEditUserName();
             EditText editPassword = loginDialog.getEditPassword();
             CheckBox cbServiceItem = loginDialog.getCbServiceItem();

            @Override
            public void onClick(View v) {
                //显示用户名、密码、是否阅读服务条款
                Toast.makeText(getContext(),"用户名:" + editUserName.getText() + "_密码:" +
                        editPassword.getText() + "\n" + (cbServiceItem.isChecked() ? "已读服务条款" : "未读服务条款"),Toast.LENGTH_SHORT).show();
                //登录的具体动作在此实现
                if(StringUtils.isNoneEmpty(editUserName.getText().toString())&&StringUtils.isNoneEmpty(editPassword.getText().toString())&&cbServiceItem.isChecked()==true){
                    loginServer(editUserName.getText().toString(),editPassword.getText().toString());

                }else {
                    Toast.makeText(MainActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
                }


            }
        });
        //显示LoginDialog
        loginDialog.show();
    }


    private void loginServer(final String userName, final String passWord) {
        Log.i("info_Login","知道了session：");
        OkHttpClient client = new OkHttpClient();
        FormBody body = new FormBody.Builder()
                .add("username",userName)
                .add("pwd",passWord)
                .add("langCode","zh-cn")
                .build();
        Request request = new Request.Builder()
                .addHeader("cookie","ss")
                .url(HttpRequestUrl.LOGIN_URL)
                .post(body)
                .build();

        //Call  call  = setCard().newCall(request);

        Call call2 = setCard().newCall(request);
        call2.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("info_call2fail",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                int userId =0;
                int returnUserId=0;
                if(response.isSuccessful()){
                    String body =response.body().string();
                    Log.i("info_call2success 返回的消息体:",body);
                    userId  = Integer.parseInt(body);
                    returnUserId = userId;
                }
                //Headers headers = response.headers();


                Headers headers =response.headers();//response为okhttp请求后的响应
                Log.i("info_respons.headers",headers+"");
                System.out.println("返回的头部信息:"+headers);
                List<String> cookies = headers.values("Set-Cookie");
                String session = (String)cookies.get(0);
                String sessionid = session.substring(0,session.indexOf(";"));

                System.out.println("返回的session id信息:"+sessionid);

                int indexEqual = sessionid.indexOf("=");
                String sessionKey = sessionid.substring(0,indexEqual-1);
                String sessionValue = sessionid.substring(indexEqual+1,sessionid.length());

                System.out.println("session key:"+sessionKey+" session value:"+sessionValue);

                Log.d(TAG, "得到的onResponse: sessionid:"+sessionid);
                //Toast.makeText(MainActivity.this, "登录的sessionid:" + sessionid, Toast.LENGTH_SHORT).show();

                SharedPreferences share = getSharedPreferences(SPkey.SESSIONSTORAGE,MODE_PRIVATE);
                SharedPreferences.Editor edit = share.edit();//编辑文件
                edit.putString("sessionid",sessionid);
                edit.commit();

                SpUtils.setKeyValue(getApplicationContext(),"userInfo","username",userName);
                SpUtils.setKeyValue(getApplicationContext(),"userInfo","pwd",passWord);
                SpUtils.setKeyValue(getApplicationContext(),"userInfo","userId",Integer.toString(userId));

                //登录成功，对话框应该隐藏
                Log.d(TAG, "onResponse: 返回的userId:"+returnUserId);
                if(returnUserId>0){
                    //
                   // Toast.makeText(MainActivity.this, "登录成功，对话框隐藏", Toast.LENGTH_SHORT).show();
                    ToastUtils.show(MainActivity.this,"登录成功，对话框隐藏");
                    System.out.println("下一步隐藏");
                    loginDialog.cancel();
                }
                //String password =sPreferences.getString("password", "")
            }
        });
    }


    private Context getContext(){
        return this;
    }



    public void getNetWorkString(String url) {
        //String   url  = "https://192.168.191.1/boot/board/boards_json";//带https的网址
        final Request request = new Request.Builder().url(url).build();
        Call  call  = setCard().newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(MainActivity.this,res,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        //2). 通过intent携带额外数据
                        String message = res;
                        intent.putExtra("MESSAGE", message);
                        //3). 启动Activity
                        startActivity(intent);

                    }
                });
            }
        });
    }

    public void postNetWorkString(String url) {
        //String   url  = "https://192.168.191.1/boot/board/boards_json";//带https的网址
        final Request request = new Request.Builder().url(url).build();
        Call  call  = setCard().newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(MainActivity.this,res,Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        //2). 通过intent携带额外数据
                        String message = res;
                        intent.putExtra("MESSAGE", message);
                        //3). 启动Activity
                        startActivity(intent);


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
            keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(getAssets().open("server.cer")));//拷贝好的证书
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

    void volleyGet(){
        // 1 创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        // 2 创建一个请求
        String url = "https://www.baidu.com";

        StringRequest stringRequest = new StringRequest(url, new com.android.volley.Response.Listener<String>() {
            // 正确接收数据回调
            @Override
            public void onResponse(String s) {
                textView.setText(s);
            }
        }, new com.android.volley.Response.ErrorListener() {// 发生异常后的监听回调
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                textView.setText("volley get加载失败" + volleyError);
            }
        });

        // 3 将创建的请求添加到请求队列中
        requestQueue.add(stringRequest);
    }

    void volleyPost(){
        // 1 创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        // 2 创建一个post请求
        //String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

        String url ="https://www.baidu.com/s?wd=baidu%20post&rsv_spt=1&rsv_iqid=0x965cc1320001503f&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=10&rsv_sug2=0&inputT=2053&rsv_sug4=2054";


        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                textView.setText(s);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                textView.setText("volley post 请求失败" + volleyError);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
//                        map.put("value1","param1");

                return map;
            }
        };

        // 3 将post请求添加到队列中
        requestQueue.add(stringRequest);
    }

    void volleyImageRequestPic(){
        // 1 创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        // 2 创建一个图片的请求
        String url = "http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg";
        ImageRequest imageRequest = new ImageRequest(url, new com.android.volley.Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                // 正确接收到图片
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                imageView.setImageResource(R.drawable.atguigu_logo);
            }
        });

        // 3 将请求添加到请求队列中
        requestQueue.add(imageRequest);
    }

    void volleyImageLoaderPic(){

        // 创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

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
        String url = "http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg";
        imageView.setVisibility(View.VISIBLE);
        ImageLoader.ImageListener imageListener = imageLoader.getImageListener(imageView, R.drawable.atguigu_logo, R.drawable.atguigu_logo);
        imageLoader.get(url, imageListener);
    }


    void volleyNetworkPic(){
        // 让控件显示
        iv_volley_networkimagview.setVisibility(View.VISIBLE);

        // 创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        // 创建一个Imageloader
        ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());

        // 默认图片和异常图片设置
        iv_volley_networkimagview.setDefaultImageResId(R.drawable.atguigu_logo);
        iv_volley_networkimagview.setErrorImageResId(R.drawable.atguigu_logo);

        // 加载图片
        String url = "http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg";
        iv_volley_networkimagview.setImageUrl(url, imageLoader);

    }

    void post(final  String url,final String json) throws Exception{
        new Thread(){
            @Override
            public void run() {
                super.run();
                //String url = "http://192.168.191.1/boot/board/boards_json";
                //String url = "https://www.baidu.com";
                OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.create(JSON, json);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                try (Response response = client.newCall(request).execute()) {
                    System.out.println("post data:"+response.body().string());
                    String msg = response.body().string();
                    Message message = Message.obtain();
                    message.what = POST;
                    message.obj = msg;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    void  get(String url) throws IOException {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String url = "https://192.168.191.1/boot/board/boards_json";
                //String url = "https://www.baidu.com";
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                try {
                    try (Response response = okHttpClient.newCall(request).execute()) {
                        System.out.println("get data:"+response.body().string());
                        String msg = response.body().string();
                        Message message = Message.obtain();
                        message.what = GET;
                        message.obj = msg;
                        handler.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    private void asy(String url){
        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                System.out.println("出错了，没有得到数据");

                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String boardJson = response.body().string();
                Log.d(TAG, "onResponse: " + boardJson);
                System.out.println("board json:"+boardJson);
//                        textView.setText(boardJson);
            }
        });
    }


}
