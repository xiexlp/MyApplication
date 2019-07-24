package android.js.com.myapplication.feature;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.js.com.myapplication.feature.fragment.eventbus.LoginEvent;
import android.js.com.myapplication.feature.utils.Base64Utils;
import android.js.com.myapplication.feature.utils.HttpRequestUrl;
import android.js.com.myapplication.feature.utils.HttpsCert;
import android.js.com.myapplication.feature.utils.SPkey;
import android.js.com.myapplication.feature.utils.SharedPreferencesUtils;
import android.js.com.myapplication.feature.utils.SpUtils;
import android.js.com.myapplication.feature.utils.ToastUtils;
import android.js.com.myapplication.feature.widget.LoadingDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.*;

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


/**
 * 登录界面
 */

public class LoginActivity extends AppCompatActivity
        implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "LoginActivity";
    //布局内的控件
    private EditText et_name;
    private EditText et_password;
    private Button mLoginBtn;
    private CheckBox checkBox_password;
    private CheckBox checkBox_login;
    private ImageView iv_see_password;

    private LoadingDialog mLoadingDialog; //显示正在加载的对话框

    String username;
    String pwd;
    boolean rememberUser;
    boolean autoLogin;

    String initPwd="111111";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //把
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) actionBar.hide();

        Intent passIntent = getIntent();
        username = passIntent.getStringExtra("username");
        pwd = passIntent.getStringExtra("pwd");
        rememberUser= passIntent.getBooleanExtra("rememberUser",true);
        autoLogin = passIntent.getBooleanExtra("autoLogin",true);

        setContentView(R.layout.activity_login);
        initViews();
        setupEvents();
        initData();

    }

    private void initData() {


        //判断用户第一次登陆
        if (firstLogin()) {
            checkBox_password.setChecked(false);//取消记住密码的复选框
            checkBox_login.setChecked(false);//取消自动登录的复选框
        }
        //判断是否记住密码
        if (remenberPassword()) {
            checkBox_password.setChecked(true);//勾选记住密码
            setTextNameAndPassword();//把密码和账号输入到输入框中
        } else {
            setTextName();//把用户账号放到输入账号的输入框中
        }

        //判断是否自动登录
        if (autoLogin()) {
            checkBox_login.setChecked(true);
            login();//去登录就可以

        }
    }

    /**
     * 把本地保存的数据设置数据到输入框中
     */
    public void setTextNameAndPassword() {
        et_name.setText("" + getLocalName());
        et_password.setText("" + getLocalPassword());
    }

    /**
     * 设置数据到输入框中
     */
    public void setTextName() {
        et_name.setText("" + getLocalName());
    }


    /**
     * 获得保存在本地的用户名
     */
    public String getLocalName() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
        String name = helper.getString("name");
        return name;
    }


    /**
     * 获得保存在本地的密码
     */
    public String getLocalPassword() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
        String password = helper.getString("password");
        return Base64Utils.decryptBASE64(password);   //解码一下
