package android.js.com.myapplication.feature.bean;

/**
 * Created by root on 2019/3/10.
 */

public class KeySearchBean {

    public KeySearchBean(int id,String name){
        this.id =id;
        this.name =name;
    }

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
