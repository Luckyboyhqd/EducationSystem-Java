package com.educational.dao;

import com.educational.entity.EduClassManagement;
import com.educational.entity.EduClassManagementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@Component
@MapperScan
public interface EduClassManagementMapper {
    int countByExample(EduClassManagementExample example);

    int deleteByExample(EduClassManagementExample example);

    int deleteByPrimaryKey(String id);

    int insert(EduClassManagement record);

    int insertSelective(EduClassManagement record);

    List<EduClassManagement> selectByExample(EduClassManagementExample example);

    EduClassManagement selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EduClassManagement record, @Param("example") EduClassManagementExample example);

    int updateByExample(@Param("record") EduClassManagement record, @Param("example") EduClassManagementExample example);

    int updateByPrimaryKeySelective(EduClassManagement record);

    int updateByPrimaryKey(EduClassManagement record);

    /**
     * 获取班级名字
     * @param classId
     * @return
     */
    EduClassManagement getClassNameById(String classId);

    /**
     * 通过班级name获取班级id
     * @param className
     * @return
     */
    EduClassManagement getClassIdByName(String className);

    /**
     * 获取班级列表
     * @return
     */
    List<EduClassManagement> getEduClasses();

    /**
     * 更改状态
     * @param id
     * @param status
     * @return
     */
    int changeClassStatus(@Param("id")String id, @Param("status")int status);

    /**
     * 通过班级编号和名字获取对象
     * @param no
     * @param name
     * @return
     */
    EduClassManagement getObjByNoAndName(@Param("no")String no, @Param("name")String name);

    /**
     * 模糊查询
     * @param find
     * @return
     */
    List<EduClassManagement> selectClassByFind(String find);

    /**
     * @param id
     * @return
     */
    EduClassManagement getEduClassById(String id);
}