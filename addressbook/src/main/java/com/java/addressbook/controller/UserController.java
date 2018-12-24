package com.java.addressbook.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.java.addressbook.entity.User;
import com.java.addressbook.entity.Util;
import com.java.addressbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

@RestController
public class UserController {
    @Autowired
    private UserService  userService;

    @RequestMapping(value = "/api/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String register(@RequestBody JSONObject data){
        String userName = data.getString("userName");
        String password = data.getString("password");
        JSONObject rs = new JSONObject();

        Boolean isExist;
        //判断账号是否存在
        isExist = userService.queryUser(userName);
        if (isExist){
            rs.put("status",2);
            return rs.toString();
        }else {
            Boolean success = userService.register(userName, password);
            if (success) {
                rs.put("status", 1);
            } else {
                rs.put("status", 0);
            }
        }
        return rs.toString();
    }

    @RequestMapping(value = "/api/login",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String login(@RequestBody JSONObject data, HttpSession session){
        JSONObject rs = new JSONObject();
        Boolean isExist = null;
        String userName = data.getString("userName");
        //判断账号是否存在
        isExist = userService.queryUser(userName);
        if (!isExist){
            rs.put("status",3);
            return rs.toString();
        }
        String password = data.getString("password");
        //判断账号密码是否正确
        isExist = userService.queryUser(userName,password);
        if(!isExist){
            rs.put("status",4);
        }else {
            Integer status = userService.getApprovalStatusByUserName(userName);
            //登入成功时修改
            if (status == 1){
                session.setAttribute("userName",userName);
                userService.loginSuccess(userName);
            }
            rs.put("status",status);
        }
        return rs.toString();
    }

    @RequestMapping(value = "/api/setUser",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String setUser(@RequestBody JSONObject data,HttpSession session){
        JSONObject rs = new JSONObject();
        String userName = (String) session.getAttribute("userName");
        if(userName == null){
            rs.put("status",2);
            return rs.toString();
        }
        User user = new User();
        user.setName(data.getString("name"));
        user.setBirth(Util.handleDate(data.getString("birth")));
        user.setProfession(data.getString("profession"));
        user.setClassName(data.getString("class"));
        user.setEntranceTime(Util.handleDate(data.getString("entranceTime")));
        user.setLeaveTime(Util.handleDate(data.getString("leaveTime")));
        user.setEmploymentUnit(data.getString("location"));
        user.setPhoto(data.getString("photo"));
        user.setPhoneNumber(data.getString("phoneNumber"));
        user.setEmail(data.getString("email"));
        user.setDescription(data.getString("description"));
        Boolean success = userService.setUserByUserName(user);
        if (success){
            rs.put("status",1);
        }else {
            rs.put("status",2);
        }
        return rs.toString();
    }

    @RequestMapping(value = "/api/getUserList",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getUserList(HttpSession session){
        JSONArray rs = new JSONArray();
        ArrayList<User> list = new ArrayList<>();
        String userName = (String) session.getAttribute("userName");
        if (userName == null){
            return rs.toString();
        }else {
            list = userService.getUserList();
            list.forEach(user ->  {
                JSONObject tmp = new JSONObject();
                tmp.put("userName",user.getUserName());
                tmp.put("name",user.getName());
                tmp.put("class",user.getClassName());
                    tmp.put("approvalStatus",user.getApprovalStatus());
                rs.add(tmp);
            });
            return rs.toString();
        }
    }

    @RequestMapping(value = "/api/getUserByUserName",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getUserByUserName(HttpSession session){
        JSONObject rs = new JSONObject();
        String userName = (String) session.getAttribute("userName");
        if (userName != null){
            User user = userService.getUserByUserName(userName);
            rs.put("name",user.getName());
            rs.put("birth",user.getBirth().toLocalDate());
            rs.put("profession",user.getProfession());
            rs.put("className",user.getClassName());
            rs.put("entranceTime",user.getEntranceTime().toLocalDate());
            rs.put("leaveTime",user.getLeaveTime().toLocalDate());
            rs.put("employmentUnit",user.getEmploymentUnit());
            rs.put("location",user.getLocation());
            rs.put("phoneNumber",user.getPhoneNumber());
            rs.put("email",user.getEmail());
            rs.put("lastLogin",user.getLastLogin().toLocalDate());
            rs.put("loginTimes",user.getLoginTimes());
            rs.put("description",user.getDescription());

        }
        return rs.toString();
    }

    @RequestMapping(value = "/api/examineAccount",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String examineAccount(@RequestBody JSONObject data,HttpSession session){
        JSONObject rs = new JSONObject();
        String userName = data.getString("userName");
        if (userName == null){
            rs.put("status",2);
        }else {
            Boolean success = userService.setApprovalStatus(userName,1);
            if (success){
                rs.put("status",1);
            }else {
                rs.put("status",0);
            }
        }
        return rs.toString();
    }

    @RequestMapping(value = "/api/disabledAccount",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String disabledAccount(@RequestBody JSONObject data,HttpSession session){
        JSONObject rs = new JSONObject();
        String userName = data.getString("userName");
        if (userName == null){
            return rs.toString();
        }else {
            Boolean success = userService.setApprovalStatus(userName,2);
            if (success){
                rs.put("status",1);
            }else {
                rs.put("status",0);
            }
        }
        return rs.toString();
    }
}
