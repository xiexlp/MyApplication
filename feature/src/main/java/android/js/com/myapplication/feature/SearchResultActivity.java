package android.js.com.myapplication.feature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SearchResultActivity extends AppCompatActivity {

    EditText search_text_edit;
    WebView topic_web_veiw;
    Button search_button;
    String url;
    String url1;
    String baiduUrl;

    private ProgressBar mLoadingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        mLoadingProgress = (ProgressBar)findViewById(R.id.progressBarLoading);

        topic_web_veiw = findViewById(R.id.topic_web_veiw);
        search_text_edit = findViewById(R.id.search_text_view);

        search_button = findViewById(R.id.search_button);


        Intent intent =getIntent();
        url = intent.getStringExtra("url");
        url1 = url;
        baiduUrl = "https://www.baidu.com";
        search_text_edit.setText(url);


        //http://192.168.51.104:8080/atguigu/json/GOODSINFO_URL.json2691
//            wbGoodInfoMore.loadUrl(Constants.GOODSINFO_URL + product_id);
        topic_web_veiw.loadUrl(baiduUrl);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        topic_web_veiw.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        //启用支持javascript
        WebSettings settings = topic_web_veiw.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);

        //优先使用缓存
        topic_web_veiw.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("webview浏览:"+url1);

                //http://192.168.51.104:8080/atguigu/json/GOODSINFO_URL.json2691
//            wbGoodInfoMore.loadUrl(Constants.GOODSINFO_URL + product_id);
                topic_web_veiw.loadUrl(url1);
                //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
                topic_web_veiw.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                        view.loadUrl(url1);
                        return true;
                    }
                });
                //启用支持javascript
                WebSettings settings = topic_web_veiw.getSettings();
                settings.setJavaScriptEnabled(true);
                settings.setUseWideViewPort(true);
                settings.setBlockNetworkImage(false);
                settings.setUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36");
                //优先使用缓存
                topic_web_veiw.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            }
        });





    }


    private class WebChromeClientProgress extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int progress) {
            if (mLoadingProgress != null) {
                mLoadingProgress.setProgress(progress);
                if (progress == 100) mLoadingProgress.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, progress);
        }
    }
}
