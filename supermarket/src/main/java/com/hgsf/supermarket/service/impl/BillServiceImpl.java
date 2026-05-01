package com.hgsf.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hgsf.supermarket.dao.BillMapper;
import com.hgsf.supermarket.entity.Bill;
import com.hgsf.supermarket.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements BillService {

    @Autowired
    private BillMapper billMapper;

    @Override
    public Page<Bill> getPageBy(Integer currentPage,
                                Integer pageSize,
                                String billCode,
                                String productName,
                                Integer providerId) {
      //封装分页对象
        Page<Bill> page = new Page<Bill>(currentPage,pageSize);
        //封装条件查询对象
        QueryWrapper<Bill> queryWrapper = new QueryWrapper<Bill>();
        //判断条件来封装
        if(null != billCode && !"".equals(billCode)){
            queryWrapper.eq("b.billCode",billCode);
        }
        if (null!=productName && !"".equals(productName)){
            queryWrapper.like("b.productName",productName);
        }
        if(null != providerId){
            queryWrapper.eq("p.id",providerId);
        }
        return billMapper.getPageBy(page,queryWrapper);
    }
}
