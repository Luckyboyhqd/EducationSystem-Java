package com.educational.controller;

import com.educational.entity.EduUser;
import com.educational.entity.User;
import com.educational.service.EduUserService;
import com.educational.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EduUserController {
    @Autowired
    private EduUserService eduUserService;

    /**
     * 登录方法
     * @param users
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultSet login(EduUser users, HttpServletRequest request){
        String name = users.getName();
        String pwd = users.getPassword();
        if(name == null || name == "" || pwd == null || pwd == ""){
            return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("登录失败");
        }
        EduUser user = eduUserService.getUserByNameAndPassword(name, pwd);
        if(user != null){
            request.getSession().setAttribute("user", user);
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(user).setMsg("登录成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("登录失败");
    }

    /**
     * 获取用户列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getEduUsers", method = RequestMethod.POST)
    public ResultSet getEduUsers(){
        List<User> list = eduUserService.getEduUsers();
        if(list != null && list.size()!= 0){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(list).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");
    }

    /**
     * 用户注销
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public String loginOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "ok";
    }

    /**
     * 更改状态
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
    public String changeStatus(String id, int status){
        int temp = eduUserService.changeStatus(id, status);
        if(temp == 1){
            return "ok";
        }
        return "no";
    }


    /**
     * 添加用户
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResultSet addUser(EduUser user){
        int temp = eduUserService.addUser(user);
        if(temp == 1){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(temp).setMsg("成功");
        }else if(temp == -1){
            return new ResultSet().setCode(HttpStatus.UNAUTHORIZED.value()).setData(-1).setMsg("用户已经存在");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(0).setMsg("失败");
    }


    /**
     * 删除用户
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delUser", method = RequestMethod.POST)
    public int delUser(String id){
        int temp = eduUserService.delUser(id);
        return temp;
    }

    /**
     * 批量删除
     * @param idList
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/batchDel", method = RequestMethod.POST)
    public int batchDel(String idList){
        //此处前端传list后台无法 出现异常 Failed to instantiate [java.util.List]: Specified class is an interface
        int temp = eduUserService.batchDel(idList);
        return temp;
    }

    /**
     * 修改密码
     * @param id
     * @param old
     * @param nPwd
     * @param aPwd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    public ResultSet changePwd(String id, String old, String nPwd, String aPwd){
        if(!nPwd.equals(aPwd)){
            return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(0).setMsg("失败");
        }
        int temp = eduUserService.changePwd(id, old, nPwd);
        if(temp == 1){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(1).setMsg("成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(0).setMsg("失败");
    }

}
