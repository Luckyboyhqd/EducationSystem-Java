package com.educational.service;

import com.educational.entity.EduUser;
import com.educational.entity.User;

import java.util.List;

public interface EduUserService {

    //登录
    EduUser getUserByNameAndPassword(String name, String password);

    //查看详情
    List<User> getEduUsers();

    //更改状态
    int changeStatus(String id, int status);

    //添加用户
    int addUser(EduUser user);

    //删除用户
    int delUser(String id);

    //批量删除
    int batchDel(String ids);

    //修改密码
    int changePwd(String id, String old, String nPwd);
}
