package com.zsc.reflect;

public class Cat {

    private String color;
    public double weight;

    public String eat(String food) {
        System.out.println("今晚cat吃了：" + food);
        return food;
    }
    private void sleep(Integer num, String color) {
        System.out.println(num + "个颜色是" + color + "的猫猫在睡觉");
    }

    public Cat() {
    }
    private Cat(String color, double weight) {
        this.color = color;
        this.weight = weight;
    }
    @Override
    public String toString() {
        return "Cat{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
