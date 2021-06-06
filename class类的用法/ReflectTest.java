package com.project;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {

    public static void main(String[] args) {
//        //得到Class对象的方式
//        //1、类名.Class
//        Class class1 = Car.class;
//        //2、对象.getClass（）
//        Car c1 = new Car();
//        Class class2 = c1.getClass();
//        //3、Class.forName(‘类全路径’)
//        Class class3 = null;
//        try {
//            class3 = Class.forName("com.project.ReflectTest");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

//------------------------------------------------------

//        Car c1 = new Car();
//        Class c = Car.class;
//        //得到该类中所有的公有属性集合
////        Field[] fs = c.getFields();
//
//        //得到该类中定义的属性集合，包含私有属性
//        Field[] fs = c.getDeclaredFields();
//        for(Field f: fs){
//            System.out.println(f.getName());
//        }
// ----------------------------------------------------------
        //得到该类中所有的公有方法，包括继承的公有方法
//        Method[] ms = c.getMethods();

        //得到该类中定义的所有方法，不包括继承的方法
//        Method[] ms = c.getDeclaredMethods();
//        for(Method m : ms) {
//            System.out.println(m.getName());
//        }

//-----------------------------------------------------------


//        Class c = Car.class;
//        try {
//            //调用该类的无参构造方法，产生该类的对象
//            Object obj = c.getDeclaredConstructor().newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//  -------------------------------------------------
//        try {
//            Class.forName("com.project.Car");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//-----------------------------------------------------
        String str = JOptionPane.showInputDialog(null, "请输入类的全路径");
        try {
            Class c = Class.forName(str);
            Method[] ms = c.getMethods();
            for(Method m : ms){
                System.out.println(m.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
