package com.educational.controller;

import com.educational.entity.EduTextbook;
import com.educational.service.EduTextbookService;
import com.educational.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EduTextbookController {
    @Autowired
    private EduTextbookService eduTextbookService;


    /**
     *获取班级列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getEduTextbook", method = RequestMethod.POST)
    public ResultSet getEduTextbook(){
        List<EduTextbook> list = eduTextbookService.getEduTextbook();
        if(list != null && list.size()!= 0){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(list).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");

    }

    /**
    * 获取班级列表通过id
    * @return
    */
    @ResponseBody
    @RequestMapping(value = "/getEduBookById", method = RequestMethod.POST)
    public ResultSet getEduBookById(String id){
        List<EduTextbook> list = eduTextbookService.getEduBookById(id);
        if(list != null && list.size()!= 0){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(list).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");

    }

    /**
     *模糊查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectBookByFind", method = RequestMethod.POST)
    public ResultSet selectBookByFind(String find){
        List<EduTextbook> list = eduTextbookService.selectBookByFind(find);
        if(list != null && list.size()!= 0){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(list).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");

    }

    /**
     * 编辑课程
     * @param book
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editBook", method = RequestMethod.POST)
    public ResultSet editBook(EduTextbook book){
        int temp = eduTextbookService.editBook(book);
        if(temp == 1){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(1).setMsg("成功");
        }
        return new ResultSet().setCode(HttpStatus.PAYMENT_REQUIRED.value()).setData(0).setMsg("失败");
    }


    /**
     * 添加课程
     * @param book
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public ResultSet addBook(EduTextbook book){
        int temp = eduTextbookService.addBook(book);
        if(temp == 1){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(temp).setMsg("成功");
        }else if(temp == -1) {
            return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(temp).setMsg("已经存在");
        }else if(temp == -2){
            return new ResultSet().setCode(HttpStatus.UNAUTHORIZED.value()).setData(temp).setMsg("录入人不存在");
        }
            return new ResultSet().setCode(HttpStatus.PAYMENT_REQUIRED.value()).setData(0).setMsg("失败");
    }

    /**
     * 选择课程
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changeBookStatus", method = RequestMethod.POST)
    public String changeBookStatus(String id, int status){
        int temp = eduTextbookService.changeBookStatus(id, status);
        if(temp == 1){
            return "ok";
        }
        return "no";
    }

    /**
     * 删除课程
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delBook", method = RequestMethod.POST)
    public int delBook(String id){
        return eduTextbookService.delBookById(id);

    }


}
