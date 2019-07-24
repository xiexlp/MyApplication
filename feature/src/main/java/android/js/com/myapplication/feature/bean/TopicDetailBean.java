package android.js.com.myapplication.feature.bean;

import java.util.List;

/**
 * Created by root on 2019/2/8.
 */

public class TopicDetailBean {


    /**
     * specType : 0
     * updateTimeFormat : 2019-02-08 11:52:51
     * topScope : 0
     * highColor :
     * isDigest : 0
     * replyList : [{"reward":0,"createTimeFormat":"2019-02-08 11:52:51","updateTimeFormat":"2019-02-08 11:52:51","parentReplyUserId":0,"replyType":0,"parentReplyId":0,"remoteIp":"","updateTime":1549597971843,"avatar":"\\topic-img\\upload\\uw0jz_new.jpg","attachList":null,"topicUserId":1,"title":"Re:tomcat https的建立 8443的问题","userId":1,"content":"re:配置成功","isBest":"","attaches":0,"topicId":522,"replies":0,"createTime":1549597971843,"isHidePost":"","replyId":150,"boardId":19,"state":0,"username":"canava"}]
     * diggups : 0
     * title : tomcat https的建立 8443的问题
     * content : <div><br></div><div><br></div><a href="https://blog.csdn.net/a532672728/article/details/72890905" target="_blank"><a href="https://blog.csdn.net/a532672728/article/details/72890905" target="_blank">https://blog.csdn.net/a532672728/article/details/72890905</a></a><div><br></div><div><div>tomcat设置https端口时,8443和443区别:</div><div>1. 8443端口在访问时需要加端口号,相当于http的8080,不可通过域名直接访问,需要加上端口号;<a href="https://yuming.com:8443" target="_blank"><a href="https://yuming.com:8443" target="_blank">https://yuming.com:8443</a></a>。</div><div>2. 443端口在访问时不需要加端口号,相当于http的80,可通过域名直接访问;例:<a href="https://yuming.com" target="_blank"><a href="https://yuming.com" target="_blank">https://yuming.com</a></a>。</div><div><br></div><div><br></div><div><br></div><div><a href="https://blog.csdn.net/a532672728/article/details/72890905" target="_blank"><a href="https://blog.csdn.net/a532672728/article/details/72890905" target="_blank">https://blog.csdn.net/a532672728/article/details/72890905</a></a></div><div><br></div><div>*问:https使用域名访问网站,而不显示端口号?</div><div><br></div><div><br></div><div><a href="https://blog.csdn.net/u012012240/article/details/73805158" target="_blank"><a href="https://blog.csdn.net/u012012240/article/details/73805158" target="_blank">https://blog.csdn.net/u012012240/article/details/73805158</a></a></div><div>&nbsp;答:将端口号设置为443,即可通过域名直接访问网站</div><div><br></div></div><div><br></div><div>参考</div><div><br></div><div><a href="http://tomcat.apache.org/tomcat-8.0-doc/ssl-howto.html" target="_blank">http://tomcat.apache.org/tomcat-8.0-doc/ssl-howto.html</a></div>
     * lastNickname :
     * lastPostTime : 1548921077830
     * visits : 0
     * lastPostUser :
     * pages : [1]
     * diggdns : 0
     * nickname :
     * state : 0
     * topExpireDate : 1548921077830
     * likes : 0
     * reward : 0
     * createTimeFormat : 2019-01-31 15:51:17
     * pageCount : 1
     * remoteIp :
     * indexStatus : 1
     * updateUser :
     * updateTime : 1549597971851
     * avatar : \\topic-img\\upload\\uw0jz_new.jpg
     * attachList : null
     * sectionId : 0
     * isReplyNotice :
     * highExpireDate : 1548921077830
     * userId : 1
     * attaches : 0
     * catId : 0
     * topicId : 522
     * replies : 1
     * createTime : 1548921077830
     * isHidePost : 0
     * isSolved :
     * attachIcon :
     * boardId : 19
     * subContent : <div><br></div><div><br></div><a href="https://blog.csdn.net/a532672728/article/details/72890905" target="_blank"><a href="https://blog.csdn.net/a532672728/article/details/72890905" target="_blank">ht ......
     * username : canava
     */

