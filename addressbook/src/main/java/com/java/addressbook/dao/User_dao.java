package com.java.addressbook.dao;

import com.java.addressbook.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.sql.Date;

@Mapper
public interface User_dao {
    @Select("select * from user")
    ArrayList<User> getUserList();

    @Select("select * from user where userName = #{userName}")
    User getUserByUserName(String userName);

    @Select("select loginTimes from user where userName = #{userName}")
    Integer getLoginTimesByUserName(String userName);

    @Select("select * from user where userName = #{userName} and password = #{password}")
    User queryUser(@Param("userName")String userName,@Param("password")String password);

    @Insert("insert into user(userName,password) values(#{userName},#{password})")
    Boolean addUser(@Param("userName")String userName,@Param("password")String password);

    @Update("update user set approvalStatus=#{status} where userName=#{userName}")
    Boolean changeApprovalStatus(@Param("userName")String userName,@Param("status")Integer status);

    @Update("update user set lastLogin = #{lastLogin} where userName = #{userName}")
    Boolean setLastLoginByUserName(@Param("userName")String userName,@Param("lastLogin")Date lastLogin);

    @Update("update user set loginTimes = #{loginTimes} where userName = #{userName}")
    Boolean setLoginTimesByUserName(@Param("userName")String userName,@Param("loginTimes") Integer loginTimes);

    @Update("update user set name=#{name},birth=#{birth},profession=#{profession},className=#{className},entranceTime=#{entranceTime},leaveTime=#{leaveTime},employmentUnit=#{employmentUnit},location=#{location},phoneNumber=#{phoneNumber},email=#{email},description=#{description}")
    Boolean setUserByUserName(@Param("userName")String userName,@Param("name") String name,@Param("birth") Date birth,@Param("profession") String profession,@Param("className") String className,@Param("entranceTime") Date entranceTime,@Param("leaveTime") Date leaveTime,@Param("employmentUnit") String employmentUnit,@Param("location") String location,@Param("phoneNumber") String phoneNumber,@Param("email") String email,@Param("description") String description);
}
