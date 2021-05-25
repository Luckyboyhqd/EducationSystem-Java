package com.educational.service;


import com.educational.entity.Course;
import com.educational.entity.EduCourse;

import java.util.List;

public interface EduCourseService {

    //获取课程信息
    List<Course> getEduCourses();

    //添加课程
    int addCourse(Course course);

    //教师选择课程
    int chooseCourse(String uid, String cid, String className, int status);

    //删除课程
    int delCourseById(String id);

    //教材分配课程
    int addCourseBook(String courseName, String bookName, String className);

    //获取具体分配
    List<Course> getCoursesText(String id, int role);

    List<Course> getCoursesById(String id);

    List<Course> selectCourseByFind(String find);

    List<Course> getEduCourseById(String id);

    int editCourse(EduCourse course);

    List<Course> selectObjByFind(String find);
}
