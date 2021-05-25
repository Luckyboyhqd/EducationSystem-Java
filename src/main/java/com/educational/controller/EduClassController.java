package com.educational.controller;

import com.educational.entity.EduClassManagement;
import com.educational.entity.LearnStat;
import com.educational.service.EduClassService;
import com.educational.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EduClassController {
    @Autowired
    private EduClassService eduClassService;

    /**
     *获取班级列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getEduClasses", method = RequestMethod.POST)
    public ResultSet getEduClasses(){
        List<EduClassManagement> list = eduClassService.getEduClasses();
        if(list != null && list.size()!= 0){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(list).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");

    }

    /**
     * 更改状态
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changeClassStatus", method = RequestMethod.POST)
    public String changeClassStatus(String id, int status){
        int temp = eduClassService.changeClassStatus(id, status);
        if(temp == 1){
            return "ok";
        }
        return "no";
    }

    /**
     * 删除班级
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delClass", method = RequestMethod.POST)
    public int delClass(String id){
        int temp = eduClassService.delClass(id);
        return temp;
    }

    /**
     * 添加班级
     * @param e
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addClass", method = RequestMethod.POST)
    public ResultSet addClass(EduClassManagement e){
        int temp = eduClassService.addClass(e);
        if(temp == 1){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(temp).setMsg("成功");
        }else if(temp == -1){
            return new ResultSet().setCode(HttpStatus.UNAUTHORIZED.value()).setData(-1).setMsg("用户已经存在");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(0).setMsg("失败");
    }

    /**
     * selectByFind
     */
    @ResponseBody
    @RequestMapping(value = "/selectClassByFind", method = RequestMethod.POST)
    public ResultSet selectClassByFind(String find){
        List<EduClassManagement> list = eduClassService.selectClassByFind(find);
        if(list != null && list.size()!= 0){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(list).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");
    }

    /**
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getEduClassById", method = RequestMethod.POST)
    public ResultSet getEduClassById(String id){
        EduClassManagement edu = eduClassService.getEduClassById(id);
        if(edu != null){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(edu).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");

    }

    /**
     * 编辑班级
     * @param e
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editClass", method = RequestMethod.POST)
    public ResultSet editClass(EduClassManagement e){
        int temp = eduClassService.editClass(e);
        if(temp == 1){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(temp).setMsg("成功");
        }else if(temp == -1){
            return new ResultSet().setCode(HttpStatus.UNAUTHORIZED.value()).setData(-1).setMsg("老师不存在");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(0).setMsg("失败");
    }

}
