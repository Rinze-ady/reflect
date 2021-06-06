package reflectTest2.dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {

    //加载驱动
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //建立连接
    protected Connection con;

    //sql语句执行对象
    protected PreparedStatement ps;

    //结果集
    protected ResultSet rs;

    //加载驱动
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立连接
     */
    public void setConnection(){
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:6789/dormitory?characterEncoding=utf-8",
                    "root", "lovo");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 关闭连接
     */
    public void closeConnection(){
        try {
            if(rs!=null){ //避免空指针
                rs.close();
            }
            ps.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * 对数据库进行增删改操作
     * @param sql 更新sql语句
     * @param valueArray 值列表
     */
    public void updateData(String sql, Object...valueArray){
        this.setConnection();
        try {
            ps = con.prepareStatement(sql);
            for(int i=0; i<valueArray.length; i++){
                ps.setObject(i+1, valueArray[i]);
            }

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }
    }

    /*
     * 查询
     * @param beanClass 集合中存放元素的类模板
     * @param sql 查询sql语句
     * @param valueArray 值列表
     * @return 集合
     */
//    方法一：效率低
    /*public List selectData(Class beanClass, String sql, Object...valueArray){
        List list = new ArrayList();

        this.setConnection();
        try {
            ps = con.prepareStatement(sql);
            if(valueArray !=null && valueArray.length != 0) {
                for (int i = 0; i < valueArray.length; i++) {
                    ps.setObject(i + 1, valueArray[i]);
                }
            }

            //得到属性列表，得到的是所有的属性，在查询部分列时就会有问题，用try块来解决
            Field[] farray = beanClass.getDeclaredFields();

            rs= ps.executeQuery();
            while (rs.next()){
                //产生该实体类对象
               Object beanObj = beanClass.getDeclaredConstructor().newInstance();
                for (int i = 0; i < farray.length; i++) {
                    //得到属性名
                    String fileName = farray[i].getName();

                    //这个try块用于查询部分列的情况，查询部分列时会报异常，把它捕获且忽略，继续运行
                    try {
//                    System.out.println(fileName);
                        //从结果集中得到该属性对应的值
                        Object value = rs.getObject(fileName);

                        if (value instanceof java.sql.Date) {
                            value = LocalDate.parse(value.toString());
                        }

                        //根据属性名得到属性对象
                        Field f = beanClass.getDeclaredField(fileName);
                        //忽略访问修饰符的检查
                        f.setAccessible(true);
                        //将beanObj中当前属性赋值为value
                        f.set(beanObj, value);
                    }catch (Exception e){
                        continue;
                    }
                }

                list.add(beanObj);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }

        return list;
    }*/

//    方法二:提高效率

    public List selectData(Class beanClass, String sql, Object...valueArray){

        List list = new ArrayList();

    this.setConnection();
    try {
        ps = con.prepareStatement(sql);
        if(valueArray !=null && valueArray.length != 0) {
            for (int i = 0; i < valueArray.length; i++) {
                ps.setObject(i + 1, valueArray[i]);
            }
        }

        rs= ps.executeQuery();
        //得到结果集检查对象
        ResultSetMetaData rm = rs.getMetaData();
        //得到查询列的个数
        int num = rm.getColumnCount();

        while (rs.next()){
            //产生该实体类对象
            Object beanObj = beanClass.getDeclaredConstructor().newInstance();
            for(int i=1; i<=num  ;i++){
                //得到指定编号对应的列名
//                System.out.println(rm.getColumnLabel(i));
                String columnName = rm.getColumnLabel(i);
                //从结果集中得到指定列的值
                Object value = rs.getObject(columnName);

                System.out.println(value);
                if (value instanceof java.sql.Date) {
                    value = LocalDate.parse(value.toString());
                }

                //得到指定列对应的属性对象
                Field f = beanClass.getDeclaredField(columnName);
                f.setAccessible(true);
                f.set(beanObj,value);
            }


            list.add(beanObj);
        }
    } catch (Exception throwables) {
        throwables.printStackTrace();
    }finally {
        this.closeConnection();
    }

    return list;
}

    public static void main(String[] args) {
        BaseDao dao = new BaseDao();
        dao.setConnection();
//        System.out.println(dao.selectData());

    }


}