    private int specType;
    private String updateTimeFormat;
    private int topScope;
    private String highColor;
    private int isDigest;
    private int diggups;
    private String title;
    private String content;
    private String lastNickname;
    private long lastPostTime;
    private int visits;
    private String lastPostUser;
    private int diggdns;
    private String nickname;
    private int state;
    private long topExpireDate;
    private int likes;
    private int reward;
    private String createTimeFormat;
    private int pageCount;
    private String remoteIp;
    private int indexStatus;
    private String updateUser;
    private long updateTime;
    private String avatar;
    private Object attachList;
    private int sectionId;
    private String isReplyNotice;
    private long highExpireDate;
    private int userId;
    private int attaches;
    private int catId;
    private int topicId;
    private int replies;
    private long createTime;
    private int isHidePost;
    private String isSolved;
    private String attachIcon;
    private int boardId;
    private String subContent;
    private String username;
    private List<ReplyListBean> replyList;
    private List<Integer> pages;

    public int getSpecType() {
        return specType;
    }

    public void setSpecType(int specType) {
        this.specType = specType;
    }

    public String getUpdateTimeFormat() {
        return updateTimeFormat;
    }

    public void setUpdateTimeFormat(String updateTimeFormat) {
        this.updateTimeFormat = updateTimeFormat;
    }

    public int getTopScope() {
        return topScope;
    }

    public void setTopScope(int topScope) {
        this.topScope = topScope;
    }

    public String getHighColor() {
        return highColor;
    }

    public void setHighColor(String highColor) {
        this.highColor = highColor;
    }

    public int getIsDigest() {
        return isDigest;
    }

    public void setIsDigest(int isDigest) {
        this.isDigest = isDigest;
    }

    public int getDiggups() {
        return diggups;
    }

