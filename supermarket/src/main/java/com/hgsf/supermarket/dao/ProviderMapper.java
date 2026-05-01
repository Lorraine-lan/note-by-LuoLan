package com.hgsf.supermarket.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hgsf.supermarket.entity.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderMapper extends BaseMapper<Provider> {
    // 根据供应商编码查询供应商
    Provider getByProCode(String proCode);

    // 查询供应商列表（带条件）
    List<Provider> getProviderList(
            @Param("proCode") String proCode,
            @Param("proName") String proName
    );

    // 统计供应商数量
    Long countProviders(
            @Param("proCode") String proCode,
            @Param("proName") String proName
    );

    // 检查供应商编码是否存在
    Integer checkProCodeExists(String proCode);

    // 检查供应商是否有账单
    Integer checkHasBill(Long providerId);
}