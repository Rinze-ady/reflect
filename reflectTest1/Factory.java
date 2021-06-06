package com.project.reflectTest1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * 工厂类
 */
public class Factory {
    /**创建属性对象*/
    private static Properties pro = new Properties();

    /***/
    static{
        try {
            //加载属性文件
            pro.load(new FileReader("src/com/project/dao.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据接口名称，得到实现类对象
     * @param interfaceName 接口名称
     * @return 实现类对象
     */
    public static Object getObject(String interfaceName){
        //根据键对象，得到值对象
        String value= pro.getProperty(interfaceName);
//        System.out.println(value);


        try {
            //加载类，得到类模板
            Class c = Class.forName(value);
            //产生该类的对象
            return c.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
       Object obj =  Factory.getObject("IUserDao");
        System.out.println(obj);
    }
}