    public void setDiggups(int diggups) {
        this.diggups = diggups;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLastNickname() {
        return lastNickname;
    }

    public void setLastNickname(String lastNickname) {
        this.lastNickname = lastNickname;
    }

    public long getLastPostTime() {
        return lastPostTime;
    }

    public void setLastPostTime(long lastPostTime) {
        this.lastPostTime = lastPostTime;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public String getLastPostUser() {
        return lastPostUser;
    }

    public void setLastPostUser(String lastPostUser) {
        this.lastPostUser = lastPostUser;
    }

    public int getDiggdns() {
        return diggdns;
    }

    public void setDiggdns(int diggdns) {
        this.diggdns = diggdns;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getTopExpireDate() {
        return topExpireDate;
    }

    public void setTopExpireDate(long topExpireDate) {
        this.topExpireDate = topExpireDate;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public String getCreateTimeFormat() {
        return createTimeFormat;
    }

    public void setCreateTimeFormat(String createTimeFormat) {
        this.createTimeFormat = createTimeFormat;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public int getIndexStatus() {
        return indexStatus;
    }

    public void setIndexStatus(int indexStatus) {
        this.indexStatus = indexStatus;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Object getAttachList() {
        return attachList;
    }

    public void setAttachList(Object attachList) {
        this.attachList = attachList;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getIsReplyNotice() {
        return isReplyNotice;
    }

    public void setIsReplyNotice(String isReplyNotice) {
        this.isReplyNotice = isReplyNotice;
    }

    public long getHighExpireDate() {
        return highExpireDate;
    }

    public void setHighExpireDate(long highExpireDate) {
        this.highExpireDate = highExpireDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAttaches() {
        return attaches;
    }

    public void setAttaches(int attaches) {
        this.attaches = attaches;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getIsHidePost() {
        return isHidePost;
    }

    public void setIsHidePost(int isHidePost) {
        this.isHidePost = isHidePost;
    }

    public String getIsSolved() {
        return isSolved;
    }

    public void setIsSolved(String isSolved) {
        this.isSolved = isSolved;
    }

    public String getAttachIcon() {
        return attachIcon;
    }

    public void setAttachIcon(String attachIcon) {
        this.attachIcon = attachIcon;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getSubContent() {
        return subContent;
    }

    public void setSubContent(String subContent) {
        this.subContent = subContent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ReplyListBean> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyListBean> replyList) {
        this.replyList = replyList;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public static class ReplyListBean {
        /**
         * reward : 0
         * createTimeFormat : 2019-02-08 11:52:51
         * updateTimeFormat : 2019-02-08 11:52:51
         * parentReplyUserId : 0
         * replyType : 0
         * parentReplyId : 0
         * remoteIp :
         * updateTime : 1549597971843
         * avatar : \\topic-img\\upload\\uw0jz_new.jpg
         * attachList : null
         * topicUserId : 1
         * title : Re:tomcat https的建立 8443的问题
         * userId : 1
         * content : re:配置成功
         * isBest :
         * attaches : 0
         * topicId : 522
         * replies : 0
         * createTime : 1549597971843
         * isHidePost :
         * replyId : 150
         * boardId : 19
         * state : 0
         * username : canava
         */

        private int reward;
        private String createTimeFormat;
        private String updateTimeFormat;
        private int parentReplyUserId;
        private int replyType;
        private int parentReplyId;
        private String remoteIp;
        private long updateTime;
        private String avatar;
        private Object attachList;
        private int topicUserId;
        private String title;
        private int userId;
        private String content;
        private String isBest;
        private int attaches;
        private int topicId;
        private int replies;
        private long createTime;
        private String isHidePost;
        private int replyId;
        private int boardId;
        private int state;
        private String username;

        public int getReward() {
            return reward;
        }

        public void setReward(int reward) {
            this.reward = reward;
        }

        public String getCreateTimeFormat() {
            return createTimeFormat;
        }

        public void setCreateTimeFormat(String createTimeFormat) {
            this.createTimeFormat = createTimeFormat;
        }

        public String getUpdateTimeFormat() {
            return updateTimeFormat;
        }

        public void setUpdateTimeFormat(String updateTimeFormat) {
            this.updateTimeFormat = updateTimeFormat;
        }

        public int getParentReplyUserId() {
            return parentReplyUserId;
        }

        public void setParentReplyUserId(int parentReplyUserId) {
            this.parentReplyUserId = parentReplyUserId;
        }

        public int getReplyType() {
            return replyType;
        }

        public void setReplyType(int replyType) {
            this.replyType = replyType;
        }

        public int getParentReplyId() {
            return parentReplyId;
        }

        public void setParentReplyId(int parentReplyId) {
            this.parentReplyId = parentReplyId;
        }

        public String getRemoteIp() {
            return remoteIp;
        }

        public void setRemoteIp(String remoteIp) {
            this.remoteIp = remoteIp;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Object getAttachList() {
            return attachList;
        }

        public void setAttachList(Object attachList) {
            this.attachList = attachList;
        }

        public int getTopicUserId() {
            return topicUserId;
        }

        public void setTopicUserId(int topicUserId) {
            this.topicUserId = topicUserId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIsBest() {
            return isBest;
        }

        public void setIsBest(String isBest) {
            this.isBest = isBest;
        }

        public int getAttaches() {
            return attaches;
        }

        public void setAttaches(int attaches) {
            this.attaches = attaches;
        }

        public int getTopicId() {
            return topicId;
        }

        public void setTopicId(int topicId) {
            this.topicId = topicId;
        }

        public int getReplies() {
            return replies;
        }

        public void setReplies(int replies) {
            this.replies = replies;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getIsHidePost() {
            return isHidePost;
        }

        public void setIsHidePost(String isHidePost) {
            this.isHidePost = isHidePost;
        }

        public int getReplyId() {
            return replyId;
        }

        public void setReplyId(int replyId) {
            this.replyId = replyId;
        }

        public int getBoardId() {
            return boardId;
        }

        public void setBoardId(int boardId) {
            this.boardId = boardId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
