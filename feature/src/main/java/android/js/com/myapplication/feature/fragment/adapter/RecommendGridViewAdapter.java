package android.js.com.myapplication.feature.fragment.adapter;

import android.accounts.AccountAuthenticatorActivity;
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

//import com.atguigu.shoppingmall.R;
//import com.atguigu.shoppingmall.home.bean.ResultBean;
//import com.atguigu.shoppingmall.utils.Constants;

/**
 * Created by Administrator on 2016/9/29.
 */
public class RecommendGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBean.RecommendInfoBean> data;

    public RecommendGridViewAdapter(Context mContext, List<ResultBean.RecommendInfoBean> data) {
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
            convertView = View.inflate(mContext, R.layout.item_recommend_grid_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ResultBean.RecommendInfoBean recommendInfoBean = data.get(position);
//        Glide.with(mContext)
//                .load(Constants.BASE_URl_IMAGE +recommendInfoBean.getFigure())
//                .into(holder.ivRecommend);



        System.out.println("推荐图片2222");
        loadImage((Activity)mContext,Constants.BASE_URl_IMAGE +recommendInfoBean.getFigure(),holder.ivRecommend);

        holder.tvName.setText(recommendInfoBean.getName());
        holder.tvPrice.setText("￥" + recommendInfoBean.getCover_price());
        return convertView;
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
                System.out.println("RecommendGridView 收到图片消息:" + res);
                appCompatActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(MainActivity.this,res,Toast.LENGTH_LONG).show();
                        Bitmap bitmap = BitmapFactory.decodeStream(res);

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

    static class ViewHolder {
        //@Bind(R.id.iv_recommend)
        ImageView ivRecommend;
        //@Bind(R.id.tv_name)
        TextView tvName;
        //@Bind(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            //super(view);
            //ButterKnife.bind(this, view);
            ivRecommend= view.findViewById(R.id.iv_recommend);
            tvName = view.findViewById(R.id.tv_name);
            tvPrice = view.findViewById(R.id.tv_price);
        }
    }
}
