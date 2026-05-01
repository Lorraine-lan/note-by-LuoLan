package com.hgsf.supermarket.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hgsf.supermarket.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    //根据用户名查询用户信息及对应的权限名称
    public User getByUserCode(String userCode);
    // 查询非admin用户列表
    List<User> getNonAdminUsers(
            @Param("userCode") String userCode,
            @Param("userName") String userName
    );

    // 统计非admin用户数量
    Long countNonAdminUsers(
            @Param("userCode") String userCode,
            @Param("userName") String userName
    );

    // 检查用户编码是否存在
    Integer checkUserCodeExists(String userCode);

    // 根据ID查询用户（包含角色信息）
    User selectUserWithRoleById(Long id);

    // 更新用户状态
    int updateUserStatus(@Param("id") Long id, @Param("status") Integer status);

}
