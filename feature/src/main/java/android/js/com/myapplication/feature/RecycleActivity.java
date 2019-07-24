package android.js.com.myapplication.feature;

import android.js.com.myapplication.feature.R;
import android.js.com.myapplication.feature.SplashDemo.GeneralAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecycleActivity extends AppCompatActivity {

    RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        //初始化数据
        List<String> datas = new ArrayList<>();
        for(int i=0;i<10;i++){
            datas.add("item "+i);
        }

        recyclerView = findViewById(R.id.recycler_view);
//设置LayoutManager为LinearLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//设置Adapter
        recyclerView.setAdapter(new GeneralAdapter(this,datas));

    }


}
