package com.hgsf.supermarket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgsf.supermarket.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

public interface UserService extends IService<User> {
    User getByUserCode(String userCode);

    // 添加用户（密码加密加盐）
    boolean addUser(User user);

    // 分页查询非admin用户
    Map<String, Object> getNonAdminUsers(Integer pageNum, Integer pageSize, String userCode, String userName);

    // 启用用户
    boolean enableUser(Long userId);

    // 冻结用户
    boolean freezeUser(Long userId);

    // 检查用户编码是否已存在
    boolean checkUserCodeExists(String userCode);
}