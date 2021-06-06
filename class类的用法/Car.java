package com.project;

public class Car {
    static {
        System.out.println("静态块");
    }

    public int price;
    public String type;
    private int age;

    public void move(){
        System.out.println("移动");
    }

    private void speak(){
        System.out.println("气轰");
    }
}
