package com.educational.dao;

import com.educational.entity.EduTextbook;
import com.educational.entity.EduTextbookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@Component
@MapperScan
public interface EduTextbookMapper {
    int countByExample(EduTextbookExample example);

    int deleteByExample(EduTextbookExample example);

    int deleteByPrimaryKey(String id);

    int insert(EduTextbook record);

    int insertSelective(EduTextbook record);

    List<EduTextbook> selectByExample(EduTextbookExample example);

    EduTextbook selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EduTextbook record, @Param("example") EduTextbookExample example);

    int updateByExample(@Param("record") EduTextbook record, @Param("example") EduTextbookExample example);

    int updateByPrimaryKeySelective(EduTextbook record);

    int updateByPrimaryKey(EduTextbook record);

    /**
     * 获取教材信息
     * @param textId
     * @return
     */
    EduTextbook getTextById(String textId);

    /**
     * 获取教材id
     * @param textName
     * @return
     */
    EduTextbook getTextIdByName(String textName);

    /**
     * 获取教材详情
     * @return
     */
    List<EduTextbook> getEduTextbook();

    /**
     * 更改状态
     * @param id
     * @param status
     * @return
     */
    int changeBookStatus(@Param("id")String id, @Param("status")int status);

    /**
     * 添加教材
     * @param isbn
     * @param name
     * @return
     */
    EduTextbook getObjByIsbnAndName(@Param("isbn")String isbn, @Param("name")String name);

    List<EduTextbook> selectBookByFind(String find);

    List<EduTextbook> getEduBookById(String id);
}