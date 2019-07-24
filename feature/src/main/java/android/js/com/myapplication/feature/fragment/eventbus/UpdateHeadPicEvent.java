package android.js.com.myapplication.feature.fragment.eventbus;

public class UpdateHeadPicEvent {
  private String mUrl;
  public UpdateHeadPicEvent(String url) {
    this.mUrl = url;
  }
  public String getUrl() {
    return mUrl;
  }
}