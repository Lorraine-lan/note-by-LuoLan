package com.hgsf.supermarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hgsf.supermarket.dao.RoleMapper;
import com.hgsf.supermarket.entity.Role;
import com.hgsf.supermarket.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}