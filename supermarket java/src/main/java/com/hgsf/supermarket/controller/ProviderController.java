package com.hgsf.supermarket.controller;

import com.hgsf.supermarket.entity.Provider;
import com.hgsf.supermarket.service.ProviderService;
import com.hgsf.supermarket.utils.R;
import com.hgsf.supermarket.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/provider")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    // 添加供应商
    @PostMapping("/add")
    public R<Boolean> addProvider(@RequestBody Provider provider) {
        try {
            // 验证供应商编码
            if (provider.getProCode() == null || provider.getProCode().trim().isEmpty()) {
                return R.createError(StatusCode.ERROR, "供应商编码不能为空");
            }

            // 验证供应商名称
            if (provider.getProName() == null || provider.getProName().trim().isEmpty()) {
                return R.createError(StatusCode.ERROR, "供应商名称不能为空");
            }

            boolean success = providerService.addProvider(provider);
            if (success) {
                return R.createSuccess(true);
            } else {
                return R.createError(StatusCode.ERROR, "添加失败");
            }
        } catch (Exception e) {
            return R.createError(StatusCode.ERROR, e.getMessage());
        }
    }

    // 更新供应商
    @PostMapping("/update")
    public R<Boolean> updateProvider(@RequestBody Provider provider) {
        try {
            if (provider.getId() == null) {
                return R.createError(StatusCode.ERROR, "供应商ID不能为空");
            }

            boolean success = providerService.updateProvider(provider);
            if (success) {
                return R.createSuccess(true);
            } else {
                return R.createError(StatusCode.ERROR, "更新失败");
            }
        } catch (Exception e) {
            return R.createError(StatusCode.ERROR, e.getMessage());
        }
    }

    // 根据ID查询供应商
    @GetMapping("/getById")
    public R<Provider> getById(@RequestParam Long id) {
        try {
            if (id == null) {
                return R.createError(StatusCode.ERROR, "供应商ID不能为空");
            }

            Provider provider = providerService.getById(id);
            if (provider == null) {
                return R.createError(StatusCode.ERROR, "供应商不存在");
            }
            return R.createSuccess(provider);
        } catch (Exception e) {
            return R.createError(StatusCode.ERROR, "查询失败: " + e.getMessage());
        }
    }

    // 根据供应商编码查询供应商
    @GetMapping("/getByProCode")
    public R<Provider> getByProCode(@RequestParam String proCode) {
        try {
            if (proCode == null || proCode.trim().isEmpty()) {
                return R.createError(StatusCode.ERROR, "供应商编码不能为空");
            }

            Provider provider = providerService.getByProCode(proCode);
            return R.createSuccess(provider);
        } catch (Exception e) {
            return R.createError(StatusCode.ERROR, "查询失败: " + e.getMessage());
        }
    }

    // 查询供应商列表（分页+条件查询）
    @GetMapping("/list")
    public R<Map<String, Object>> getProviderList(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "8") Integer pageSize,
            @RequestParam(required = false) String proCode,
            @RequestParam(required = false) String proName) {
        try {
            Map<String, Object> result = providerService.getProviderList(pageNum, pageSize, proCode, proName);
            return R.createSuccess(result);
        } catch (Exception e) {
            return R.createError(StatusCode.ERROR, "查询失败: " + e.getMessage());
        }
    }

    // 检查供应商编码是否存在
    @GetMapping("/checkProCode")
    public R<Boolean> checkProCode(@RequestParam String proCode) {
        try {
            if (proCode == null || proCode.trim().isEmpty()) {
                return R.createSuccess(false);
            }

            boolean exists = providerService.checkProCodeExists(proCode);
            return R.createSuccess(exists);
        } catch (Exception e) {
            // 出现异常时返回false，不影响前端流程
            return R.createSuccess(false);
        }
    }

    // 删除供应商（检查是否有账单）
    @DeleteMapping("/delete/{id}")
    public R<Boolean> deleteProvider(@PathVariable Long id) {
        try {
            // 检查是否有账单
            Integer billCount = providerService.checkHasBill(id);
            if (billCount != null && billCount > 0) {
                return R.createError(StatusCode.ERROR, "该供应商下有账单，无法删除！");
            }

            // 删除供应商
            boolean success = providerService.removeById(id);
            if (success) {
                return R.createSuccess(true);
            } else {
                return R.createError(StatusCode.ERROR, "删除失败");
            }
        } catch (Exception e) {
            return R.createError(StatusCode.ERROR, "删除失败: " + e.getMessage());
        }
    }
    @GetMapping("/findAll") // 请求方式为GET，和前端axios.get()对应
    public R findAll() {
        // 调用服务层方法，查询所有供应商数据
        List<Provider> providerList = providerService.list(); // 若用MyBatis-Plus，直接用list()方法查询全部；若自定义方法，替换为providerService.findAll()

        // 封装返回结果，和前端预期的 {flag: true, data: 供应商列表} 格式对应
        return R.createSuccess(providerList);
    }
}