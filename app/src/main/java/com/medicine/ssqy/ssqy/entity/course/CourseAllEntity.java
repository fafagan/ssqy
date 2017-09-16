package com.medicine.ssqy.ssqy.entity.course;

/**
 * Created by Administrator on 2017-09-11.
 */

public class CourseAllEntity {
    
    
    /**
     * audio : {"learned":0,"all":10}
     * pic : {"learned":0,"all":9}
     * video : {"learned":0,"all":1}
     */
    
    private AudioEntity audio;
    private PicEntity pic;
    private VideoEntity video;
    
    public AudioEntity getAudio() {
        return audio;
    }
    
    public void setAudio(AudioEntity audio) {
        this.audio = audio;
    }
    
    public PicEntity getPic() {
        return pic;
    }
    
    public void setPic(PicEntity pic) {
        this.pic = pic;
    }
    
    public VideoEntity getVideo() {
        return video;
    }
    
    public void setVideo(VideoEntity video) {
        this.video = video;
    }
    
    public static class AudioEntity {
        /**
         * learned : 0
         * all : 10
         */
        
        private int learned;
        private int all;
        
        public int getLearned() {
            return learned;
        }
        
        public void setLearned(int learned) {
            this.learned = learned;
        }
        
        public int getAll() {
            return all;
        }
        
        public void setAll(int all) {
            this.all = all;
        }
    }
    
    public static class PicEntity {
        /**
         * learned : 0
         * all : 9
         */
        
        private int learned;
        private int all;
        
        public int getLearned() {
            return learned;
        }
        
        public void setLearned(int learned) {
            this.learned = learned;
        }
        
        public int getAll() {
            return all;
        }
        
        public void setAll(int all) {
            this.all = all;
        }
    }
    
    public static class VideoEntity {
        /**
         * learned : 0
         * all : 1
         */
        
        private int learned;
        private int all;
        
        public int getLearned() {
            return learned;
        }
        
        public void setLearned(int learned) {
            this.learned = learned;
        }
        
        public int getAll() {
            return all;
        }
        
        public void setAll(int all) {
            this.all = all;
        }
    }
}
