package android.js.com.myapplication.feature.bean;

/**
 * Created by root on 2019/2/25.
 */

public class FileUploadResultBean {


    /**
     * dateDir : 20190225
     * fileName : output_image.jpg
     * fileSize : 833411
     * relUrl : 20190225/aFZKfLtI5Y.jpg
     * type : .jpg
     * url : https://localhost/topic-img/upload/20190225/aFZKfLtI5Y.jpg
     * uuid : aFZKfLtI5Y
     */

    private String dateDir;
    private String fileName;
    private int fileSize;
    private String relUrl;
    private String type;
    private String url;
    private String uuid;

    public String getDateDir() {
        return dateDir;
    }

    public void setDateDir(String dateDir) {
        this.dateDir = dateDir;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getRelUrl() {
        return relUrl;
    }

    public void setRelUrl(String relUrl) {
        this.relUrl = relUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
