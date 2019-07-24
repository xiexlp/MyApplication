package android.js.com.myapplication.feature.jiecaoplayeractivity;

import android.content.Intent;
import android.js.com.myapplication.feature.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by Nathen on 16/7/31.
 */
public class ListViewActivity extends AppCompatActivity implements View.OnClickListener {
    Button mNormalList, mViewPagerList, mMultiHolderList, mRecyleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setTitle("About 视频播放 ListView");
        setContentView(R.layout.activity_listview);

        mNormalList = (Button) findViewById(R.id.normal_list);
        mViewPagerList = (Button) findViewById(R.id.viewpayer_list);
        mMultiHolderList = (Button) findViewById(R.id.multi_holder_list);
        mRecyleView = (Button) findViewById(R.id.recyleview);

        mNormalList.setOnClickListener(this);
        mViewPagerList.setOnClickListener(this);
        mMultiHolderList.setOnClickListener(this);
        mRecyleView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.normal_list) {
            System.out.println("正常的播放");
            //startActivity(new Intent(ListViewActivity.this, ListViewNormalActivity.class));

        } else if (i == R.id.viewpayer_list) {
            System.out.println("viewpager的视频播放");
            //startActivity(new Intent(ListViewActivity.this, ListViewViewpagerActivity.class));

        } else if (i == R.id.multi_holder_list) {
            System.out.println("分级的视频播放");
            //startActivity(new Intent(ListViewActivity.this, ListViewMultiHolderActivity.class));

        } else if (i == R.id.recyleview) {
            System.out.println("recycleview的视频播放");
            //startActivity(new Intent(ListViewActivity.this, RecyclerViewNormalActivity.class));

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
