package android.js.com.myapplication.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewTestActivity extends AppCompatActivity {

    WebView web_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_test);

        web_view = findViewById(R.id.web_view);
        String url="https://192.168.191.1/boot/topic/topicattach?topicId=84";
        final String baiduUrl ="https://www.baidu.com";

        //http://192.168.51.104:8080/atguigu/json/GOODSINFO_URL.json2691
//            wbGoodInfoMore.loadUrl(Constants.GOODSINFO_URL + product_id);
        web_view.loadUrl(baiduUrl);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        web_view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(baiduUrl);
                return true;
            }
        });
        //启用支持javascript
        WebSettings settings = web_view.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);

        //优先使用缓存
        web_view.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }
}
