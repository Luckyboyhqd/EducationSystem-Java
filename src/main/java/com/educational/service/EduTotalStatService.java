package com.educational.service;

import com.educational.entity.LearnStat;

import java.util.List;

public interface EduTotalStatService {

    //查询统计记录
    List<LearnStat> getLearnStats();

    List<LearnStat> selectByFind(String find);
}
