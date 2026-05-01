package com.hgsf.supermarket.controller;

import com.hgsf.supermarket.entity.User;
import com.hgsf.supermarket.service.UserService;
import com.hgsf.supermarket.utils.PasswordUtil;
import com.hgsf.supermarket.utils.R;
import com.hgsf.supermarket.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
// 跨域注解 保留，解决前端跨域问题
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    private UserService userService;

    // 正确的、带加密加盐校验的 修改密码核心接口
    @GetMapping("/updatePwd")
    public R updatePwd(@RequestParam Long id, @RequestParam String oldPwd, @RequestParam String newPwd){
        //1、id在不在  2、旧密码对不对（加密加盐比对）  3、新密码加密
        User user = userService.getById(id);
        String encode_oldPwd = PasswordUtil.encode(oldPwd,user.getSalt());
        if(user.getUserPassword().equals(encode_oldPwd)){
            user.setUserPassword(PasswordUtil.encode(newPwd,user.getSalt()));
            userService.updateById(user);
            return  R.createSuccess();
        }else {
            return R.createError(StatusCode.ERROR,"旧密码错误，请重新输入");
        }
    }

    @PostMapping("/update")
    public R update(@RequestBody User user) {
        try {
            // 1. 先查询数据库中的原始用户（保留密码、盐值等关键字段）
            User originalUser = userService.getById(user.getId());
            if (originalUser == null) {
                // 用户不存在，返回错误（适配R.java的createError方法）
                return R.createError(StatusCode.ERROR, "用户不存在，无法修改资料");
            }

            // 2. 只覆盖需要修改的「个人资料字段」，完全保留密码、盐值
            originalUser.setUserName(user.getUserName());       // 用户名
            originalUser.setGender(user.getGender());           // 性别
            originalUser.setBirthday(user.getBirthday());       // 生日
            originalUser.setPhone(user.getPhone());             // 手机号
            originalUser.setAddress(user.getAddress());         // 地址
            // ❗ 刻意不修改 userPassword 和 salt 字段，保留数据库原始值

            // 3. 执行更新（仅更新资料字段，密码/盐值不变）
            boolean flag = userService.updateById(originalUser);

            if(flag){
                // 成功：使用R.java的createSuccess，默认flag=true，message="操作成功"
                return R.createSuccess(null);
            }else{
                // 失败：适配R.java的createError方法
                return R.createError(StatusCode.ERROR, "个人资料修改失败！");
            }
        } catch (Exception e) {
            // 异常：适配R.java的createError方法，返回异常信息
            return R.createError(StatusCode.ERROR, "修改异常："+e.getMessage());
        }
    }
    // ======================== 唯一修改处 ↑↑↑ 结束 ========================

    // 根据ID查询用户，带异常捕获+统一响应体，健壮性更高
    @GetMapping("/getById")
    public R<User> getById(@RequestParam Long id) {
        try {
            User user = userService.getById(id);
            return R.createSuccess(user);
        } catch (Exception e) {
            return R.createError(StatusCode.ERROR, "查询失败: " + e.getMessage());
        }
    }

    // 添加用户
    @PostMapping("/add")
    public R<Boolean> addUser(@RequestBody User user) {
        try {
            boolean success = userService.addUser(user);
            return success ? R.createSuccess(true) : R.createError(StatusCode.ERROR, "添加失败");
        } catch (Exception e) {
            return R.createError(StatusCode.ERROR, e.getMessage());
        }
    }

    // 查询非admin用户列表（分页+条件查询）
    @GetMapping("/listNonAdmin")
    public R<Map<String, Object>> listNonAdminUsers(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "8") Integer pageSize,
            @RequestParam(required = false) String userCode,
            @RequestParam(required = false) String userName) {
        try {
            Map<String, Object> result = userService.getNonAdminUsers(pageNum, pageSize, userCode, userName);
            return R.createSuccess(result);
        } catch (Exception e) {
            return R.createError(StatusCode.ERROR, "查询失败: " + e.getMessage());
        }
    }

    // 启用用户
    @PostMapping("/enable/{id}")
    public R<Boolean> enableUser(@PathVariable Long id) {
        try {
            boolean success = userService.enableUser(id);
            return success ? R.createSuccess(true) : R.createError(StatusCode.ERROR, "启用失败");
        } catch (Exception e) {
            return R.createError(StatusCode.ERROR, e.getMessage());
        }
    }

    // 冻结用户
    @PostMapping("/freeze/{id}")
    public R<Boolean> freezeUser(@PathVariable Long id) {
        try {
            boolean success = userService.freezeUser(id);
            return success ? R.createSuccess(true) : R.createError(StatusCode.ERROR, "冻结失败");
        } catch (Exception e) {
            return R.createError(StatusCode.ERROR, e.getMessage());
        }
    }

    // 检查用户编码是否存在
    @GetMapping("/checkUserCode")
    public R<Boolean> checkUserCode(@RequestParam String userCode) {
        try {
            boolean exists = userService.checkUserCodeExists(userCode);
            return R.createSuccess(exists);
        } catch (Exception e) {
            return R.createError(StatusCode.ERROR, "检查失败: " + e.getMessage());
        }
    }

    // 根据用户编码查询用户
    @GetMapping("/getByUserCode")
    public R<User> getByUserCode(@RequestParam String userCode) {
        try {
            User user = userService.getByUserCode(userCode);
            return R.createSuccess(user);
        } catch (Exception e) {
            return R.createError(StatusCode.ERROR, "查询失败: " + e.getMessage());
        }
    }
}