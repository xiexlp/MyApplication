package android.js.com.myapplication.feature;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class ActivityTranactionActivity extends Activity implements View.OnClickListener {

    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;


    private Button ib_good_info_back;
    private Button ib_good_info_more;
    private Button btn_good_info_topay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranaction);

        findViewById(R.id.ib_good_info_back).setOnClickListener(this);
        findViewById(R.id.ib_good_info_more).setOnClickListener(this);
        llGoodsRoot = (LinearLayout) findViewById(R.id.ll_goods_root);
        tvGoodInfoCallcenter = (TextView) findViewById(R.id.tv_good_info_callcenter);
        tvGoodInfoCollection = (TextView) findViewById(R.id.tv_good_info_collection);
        findViewById(R.id.btn_good_info_topay).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.ib_good_info_back) {//TODO implement
            System.out.println("返回");
            Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show();
        } else if (i == R.id.ib_good_info_more) {//TODO implement
            System.out.println("更多");
            Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show();
        } else if (i == R.id.btn_good_info_topay) {//TODO implement
            System.out.println("立即支付");
            Toast.makeText(this, "立即支付", Toast.LENGTH_SHORT).show();
        }
    }
}
