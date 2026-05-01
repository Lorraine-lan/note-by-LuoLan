package com.hgsf.supermarket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgsf.supermarket.entity.Provider;

import java.util.Map;

public interface ProviderService extends IService<Provider> {
    // 根据供应商编码查询供应商
    Provider getByProCode(String proCode);

    // 添加供应商
    boolean addProvider(Provider provider);

    // 更新供应商
    boolean updateProvider(Provider provider);

    // 分页查询供应商列表
    Map<String, Object> getProviderList(Integer pageNum, Integer pageSize, String proCode, String proName);

    // 检查供应商编码是否已存在
    boolean checkProCodeExists(String proCode);

    // 检查供应商是否有账单
    Integer checkHasBill(Long providerId);
}