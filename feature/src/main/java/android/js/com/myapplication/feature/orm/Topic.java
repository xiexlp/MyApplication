package android.js.com.myapplication.feature.orm;

/**
* last update time:"18-03-10 15:39:19"
* last update user:pku
*/

import android.js.com.myapplication.feature.common.TableAnno;
import android.js.com.myapplication.feature.ormex.TopicEx;
import android.js.com.myapplication.feature.utils.MathUtils;
import android.js.com.myapplication.feature.utils.StringProcess;
import android.js.com.myapplication.feature.utils.TimeUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



@TableAnno(name="ejf_topic")
public class Topic extends TopicEx {
	
	
	public Topic(){
		
	}
	
	
	List<Attach> attachList;
	String subContent;
	String avatar;

	public List<Attach> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<Attach> attachList) {
		this.attachList = attachList;
	}

	/**
	 * 页数计算
	 * @return
	 */
	public int getPageCount(){
        int pageCount= MathUtils.upperInteger(this.getReplies(),20);
        System.out.println("pageCount:"+pageCount);
        return pageCount;
    }

	/**
	 * 页号list
	 * @return
	 */
    public List<Integer> getPages(){
        int pageCount=getPageCount();
        List<Integer> pages = new ArrayList();
        if(pageCount>=1){
            for(int i=1;i<=pageCount;i++){
                pages.add(i);
            }
        }else return null;
        return  pages;
    }

	public String getSubContent() {
		subContent= StringProcess.cutBefore200(getContent(),200);
		return subContent;
	}

	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}

	public String getAvatar() {
		//avatar = Globe.getJConn().hgetAll(Globe.USERAVATARMAPKEY).get(Integer.toString(getUserId()));
		//System.out.println("userId:"+getUserId());
//    	String avatar =Globe.avatarUrlMap.get(getUserId());
//    	if(avatar==null) avatar= Globe.getJConn().hgetAll(Globe.USERAVATARMAPKEY).get(Integer.toString(getUserId()));
    	//avatar="1111";
    	//System.out.println("topic avatar:"+avatar);
    	return avatar;
		//return Globe.getJConn().get(Integer.toString(getUserId()));
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public String getUpdateTimeFormat(){
		return TimeUtil.getTimeFormat(getUpdateTime(), TimeUtil.DATE_FORMAT);
	}

	public String getCreateTimeFormat(){
		return TimeUtil.getTimeFormat(getCreateTime(),TimeUtil.DATE_FORMAT);
	}
}