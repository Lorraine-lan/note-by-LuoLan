package com.hgsf.supermarket.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hgsf.supermarket.entity.Bill;
import org.apache.ibatis.annotations.Param;

public interface BillMapper extends BaseMapper<Bill> {

    public Page<Bill> getPageBy(Page<Bill> page,
                                @Param("ew") QueryWrapper<Bill> queryWrapper);

}
