package android.js.com.myapplication.feature.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by root on 2019/2/22.
 */

public class SpUtils {

    public static String getJESSIONID(Context context,String sessionKey){
        //显示用户此前录入的数据
        SharedPreferences sPreferences=context.getSharedPreferences(SPkey.SESSIONSTORAGE, context.MODE_PRIVATE);
        String mysessionid=sPreferences.getString(sessionKey, "");
        System.out.println("mysession id:"+mysessionid);
        return mysessionid;
    }

    public static String getSessionCookie(Context context,String sessionKey){
        //显示用户此前录入的数据
        SharedPreferences sPreferences=context.getSharedPreferences(SPkey.SESSIONSTORAGE, context.MODE_PRIVATE);
        String mysessionid=sPreferences.getString("sessionid", "");
        System.out.println("mysession id:"+mysessionid);
        return mysessionid;
    }


    public static void setKeyValue(Context context,String storage,String key,String value){
        System.out.println("context is null?"+context);
        SharedPreferences share = context.getSharedPreferences(storage,context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();//编辑文件
        edit.putString(key,value);
        edit.commit();
    }

    public static String getKeyValue(Context context,String storage,String key){
        SharedPreferences sPreferences=context.getSharedPreferences(storage, context.MODE_PRIVATE);
        String value=sPreferences.getString(key, "");
        System.out.println("key id:"+key);
        return value;
    }

}
