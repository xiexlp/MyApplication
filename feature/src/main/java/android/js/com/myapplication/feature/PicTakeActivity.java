package android.js.com.myapplication.feature;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.js.com.myapplication.feature.bean.FileUploadResultBean;
import android.js.com.myapplication.feature.utils.BitmapUtils;
import android.js.com.myapplication.feature.utils.Constants;
import android.js.com.myapplication.feature.utils.FileUtils;
import android.js.com.myapplication.feature.utils.HttpRequestUrl;
import android.js.com.myapplication.feature.utils.HttpsCert;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PicTakeActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1100;
    private static final int RESULT_CAMERA_IMAGE = 1101;
    private static final int CAMERA_JAVA_REQUEST_CODE = 1102;

    public static final int TAKE_PHOTO = 1;

    public static final int CHOOSE_PHOTO = 2;

    Button open_pic_pop_button;
    Button upload_pic_button;
    ImageView picture;

    private Uri imageUri;

    private File imageLocalFile;

    private WebView remote_pic_web_view;
    private TextView upload_pic_url_text_view;

    public static final String TAG="PicTakeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_take);

        //申明一个权限,声明照片权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_JAVA_REQUEST_CODE);
        }

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();


        picture = findViewById(R.id.picture);
        open_pic_pop_button = findViewById(R.id.open_pic_pop_button);
        open_pic_pop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopueWindow();
            }
        });


        upload_pic_button = findViewById(R.id.upload_pic_button);
        upload_pic_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("upload pic button");
                //图片上传到服务器
                uploadImg();

            }
        });

        remote_pic_web_view= findViewById(R.id.remote_pic_web_view);

        upload_pic_url_text_view = findViewById(R.id.upload_pic_url_text_view);
    }


    private void showPopueWindow(){
        View popView = View.inflate(this,R.layout.popupwindow_camera_need,null);
        Button bt_album = (Button) popView.findViewById(R.id.btn_pop_album);
        Button bt_camera = (Button) popView.findViewById(R.id.btn_pop_camera);
        Button bt_cancle = (Button) popView.findViewById(R.id.btn_pop_cancel);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels*1/3;

        final PopupWindow popupWindow = new PopupWindow(popView,weight,height);
        //popupWindow.setAnimationStyle(R.style.anim_popup_dir);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);

        bt_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
               // startActivityForResult(i, RESULT_LOAD_IMAGE);
                openAlbum();
                popupWindow.dismiss();

            }
        });
        bt_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //takeCamera(RESULT_CAMERA_IMAGE);

                // 创建File对象，用于存储拍照后的图片
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");

                imageLocalFile = outputImage;

                System.out.println("存储文件的文件夹位置:"+getExternalCacheDir());

                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT < 24) {
                    imageUri = Uri.fromFile(outputImage);
                } else {
                    imageUri = FileProvider.getUriForFile(PicTakeActivity.this, "com.example.js.cameraalbumtest.fileprovider", outputImage);
                }
                // 启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);



                popupWindow.dismiss();

            }
        });
        bt_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM,0,50);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        //从这可以做上传的操作,
                        picture.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        // 4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void uploadImg(){

