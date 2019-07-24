package android.js.com.myapplication.feature;

import android.content.Intent;
import android.graphics.Color;
import android.js.com.myapplication.feature.bean.TopicDetailBean;
import android.js.com.myapplication.feature.editor.RichEditor;
import android.js.com.myapplication.feature.fragment.adapter.ProductListRecycleAdapter;
import android.js.com.myapplication.feature.utils.Constants;
import android.js.com.myapplication.feature.utils.HttpRequestUrl;
import android.js.com.myapplication.feature.utils.HttpsCert;
import android.js.com.myapplication.feature.utils.SpUtils;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.r0adkll.slidr.Slidr;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by root on 2019/2/22.
 */

public class TopicNewActivity extends AppCompatActivity {

    private static final int RESULT_CODE = 1003;
    private static final String TAG = "TopicNewActivity";
    int boardId=0;
    String boardName;

    RichEditor mEditor;
    TextView mPreview;
    EditText topic_title_edit_text;

    //声明，放在声明中
//TextView et_main_message;
    TextView tv_board_id;


    int userId =0;
    String username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //隐藏标题栏
       ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) actionBar.hide();


        setContentView(R.layout.activity_topic_new);




        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
         boardId = bundle.getInt("boardId");

         boardName = bundle.getString("boardName");
        userId = Integer.parseInt(SpUtils.getKeyValue(getApplicationContext(),"userInfo","userId"));
        //username = bundle.getString("username");
        username = SpUtils.getKeyValue(getApplicationContext(),"userInfo","username");

        topic_title_edit_text = (EditText) findViewById(R.id.topic_title_edit_text);
        //实例化，放在onCreate方法中
       // tv_board_id=(TextView) findViewById(R.id.tv_board_id);
       // tv_board_id.setText(boardId);

        mEditor = (RichEditor) findViewById(R.id.editor);
        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(22);
        mEditor.setEditorFontColor(Color.RED);
        //mEditor.setEditorBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundResource(R.drawable.bg);
        mEditor.setPadding(10, 10, 10, 10);
        //mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        mEditor.setPlaceholder("Insert text here...");
        //mEditor.setInputEnabled(false);

        Slidr.attach(this);

        mPreview = (TextView) findViewById(R.id.preview);
        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override public void onTextChange(String text) {
                mPreview.setText(text);
            }
        });

        findViewById(R.id.action_undo).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.undo();
            }
        });

        findViewById(R.id.action_redo).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.redo();
            }
        });

        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBold();
            }
        });

        findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setItalic();
            }
        });

        findViewById(R.id.action_subscript).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setSubscript();
            }
        });

        findViewById(R.id.action_superscript).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setSuperscript();
            }
        });

        findViewById(R.id.action_strikethrough).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setStrikeThrough();
            }
        });

        findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setUnderline();
            }
        });

        findViewById(R.id.action_heading1).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(1);
            }
        });

        findViewById(R.id.action_heading2).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(2);
            }
        });

        findViewById(R.id.action_heading3).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(3);
            }
        });

        findViewById(R.id.action_heading4).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(4);
            }
        });

        findViewById(R.id.action_heading5).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(5);
            }
        });

        findViewById(R.id.action_heading6).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(6);
            }
        });

        findViewById(R.id.action_txt_color).setOnClickListener(new View.OnClickListener() {
            private boolean isChanged;

            @Override public void onClick(View v) {
                mEditor.setTextColor(isChanged ? Color.BLACK : Color.RED);
                isChanged = !isChanged;
            }
        });

        findViewById(R.id.action_bg_color).setOnClickListener(new View.OnClickListener() {
            private boolean isChanged;

            @Override public void onClick(View v) {
                mEditor.setTextBackgroundColor(isChanged ? Color.TRANSPARENT : Color.YELLOW);
                isChanged = !isChanged;
            }
        });

        findViewById(R.id.action_indent).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setIndent();
            }
        });

        findViewById(R.id.action_outdent).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setOutdent();
            }
        });

        findViewById(R.id.action_align_left).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignLeft();
            }
        });

        findViewById(R.id.action_align_center).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignCenter();
            }
        });

        findViewById(R.id.action_align_right).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignRight();
            }
        });

        findViewById(R.id.action_blockquote).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBlockquote();
            }
        });

        findViewById(R.id.action_insert_bullets).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBullets();
            }
        });

        findViewById(R.id.action_insert_numbers).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setNumbers();
            }
        });

        findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.insertImage("http://www.1honeywan.com/dachshund/image/7.21/7.21_3_thumb.JPG",
                        "dachshund");
            }
        });

        findViewById(R.id.action_insert_link).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.insertLink("https://github.com/wasabeef", "wasabeef");
            }
        });
        findViewById(R.id.action_insert_checkbox).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.insertTodo();
            }
        });



    }


    public void btnBack(View v){
        TopicNewActivity.this.finish();
    }

    public void btnSave(View v){
        // Toast.makeText(RicheditorTestActivity.this,"保存成功main activity",Toast.LENGTH_SHORT).show();
        Intent intent =new Intent();
//        intent.putExtra("name","canavaluo");
//
//
        String title = topic_title_edit_text.getText().toString();
        String content = mEditor.getHtml();
        Log.d(TAG, "btnSave: title "+title);
        intent.putExtra("content",content);
        Log.d(TAG, "btnSave: title:"+title+" content:"+content);

        TopicDetailBean topicDetailBean = new TopicDetailBean();
        topicDetailBean.setTitle(title);
        topicDetailBean.setContent(content);
        topicDetailBean.setUserId(userId);
        topicDetailBean.setUsername(username);
        topicDetailBean.setBoardId(boardId);
        topicDetailBean.setAvatar("https://192.168.191.1/img/sss.jpg");
        postNetWorkString(HttpRequestUrl.TOPIC_ADD_URL,topicDetailBean);

//
//        setResult(RESULT_CODE,intent);
//        //        finish();
//        RicheditorTestActivity.this.finish();

        //这里增加处理主题增加的逻辑，应该还需要快速回复的功能，再增加搜索的功能

        this.setResult(RESULT_CODE, intent);
        finish();


    }


    public void postNetWorkString(String url,TopicDetailBean topicDetailBean) {
        //String   url  = "https://192.168.191.1/boot/board/boards_json";//带https的网址

        FormBody body = new FormBody.Builder()
                .add("title",topicDetailBean.getTitle())
                .add("content",topicDetailBean.getContent())
                .add("avatar",topicDetailBean.getAvatar())
                .add("userId",Integer.toString(topicDetailBean.getUserId()))
                .add("username",topicDetailBean.getUsername())
                .add("pwd","111111")
                .add("boardId",Integer.toString(topicDetailBean.getBoardId()))
                .add("langCode","zh-cn")
                .build();

        String sessionid= SpUtils.getSessionCookie(this,"sessionid");
        System.out.println("取出来的session id:"+sessionid);

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
                System.out.println("增加主题成功");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("正常收到post消息");


                    }
                });
            }
        });
    }



}
