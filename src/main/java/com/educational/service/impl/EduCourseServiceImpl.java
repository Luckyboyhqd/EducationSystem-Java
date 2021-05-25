package com.educational.service.impl;

import com.educational.dao.*;
import com.educational.entity.*;
import com.educational.service.EduCourseService;
import com.educational.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EduCourseServiceImpl implements EduCourseService {
    @Autowired
    private EduCourseMapper eduCourseMapper;
    @Autowired
    private EduUserMapper eduUserMapper;
    @Autowired
    private EduClassManagementMapper eduClassManagementMapper;
    @Autowired
    private RelCourseClassUserMapper relCourseClassUserMapper;
    @Autowired
    private EduTextbookMapper eduTextbookMapper;

    /**
     * 获取课程列表
     * @return
     */
    @Transactional
    @Override
    public List<Course> getEduCourses() {
        List<EduCourse> list = eduCourseMapper.getEduCourses();
        return getCourse(list);
    }

    @Override
    public List<Course> selectCourseByFind(String find) {
        List<EduCourse> list = eduCourseMapper.selectCourseByFind(find);
        return getCourse(list);
    }

    @Override
    public List<Course> getEduCourseById(String id) {
        List<EduCourse> list = eduCourseMapper.getEduCourseById(id);
        return getCourse(list);
    }

    /**
     * 编辑课程
     * @param course
     * @return
     */
    @Override
    public int editCourse(EduCourse course) {
        System.out.println(course);
        String userName = course.getUserId();
        EduUser user = eduUserMapper.searchUserByNickName(userName);
        if(user != null){
            course.setUserId(user.getId());
        }else {
            return 0;
        }
        String remark = course.getRemark();
        if(remark.equals("上学期")){
            course.setRemark("0");
        }else{
            course.setRemark("1");
        }
        return eduCourseMapper.updateByPrimaryKeySelective(course);
    }

    @Override
    public List<Course> selectObjByFind(String find) {
        List<EduCourse> list = eduCourseMapper.selectObjByFind(find);
        return getCourseWay(list);
    }

    public List<Course> getCourse(List<EduCourse> list){
        List<Course> courses = new ArrayList<>();
        if(list != null && list.size() != 0){
            for (int i = 0; i < list.size(); i++) {
                EduCourse ec = list.get(i);
                //获取课程的id
                String courseId = ec.getId();
                //获取信息
                List<RelCourseClassUser> rcu = relCourseClassUserMapper.getClassIdAndUserIdByCourseId(courseId);
                for (int j = 0; j < rcu.size(); j++) {
                    RelCourseClassUser r = rcu.get(j);
                    Course course = new Course();
                    course.setId(ec.getId());
                    course.setCourseNo(ec.getCourseNo());
                    course.setName(ec.getName());
                    course.setStyle(ec.getStyle());
                    course.setCredit(ec.getCredit());
                    course.setTheoryTime(ec.getTheoryTime());
                    course.setExperimentTime(ec.getExperimentTime());
                    //获取班级id
                    String classId = r.getClassId();
                    EduClassManagement eduClass = eduClassManagementMapper.getClassNameById(classId);
                    course.setClassName(eduClass.getName());
                    if(ec.getStaus() == 1){//未分配
                        course.setStauts("未分配");
                        course.setTeacher("无");
                    }else if(ec.getStaus() == 2){//已分配
                        course.setStauts("已分配");
                        String id = r.getUserId();//获取老师id
                        EduUser teacher = eduUserMapper.selectByUserId(id);
                        course.setTeacher(teacher.getNickName());
                    }else{//禁用
                        course.setStauts("禁用");
                        course.setTeacher("无");
                    }
                    courses.add(course);

                }
            }
            for (int i = 0; i < courses.size(); i++) {
                Course c = courses.get(i);
                c.setNum(courses.size());
            }
            return courses;
        }
        return null;
    }

    /**
     * 获取教材 课程分配
     * @return
     */
    @Override
    public List<Course> getCoursesText(String id, int role) {
        List<EduCourse> list = null;
        if(role == 0){
            list = eduCourseMapper.getCoursesTextById(id);
        }else{
            list = eduCourseMapper.getCoursesText();
        }
        return getCourseWay(list);
    }

    public List<Course> getCourseWay(List<EduCourse> list){
        List<Course> courseList = new ArrayList<>();
        //获取所有分配好了的课程
        if(list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                EduCourse ec = list.get(i);
                if(ec.getTextbookId() == null || ec.getTextbookId().equals("")){
                    continue;
                }
                Course course = new Course();
                course.setId(ec.getId());//课程id
                course.setCourseNo(ec.getCourseNo());//课程编号
                course.setName(ec.getName());//名称

                //获取课程的id
                String textId = ec.getTextbookId();
                EduTextbook eduTextbook = eduTextbookMapper.getTextById(textId);

                RelCourseClassUser rccu = relCourseClassUserMapper.getClassIdByCourseId(ec.getId());
                String classId = rccu.getClassId();
                EduClassManagement eclass = eduClassManagementMapper.getClassNameById(classId);
                String className = eclass.getName();
                course.setClassName(className);
                course.setTeacher(eduUserMapper.selectByUserId(ec.getUserId()).getNickName());
                course.setTextBook(eduTextbook.getIsbn());
                course.setStauts(eduTextbook.getName());
                course.setStyle((eduTextbook.getAuthor()));
                courseList.add(course);
            }
            for (int i = 0; i < courseList.size(); i++) {
                Course ec = courseList.get(i);
                ec.setNum(courseList.size());
            }
            return courseList;
        }
        return null;
    }


    @Override
    public List<Course> getCoursesById(String id) {
        List<EduCourse> list = eduCourseMapper.getCoursesById(id);
        return getCourseWay(list);
    }

    /**
     * 添加课层
     * @param course
     * @return
     */
    @Transactional
    @Override
    public int addCourse(Course course) {
        String no = course.getCourseNo();
        String coursName = course.getName();
        //通过编号和名称 查看课程是否存在
        EduCourse ec = eduCourseMapper.getEduCourseByNoAndName(no, coursName);
        if(ec != null && !ec.equals("")){
            return -1;//该条记录已经存在
        }
        //生成uuid
        EduCourse eduCourse = new EduCourse();
        String id = UuidUtils.getUUID();//课程表id
        eduCourse.setId(id);//id
        eduCourse.setCourseNo(no);//编号
        eduCourse.setName(coursName);//名称
        int temp = Integer.valueOf(course.getStyle());//获取课程类型 0必修1选修2学位课
        if(temp ==  0){
            eduCourse.setStyle("必修");
        }else if(temp == 1){
            eduCourse.setStyle("选修");
        }else{
            eduCourse.setStyle("学位课");
        }
        eduCourse.setCredit(course.getCredit());
        eduCourse.setTheoryTime(course.getTheoryTime());
        eduCourse.setExperimentTime(course.getExperimentTime());
//        String textName = course.getTextBook();//获取教材name
//        EduTextbook eduTextbook = eduTextbookMapper.getTextIdByName(textName);
//        if(eduTextbook == null || eduTextbook.equals("")){
//            return -3;//教材不存在
//        }
//        eduCourse.setTextbookId(eduTextbook.getId());
        int num = Integer.valueOf(course.getStauts());
        eduCourse.setStaus(num);
        eduCourse.setCreateTime(new Date());
        eduCourse.setUserId(course.getTeacherId());//创建人
        eduCourse.setRemark(String.valueOf(course.getTerm()));//学期
        if(num == 0){//未分配老师
            //通过班级名称获取班级id
            RelCourseClassUser rccu = new RelCourseClassUser();
            rccu.setId(UuidUtils.getUUID());
            EduClassManagement classObj = eduClassManagementMapper.getClassIdByName(course.getClassName());
            if(classObj == null){
                return 0;
            }
            rccu.setCourseId(id);//课程表id
            rccu.setClassId(classObj.getId());
            rccu.setUserId(null);
            //往关系表插入数据relCourseClassUserMapper
            int rcc = relCourseClassUserMapper.insert(rccu);
            if(rcc == 0){
                return -2;//插入关系表失败
            }
        }else{//已分配老师
            RelCourseClassUser rccu = new RelCourseClassUser();
            rccu.setId(UuidUtils.getUUID());
            EduClassManagement classObj = eduClassManagementMapper.getClassIdByName(course.getClassName());
            if(classObj == null){
                return 0;
            }
            rccu.setCourseId(id);//课程表id
            rccu.setClassId(classObj.getId());
            EduUser user = eduUserMapper.searchUserByNickName(course.getTeacher());
            rccu.setUserId(user.getId());
        }
        int m = eduCourseMapper.insert(eduCourse);
        if(m == 1){
            return 1;
        }
        return 0;
    }

    /**
     * 选择课程
     * @param uid
     * @param cid
     * @param className
     * @param status
     * @return
     */
    @Transactional
    @Override
    public int chooseCourse(String uid, String cid, String className, int status) {
        //更改课程表
        int temp = eduCourseMapper.updateStatusById(cid, status);
        if(temp == 0){
            return 0;//更新失败
        }
        //通过编辑name查询对应id
        EduClassManagement eduClassManagement = eduClassManagementMapper.getClassIdByName(className);
        String classId = eduClassManagement.getId();
        //查询关系表
        RelCourseClassUser r = relCourseClassUserMapper.getObjByCourseIdAndClassId(cid, classId);
        RelCourseClassUser relCourseClassUser = new RelCourseClassUser();
        if(r == null || r.equals("")){
            relCourseClassUser.setId(UuidUtils.getUUID());
            relCourseClassUser.setUserId(uid);
            relCourseClassUser.setClassId(classId);
            relCourseClassUser.setCourseId(cid);
            return relCourseClassUserMapper.insert(relCourseClassUser);

        }
        return relCourseClassUserMapper.updateUserId(r.getId(), uid);
    }

    /**
     * 删除课程
     * @param id
     * @return
     */
    @Transactional
    @Override
    public int delCourseById(String id) {
        //主表删除
        int temp = eduCourseMapper.deleteByPrimaryKey(id);
        if(temp == 0){
            return 0;
        }
        return relCourseClassUserMapper.delCourseById(id);
    }

    /**
     * 教材分配课程
     * @param courseName
     * @param bookName
     * @return
     */
    @Override
    public int addCourseBook(String courseName, String bookName, String className) {
        EduCourse course = eduCourseMapper.getCourseByName(courseName);
        //通过班级名字查询对应id
        EduClassManagement eduClass = eduClassManagementMapper.getClassIdByName(className);
        RelCourseClassUser rel = relCourseClassUserMapper.getObjByCourseIdAndClassId(course.getId(), eduClass.getId());
        if(rel != null && !rel.equals("")){
            return 0;//已经存在
        }
        if(course != null && !course.equals("")){
            //先判断是否存在记录
            String bookId = course.getTextbookId();
            if(bookId != null && !bookId.equals("")){
                return 0;//已经存在改记录
            }
            //此时改条记录为空
            EduTextbook eduTextbook = eduTextbookMapper.getTextIdByName(bookName);
            if(eduTextbook != null || !eduTextbook.equals("")){
                String textId = eduTextbook.getId();
                int temp = eduCourseMapper.updateCourseByCourseName(courseName, textId);
                return temp;
            }
        }
        return 0;
    }

}
