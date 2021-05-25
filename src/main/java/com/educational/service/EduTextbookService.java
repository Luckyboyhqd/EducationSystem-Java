package com.educational.service;

import com.educational.entity.EduTextbook;

import java.util.List;

public interface EduTextbookService {

    //获取教材
    List<EduTextbook> getEduTextbook();

    //添加教材
    int addBook(EduTextbook book);

    //更改状态
    int changeBookStatus(String id, int status);

    //删除教材
    int delBookById(String id);

    List<EduTextbook> selectBookByFind(String find);

    int editBook(EduTextbook book);

    List<EduTextbook> getEduBookById(String id);
}
