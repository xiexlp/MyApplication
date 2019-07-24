package android.js.com.myapplication.feature.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;


/**
 * Created by Administrator on 2015-7-28.
 */
public class StringProcess {

    public static String cutBefore200(String content,int len){
        String subcontent="";
        if(content.length()<=len){
            return  content;
        }else {
            subcontent=content.substring(0,len)+" "+"......";
        }
        return subcontent;
    }

    public static String concatBuilder(String prefix,String content){
        StringBuilder sb = new StringBuilder(prefix);sb.append(content);
        return sb.toString();
    }

    public static String concatBuffer(String prefix,String groupID){
        StringBuffer sb = new StringBuffer(prefix).append(groupID);
        return sb.toString();
    }

    public static void testReplace(){
        long begin = System.currentTimeMillis();
        String imgs = "<p><img src=\"/ueditor/jsp/upload/image/20150828/1440770825471066078.jpg\" " +
                "title=\"1440770825471066078.jpg\" alt=\"72f082025aafa40f50132fd9a964034f79f019d8.jpg\"/></p>";
        System.out.println(imgs);

        String img = StringUtils.replace(imgs,"<img","<upload>/</upload>");
        long end1 = System.currentTimeMillis();
        long exe1 = end1-begin;
        System.out.println("exe1:"+exe1);
        String img2 = imgs.replaceAll("<img.*/>", "<upload>/pic...</upload>");
        System.out.println(img2);
        long end =System.currentTimeMillis();
        long exe = end-end1;
        System.out.println("exe:"+exe);
    }

    public static void main(String[] args) {
        String url ="aaa=bbb&ccc";
        String[]  urls = url.split("=");

        int i = url.indexOf("aaa");
        int j = i+"aaa".length()+1;
        System.out.println(url.substring(0,"aaa".length()));
        System.out.println(url.substring(j));
        System.out.println(urls[0]);
        System.out.println(urls[1]);
    }

