package com.educational.dao;

import com.educational.entity.EduCourse;
import com.educational.entity.EduCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@Component
@MapperScan
public interface EduCourseMapper {
    int countByExample(EduCourseExample example);

    int deleteByExample(EduCourseExample example);

    int deleteByPrimaryKey(String id);

    int insert(EduCourse record);

    int insertSelective(EduCourse record);

    List<EduCourse> selectByExample(EduCourseExample example);

    EduCourse selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EduCourse record, @Param("example") EduCourseExample example);

    int updateByExample(@Param("record") EduCourse record, @Param("example") EduCourseExample example);

    int updateByPrimaryKeySelective(EduCourse record);

    int updateByPrimaryKey(EduCourse record);

    /**
     * 查询课程列表
     * @return
     */
    List<EduCourse> getEduCourses();

    /**
     * 通过编号和名字查看是否存在该条记录
     * @param no
     * @param coursName
     * @return
     */
    EduCourse getEduCourseByNoAndName(@Param("no")String no, @Param("coursName")String coursName);

    /**
     * 更改状态
     * @param cid
     * @param status
     * @return
     */
    int updateStatusById(@Param("cid")String cid, @Param("status")int status);

    /**
     * 删除教材
     * @param id
     * @return
     */
    int updateBookId(@Param("id")String id, @Param("nul")String nul);

    /**
     * 查询已分配状态的课程
     * @return
     */
    List<EduCourse> getCourseByStatus();

    /**
     * 条件查询
     * @param find
     * @return
     */
    List<EduCourse> selectByFind(String find);

    /**
     * 查询记录
     * @param courseName
     * @return
     */
    EduCourse getCourseByName(String courseName);

    int updateCourseByCourseName(@Param("courseName")String courseName, @Param("textId")String textId);

    /**
     * 获取据图教材课程分配
     * @return
     */
    List<EduCourse> getCoursesText();

    List<EduCourse> getCoursesById(String id);

    List<EduCourse> selectCourseByFind(String find);

    List<EduCourse> getCoursesTextById(String id);

    List<EduCourse> getEduCourseById(String id);

    List<EduCourse> selectObjByFind(String find);
}