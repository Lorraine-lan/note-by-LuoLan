package com.hgsf.supermarket.controller;

import com.hgsf.supermarket.entity.Role;
import com.hgsf.supermarket.service.RoleService;
import com.hgsf.supermarket.utils.R;
import com.hgsf.supermarket.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 根据ID查询角色
     */
    @GetMapping("/getById")
    public R<Role> getById(@RequestParam Long id) {
        try {
            Role role = roleService.getById(id);
            return R.createSuccess(role);
        } catch (Exception e) {
            return R.createError(StatusCode.ERROR, "查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询所有角色
     */
    @GetMapping("/list")
    public R<List<Role>> list() {
        try {
            List<Role> list = roleService.list();
            return R.createSuccess(list);
        } catch (Exception e) {
            return R.createError(StatusCode.ERROR, "查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询所有角色（为前端添加用户页面使用，与list方法功能相同）
     */
    @GetMapping("/listAll")
    public R<List<Role>> listAll() {
        try {
            List<Role> list = roleService.list();
            return R.createSuccess(list);
        } catch (Exception e) {
            return R.createError(StatusCode.ERROR, "查询失败: " + e.getMessage());
        }
    }

    /**
     * 新增角色
     */
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody Role role) {
        try {
            boolean save = roleService.save(role);
            return save ? R.createSuccess(true) : R.createError(StatusCode.ERROR, "保存失败");
        } catch (Exception e) {
            return R.createError(StatusCode.ERROR, e.getMessage());
        }
    }

    /**
     * 修改角色
     */
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody Role role) {
        try {
            boolean update = roleService.updateById(role);
            return update ? R.createSuccess(true) : R.createError(StatusCode.ERROR, "更新失败");
        } catch (Exception e) {
            return R.createError(StatusCode.ERROR, e.getMessage());
        }
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/remove/{id}")
    public R<Boolean> remove(@PathVariable Long id) {
        try {
            boolean remove = roleService.removeById(id);
            return remove ? R.createSuccess(true) : R.createError(StatusCode.ERROR, "删除失败");
        } catch (Exception e) {
            return R.createError(StatusCode.ERROR, e.getMessage());
        }
    }
}