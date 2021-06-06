package com.project.reflectTest1;

import com.project.reflectTest1.IUserDao;

public class UserDaoImpl implements IUserDao {
    @Override
    public void addUser() {
        System.out.println("完成用户添加");
    }
}
