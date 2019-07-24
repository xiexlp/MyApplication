package android.js.com.myapplication.feature.fragment.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.js.com.myapplication.feature.R;
import android.js.com.myapplication.feature.bean.ResultBean;
import android.js.com.myapplication.feature.utils.Constants;
import android.js.com.myapplication.feature.utils.HttpsCert;
import android.js.com.myapplication.feature.utils.PicUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.atguigu.shoppingmall.R;
//import com.atguigu.shoppingmall.home.bean.ResultBean;
//import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

//import butterknife.Bind;
//import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/2.
 */
public class HotGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBean.HotInfoBean> data;

    public HotGridViewAdapter(Context mContext, List<ResultBean.HotInfoBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_hot_grid_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ResultBean.HotInfoBean hotInfoBean = data.get(position);




//        Glide.with(mContext)
//                .load(Constants.BASE_URl_IMAGE +hotInfoBean.getFigure())
//                .into(holder.ivHot);

        loadImage((Activity)mContext,Constants.BASE_URl_IMAGE +hotInfoBean.getFigure(),holder.ivHot);


        holder.tvName.setText(hotInfoBean.getName());
        holder.tvPrice.setText("￥" + hotInfoBean.getCover_price());
        return convertView;
    }



    static class ViewHolder {
//        @Bind(R.id.iv_hot)
        ImageView ivHot;
//        @Bind(R.id.tv_name)
        TextView tvName;
//        @Bind(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            this.ivHot = view.findViewById(R.id.iv_hot);
            tvName = view.findViewById(R.id.tv_name);
            tvPrice = view.findViewById(R.id.tv_price);
            //ButterKnife.bind(this, view);
        }
    }

    private void loadImage(final Activity mContext, String url, final ImageView imageView){
        //String   url  = "https://192.168.191.1/boot/board/boards_json";//带https的网址
        final Request request = new Request.Builder().url(url).build();
        Call call = new HttpsCert().setCard((Activity) mContext).newCall(request);
        final AppCompatActivity appCompatActivity = (AppCompatActivity)mContext;
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                appCompatActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
                Log.i("下载图片joker出错了:", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response)
                    throws IOException {
                final InputStream res = response.body().byteStream();

                //Log.e("joker",res);
                System.out.println("HotGridView 收到图片消息:" + res);
                appCompatActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(MainActivity.this,res,Toast.LENGTH_LONG).show();
                        Bitmap bitmap = BitmapFactory.decodeStream(res);
                        if(bitmap==null) return;
                        //这个地方需要调整
                        Bitmap mBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
                        mBitmap.setWidth(40);mBitmap.setHeight(20);
                        //Bitmap bitmap1=PicUtils.imageScale(bitmap,PicUtils.getAndroiodScreenPropertyWidth(),240);
                        imageView.setImageBitmap(mBitmap);
                        imageView.setAdjustViewBounds(true) ;
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        //mHolder.imageView_item_listview_topic_pic.setImageBitmap(bitmap);

                    }
                });
            }
        });
    }
}