//        File file = null;   //图片地址
//        try {
//            //String path = FileUtils.getFilePathByUri(PicTakeActivity.this,imageUri);
//
//            System.out.println("图片路径local path:"+imageLocalPath);
//            file = new File(imageLocalPath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //先压缩，后存储
        String filePath=BitmapUtils.compressImageUpload(imageLocalFile.getAbsolutePath());
        File compressedFile = new File(filePath);

        System.out.println("本地图片存储:"+compressedFile.getAbsolutePath()+" name:"+compressedFile.getName());

        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        OkHttpClient client = new OkHttpClient();


        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        //File f = new File(localPath);
        builder.addFormDataPart("file", compressedFile.getName(), RequestBody.create(MEDIA_TYPE_PNG, compressedFile));

        final MultipartBody requestBody = builder.build();
        //构建请求
        final Request request = new Request.Builder()
                .url(HttpRequestUrl.UPLOAD_FILE_URL)//地址
                .post(requestBody)//添加请求体
                .build();

        new HttpsCert().setCard(this).newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                //callBack.onFaile(e.getMessage());
                System.out.println("上传失败:e.getLocalizedMessage() = " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String fileUploadResultStr= response.body().string();
                System.out.println("上传图片返回的信息:"+fileUploadResultStr);
                List<FileUploadResultBean> fileUploadResultBeans= JSON.parseArray(fileUploadResultStr,FileUploadResultBean.class);

                final String httpPicUrl = fileUploadResultBeans.get(0).getUrl();
                System.out.println("远程服务端地址:"+httpPicUrl);

                final String newUrl = httpPicUrl.replace("localhost","192.168.191.1");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        remote_pic_web_view.loadUrl(newUrl);
                        upload_pic_url_text_view.setText(newUrl);

                        upload_pic_url_text_view.setMovementMethod(LinkMovementMethod.getInstance());
                        String link = "图片地址:<a href=\""+newUrl+"\">照相机拍的</a>"+newUrl;
                        Spanned text = Html.fromHtml(link);
                        upload_pic_url_text_view.setText(text);

                    }
                });

                //FileUpload resultBean = new Gson().fromJson(response.body().string(), FileUpload.class);
                //callBack.onSuccess(resultBean);
            }
        });




    }


    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }

        displayImage(imagePath); // 根据图片路径显示图片
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO); // 打开相册
    }

    private void takeCamera(int num) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

//        takePictureIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            String  contentUri = FileProvider.getUriForFile(PicTakeActivity.this, this.getPackageManager() + ".fileProvider", File(pt_dir))
//            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);    //将拍取的照片保存到指定URI
//        } else {
//            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(File(pt_dir)))
//        }
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            photoFile = createImageFile();
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
            }
        }

        startActivityForResult(takePictureIntent, num);//跳转界面传回拍照所得数据
    }


    private File createImageFile() {
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        String dir=storageDir.getAbsolutePath()+"/iforum_mobile/tmpPhoto";
        File fileDir = new File(dir);
        if(!fileDir.exists()) {
            try {
                fileDir.createNewFile();
                System.out.println("建立文件夹");
            } catch (IOException e) {
                System.out.println("建立文件夹失败");
                e.printStackTrace();
            }
        }

        System.out.println("存储的本地照片路径为:"+storageDir);
        File image = null;
        try {
            image = File.createTempFile(
                    generateFileName(),  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        String mCurrentPhotoPath = image.getAbsolutePath();
        System.out.println("当前图片路径:"+mCurrentPhotoPath);
        return image;
    }



    //将bitmap转化为png格式
    public File saveMyBitmap(Bitmap mBitmap){
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File file = null;
        try {
            file = File.createTempFile(
                    "pic-"+ RandomStringUtils.random(5),  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );

            FileOutputStream out=new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  file;
    }

    public static void imageUpLoad(String localPath, final Response callBack) {
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        OkHttpClient client = new OkHttpClient();


        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        File f = new File(localPath);
        builder.addFormDataPart("file", f.getName(), RequestBody.create(MEDIA_TYPE_PNG, f));

        final MultipartBody requestBody = builder.build();
        //构建请求
        final Request request = new Request.Builder()
                .url("http://  ")//地址
                .post(requestBody)//添加请求体
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                //callBack.onFaile(e.getMessage());
                System.out.println("上传失败:e.getLocalizedMessage() = " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                System.out.println("返回的信息:"+response.body().string());
                //FileUpload resultBean = new Gson().fromJson(response.body().string(), FileUpload.class);
                //callBack.onSuccess(resultBean);
            }
        });

    }

    public static String generateFileName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        return imageFileName;
    }

    private void upload(String picturePath) {
        final ProgressDialog pb= new ProgressDialog(this);

        pb.setMessage("正在上传");
        pb.setCancelable(false);
        pb.show();


    }
}
