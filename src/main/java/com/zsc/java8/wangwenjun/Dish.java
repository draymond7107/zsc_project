package com.zsc.java8.wangwenjun;


import lombok.Data;

/**
 * 菜品/盘子
 *
 * @author ZhangSuchao
 * @create 2019/9/21
 * @since 1.0.0
 */
@Data
public class Dish {

    private String name;    //菜的名称

    private Boolean vegetaian;  //是否为素

    private Integer calories;   //卡路里

    private Type type;      //类型（肉 鱼 其他）


    public Dish() {

    }

    public Dish(String name, Boolean vegetaian, Integer calories, Type type) {
        this.name = name;
        this.vegetaian = vegetaian;
        this.calories = calories;
        this.type = type;
    }

    public enum Type {MEAT, FISH, OTHER} //肉 鱼 其他

}
