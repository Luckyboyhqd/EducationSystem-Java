package com.educational.dao;

import com.educational.entity.RelCourseClassUser;
import com.educational.entity.RelCourseClassUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@Component
@MapperScan
public interface RelCourseClassUserMapper {
    int countByExample(RelCourseClassUserExample example);

    int deleteByExample(RelCourseClassUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(RelCourseClassUser record);

    int insertSelective(RelCourseClassUser record);

    List<RelCourseClassUser> selectByExample(RelCourseClassUserExample example);

    RelCourseClassUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RelCourseClassUser record, @Param("example") RelCourseClassUserExample example);

    int updateByExample(@Param("record") RelCourseClassUser record, @Param("example") RelCourseClassUserExample example);

    int updateByPrimaryKeySelective(RelCourseClassUser record);

    int updateByPrimaryKey(RelCourseClassUser record);

    /**
     * 获取用户id和班级id
     * @param courseId
     * @return
     */
    List<RelCourseClassUser> getClassIdAndUserIdByCourseId(String courseId);

    /**
     * 查询是否存在对象
     * @param cid
     * @param classId
     * @return
     */
    RelCourseClassUser getObjByCourseIdAndClassId(@Param("cid")String cid, @Param("classId")String classId);

    /**
     * 插入用户
     * @param id
     * @param uid
     * @return
     */
    int updateUserId(@Param("id")String id, @Param("uid")String uid);

    /**
     * 删除对象
     * @param id
     * @return
     */
    int delCourseById(String id);

    /**
     * 删除对象 通过班级id
     * @param id
     * @return
     */
    int delDataByClassId(String id);

    /**
     * 查询用户id
     * @param courseId
     * @return
     */
    List<String> getUserIdsByCourseId(String courseId);

    RelCourseClassUser getClassIdByCourseId(String id);
}