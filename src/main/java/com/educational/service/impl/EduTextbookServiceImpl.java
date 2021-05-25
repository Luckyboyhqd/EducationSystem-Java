package com.educational.service.impl;

import com.educational.dao.EduCourseMapper;
import com.educational.dao.EduTextbookMapper;
import com.educational.dao.EduUserMapper;
import com.educational.entity.EduClassManagement;
import com.educational.entity.EduTextbook;
import com.educational.entity.EduUser;
import com.educational.service.EduTextbookService;
import com.educational.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EduTextbookServiceImpl implements EduTextbookService {
    @Autowired
    private EduTextbookMapper eduTextbookMapper;
    @Autowired
    private EduUserMapper eduUserMapper;
    @Autowired
    private EduCourseMapper eduCourseMapper;

    @Override
    public List<EduTextbook> getEduTextbook() {
        List<EduTextbook> list = eduTextbookMapper.getEduTextbook();
        return getBookWay(list);
    }


    @Override
    public List<EduTextbook> getEduBookById(String id) {
        List<EduTextbook> list = eduTextbookMapper.getEduBookById(id);
        return getBookWay(list);
    }

    public List<EduTextbook> getBookWay(List<EduTextbook> list){
        if(list != null && list.size() != 0){
            List<EduTextbook> eduClassManagements = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                EduTextbook et = list.get(i);
                String uid = et.getUserId();//获取老师id
                EduUser user =  eduUserMapper.selectByPrimaryKey(uid);
                et.setUserId(user.getNickName());//录入人
                Date date = et.getCreateTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(date);
                et.setRemark(time);
                int status = et.getStatus();
                if(status == 1){
                    et.setOther("禁用");
                }else{
                    et.setOther("启用");
                }
                eduClassManagements.add(et);
            }
            for (int i = 0; i < list.size(); i++) {
                EduTextbook et = list.get(i);
                et.setStatus(list.size());
            }
            return eduClassManagements;
        }
        return null;
    }

    /**
     * 添加教材
     * @param book
     * @return
     */
    @Override
    public int addBook(EduTextbook book) {
        //获取教材ISBN和名称 查看数据库是否存在改对象
        String ISBN = book.getIsbn();
        String name = book.getName();
        EduTextbook textbook = eduTextbookMapper.getObjByIsbnAndName(ISBN, name);
        if(textbook != null){
            return -1;
        }
        String userName = book.getUserId();//获取录入人名字
        EduUser user = eduUserMapper.searchUserByNickName(userName);
        if(user == null){
            return -2;
        }
        String userId = user.getId();
        book.setId(UuidUtils.getUUID());
        book.setCreateTime(new Date());
        book.setUserId(userId);
        return eduTextbookMapper.insert(book);
    }

    /**
     * 更改状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public int changeBookStatus(String id, int status) {
        return eduTextbookMapper.changeBookStatus(id, status);
    }

    /**
     * 删除教材
     * @param id
     * @return
     */
    @Override
    public int delBookById(String id) {
        int temp = eduTextbookMapper.deleteByPrimaryKey(id);
        if(temp == 1){
            //课程表 将对应教材置空
            String nul = "";
            int num = eduCourseMapper.updateBookId(id, nul);
            return num;
        }
        return 0;
    }

    /**
     * 模糊查询
     * @param find
     * @return
     */
    @Override
    public List<EduTextbook> selectBookByFind(String find) {
        List<EduTextbook> list = eduTextbookMapper.selectBookByFind(find);
        return getBookWay(list);
    }

    /**
     * 编辑
     * @param book
     * @return
     */
    @Override
    public int editBook(EduTextbook book) {
        String userName = book.getUserId();
        EduUser user = eduUserMapper.searchUserByNickName(userName);
        if(user != null){
            book.setUserId(user.getId());
        }else{
            return 0;
        }
        return eduTextbookMapper.updateByPrimaryKeySelective(book);
    }

}
