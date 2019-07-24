package android.js.com.myapplication.feature.fragment.eventbus;

/**
 * Created by root on 2019/3/4.
 */

public class LoginEvent {

    public LoginEvent(int userId,String username){
        this.userId = userId;
        this.username = username;
    }

    String username;
    int userId;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
