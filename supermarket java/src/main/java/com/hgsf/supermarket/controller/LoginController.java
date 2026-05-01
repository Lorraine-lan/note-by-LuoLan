package com.hgsf.supermarket.controller;

import com.hgsf.supermarket.entity.User;
import com.hgsf.supermarket.service.UserService;
import com.hgsf.supermarket.utils.PasswordUtil;
import com.hgsf.supermarket.utils.R;
import com.hgsf.supermarket.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public R<User> login(String userName, String passWord){
        User user=userService.getByUserCode(userName);
        if (null == user){
            return R.createError(StatusCode.ERROR, "登录失败，用户名错误");
        }else{
            //用户名和密码正确
            String db = user.getUserPassword();
            String encode = PasswordUtil.encode(passWord, user.getSalt());
            if (!db.equals(encode)) {
                return R.createError(StatusCode.ERROR, "登陆失败，密码错误");
            }
            //验证状态
            if (user.getStatus() == 2){
                return R.createError(StatusCode.ERROR, "登陆失败，账户被冻结，请联系管理员");
            }
            //返回成功
            //脱敏处理
            user.setUserPassword("");
            user.setSalt("");
            return R.createSuccess(user);
        }
    }
}
