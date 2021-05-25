package com.educational.service.impl;

import com.educational.dao.EduUserMapper;
import com.educational.entity.EduUser;
import com.educational.entity.User;
import com.educational.service.EduUserService;
import com.educational.utils.UuidUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EduUserServiceImpl implements EduUserService {
    @Autowired
    private EduUserMapper eduUserMapper;

    /**
     * 登录验证
     * @param name
     * @param password
     * @return
     */
    @Override
    public EduUser getUserByNameAndPassword(String name, String password) {
        EduUser users = eduUserMapper.searchUserByName(name);
        String pwd = MD5Util(name, password);
        if(users != null){
            //将查询出来的密码和前端传过来的密码进行验证
            if(users.getPassword().equals(pwd)){
                return users;
            }
        }
        return null;
    }

    /**
     * 获取用户详情
     * @return
     */
    @Override
    public List<User> getEduUsers() {
        List<EduUser> users = eduUserMapper.getEduUsers();
        List<User> list = new ArrayList<>();
        if(users != null && users.size() != 0){
            for (int i = 0; i < users.size(); i++) {
                EduUser eduUser = users.get(i);
                User u = new User();
                String r = null;
                int role = eduUser.getUserRole();
                if(role == 0){
                    r = "普通教师";
                }else if(role == 1){
                    r = "系主任";
                }else if(role == 2){
                    r = "教务处";
                }else{
                    r = "管理员";
                }
                int status = eduUser.getStatus();
                String s = null;
                if(status == 0){
                    s = "正常";
                }else{
                    s = "禁用";
                }
                Date date = eduUser.getCreateTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(date);
                u.setId(eduUser.getId());
                u.setPassword(eduUser.getPassword());
                u.setUserRole(r);
                u.setStatus(s);
                u.setCreateTime(time);
                u.setNickName(eduUser.getNickName());
                u.setName(eduUser.getName());
                u.setPhone(eduUser.getPhone());
                u.setEmail(eduUser.getEmail());
                u.setRemark(eduUser.getRemark());
                u.setNum(users.size());
                list.add(u);
            }
        }
        return list;
    }

    /**
     * 更改用户状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public int changeStatus(String id, int status) {
        return eduUserMapper.changeStatus(id, status);
    }

    /**
     * 添加用户
     * @param
     * @return
     */
    @Override
    public int addUser(EduUser user) {
        EduUser eduUser = new EduUser();
        //生成UUID
        eduUser.setId(UuidUtils.getUUID());
        //获取前端传过来的值
        EduUser u = eduUserMapper.searchUserByName(user.getName());
        if(u != null){
            return -1;//用户名已经存在
        }
        String password = MD5Util(user.getName(), user.getPassword());
        eduUser.setName(user.getName());
        eduUser.setNickName(user.getNickName());
        eduUser.setPassword(password);
        eduUser.setPhone(user.getPhone());
        eduUser.setEmail(user.getEmail());
        eduUser.setUserRole(Integer.valueOf(user.getUserRole()));
        eduUser.setStatus(0);//默认正常可用状态
        eduUser.setCreateTime(new Date());//当前时间
        return eduUserMapper.insert(eduUser);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public int delUser(String id) {
        return eduUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int batchDel(String ids) {
        ObjectMapper oMapper = new ObjectMapper();
        String[] idList = null;
        try {
            idList = oMapper.readValue(ids, String[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(ids != null && idList.length != 0){
            for (int i = 0; i < idList.length; i++) {
                String id = idList[i];
                try{
                    eduUserMapper.deleteByPrimaryKey(id);
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }
            }
            return 1;
        }
        return 0;
    }

    /**
     * 修改密码
     * @param id
     * @param old
     * @param nPwd
     * @return
     */
    @Override
    public int changePwd(String id, String old, String nPwd) {
        EduUser user = eduUserMapper.selectByPrimaryKey(id);
        if(user != null){
            String name = user.getName();
            String pwd = MD5Util(name,old);
            if(pwd.equals(user.getPassword())){
                String newPassword = MD5Util(name,nPwd);
                return eduUserMapper.updatePasswordById(id,newPassword);
            }
            return 0;
        }
        return 0;
    }

    /**
     * MD5加密
     * @param name
     * @param password
     * @return
     */
    public static String MD5Util(String name, String password){
        String hashAlgorithmName = "MD5";// 加密方法
        int hashIterations = 1;// 加密次数
        ByteSource credentialsSalt = ByteSource.Util.bytes(name);// 加盐
        Object obj = new SimpleHash(hashAlgorithmName, password, credentialsSalt, hashIterations);
        String pwd = obj.toString();//加密后密码
        return pwd;
    }
}
