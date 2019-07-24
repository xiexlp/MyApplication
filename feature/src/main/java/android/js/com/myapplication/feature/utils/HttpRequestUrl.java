package android.js.com.myapplication.feature.utils;

/**
 * Created by root on 2019/2/22.
 */

public class HttpRequestUrl {

    public static final String SERVER_IP="192.168.197.1";

    //用户从移动端登录地址
    public static final String LOGIN_URL=Constants.URL_PRE+"/boot/user/userlogin_from_mobile";

    //主题增加地址
    public static final String TOPIC_ADD_URL=Constants.URL_PRE+"/boot/topic/topicadd_json";

    //上传图片地址
    public static final String UPLOAD_FILE_URL=Constants.URL_PRE+"/boot/upload/uploadfile";


    //回复增加地址
    public static final String REPLY_ADD_URL=Constants.URL_PRE+"/boot/reply/reply2topic_json";


    //搜索地址
    public static final String SEARCH_URL=Constants.URL_PRE+"/boot/lion/searchmicroservice_json";


    //最近更新主题地址
    public static final String TOPICS_LAST_UPDATE_JSON_URL = Constants.URL_PRE+"/boot/topic/topics_last_update_json";


    //最后一条主题更新时间 往前20条，每次刷新20条消息
    public static final String TOPICS_PRE_UPDATE_JSON_URL = Constants.URL_PRE+"/boot/topic/topics_pre_update_json";


    public static final String TOPICS_GLOBAL_LAST_UPDATE_JSON=Constants.URL_PRE+"/boot/topic/topics_global_last_update_json";


    public static final String TOPICS_GLOBAL_PRE_UPDATE_JSON=Constants.URL_PRE+"/boot/topic/topics_global_pre_update_json";

}
