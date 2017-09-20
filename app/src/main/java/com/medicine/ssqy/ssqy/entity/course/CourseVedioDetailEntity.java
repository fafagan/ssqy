package com.medicine.ssqy.ssqy.entity.course;

/**
 * Created by Administrator on 2017-09-09.
 */

public class CourseVedioDetailEntity {
    
    
    /**
     * state : true
     * coursePic : http://106.3.41.199:8066/jeesite/app/zy/files?type=video&filename=
     * courseUrl : http://106.3.41.199:8066/jeesite/app/zy/files?type=video&filename=liuzijue720p.mp4
     * uid : u002
     * courseTitle : 六字诀
     * courseID : mx201709030929010002
     * courseType : video
     * courseLength : 907000
     * courseDay : 1504617017000
     */
    
    private boolean state;
    private String coursePic;
    private String courseUrl;
    private String uid;
    private String courseTitle;
    private String courseID;
    private String courseType;
    private int courseLength;
    private long courseDay;
    /**
     * courseDetail : 作者：lest yang
     链接：https://www.zhihu.com/question/28378930/answer/40582171
     来源：知乎
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     
     六字诀是一种吐纳法。它是通过呬（sī）、呵、呼、嘘、吹、嘻六个字的不同发音口型，唇齿喉舌的用力不同，以牵动不同的脏腑经络气血的运行。历代文献对此有不少论述，秦汉的《吕氏春秋》中就有关于用导引呼吸治病的论述。《庄子·刻意》篇中说：“吹呴呼吸，吐故纳新，熊径鸟伸，为寿而已矣。”在西汉时期《王褒传》一书中，也有“呵嘘呼吸如矫松”的记载。南北朝时代陶弘景发明长息法。他在《养性延命录》一书中说：“凡行气，以鼻纳气，以口吐气，微而行之名曰长息。纳气有一，吐气有六。纳气一者谓吸也，吐气六者谓吹、呼、嘻、呵、嘘、呬，皆为长息吐气之法。时寒可吹，时温可呼， 委曲治病，吹以去风，呼以去热，嘻以去烦，呵以下气，嘘以散滞，呬以解极”。隋代天台高僧智顗大法师，在他所著的《修习止观坐禅法要》一书中，也提出了六字诀治病方法。他谈到：但观心想，用六种气治病者，即是观能治病。何谓六种气，一吹、二呼、三嘻、四呵、五嘘 、六呬。此六种息皆于唇口中，想心方便，转侧而坐，绵微而用。颂日：心配属呵肾属吹，脾呼肺呬圣皆知，肝脏热来嘘字治，三焦壅处但言嘻。传至唐代名医孙思邈，按五行相生之顺序，配合四时之季节，编写了卫生歌，奠定了六字诀治病之基础。歌云：春嘘明目夏呵心，秋呬冬吹肺肾宁。四季常呼脾化食，三焦嘻出热难停。发宜常梳气宜敛，齿宜数叩津宜咽。子欲不死修昆仑，双手摩擦常在面。
     */
    
    private String courseDetail;
    /**
     * courseLearned : false
     * courseStudy : 123213
     */
    
    private boolean courseLearned;
    private int courseStudy;
    /**
     * sha1 : 6A9D070100DF283C0B3AAB6F89085CB90230E946
     * totalSize : 148925194
     */
    
    private String sha1;
    private int totalSize;
    
    public boolean isState() {
        return state;
    }
    
    public void setState(boolean state) {
        this.state = state;
    }
    
    public String getCoursePic() {
        return coursePic;
    }
    
    public void setCoursePic(String coursePic) {
        this.coursePic = coursePic;
    }
    
    public String getCourseUrl() {
        return courseUrl;
    }
    
    public void setCourseUrl(String courseUrl) {
        this.courseUrl = courseUrl;
    }
    
    public String getUid() {
        return uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public String getCourseTitle() {
        return courseTitle;
    }
    
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
    
    public String getCourseID() {
        return courseID;
    }
    
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
    
    public String getCourseType() {
        return courseType;
    }
    
    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
    
    public int getCourseLength() {
        return courseLength;
    }
    
    public void setCourseLength(int courseLength) {
        this.courseLength = courseLength;
    }
    
    public long getCourseDay() {
        return courseDay;
    }
    
    public void setCourseDay(long courseDay) {
        this.courseDay = courseDay;
    }
    
    public String getCourseDetail() {
        return courseDetail;
    }
    
    public void setCourseDetail(String courseDetail) {
        this.courseDetail = courseDetail;
    }
    
    public boolean isCourseLearned() {
        return courseLearned;
    }
    
    public void setCourseLearned(boolean courseLearned) {
        this.courseLearned = courseLearned;
    }
    
    public int getCourseStudy() {
        return courseStudy;
    }
    
    public void setCourseStudy(int courseStudy) {
        this.courseStudy = courseStudy;
    }
    
    public String getSha1() {
        return sha1;
    }
    
    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }
    
    public int getTotalSize() {
        return totalSize;
    }
    
    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}
