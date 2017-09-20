package com.medicine.ssqy.ssqy.entity.food;

import java.util.List;

/**
 * Created by Administrator on 2017-09-21.
 */

public class FoodGaiEntity {
    
    /**
     * count : 11
     * foodsData : [{"foodTitle":"芝麻酱","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/","foodGai":"1057"},{"foodTitle":"虾皮","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodGai":"991"},{"foodTitle":"牛奶（全脂）","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodGai":"676"},{"foodTitle":"乳酪","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodGai":"659"},{"foodTitle":"芥菜","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodGai":"294"},{"foodTitle":"食品名称","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodGai":"mg/100g"},{"foodTitle":"海参","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodGai":"285"},{"foodTitle":"紫菜","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodGai":"264"},{"foodTitle":"黑木耳","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodGai":"247"},{"foodTitle":"海带","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodGai":"241"},{"foodTitle":"黑豆","foodPic":"http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/null","foodGai":"224"}]
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
         * foodTitle : 芝麻酱
         * foodPic : http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/http://106.3.41.199:8066/jeesite/resourceFiles/images/foodsport/
         * foodGai : 1057
         */
        
        private String foodTitle;
        private String foodPic;
        private String foodGai;
        
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
        
        public String getFoodGai() {
            return foodGai;
        }
        
        public void setFoodGai(String foodGai) {
            this.foodGai = foodGai;
        }
    }
}
