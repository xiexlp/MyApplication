package android.js.com.myapplication.feature.orm;

/**
* last update time:"18-03-10 12:58:59"
* last update user:pku
*/

import android.js.com.myapplication.feature.common.TableAnno;
import android.js.com.myapplication.feature.ormex.SectionEx;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

//import com.js.common.anno.TableAnno;
//import com.js.iforum.ormex.SectionEx;

@TableAnno(name="ejf_section")
public class Section extends SectionEx {

    public Section(){}

    public Section(int sectionId,String sectionName){
        this.setSectionId(sectionId);
        this.setSectionName(sectionName);
    }

    @Override
    public int hashCode(){
        return getSectionId();
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null || !(obj instanceof Section))
            return false;
        if (this == obj)
            return true;
        Section newSection = (Section)obj;
        return getSectionId()==newSection.getSectionId();
    }

    List<Section> children= new ArrayList();
    List<Board> boardList= new ArrayList();


    public List<Section> getChildren() {
        return children;
    }

    public void setChildren(List<Section> children) {
        this.children = children;
    }

    public List<Board> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<Board> boardList) {
        this.boardList = boardList;
    }





}