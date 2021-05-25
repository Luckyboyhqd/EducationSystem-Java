package com.educational.controller;

import com.educational.entity.Course;
import com.educational.entity.EduCourse;
import com.educational.service.EduCourseService;
import com.educational.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;


    /**
     * 获取课程列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getEduCourses", method = RequestMethod.POST)
    public ResultSet getEduCourses(){
        List<Course> list = eduCourseService.getEduCourses();
        if(list != null && list.size()!= 0){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(list).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");
    }


    /**
     * 通过id获取课程
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getEduCourseById", method = RequestMethod.POST)
    public ResultSet getEduCourseById(String id){
        List<Course> list = eduCourseService.getEduCourseById(id);
        if(list != null && list.size()!= 0){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(list).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");
    }
    /**
     * 添加课程
     * @param course
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public ResultSet addCourse(Course course){
        int temp = eduCourseService.addCourse(course);
        if(temp == 1){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(temp).setMsg("成功");
        }else if(temp == -1){
            return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(temp).setMsg("已经存在");
        }else if(temp == -2){
            return new ResultSet().setCode(HttpStatus.NO_CONTENT.value()).setData(temp).setMsg("插入关系表失败");
        }else if(temp == -3){
            return new ResultSet().setCode(HttpStatus.METHOD_FAILURE.value()).setData(temp).setMsg("教材不存在");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(0).setMsg("失败");
    }

    /**
     * 选择课程
     * @param uid
     * @param cid
     * @param className
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/chooseCourse", method = RequestMethod.POST)
    public String chooseCourse(String uid, String cid, String className, int status){
        int temp = eduCourseService.chooseCourse(uid, cid, className, status);
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
    @RequestMapping(value = "/delCourse", method = RequestMethod.POST)
    public int delCourse(String id){
        return eduCourseService.delCourseById(id);
    }


    /**
     * 教材分配课程
     * @param courseName
     * @param bookName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addCourseBook", method = RequestMethod.POST)
    public ResultSet addCourseBook(String courseName, String bookName, String className){
        int temp = eduCourseService.addCourseBook(courseName, bookName, className);
        if(temp != 0){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(1).setMsg("成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(0).setMsg("失败");
    }


    /**
     * 获取课程教材分配列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCoursesText", method = RequestMethod.POST)
    public ResultSet getCoursesText(String id, int role){
        List<Course> list = eduCourseService.getCoursesText(id, role);
        if(list != null && list.size()!= 0){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(list).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");
    }

    @ResponseBody
    @RequestMapping(value = "/selectObjByFind", method = RequestMethod.POST)
    public ResultSet selectObjByFind(String find){
        List<Course> list = eduCourseService.selectObjByFind(find);
        if(list != null && list.size()!= 0){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(list).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");
    }


    /**
     * 获取课程教材分配列表-teacher
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCoursesById", method = RequestMethod.POST)
    public ResultSet getCoursesById(String id){
        List<Course> list = eduCourseService.getCoursesById(id);
        if(list != null && list.size()!= 0){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(list).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");
    }


    /**
     * 模糊查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectCourseByFind", method = RequestMethod.POST)
    public ResultSet selectCourseByFind(String find){
        List<Course> list = eduCourseService.selectCourseByFind(find);
        if(list != null && list.size()!= 0){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(list).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");
    }


    @ResponseBody
    @RequestMapping(value = "/editCourse", method = RequestMethod.POST)
    public ResultSet editCourse(EduCourse course){
        int temp = eduCourseService.editCourse(course);
        if(temp == 1){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(1).setMsg("编辑成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("编辑失败");
    }


}
