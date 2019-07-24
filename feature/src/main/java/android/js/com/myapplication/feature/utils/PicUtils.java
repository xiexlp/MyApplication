package android.js.com.myapplication.feature.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by root on 2019/2/11.
 */

public class PicUtils {


    public static void getAndroiodScreenProperty() {
        //WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        //wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        int screenHeight = (int) (height / density);// 屏幕高度(dp)


        Log.d("h_bl", "屏幕宽度（像素）：" + width);
        Log.d("h_bl", "屏幕高度（像素）：" + height);
        Log.d("h_bl", "屏幕密度（0.75 / 1.0 / 1.5）：" + density);
        Log.d("h_bl", "屏幕密度dpi（120 / 160 / 240）：" + densityDpi);
        Log.d("h_bl", "屏幕宽度（dp）：" + screenWidth);
        Log.d("h_bl", "屏幕高度（dp）：" + screenHeight);
    }

    /**
     * 调整图片大小
     *
     * @param bitmap
     *            源
     * @param dst_w
     *            输出宽度
     * @param dst_h
     *            输出高度
     * @return
     */
    public static Bitmap imageScale(Bitmap bitmap, int dst_w, int dst_h) {
        int src_w = bitmap.getWidth();
        int src_h = bitmap.getHeight();
        float scale_w = ((float) dst_w) / src_w;
        float scale_h = ((float) dst_h) / src_h;
        Matrix matrix = new Matrix();
        matrix.postScale(scale_w, scale_h);
        Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, src_w, src_h, matrix,
                true);
        return dstbmp;
    }

    public static int getAndroiodScreenPropertyWidth() {
        //WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        //wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        int screenHeight = (int) (height / density);// 屏幕高度(dp)


        Log.d("h_bl", "屏幕宽度（像素）：" + width);
        Log.d("h_bl", "屏幕高度（像素）：" + height);
        Log.d("h_bl", "屏幕密度（0.75 / 1.0 / 1.5）：" + density);
        Log.d("h_bl", "屏幕密度dpi（120 / 160 / 240）：" + densityDpi);
        Log.d("h_bl", "屏幕宽度（dp）：" + screenWidth);
        Log.d("h_bl", "屏幕高度（dp）：" + screenHeight);
        return screenWidth;
    }


    public static void loadImage(final Activity mContext, String url, final ImageView imageView){
        //String   url  = "https://192.168.191.1/boot/board/boards_json";//带https的网址
        final Request request = new Request.Builder().url(url).build();
        Call call = new HttpsCert().setCard((Activity) mContext).newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mContext.runOnUiThread(new Runnable() {
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
                mContext.runOnUiThread(new Runnable() {
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