    public static void test2(String[] args) {
        String content="<p style=\"font-family: SimSun; margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; line-height: 30px; white-space: normal; overflow: visible !important; background-color: rgb(255, 255, 255);\">銆愮幆鐞冭埅绌烘姤閬撱�戜复杩�4鏈堬紝缃傜矡鑺卞紑銆�<a href=\"http://country.huanqiu.com/china\" class=\"linkAbout\" target=\"_blank\" title=\"涓浗\" style=\"font-family: Arial, Helvetica, 瀹嬩綋; font-size: 14px; margin: 0px 5px; padding: 0px 0px 2px; color: rgb(6, 52, 111); text-decoration: none; border-bottom-width: 1px; border-bottom-style: dotted; border-bottom-color: rgb(6, 52, 111);\">涓浗</a>骞夸笢鐪佹渤婧愬競鍩庡崡鐨勫亸鍍诲北鍖猴紝涓�鏋剁櫧鑹茬殑鍥涙棆缈兼棤浜烘満鑵剧┖鑰岃捣锛屸�滃棥鈥濈殑涓�澹帮紝寰堝揩璺冨埌浜�1000绫崇殑楂樼┖锛屽湪鏂瑰渾鍗佸嚑閲岀殑灞辨灄涓潵鍥炵洏鏃嬪崌闄嶃�傚畠鎵�閰嶅鐨勪竴棰楅珮娓呮憚鍍忓ご鍜孏PS绯荤粺锛屾闅愯斀鍦颁睛瀵熷北鏋�<a href=\"http://country.huanqiu.com/central_african\" class=\"linkAbout\" target=\"_blank\" title=\"涓潪\" style=\"font-family: Arial, Helvetica, 瀹嬩綋; font-size: 14px; margin: 0px 5px; padding: 0px 0px 2px; color: rgb(6, 52, 111); text-decoration: none; border-bottom-width: 1px; border-bottom-style: dotted; border-bottom-color: rgb(6, 52, 111);\">涓潪</a>娉曠妞嶇殑缃傜矡鐢般��</p><p style=\"font-family: SimSun; margin: 23px auto 0px; padding: 0px; list-style: none; line-height: 30px; white-space: normal; overflow: visible !important; background-color: rgb(255, 255, 255);\">銆�銆�杩欎釜鍦烘櫙骞堕潪鍑鸿嚜濂借幈鍧為粦绉戞妧鐢靛奖銆婄粷瀵嗛琛屻�嬶紝瀹冨凡缁忕湡瀹炲瓨鍦ㄣ�傛瘡骞存�绘湁閭ｄ箞鍑犳锛屽箍涓滅渷娌虫簮甯傚叕瀹夊眬灞�闀胯禆鏄屽浆寰楀湪杩欐牱鐨勫北鍖哄害杩囥�備粬鎹笂榛戣壊鐨勮瀵熶綔璁湇锛岃笍鐫�闃叉粦闈达紝鎵嬫寔涓�涓粦鑹茬殑鏂瑰舰鎺у埗灞忓箷鏉匡紝鎿嶄綔涓嶅湪瑙嗙嚎鑼冨洿鍐呯殑鏃犱汉鏈恒��</p><p style=\"font-family: SimSun; margin: 23px auto 0px; padding: 0px; list-style: none; line-height: 30px; white-space: normal; overflow: visible !important; background-color: rgb(255, 255, 255);\">銆�銆�涓�寮�濮嬶紝璧栨槍褰湁浜涚揣寮狅紝鏍戜笡鐙獎锛屽湪褰撲腑璧烽檷鏄釜鎶�鏈椿鍎裤�傝禆鏄屽浆鍛婅瘔灏忕紪锛屾搷鎺ф墜鐨勫績鐞嗙礌璐ㄨ闈炲父濂斤紝绋嶄笉娉ㄦ剰椋炴満灏变細鎾炲潖銆傛櫘閫氳埅妯′竴鍛ㄥ嵆鍙笂鎵嬶紝浣嗚繖鏍风殑鏃犱汉鏈烘病鏈夊崐骞存椂闂存牴鏈棤娉曠啛缁冦��</p><p style=\"font-family: SimSun; margin: 23px auto 0px; padding: 0px; list-style: none; line-height: 30px; white-space: normal; overflow: visible !important; background-color: rgb(255, 255, 255);\">銆�銆�宸笉澶氬悓涓�鏃堕棿锛岃繙鍦�1300澶氬叕閲屽鐨勬箹鍖楄闃筹紝涓�鏋剁敤浜庡啘鑽柗娲掔殑鍥哄畾缈兼棤浜烘満姝ｄ粠涓�鐗囩豢娌规补鐨勫皬楹︾敯涓婄┖寰愬緪鎺犺繃銆備负浜嗕繚璇佸柗娲掑潎鍖�锛屾箹鍖楄闃抽闃查槦鐨勫伐浣滀汉鍛樺繀椤绘椂鍒昏閱掔潃锛屼互鎿嶆帶鏃犱汉鏈哄湪璺濈楹︾椤剁2绫冲乏鍙崇┖涓琛屻��</p><p style=\"font-family: SimSun; margin: 23px auto 0px; padding: 0px; list-style: none; line-height: 30px; white-space: normal; overflow: visible !important; background-color: rgb(255, 255, 255);\">銆�銆�<strong style=\"font-size: 14px; margin: 0px; padding: 0px;\">鈥滀腑鍥芥嫢鏈�18浜夸憨鑰曞湴锛屽簲璇ユ湁10涓囨灦鍐滅敤鏃犱汉鏈虹殑甯傚満銆傗��</strong>&nbsp;瀹夐槼鍏ㄤ赴鑸┖妞嶄繚绉戞妧鏈夐檺鍏徃钁ｄ簨闀垮懆鍥藉己鍛婅瘔灏忕紪锛�<strong style=\"font-size: 14px; margin: 0px; padding: 0px;\">鍦ㄥ啘涓氭棤浜烘満浣跨敤鍘嗗彶鏈�鎮犱箙鐨�<a href=\"http://country.huanqiu.com/japan\" class=\"linkAbout\" target=\"_blank\" title=\"鏃ユ湰\" style=\"font-family: Arial, Helvetica, 瀹嬩綋; margin: 0px 5px; padding: 0px 0px 2px; color: rgb(6, 52, 111); text-decoration: none; border-bottom-width: 1px; border-bottom-style: dotted; border-bottom-color: rgb(6, 52, 111);\">鏃ユ湰</a>锛�3000鏋跺啘鐢ㄦ棤浜烘満姝ｈ鐩栫潃鏃ユ湰8000涓囦憨鑰曞湴銆�</strong></p><p><br/></p>";
        System.out.println("pre:"+content);

        content=content.replaceAll("style=.*?;\"", " ").replaceAll("<a href=.*?>"," ").replaceAll("</a>","");
        //content=content.replace(regEx_style," ");
        //content = content.replaceAll();
        System.out.println("after:"+content);

        int index = content.lastIndexOf("銆�");
        String sub = content.substring(0,index).trim();
        System.out.println(sub);

        String pre ="<p  >銆婅瘉鍒告棩鎶ャ�嬭鑰呰幏鎮夛紝鍥藉鍙戞敼濮旂綉绔�22鏃ャ��23鏃ヨ繛缁袱鏃ュ垔鍙� 銆婃垜鍥芥湁鏉′欢鏈夎兘鍔涘疄鐜板叏骞寸粡娴庡彂灞曢鏈熺洰鏍囥�嬨�� 銆婃垜鍥�7%鐨勭粡娴庡閫熸槸鍙俊鐨勩�嬨�� 銆婂浣曠湅寰呭綋鍓嶄腑鍥界粡娴庡舰鍔匡紵銆嬩笁绡囩讲鍚嶆枃绔狅紝寮鸿皟鎴戝浗涓婂崐骞�7%鐨勭粡娴庡閫熸槸鍙俊鐨勶紝涓嬪崐骞存垜鍥界粡娴庡皢缁х画杩愯鍦ㄥ悎鐞嗗尯闂达紝鏁翠綋骞崇ǔ鐨勬�佸娍涓嶄細鏀瑰彉锛屽叏骞寸粡娴庡閫熸湁鏈涗繚鎸佸湪7%宸﹀彸銆傜洰鍓嶏紝涓浗缁忔祹鍙戝睍杩涘叆鏂板父鎬侊紝鍥介檯绀句細瑕佽璇嗗拰閫傚簲杩欑鍙樺寲锛屼笉鑳藉瘎甯屾湜浜庝腑鍥界粡娴庡缁堜繚鎸佸湪楂橀�熷闀跨姸鎬併�傚悓鏃讹紝鏇磋鐪嬪埌褰撳墠涓浗灏变笟鍩烘湰绋冲畾锛屽眳姘戞秷璐圭墿浠锋俯鍜屼笂娑紝涓浗缁忔祹浠嶇劧鏄闀挎渶蹇殑涓昏缁忔祹浣撲箣涓�锛屼粛鐒舵槸涓栫晫缁忔祹澧為暱鐨勯噸瑕佸紩鎿庛��</p><p  >銆�銆�鏂囩珷涓槑纭紝涓婂崐骞�7%鐨勭粡娴庡闀块�熷害鍏锋湁鍩烘湰闈㈡敮鎾戙�備竴鏄緵缁欓潰鍥炵ǔ銆備笂鍗婂勾锛屽崰GDP姣旈噸杩�90%鐨勫伐涓氬拰鏈嶅姟涓氬疄鐜版璺屽洖绋筹紝宸ヤ笟澧為�熶笌涓�瀛ｅ害鍩烘湰鎸佸钩锛屾湇鍔′笟澧為�熻緝涓�瀛ｅ害鎻愰珮0.5涓櫨鍒嗙偣銆備簩鏄渶姹傞潰鍩烘湰绋冲畾銆備笂鍗婂勾瀹為檯娑堣垂澧為暱淇濇寔绋冲畾锛屾姇璧勫拰鍑哄彛涔熷湪浜屽搴﹀嚭鐜颁簡鍥炵ǔ杩硅薄銆備笁鏄儴鍒嗗尯鍩熷闀挎彁閫熴�傚崰鍏ㄥ浗GDP姣旈噸澶с�佽浆鍨嬫棭鐨勯暱涓夎鍜岀彔涓夎绛夊湴鍖猴紝涓婂崐骞寸粡娴庡闀垮揩浜庡叏鍥芥按骞筹紝GDP鎺掑悕鍓嶄簲浣嶇殑骞夸笢銆佹睙鑻忋�佸北涓溿�佹禉姹熴�佹渤鍗楃殑缁忔祹澧為�熶簩瀛ｅ害蹇簬涓�瀛ｅ害銆傚洓鏄皯鐢熸暟鎹〃鐜颁寒涓姐�傞鍏堬紝灏变笟澧為暱鑹ソ銆備笂鍗婂勾鍏ㄥ浗鍩庨晣鏂板灏变笟718涓囦汉锛屽畬鎴愬叏骞寸洰鏍囦换鍔＄殑71.8%锛�1鏈堜唤-8鏈堜唤瀹屾垚鍏ㄥ勾鐩爣95.2%銆傚叾娆★紝灞呮皯鏀跺叆澧為暱杈冨揩銆備笂鍗婂勾鍏ㄥ浗灞呮皯浜哄潎鍙敮閰嶆敹鍏ュ悓姣斿疄闄呭闀�7.6%锛屾瘮缁忔祹澧為�熼珮0.6涓櫨鍒嗙偣銆�</p><p  >銆�銆�鍊煎緱娉ㄦ剰鐨勬槸锛岀粡娴庤浆鍨嬫湡瀹炵墿鎸囨爣涓庣粡娴庡闀跨殑鍏崇郴灏嗗彂鐢熼噸澶ц皟鏁达紝浠呬粎鏍规嵁鐢ㄧ數閲忋�佽繍杈撻噺绛夊嚑涓疄鐗╅噺鎸囨爣涓庣粡娴庡閫熺殑寮规�у叧绯诲氨寰楀嚭鐩墠涓浗缁忔祹澧為�熸暣浣撻珮浼扮殑缁撹鏄笉鍙潬鐨勩�� <span  >杩戝勾鏉ワ紝涓浗鐨勭粡娴庣粨鏋勫彂鐢熶簡閲嶅ぇ鍙樺寲锛岃繖浜涘疄鐗╂寚鏁颁笌缁忔祹澧為暱鐨勫叧绯讳篃闅忎箣鏀瑰彉銆備竴鏄湇鍔′笟姣旈噸澶у箙涓婂崌銆�2015骞翠笂鍗婂勾锛屾湇鍔′笟鍗燝DP姣旈噸杈惧埌49.5%锛岃緝2007骞存彁楂樹簡6.6涓櫨鍒嗙偣銆傛湇鍔′笟姣旈噸鐨勫ぇ骞呬笂鍗囧繀灏嗘敼鍙樼敤鐢甸噺銆佽繍杈撻噺绛夊疄鐗╂寚鏍囦笌缁忔祹澧為暱鐨勫脊鎬у叧绯汇�備簩鏄尰鑽埗閫犱笟銆佺數瀛愬強閫氫俊璁惧鍒堕�犱笟绛夐珮鏂版妧鏈骇涓氬湪宸ヤ笟涓崰姣斿ぇ骞呮彁楂橈紝鑰岃繖浜涜涓氬叿鏈変綆鑳借�椼�侀珮闄勫姞鍊肩瓑鐗瑰緛銆傝繖绉嶇粨鏋勮皟鏁村繀鐒跺鑷村崟浣岹DP鑳借�楃殑鎸佺画涓嬮檷锛岀洿鎺ヤ細闄嶄綆鐢ㄧ數闇�姹備笌璐ц繍闇�姹傚閫熴��</span><span  >鎬荤殑鏉ョ湅锛屽綋鍓嶆垜鍥界粡娴庤繍琛屾瘮杈冨钩绋筹紝澧為�熸崲鎸℃瘮杈冨钩椤猴紝缁撴瀯璋冩暣鍜屽姩鍔涜浆鎹㈢ǔ姝ユ帹杩涳紝鍙戝睍姘村钩閫愭鍚戜腑楂樼杩堣繘锛岀鍚堝畯瑙傝皟鎺т笌鍙戝睍棰勬湡銆傞殢鐫�绋冲闀裤�佷績鏀归潻銆佽皟缁撴瀯鍚勯」鏀跨瓥鎺柦杩涗竴姝ヨ惤瀹炲拰瀹屽杽锛岀粡娴庢湁鏈涗繚鎸佸钩绋宠繍琛屾�佸娍銆傚湪缁忔祹澧為暱鎬讳綋骞崇ǔ銆佸競鍦轰緵闇�鐘跺喌鍩烘湰绋冲畾鐨勬儏鍐典笅锛屽氨涓氬矖浣嶅皢鎸佺画澧炲姞锛岀墿浠锋�绘按骞虫俯鍜屼笂娑紝鍏ㄥ勾缁忔祹鍙戝睍涓昏棰勬湡鐩爣鍙互瀹炵幇銆�</span></p><p><br/></p>";
        pre=pre.replaceAll("<p","<p style=\"color: rgb(165, 165, 165);\"");
        System.out.println("after:"+pre);
    }

    public static void test(String[] args) {
        long begin = System.currentTimeMillis();

        String value="b*|111";
        int resourceIndex = value.indexOf("|");
        String resoure = value.substring(0, resourceIndex);
        String actionValue = value.substring(resourceIndex + 1);
        System.out.println("resource:"+resoure+" actionValue:"+actionValue);

        long end =System.currentTimeMillis();
        long exe = end-begin;
        System.out.println("exe time:"+exe);

        String[] permissions = StringUtils.split(value,"|");
        resoure = permissions[0];
        actionValue=permissions[1];
        System.out.println("resource:"+resoure+" action:"+actionValue);
        long end1 = System.currentTimeMillis();
        long exe1 = end1-end;
        System.out.println("exe1 time:"+exe1);

    }


}
