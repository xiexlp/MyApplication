package android.js.com.myapplication.feature.pullrefresh;

import android.content.Intent;
import android.js.com.myapplication.feature.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PullRefreshActivity extends AppCompatActivity implements View.OnClickListener {


    private Button pullRefreshListViewButton;
    private Button pullRefreshGridViewButton;
    private Button pullRefreshFragmentViewButton;
    private Button pullRefreshViewpagerViewButton;
    private Button pullRefreshViewpager2ViewButton;
    private Button pullRefreshWebviewViewButton;
    private Button pullRefreshWebviewViewButton2;
    private Button pullRefreshScrollViewButton;



    private TextView titleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_refresh);
        findViews();
        titleView= findViewById(R.id.titleView);
        titleView.setText("测试pull to refresh");
    }




    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-02-03 19:08:03 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        pullRefreshListViewButton = (Button)findViewById( R.id.pull_refresh_list_view_button );
        pullRefreshGridViewButton = (Button)findViewById( R.id.pull_refresh_grid_view_button );
        pullRefreshFragmentViewButton = (Button)findViewById( R.id.pull_refresh_fragment_view_button );
        pullRefreshViewpagerViewButton = (Button)findViewById( R.id.pull_refresh_viewpager_view_button );
        pullRefreshViewpager2ViewButton = (Button)findViewById( R.id.pull_refresh_viewpager2_view_button );
        pullRefreshWebviewViewButton = (Button)findViewById( R.id.pull_refresh_webview_view_button );
        pullRefreshWebviewViewButton2=(Button) findViewById(R.id.pull_refresh_webview_view2_button);
        pullRefreshScrollViewButton =(Button)findViewById(R.id.pull_refresh_scroll_view_button);



        pullRefreshListViewButton.setOnClickListener( this );
        pullRefreshGridViewButton.setOnClickListener( this );
        pullRefreshFragmentViewButton.setOnClickListener( this );
        pullRefreshViewpagerViewButton.setOnClickListener( this );
        pullRefreshViewpager2ViewButton.setOnClickListener( this );
        pullRefreshWebviewViewButton.setOnClickListener( this );
        pullRefreshWebviewViewButton2.setOnClickListener(this);
        pullRefreshScrollViewButton.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-02-03 19:08:03 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == pullRefreshListViewButton ) {
            System.out.println("list view pull refresh 拉动刷新");
            // Handle clicks for pullRefreshListViewButton

            startActivity(new Intent(this,PullToRefreshListActivity.class));


        } else if ( v == pullRefreshGridViewButton ) {
            System.out.println("grid view pull refresh 拉动刷新");

            startActivity(new Intent(this,PullToRefreshGridActivity.class));

            // Handle clicks for pullRefreshGridViewButton
        } else if ( v == pullRefreshFragmentViewButton ) {
            System.out.println("fragment view pull refresh 拉动刷新");

            startActivity(new Intent(this,PullToRefreshListFragmentActivity.class));
            // Handle clicks for pullRefreshFragmentViewButton
        } else if ( v == pullRefreshViewpagerViewButton ) {
            System.out.println("list view in viewpager pull refresh 拉动刷新");

            startActivity(new Intent(this,PullToRefreshListInViewPagerActivity.class));

            // Handle clicks for pullRefreshViewpagerViewButton
        } else if ( v == pullRefreshViewpager2ViewButton ) {

            startActivity(new Intent(this,PullToRefreshViewPagerActivity.class));
            System.out.println("viewpager2 view pull refresh 拉动刷新");
            // Handle clicks for pullRefreshViewpager2ViewButton
        } else if ( v == pullRefreshWebviewViewButton ) {


            startActivity(new Intent(this,PullToRefreshWebViewActivity.class));
            System.out.println("pull refresh webview pull refresh 拉动刷新");
            // Handle clicks for pullRefreshWebviewViewButton
        }else if ( v == pullRefreshWebviewViewButton2 ) {


            startActivity(new Intent(this,PullToRefreshWebView2Activity.class));
            System.out.println("pull refresh webview pull refresh 拉动刷新2");
            // Handle clicks for pullRefreshWebviewViewButton
        }else if ( v == pullRefreshScrollViewButton ) {


            startActivity(new Intent(this,PullToRefreshScrollViewActivity.class));
            System.out.println("pull refresh scroll view  pull refresh 拉动刷新");
            // Handle clicks for pullRefreshWebviewViewButton
        }
    }

}
