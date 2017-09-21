package com.medicine.ssqy.ssqy.common;

/**
 * Created by SJ on 2016/1/3.
 */
public class URLConstant {
    
  public static final String BASE_URL="http://106.3.41.199:8066/jeesite/app/zy/";
    
    public static final String QUESTION_URL=BASE_URL+"surveyView?uid=";
    public static final String SURVEY_RESULT_URL=BASE_URL+"physicalAnalysis?uid=";
    
//    public static final String BASE_URL="http://192.168.1.102:10086/jeesite/app/zy/";
    public static final String REG_URL=BASE_URL+"reg";
    public static final String LOGIN_URL=BASE_URL+"login";
    public static final String USERSET_URL=BASE_URL+"userset";
    public static final String USERPWD_URL=BASE_URL+"pwdfind";
    
    
    public static final String COURSE_HISTORY_URL=BASE_URL+"courseHistory";
    public static final String COURSE_LIST_URL=BASE_URL+"courseCount";
    
    
    public static final String AUDIO_LIST_URL=BASE_URL+"courseAudio";
    public static final String AUDIO_DETAIL_URL=BASE_URL+"courseAudioDetail";
    public static final String AUDIO_PROGRESS_URL=BASE_URL+"courseAudioRecord";
    
    public static final String VIDEO_LIST_URL=BASE_URL+"courseVideo";
    public static final String VIDEO_DETAIL_URL=BASE_URL+"courseVideoDetail";
    public static final String VIDEO_PROGRESS_URL=BASE_URL+"courseVideoRecord";
    
    
    public static final String DK_URL=BASE_URL+"daka";
    public static final String DK_RECORD_URL=BASE_URL+"datarecord";
    
    public static final String PIC_LIST_URL=BASE_URL+"coursePic";
    public static final String PIC_DETAIL_URL=BASE_URL+"coursePicDetail";
//    public static final String PIC_PROGRESS_URL=BASE_URL+"coursePicDetail";
    
    public static final String QUERY_DIARY_URL=BASE_URL+"queryDiary";
    public static final String SAVE_DIARY_URL=BASE_URL+"saveDiary";
    public static final String QUERY_MONTH_DIARYS_URL=BASE_URL+"calendarList";
    
    
    public static final String SSQY_URL=BASE_URL+"handbook";
    public static final String SEASON_URL=BASE_URL+"seasonCourse";
    
    public static final String JBGL_ADD_URL=BASE_URL+"recordAdd";
    public static final String JBGL_LIST_URL=BASE_URL+"recordlist";
    public static final String JBGL_DEL_URL=BASE_URL+"recordDel";
    public static final String JBGL_MODIFY_URL=BASE_URL+"recordModify";
    
    
    public static final String SYSTEM_MSG_URL=BASE_URL+"systemMsg";
    
    public static final int RECORD_TYPE_XT=0;
    public static final int RECORD_TYPE_XY=1;
    public static final int RECORD_TYPE_TZ=2;
    public static final int RECORD_TYPE_YY=3;
    public static final int RECORD_TYPE_SS=4;
    public static final int  RECORD_TYPE_YD=5;
//    18293105137
    
    
    
    public static final String UTIL_FOODHOT_URL=BASE_URL+"foodhot?type=0";
    public static final String UTIL_FOODPL_URL=BASE_URL+"foodPiaoling?type=0";
    public static final String UTIL_FOODGAI_URL=BASE_URL+"foodGai?type=0";
    public static final String UTIL_FOODSPORT_URL=BASE_URL+"sports?type=0";
    public static final String UTIL_FOODHOT_QUERY_URL=BASE_URL+"foodhotSearch";
    public static final String UTIL_FOODPL_QUERY_URL=BASE_URL+"foodPiaolingSearch";
    public static final String UTIL_FOODGAI_QUERY_URL=BASE_URL+"foodGaiSearch";
    public static final String UTIL_FOODSPORT_QUERY_URL=BASE_URL+"sportsSearch";
}
