package com.hgsf.supermarket.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hgsf.supermarket.entity.Bill;

public interface BillService extends IService<Bill> {
    public Page<Bill> getPageBy(Integer currentPage,
                                Integer pageSize,
                                String billCode,
                                String productName,
                                Integer providerId);

}