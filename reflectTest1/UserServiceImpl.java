package com.project.reflectTest1;

import com.project.reflectTest1.Factory;
import com.project.reflectTest1.IUserDao;
import com.project.reflectTest1.IUserService;

public class UserServiceImpl implements IUserService {
//    private IUserDao dao = new UserDaoImpl();
    private IUserDao dao = (IUserDao) Factory.getObject("IUserDao");

    @Override
    public void addUser() {
        dao.addUser();

    }

    public static void main(String[] args) {
        IUserService u = (IUserService) Factory.getObject("IUserService");
        u.addUser();
    }
}
