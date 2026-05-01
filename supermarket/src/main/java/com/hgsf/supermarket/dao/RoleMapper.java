package com.hgsf.supermarket.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hgsf.supermarket.entity.Role;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    // 查询所有角色
    @Select("SELECT * FROM smbms_role")
    List<Role> getAllActiveRoles();
}