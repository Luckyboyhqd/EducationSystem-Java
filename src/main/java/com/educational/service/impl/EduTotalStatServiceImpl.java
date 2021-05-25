package com.educational.service.impl;

import com.educational.dao.EduCourseMapper;
import com.educational.dao.EduUserMapper;
import com.educational.dao.RelCourseClassUserMapper;
import com.educational.entity.EduCourse;
import com.educational.entity.LearnStat;
import com.educational.service.EduTotalStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class EduTotalStatServiceImpl implements EduTotalStatService {
    @Autowired
    private EduCourseMapper eduCourseMapper;
    @Autowired
    private RelCourseClassUserMapper relCourseClassUserMapper;
    @Autowired
    private EduUserMapper eduUserMapper;

    /**
     * 获取统计
     * @return
     */
    @Override
    public List<LearnStat> getLearnStats() {
        List<EduCourse> courses = eduCourseMapper.getCourseByStatus();
        if(courses != null && courses.size() != 0){
            List<LearnStat> learnStats = new ArrayList<>();
            for (int i = 0; i < courses.size(); i++) {
                EduCourse ec = courses.get(i);
                String courseId = ec.getId();//获取课程id
                String courseName = ec.getName();//获取课程name
                BigDecimal totalWorkload = ec.getTheoryTime().add(ec.getExperimentTime());//总课时
                String style = ec.getStyle();//课程类型
                String term = ec.getRemark();
                String academicYear = "1/2学年";
                List<String> uids = relCourseClassUserMapper.getUserIdsByCourseId(courseId);
                if(uids != null && uids.size() != 0){
                    for (int j = 0; j < uids.size(); j++) {
                        LearnStat ls = new LearnStat();
                        //获取用户id
                        String userId = uids.get(j);
                        String nickName = eduUserMapper.getNickNameById(userId);
                        ls.setId(courseId);
                        ls.setName(nickName);
                        ls.setCourseName(courseName);
                        if(term.equals("0")){
                            ls.setTerm("上学期");
                        }else{
                            ls.setTerm("下学期");
                        }
                        ls.setAcademicYear(academicYear);
                        ls.setStyle(style);
                        ls.setTotalWorkload(totalWorkload);
                        learnStats.add(ls);
                    }
                }

            }
            for (int i = 0; i < learnStats.size(); i++) {
                LearnStat l = learnStats.get(i);
                l.setNum(learnStats.size());
            }
            return learnStats;
        }
        return null;
    }

    /**
     * 模糊查询
     * @param find
     * @return
     */
    @Override
    public List<LearnStat> selectByFind(String find) {
        List<EduCourse> courses = eduCourseMapper.selectByFind(find);
        if(courses != null && courses.size() != 0){
            List<LearnStat> learnStats = new ArrayList<>();
            for (int i = 0; i < courses.size(); i++) {
                EduCourse ec = courses.get(i);
                String courseId = ec.getId();//获取课程id
                String courseName = ec.getName();//获取课程name
                BigDecimal totalWorkload = ec.getTheoryTime().add(ec.getExperimentTime());//总课时
                String style = ec.getStyle();//课程类型
                String term = ec.getRemark();
                String academicYear = "1/2学年";
                List<String> uids = relCourseClassUserMapper.getUserIdsByCourseId(courseId);
                if(uids != null && uids.size() != 0){
                    for (int j = 0; j < uids.size(); j++) {
                        LearnStat ls = new LearnStat();
                        //获取用户id
                        String userId = uids.get(j);
                        String nickName = eduUserMapper.getNickNameById(userId);
                        ls.setId(courseId);
                        ls.setName(nickName);
                        ls.setCourseName(courseName);
                        if(term.equals("0")){
                            ls.setTerm("上学期");
                        }else{
                            ls.setTerm("下学期");
                        }
                        ls.setAcademicYear(academicYear);
                        ls.setStyle(style);
                        ls.setTotalWorkload(totalWorkload);
                        learnStats.add(ls);
                    }
                }

            }
            for (int i = 0; i < learnStats.size(); i++) {
                LearnStat l = learnStats.get(i);
                l.setNum(learnStats.size());
            }
            return learnStats;
        }
        return null;
    }
}
