package com.hgsf.supermarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hgsf.supermarket.dao.ProviderMapper;
import com.hgsf.supermarket.entity.Provider;
import com.hgsf.supermarket.service.ProviderService;
import com.hgsf.supermarket.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, Provider> implements ProviderService {

    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public Provider getByProCode(String proCode) {
        return providerMapper.getByProCode(proCode);
    }

    @Override
    @Transactional
    public boolean addProvider(Provider provider) {
        // 检查供应商编码是否已存在
        if (checkProCodeExists(provider.getProCode())) {
            throw new RuntimeException("供应商编码已存在");
        }

        // 设置创建时间
        provider.setCreationDate(new Date());

        // 保存供应商
        return this.save(provider);
    }

    @Override
    @Transactional
    public boolean updateProvider(Provider provider) {
        // 更新修改时间
        provider.setModifyDate(new Date());

        return this.updateById(provider);
    }

    @Override
    public Map<String, Object> getProviderList(Integer pageNum, Integer pageSize, String proCode, String proName) {
        Map<String, Object> result = new HashMap<>();

        // 查询数据列表
        List<Provider> providerList = providerMapper.getProviderList(proCode, proName);

        // 查询总数
        Long total = providerMapper.countProviders(proCode, proName);

        // 手动分页
        int start = (pageNum - 1) * pageSize;
        int fromIndex = Math.min(start, providerList.size());
        int toIndex = Math.min(start + pageSize, providerList.size());
        List<Provider> pageList = providerList.subList(fromIndex, toIndex);

        // 处理数据，转换日期格式
        for (Provider provider : pageList) {
            if (provider.getCreationDate() != null) {
                provider.setCreationDateStr(DateUtil.dateToString(provider.getCreationDate(), "yyyy-MM-dd"));
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
    public boolean checkProCodeExists(String proCode) {
        Integer count = providerMapper.checkProCodeExists(proCode);
        return count != null && count > 0;
    }

    @Override
    public Integer checkHasBill(Long providerId) {
        return providerMapper.checkHasBill(providerId);
    }
}