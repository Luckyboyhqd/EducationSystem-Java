package com.educational.dao;

import com.educational.entity.EduUser;
import com.educational.entity.EduUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@Component
@MapperScan
public interface EduUserMapper {
    int countByExample(EduUserExample example);

    int deleteByExample(EduUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(EduUser record);

    int insertSelective(EduUser record);

    List<EduUser> selectByExample(EduUserExample example);

    EduUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EduUser record, @Param("example") EduUserExample example);

    int updateByExample(@Param("record") EduUser record, @Param("example") EduUserExample example);

    int updateByPrimaryKeySelective(EduUser record);

    int updateByPrimaryKey(EduUser record);

    /**
     * 通过用户名查询对象
     * @param name
     * @return
     */
    EduUser searchUserByName(String name);

    /**
     * 获取用户详情
     * @return
     */
    List<EduUser> getEduUsers();

    /**
     * 更改用户状态
     * @param id
     * @param status
     * @return
     */
    int changeStatus(@Param("id")String id, @Param("status")int status);

    /**
     * 更新密码
     * @param id
     * @param newPassword
     * @return
     */
    int updatePasswordById(@Param("id")String id, @Param("newPassword")String newPassword);

    /**
     * 获取老师id
     * @param id
     * @return
     */
    EduUser selectByUserId(String id);

    /**
     * nick_name查询用户id
     * @param teacher
     * @return
     */
    EduUser searchUserByNickName(String teacher);

    /**
     * 获取
     * @param userId
     * @return
     */
    String getNickNameById(String userId);
}