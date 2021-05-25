package com.educational.controller;

import com.educational.entity.LearnStat;
import com.educational.service.EduTotalStatService;
import com.educational.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EduTotalStatController {
    @Autowired
    private EduTotalStatService eduTotalStatService;

    @ResponseBody
    @RequestMapping(value = "/getLearnStats", method = RequestMethod.POST)
    public ResultSet getLearnStats(){
        List<LearnStat> list = eduTotalStatService.getLearnStats();
        if(list != null && list.size()!= 0){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(list).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");
    }

    /**
     * selectByFind
     */
    @ResponseBody
    @RequestMapping(value = "/selectByFind", method = RequestMethod.POST)
    public ResultSet selectByFind(String find){
        List<LearnStat> list = eduTotalStatService.selectByFind(find);
        if(list != null && list.size()!= 0){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(list).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");
    }
}
