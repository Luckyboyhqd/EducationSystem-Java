package com.educational.service;

import com.educational.entity.EduClassManagement;

import java.util.List;

public interface EduClassService {

    //获取班级列表
    List<EduClassManagement> getEduClasses();

    //更改状态
    int changeClassStatus(String id, int status);

    //删除班级
    int delClass(String id);

    //添加班级
    int addClass(EduClassManagement e);

    //模糊查询
    List<EduClassManagement> selectClassByFind(String find);

    //修改班级
    EduClassManagement getEduClassById(String id);

    //编辑班级
    int editClass(EduClassManagement e);
}
