package com.marstalk.spring.factorybean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Value("${user.name}")
    private String a;
    @Autowired
    private UserMapper userMapper;

    public void update() {
        userMapper.update();
        System.out.println(a);
    }
}
