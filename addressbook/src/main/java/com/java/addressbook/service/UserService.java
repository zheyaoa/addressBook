package com.java.addressbook.service;

import com.java.addressbook.dao.User_dao;
import com.java.addressbook.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;


@Service
public class UserService {
    @Autowired
    User_dao user_dao;
    public Boolean register(String userName,String password){
        Boolean success = false;
        success = user_dao.addUser(userName,password);
        return  success;
    }
    //根据账号判断用户是否存在
    public Boolean queryUser(String userName){
        Boolean isExist = false;
        System.out.println("user_dao"+user_dao);
        User user = user_dao.getUserByUserName(userName);
        if (user!=null){
            isExist = true;
        }
        return isExist;
    }
    //根据userName,password判断用户是否存在
    public Boolean queryUser(String userName,String password){
        Boolean isExist = false;
        User user  =  user_dao.queryUser(userName,password);
        if (user!=null){
            isExist = true;
        }
        return  isExist;
    }
    public ArrayList<User> getUserList(){
        ArrayList<User> list  = null;
        list = user_dao.getUserList();
        return  list;
    }
    public User getUserByUserName(String userName){
        User user = null;
        user = user_dao.getUserByUserName(userName);
        return user;
    }
    public Boolean setApprovalStatus(String userName,Integer status){
        Boolean success =  null;
        success = user_dao.changeApprovalStatus(userName,status);
        return  success;
    }
    public Integer getApprovalStatusByUserName(String userName){
        User user = this.getUserByUserName(userName);
        Integer approvalStatus = user.getApprovalStatus();
        return approvalStatus;
    }
    public Boolean setUserByUserName(User user){
        Boolean success = null;
        success = user_dao.setUserByUserName(user.getUserName(),user.getName(),user.getBirth(),user.getProfession(),user.getClassName(),user.getEntranceTime(),user.getLeaveTime(),user.getEmploymentUnit(),user.getLocation(),user.getPhoneNumber(),user.getEmail(),user.getDescription());
        return  success;
    }
    public void loginSuccess(String userName){
        Integer loginTimes = user_dao.getLoginTimesByUserName(userName);
        user_dao.setLoginTimesByUserName(userName,loginTimes+1);
        java.sql.Date lastLogin =new java.sql.Date(new java.util.Date().getTime());
        user_dao.setLastLoginByUserName(userName,lastLogin);
    }
}
