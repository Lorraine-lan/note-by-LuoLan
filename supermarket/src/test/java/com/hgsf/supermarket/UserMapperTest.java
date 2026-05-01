package com.hgsf.supermarket;

import com.hgsf.supermarket.dao.UserMapper;
import com.hgsf.supermarket.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void getByUserCodeTest(){
        User user=userMapper.getByUserCode("admin");
        System.out.println(user);
        System.out.println(user.getRole());
    }
}
