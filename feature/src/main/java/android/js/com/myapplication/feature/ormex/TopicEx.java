package android.js.com.myapplication.feature.ormex;

/**
* last update time:"18-06-04 17:50:14"
* last update user:pku
*/

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class TopicEx{

private int topicId;
private String attachIcon;
private int attaches;
private int catId;
private String content;
private long createTime;
private int diggdns;
private int diggups;
private String highColor;
private long highExpireDate;
private int isDigest;
private int isHidePost;
private String isReplyNotice;
private String isSolved;
private String lastNickname;
private long lastPostTime;
private String lastPostUser;
private String nickname;
private String remoteIp;
private int replies;
private int reward;
private int sectionId;
private int specType;
private int state;
private String title;
private long topExpireDate;
private int topScope;
private long updateTime;
private String updateUser;
private int userId;
private int visits;
private int boardId;
private int likes;
private String username;
private int indexStatus;

Map<String,Object> map=null;

public TopicEx(){}

public int getTopicId(){
return topicId;
}

public void setTopicId(int topicId){
this.topicId = topicId;
}
public String getAttachIcon(){
return attachIcon;
}

public void setAttachIcon(String attachIcon){
this.attachIcon = attachIcon;
}
public int getAttaches(){
return attaches;
}

public void setAttaches(int attaches){
this.attaches = attaches;
}
public int getCatId(){
return catId;
}

public void setCatId(int catId){
this.catId = catId;
}
public String getContent(){
return content;
}

public void setContent(String content){
this.content = content;
}
public long getCreateTime(){
return createTime;
}

public void setCreateTime(long createTime){
this.createTime = createTime;
}
public int getDiggdns(){
return diggdns;
}

public void setDiggdns(int diggdns){
this.diggdns = diggdns;
}
public int getDiggups(){
return diggups;
}

public void setDiggups(int diggups){
this.diggups = diggups;
}
public String getHighColor(){
return highColor;
}

public void setHighColor(String highColor){
this.highColor = highColor;
}
public long getHighExpireDate(){
return highExpireDate;
}

public void setHighExpireDate(long highExpireDate){
this.highExpireDate = highExpireDate;
}
public int getIsDigest(){
return isDigest;
}

public void setIsDigest(int isDigest){
this.isDigest = isDigest;
}
public int getIsHidePost(){
return isHidePost;
}

public void setIsHidePost(int isHidePost){
this.isHidePost = isHidePost;
}
public String getIsReplyNotice(){
return isReplyNotice;
}

public void setIsReplyNotice(String isReplyNotice){
this.isReplyNotice = isReplyNotice;
}
public String getIsSolved(){
return isSolved;
}

public void setIsSolved(String isSolved){
this.isSolved = isSolved;
}
public String getLastNickname(){
return lastNickname;
}

public void setLastNickname(String lastNickname){
this.lastNickname = lastNickname;
}
public long getLastPostTime(){
return lastPostTime;
}

public void setLastPostTime(long lastPostTime){
this.lastPostTime = lastPostTime;
}
public String getLastPostUser(){
return lastPostUser;
}

public void setLastPostUser(String lastPostUser){
this.lastPostUser = lastPostUser;
}
public String getNickname(){
return nickname;
}

public void setNickname(String nickname){
this.nickname = nickname;
}
public String getRemoteIp(){
return remoteIp;
}

public void setRemoteIp(String remoteIp){
this.remoteIp = remoteIp;
}
public int getReplies(){
return replies;
}

public void setReplies(int replies){
this.replies = replies;
}
public int getReward(){
return reward;
}

public void setReward(int reward){
this.reward = reward;
}
public int getSectionId(){
return sectionId;
}

public void setSectionId(int sectionId){
this.sectionId = sectionId;
}
public int getSpecType(){
return specType;
}

public void setSpecType(int specType){
this.specType = specType;
}
public int getState(){
return state;
}

public void setState(int state){
this.state = state;
}
public String getTitle(){
return title;
}

public void setTitle(String title){
this.title = title;
}
public long getTopExpireDate(){
return topExpireDate;
}

public void setTopExpireDate(long topExpireDate){
this.topExpireDate = topExpireDate;
}
public int getTopScope(){
return topScope;
}

public void setTopScope(int topScope){
this.topScope = topScope;
}
public long getUpdateTime(){
return updateTime;
}

public void setUpdateTime(long updateTime){
this.updateTime = updateTime;
}
public String getUpdateUser(){
return updateUser;
}

public void setUpdateUser(String updateUser){
this.updateUser = updateUser;
}
public int getUserId(){
return userId;
}

public void setUserId(int userId){
this.userId = userId;
}
public int getVisits(){
return visits;
}

public void setVisits(int visits){
this.visits = visits;
}
public int getBoardId(){
return boardId;
}

public void setBoardId(int boardId){
this.boardId = boardId;
}
public int getLikes(){
return likes;
}

public void setLikes(int likes){
this.likes = likes;
}
public String getUsername(){
return username;
}

public void setUsername(String username){
this.username = username;
}
public int getIndexStatus(){
return indexStatus;
}

public void setIndexStatus(int indexStatus){
this.indexStatus = indexStatus;
}

public Map toMap(){
if(map==null){
    map = new HashMap();
        map.put("topic_id",this.getTopicId());
        map.put("attach_icon",this.getAttachIcon());
        map.put("attaches",this.getAttaches());
        map.put("cat_id",this.getCatId());
        map.put("content",this.getContent());
        map.put("create_time",this.getCreateTime());
        map.put("diggdns",this.getDiggdns());
        map.put("diggups",this.getDiggups());
        map.put("high_color",this.getHighColor());
        map.put("high_expire_date",this.getHighExpireDate());
        map.put("is_digest",this.getIsDigest());
        map.put("is_hide_post",this.getIsHidePost());
        map.put("is_reply_notice",this.getIsReplyNotice());
        map.put("is_solved",this.getIsSolved());
        map.put("last_nickname",this.getLastNickname());
        map.put("last_post_time",this.getLastPostTime());
        map.put("last_post_user",this.getLastPostUser());
        map.put("nickname",this.getNickname());
        map.put("remote_ip",this.getRemoteIp());
        map.put("replies",this.getReplies());
        map.put("reward",this.getReward());
        map.put("section_id",this.getSectionId());
        map.put("spec_type",this.getSpecType());
        map.put("state",this.getState());
        map.put("title",this.getTitle());
        map.put("top_expire_date",this.getTopExpireDate());
        map.put("top_scope",this.getTopScope());
        map.put("update_time",this.getUpdateTime());
        map.put("update_user",this.getUpdateUser());
        map.put("user_id",this.getUserId());
        map.put("visits",this.getVisits());
        map.put("board_id",this.getBoardId());
        map.put("likes",this.getLikes());
        map.put("username",this.getUsername());
        map.put("index_status",this.getIndexStatus());
    }
return map;
}


}