//       return password;   //解码一下

    }

    /**
     * 判断是否自动登录
     */
    private boolean autoLogin() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
        boolean autoLogin = helper.getBoolean("autoLogin", false);
        return autoLogin;
    }

    /**
     * 判断是否记住密码
     */
    private boolean remenberPassword() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
        boolean remenberPassword = helper.getBoolean("remenberPassword", false);
        return remenberPassword;
    }


    private void initViews() {
        mLoginBtn = (Button) findViewById(R.id.btn_login);
        et_name = (EditText) findViewById(R.id.et_account);

        et_password = (EditText) findViewById(R.id.et_password);


        if(rememberUser){
            et_name.setText(username);
            et_password.setText(pwd);
        }

        //设个默认值
        et_password.setText(initPwd);

        checkBox_password = (CheckBox) findViewById(R.id.checkBox_password);
        checkBox_login = (CheckBox) findViewById(R.id.checkBox_login);

        if(autoLogin){
            checkBox_login.setChecked(autoLogin);
        }

        iv_see_password = (ImageView) findViewById(R.id.iv_see_password);
    }

    private void setupEvents() {
        mLoginBtn.setOnClickListener(this);
        checkBox_password.setOnCheckedChangeListener(this);
        checkBox_login.setOnCheckedChangeListener(this);
        iv_see_password.setOnClickListener(this);

    }

    /**
     * 判断是否是第一次登陆
     */
    private boolean firstLogin() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
        boolean first = helper.getBoolean("first", true);
        if (first) {
            //创建一个ContentVa对象（自定义的）设置不是第一次登录，,并创建记住密码和自动登录是默认不选，创建账号和密码为空
            helper.putValues(new SharedPreferencesUtils.ContentValue("first", false),
                    new SharedPreferencesUtils.ContentValue("remenberPassword", false),
                    new SharedPreferencesUtils.ContentValue("autoLogin", false),
                    new SharedPreferencesUtils.ContentValue("name", ""),
                    new SharedPreferencesUtils.ContentValue("password", ""));
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_login) {
            loadUserName();    //无论如何保存一下用户名
            login(); //登陆

        } else if (i == R.id.iv_see_password) {
            setPasswordVisibility();    //改变图片并设置输入框的文本可见或不可见

        }
    }

    /**
     * 模拟登录情况
     * 用户名csdn，密码123456，就能登录成功，否则登录失败
     */
    private void login() {

        //先做一些基本的判断，比如输入的用户命为空，密码为空，网络不可用多大情况，都不需要去链接服务器了，而是直接返回提示错误
        if (getAccount().isEmpty()){
            showToast("你输入的账号为空！");
            return;
        }

        if (getPassword().isEmpty()){
            showToast("你输入的密码为空！");
            return;
        }
        //登录一般都是请求服务器来判断密码是否正确，要请求网络，要子线程
        //showLoading();//显示加载框
        Thread loginRunnable = new Thread() {

            @Override
            public void run() {
                super.run();
                setLoginBtnClickable(false);//点击登录后，设置登录按钮不可点击状态


                //睡眠3秒
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String userName = et_name.getText().toString();
                String pwd = et_password.getText().toString();
                boolean rememberUserName = checkBox_password.isChecked();
                boolean autoLogin = checkBox_login.isChecked();
                loginServer(userName,pwd,rememberUserName,autoLogin);

                //判断账号和密码

                /**
                if (getAccount().equals("csdn") && getPassword().equals("123456")) {
                    showToast("登录成功");
                    loadCheckBoxState();//记录下当前用户记住密码和自动登录的状态;

                    startActivity(new Intent(LoginActivity.this, LoginAfterActivity.class));
                    finish();//关闭页面
                } else {
                    showToast("输入的登录账号或密码不正确");
                }


                setLoginBtnClickable(true);  //这里解放登录按钮，设置为可以点击
                hideLoading();//隐藏加载框
                **/
            }
        };
        loginRunnable.start();
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
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);

                    //loginDialog.cancel();
                }
                //String password =sPreferences.getString("password", "")
            }
        });
    }


    /**
     * 保存用户账号
     */
    public void loadUserName() {
        if (!getAccount().equals("") || !getAccount().equals("请输入登录账号")) {
            SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
            helper.putValues(new SharedPreferencesUtils.ContentValue("name", getAccount()));
        }

    }

    /**
     * 设置密码可见和不可见的相互转换
     */
    private void setPasswordVisibility() {
        if (iv_see_password.isSelected()) {
            iv_see_password.setSelected(false);
            //密码不可见
            et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        } else {
            iv_see_password.setSelected(true);
            //密码可见
            et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }

    }

    /**
     * 获取账号
     */
    public String getAccount() {
        return et_name.getText().toString().trim();//去掉空格
    }

    /**
     * 获取密码
     */
    public String getPassword() {
        return et_password.getText().toString().trim();//去掉空格
    }


    /**
     * 保存用户选择“记住密码”和“自动登陆”的状态
     */
    private void loadCheckBoxState() {
        loadCheckBoxState(checkBox_password, checkBox_login);
    }

    /**
     * 保存按钮的状态值
     */
    public void loadCheckBoxState(CheckBox checkBox_password, CheckBox checkBox_login) {

        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");

        //如果设置自动登录
        if (checkBox_login.isChecked()) {
            //创建记住密码和自动登录是都选择,保存密码数据
            helper.putValues(
                    new SharedPreferencesUtils.ContentValue("remenberPassword", true),
                    new SharedPreferencesUtils.ContentValue("autoLogin", true),
                    new SharedPreferencesUtils.ContentValue("password", Base64Utils.encryptBASE64(getPassword())));

        } else if (!checkBox_password.isChecked()) { //如果没有保存密码，那么自动登录也是不选的
            //创建记住密码和自动登录是默认不选,密码为空
            helper.putValues(
                    new SharedPreferencesUtils.ContentValue("remenberPassword", false),
                    new SharedPreferencesUtils.ContentValue("autoLogin", false),
                    new SharedPreferencesUtils.ContentValue("password", ""));
        } else if (checkBox_password.isChecked()) {   //如果保存密码，没有自动登录
            //创建记住密码为选中和自动登录是默认不选,保存密码数据
            helper.putValues(
                    new SharedPreferencesUtils.ContentValue("remenberPassword", true),
                    new SharedPreferencesUtils.ContentValue("autoLogin", false),
                    new SharedPreferencesUtils.ContentValue("password", Base64Utils.encryptBASE64(getPassword())));
        }
    }

    /**
     * 是否可以点击登录按钮
     *
     * @param clickable
     */
    public void setLoginBtnClickable(boolean clickable) {
        mLoginBtn.setClickable(clickable);
    }


    /**
     * 显示加载的进度款
     */
    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this, getString(R.string.loading), false);
        }
        mLoadingDialog.show();
    }


    /**
     * 隐藏加载的进度框
     */
    public void hideLoading() {
        if (mLoadingDialog != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLoadingDialog.hide();
                }
            });

        }
    }


    /**
     * CheckBox点击时的回调方法 ,不管是勾选还是取消勾选都会得到回调
     *
     * @param buttonView 按钮对象
     * @param isChecked  按钮的状态
     */
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == checkBox_password) {  //记住密码选框发生改变时
            if (!isChecked) {   //如果取消“记住密码”，那么同样取消自动登陆
                checkBox_login.setChecked(false);
            }
        } else if (buttonView == checkBox_login) {   //自动登陆选框发生改变时
            if (isChecked) {   //如果选择“自动登录”，那么同样选中“记住密码”
                checkBox_password.setChecked(true);
            }
        }
    }


    /**
     * 监听回退键
     */
    @Override
    public void onBackPressed() {
        if (mLoadingDialog != null) {
            if (mLoadingDialog.isShowing()) {
                mLoadingDialog.cancel();
            } else {
                finish();
            }
        } else {
            finish();
        }

    }

    /**
     * 页面销毁前回调的方法
     */
    protected void onDestroy() {
        if (mLoadingDialog != null) {
            mLoadingDialog.cancel();
            mLoadingDialog = null;
        }
        super.onDestroy();
    }


    public void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
