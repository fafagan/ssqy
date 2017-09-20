package com.medicine.ssqy.ssqy.entity.food;

import java.util.List;

/**
 * Created by Administrator on 2017-09-21.
 */

public class FoodHotEntity {
    
    /**
     * count : 50
     * foodsData : [{"foodTitle":"小米","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"358/100"},{"foodTitle":"馒头","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"208/100"},{"foodTitle":"米粥（粳米）","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"46/100"},{"foodTitle":"玉米","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"336/100"},{"foodTitle":"豆浆","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"13/100"},{"foodTitle":"腐竹","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"459/100"},{"foodTitle":"烧饼","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"302/100"},{"foodTitle":"油饼","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"399/100"},{"foodTitle":"竹笋（干）","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"280/100"},{"foodTitle":"土豆","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"81/100"},{"foodTitle":"山药","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"67/100"},{"foodTitle":"黄豆芽","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"44/100"},{"foodTitle":"黄花菜","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"203/100"},{"foodTitle":"芋头","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"94/100"},{"foodTitle":"香椿","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"62/100"},{"foodTitle":"胡萝卜","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"39/100"},{"foodTitle":"松子仁","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"698/100"},{"foodTitle":"葵花籽","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/10018.jpg","foodhot":"1194/100"},{"foodTitle":"南瓜子（炒）","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"844/100"},{"foodTitle":"葡萄干","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"341/100"},{"foodTitle":"核桃（鲜）","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"760/100"},{"foodTitle":"金丝小枣","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"397/100"},{"foodTitle":"大枣（干）","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"338/100"},{"foodTitle":"椰子","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"700/100"},{"foodTitle":"香蕉","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"154/100"},{"foodTitle":"鸡蛋黄","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"328/100"},{"foodTitle":"咸鸭蛋","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"216/100"},{"foodTitle":"松花蛋（鸭）","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"190/100"},{"foodTitle":"鸡蛋（红皮）","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"177/100"},{"foodTitle":"鸭蛋","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"207/100"},{"foodTitle":"奶油","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"720/100"},{"foodTitle":"黄油","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"892/100"},{"foodTitle":"牛奶","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"54/100"},{"foodTitle":"墨鱼（干）","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"350/100"},{"foodTitle":"鱼子酱（大马哈）","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"252/100"},{"foodTitle":"海米","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"195/100"},{"foodTitle":"鲢鱼","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"167/100"},{"foodTitle":"大黄鱼","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"145/100"},{"foodTitle":"基围虾","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"168/100"},{"foodTitle":"蛤蜊（毛蛤蜊）","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"388/100"},{"foodTitle":"蚶子","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"263/100"},{"foodTitle":"猪肉（肥）","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"816/100"},{"foodTitle":"猪肉（瘦）","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"143/100"},{"foodTitle":"午餐肉","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"229/100"},{"foodTitle":"牛肉（瘦）","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"106/100"},{"foodTitle":"牛肉（前腱）","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"105/100"},{"foodTitle":"北京烤鸭","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"545/100"},{"foodTitle":"驴肉","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"116/100"},{"foodTitle":"鹌鹑","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"190/100"},{"foodTitle":"香肠","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodhot":"508/100"}]
     */
    
    private int count;
    private List<FoodsDataEntity> foodsData;
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    public List<FoodsDataEntity> getFoodsData() {
        return foodsData;
    }
    
    public void setFoodsData(List<FoodsDataEntity> foodsData) {
        this.foodsData = foodsData;
    }
    
    public static class FoodsDataEntity {
        /**
         * foodTitle : 小米
         * foodPic : http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null
         * foodhot : 358/100
         */
        
        private String foodTitle;
        private String foodPic;
        private String foodhot;
        
        public String getFoodTitle() {
            return foodTitle;
        }
        
        public void setFoodTitle(String foodTitle) {
            this.foodTitle = foodTitle;
        }
        
        public String getFoodPic() {
            return foodPic;
        }
        
        public void setFoodPic(String foodPic) {
            this.foodPic = foodPic;
        }
        
        public String getFoodhot() {
            return foodhot;
        }
        
        public void setFoodhot(String foodhot) {
            this.foodhot = foodhot;
        }
    }
}
