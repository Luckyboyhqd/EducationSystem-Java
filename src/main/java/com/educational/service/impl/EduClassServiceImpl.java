package com.educational.service.impl;


import com.educational.dao.EduClassManagementMapper;
import com.educational.dao.EduUserMapper;
import com.educational.dao.RelCourseClassUserMapper;
import com.educational.entity.EduClassManagement;
import com.educational.entity.EduUser;
import com.educational.service.EduClassService;
import com.educational.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EduClassServiceImpl implements EduClassService {
    @Autowired
    private EduClassManagementMapper eduClassManagementMapper;
    @Autowired
    private EduUserMapper eduUserMapper;
    @Autowired
    private RelCourseClassUserMapper relCourseClassUserMapper;


    /**
     * 获取班级列表
     * @return
     */
    @Transactional
    @Override
    public List<EduClassManagement> getEduClasses(){
        List<EduClassManagement> list = eduClassManagementMapper.getEduClasses();
        if(list != null && list.size() != 0){
            List<EduClassManagement> eduClassManagements = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                EduClassManagement et = list.get(i);
                String uid = et.getUserId();//获取老师id
                EduUser user =  eduUserMapper.selectByPrimaryKey(uid);
                et.setUserId(user.getNickName());
                Date date = et.getCreateTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(date);
                et.setRemark(time);
                int status = et.getStatus();
                if(status == 0){
                    et.setOther("启用");
                }else{
                    et.setOther("禁用");
                }
                eduClassManagements.add(et);
            }
            for (int i = 0; i < list.size(); i++) {
                EduClassManagement et = list.get(i);
                et.setStatus(list.size());
            }
            return eduClassManagements;
        }
        return null;
    }

    /**
     * 更改状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public int changeClassStatus(String id, int status) {
        return eduClassManagementMapper.changeClassStatus(id, status);
    }

    /**
     * 删除班级
     * @param id
     * @return
     */
    @Override
    public int delClass(String id) {
        int temp = eduClassManagementMapper.deleteByPrimaryKey(id);
        //删除关联表中数据
        if(temp == 1){
            return relCourseClassUserMapper.delDataByClassId(id);
        }
        return 0;
    }

    /**
     * 添加班级
     * @param e
     * @return
     */
    @Override
    public int addClass(EduClassManagement e) {
        //获取班级名称 和 编号 查看是否存在
        String no = e.getClassNo();
        String name = e.getName();
        EduClassManagement eduClassManagement = eduClassManagementMapper.getObjByNoAndName(no, name);
        if(eduClassManagement != null){
            return -1;
        }
        e.setId(UuidUtils.getUUID());//uuid
        String teacherName = e.getUserId();//获取老师名字
        EduUser user = eduUserMapper.searchUserByNickName(teacherName);
        String uid = user.getId();
        if(uid == null || uid.equals("")){
            return 0;
        }
        e.setCreateTime(new Date());//当前时间
        e.setUserId(uid);
        return eduClassManagementMapper.insert(e);
    }

    /**
     * 模糊查询
     * @param find
     * @return
     */
    @Override
    public List<EduClassManagement> selectClassByFind(String find) {
        List<EduClassManagement> list = eduClassManagementMapper.selectClassByFind(find);
        if(list != null && list.size() != 0){
            List<EduClassManagement> eduClassManagements = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                EduClassManagement et = list.get(i);
                String uid = et.getUserId();//获取老师id
                EduUser user =  eduUserMapper.selectByPrimaryKey(uid);
                et.setUserId(user.getNickName());
                Date date = et.getCreateTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(date);
                et.setRemark(time);
                int status = et.getStatus();
                if(status == 0){
                    et.setOther("启用");
                }else{
                    et.setOther("禁用");
                }
                eduClassManagements.add(et);
            }
            for (int i = 0; i < list.size(); i++) {
                EduClassManagement et = list.get(i);
                et.setStatus(list.size());
            }
            return eduClassManagements;
        }
        return null;
    }

    @Override
    public EduClassManagement getEduClassById(String id) {
        EduClassManagement e = eduClassManagementMapper.getEduClassById(id);
        String name = eduUserMapper.getNickNameById(e.getUserId());
        e.setUserId(name);
        return e;
    }

    /**
     * 编辑
     * @param e
     * @return
     */

    @Override
    public int editClass(EduClassManagement e) {
        String name = e.getUserId();
        EduUser u = eduUserMapper.searchUserByNickName(name);
        if(u == null){
            return -1;//老师不存在
        }
        e.setUserId(u.getId());
        return eduClassManagementMapper.updateByPrimaryKeySelective(e);
    }
}
