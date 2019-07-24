package android.js.com.myapplication.feature;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.js.com.myapplication.feature.fragment.eventbus.LoginEvent;
import android.js.com.myapplication.feature.utils.HttpRequestUrl;
import android.js.com.myapplication.feature.utils.HttpsCert;
import android.js.com.myapplication.feature.utils.SPkey;
import android.js.com.myapplication.feature.utils.SharedPreferencesUtils;
import android.js.com.myapplication.feature.utils.SpUtils;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String TAG = "SplashScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去除title
       requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //隐藏标题栏
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        //得到当前界面的装饰视图
        if(Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            //设置让应用主题内容占据状态栏和导航栏
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //设置状态栏和导航栏颜色为透明
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
        //隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) actionBar.hide();

        setContentView(R.layout.activity_splash_screen);

        String username= SpUtils.getKeyValue(getApplicationContext(),"userInfo","username");
        System.out.println("取出来的用户名:"+username);
        String pwd = SpUtils.getKeyValue(getApplicationContext(),"userInfo","pwd");
        System.out.println("取出来的密码:"+pwd);
        String autoLoginStr= SpUtils.getKeyValue(getApplicationContext(), SPkey.USERINFO,SPkey.AUTOLOGIN);
        System.out.println("是否自动登录:"+autoLoginStr);
        String rememberUserStr = SpUtils.getKeyValue(getApplicationContext(),SPkey.REMEMBERUSER,SPkey.REMEMBERUSER);
        System.out.println("是否记住用户:"+rememberUserStr);
        boolean rememberUser = Boolean.getBoolean(rememberUserStr);
        boolean autoLogin = Boolean.getBoolean(autoLoginStr);

        Toast.makeText(this, "用户名:" + username + " 密码:" + pwd, Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "自动登录:" + autoLoginStr + " 是否记录用户:" + rememberUserStr, Toast.LENGTH_SHORT).show();
        if(!username.equalsIgnoreCase("")&&!pwd.equalsIgnoreCase("")){
            //自动登录
            if(autoLogin){
                loginServer(username,pwd,rememberUser,autoLogin);
            }
        }else {
            Intent intent = new Intent(SplashScreenActivity.this,LoginActivity.class);
            if(rememberUser){
                intent.putExtra("username", username);
                intent.putExtra("pwd",pwd);
                intent.putExtra("rememberUser",rememberUser);
                intent.putExtra("autoLogin",autoLogin);
            }
            startActivity(intent);
            finish();
        }


        //两秒钟之后进入主页面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
                startActivity(intent);
                //结束当前页面
                finish();
            }
        },1000);
    }


    private void loginServer(final String userName, final String passWord, final boolean rememberUserName, final boolean autoLogin) {
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

        Call call2 = new HttpsCert().setCard(this).newCall(request);
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
                if(rememberUserName)
                    SpUtils.setKeyValue(getApplicationContext(),"userInfo","rememberUserName","1");
                else SpUtils.setKeyValue(getApplicationContext(),"userInfo","rememberUserName","0");

                if(autoLogin)
                    SpUtils.setKeyValue(getApplicationContext(),"userInfo","autoLogin","1");
                else SpUtils.setKeyValue(getApplicationContext(),"userInfo","autoLogin","0");

                //登录成功，对话框应该隐藏
                Log.d(TAG, "onResponse: 返回的userId:"+returnUserId);
                if(returnUserId>0){
                    //
                    // Toast.makeText(MainActivity.this, "登录成功，对话框隐藏", Toast.LENGTH_SHORT).show();
                    //ToastUtils.show(LoginActivity.this,"登录成功，对话框隐藏");
                    System.out.println("登录成功，下一步隐藏");
                    //普通事件
                    //EventBus.getDefault().post(new LoginEvent(returnUserId,userName));
                    //粘性事件
                    EventBus.getDefault().postSticky(new LoginEvent(returnUserId,userName));
                    System.out.println("已经发送Eventbus消息");
                    Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
                    startActivity(intent);

                    //loginDialog.cancel();
                }
                //String password =sPreferences.getString("password", "")
            }
        });
    }





}
