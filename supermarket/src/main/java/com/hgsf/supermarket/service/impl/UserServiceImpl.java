package com.hgsf.supermarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hgsf.supermarket.dao.UserMapper;
import com.hgsf.supermarket.entity.User;
import com.hgsf.supermarket.service.UserService;
import com.hgsf.supermarket.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByUserCode(String userCode) {
        return userMapper.getByUserCode(userCode);
    }

    @Override
    @Transactional
    public boolean addUser(User user) {
        // 检查用户编码是否已存在
        if (checkUserCodeExists(user.getUserCode())) {
            throw new RuntimeException("用户编码已存在");
        }

        // 使用PasswordUtil生成盐和加密密码
        String salt = PasswordUtil.salt(); // 生成8位盐
        String encryptedPassword = PasswordUtil.encode(user.getUserPassword(), salt);

        user.setSalt(salt);
        user.setUserPassword(encryptedPassword);

        // 设置默认状态为启用（1）
        if (user.getStatus() == null) {
            user.setStatus(1);
        }

        // 设置创建时间
        user.setCreationDate(new Date());

        // 保存用户
        return this.save(user);
    }

    @Override
    public Map<String, Object> getNonAdminUsers(Integer pageNum, Integer pageSize, String userCode, String userName) {
        Map<String, Object> result = new HashMap<>();

        // 计算起始位置
        int start = (pageNum - 1) * pageSize;

        // 查询数据列表
        List<User> userList = userMapper.getNonAdminUsers(userCode, userName);

        // 查询总数
        Long total = userMapper.countNonAdminUsers(userCode, userName);

        // 手动分页（实际应该用数据库分页，这里为了简单手动处理）
        int fromIndex = Math.min(start, userList.size());
        int toIndex = Math.min(start + pageSize, userList.size());
        List<User> pageList = userList.subList(fromIndex, toIndex);

        // 处理数据，方便前端显示
        for (User user : pageList) {
            // 设置状态显示文本：1=启用，2=冻结
            if (user.getStatus() != null) {
                // 状态1=启用，状态2=冻结
                if (user.getStatus() == 1) {
                    user.setStatusText("启用");
                } else if (user.getStatus() == 2) {
                    user.setStatusText("冻结");
                } else {
                    // 如果还有其他状态值，可以在这里处理
                    user.setStatusText("未知状态");
                }
            }

            // 设置性别显示文本
            if (user.getGender() != null) {
                // 假设1=男，2=女
                if (user.getGender() == 1) {
                    user.setGenderText("男");
                } else if (user.getGender() == 2) {
                    user.setGenderText("女");
                } else {
                    user.setGenderText("未知");
                }
            }
        }

        result.put("list", pageList);
        result.put("total", total);
        result.put("pageNum", pageNum);
        result.put("pageSize", pageSize);
        result.put("totalPages", (int) Math.ceil((double) total / pageSize));

        return result;
    }

    @Override
    @Transactional
    public boolean enableUser(Long userId) {
        User user = this.getById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if ("admin".equals(user.getUserCode())) {
            throw new RuntimeException("不能修改admin用户状态");
        }
        // 启用状态设置为1
        user.setStatus(1);
        user.setModifyDate(new Date());
        return this.updateById(user);
    }

    @Override
    @Transactional
    public boolean freezeUser(Long userId) {
        User user = this.getById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if ("admin".equals(user.getUserCode())) {
            throw new RuntimeException("不能修改admin用户状态");
        }
        // 冻结状态设置为2（根据您的数据库）
        user.setStatus(2);
        user.setModifyDate(new Date());
        return this.updateById(user);
    }

    @Override
    public boolean checkUserCodeExists(String userCode) {
        Integer count = userMapper.checkUserCodeExists(userCode);
        return count != null && count > 0;
    }
}