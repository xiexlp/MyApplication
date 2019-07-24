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
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
 * Created by Administrator on 2016/9/28.
 */
public class ChannelAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBean.ChannelInfoBean> channel_info;




    public ChannelAdapter(Context mContext, List<ResultBean.ChannelInfoBean> channel_info) {
        this.mContext = mContext;
        this.channel_info = channel_info;
    }


    Handler handler = new Handler();


    @Override
    public int getCount() {
        return channel_info == null ? 0 : channel_info.size();
    }

    @Override
    public Object getItem(int position) {
        return channel_info.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holer;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_channel, null);
            holer = new ViewHolder(convertView);
            convertView.setTag(holer);
        } else {
            holer = (ViewHolder) convertView.getTag();
        }
        final ResultBean.ChannelInfoBean channelInfoBean = channel_info.get(position);
        holer.tvChannel.setText(channelInfoBean.getChannel_name());
//        Glide.with(mContext)
//                .load(Constants.BASE_URl_IMAGE +channelInfoBean.getImage())
//                .into(holer.ivChannel);
        //修改一下
        System.out.println("ChannelView频道图片log url:"+Constants.BASE_URl_IMAGE +channelInfoBean.getImage());
        loadImage((Activity) mContext,Constants.BASE_URl_IMAGE +channelInfoBean.getImage(),holer.ivChannel);

        return convertView;
    }

    class ViewHolder {
        //@Bind(R.id.iv_channel)
        ImageView ivChannel;
        //@Bind(R.id.tv_channel)
        TextView tvChannel;

        ViewHolder(View view) {
            ivChannel = view.findViewById(R.id.iv_channel);
            tvChannel =view.findViewById(R.id.tv_channel);
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
                System.out.println("收到图片消息:" + res);
                appCompatActivity.runOnUiThread(new Runnable() {
                //handler.post(new Runnable(){
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
}
