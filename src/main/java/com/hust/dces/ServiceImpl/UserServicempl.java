package com.hust.dces.ServiceImpl;

import com.hust.dces.Entity.User;
import com.hust.dces.Mapper.UserMapper;
import com.hust.dces.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicempl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer addUserInfo(User user) {
        return userMapper.addUserInfo(user);
    }

    @Override
    public User loginuser(String username, String password) {
        return userMapper.loginuser(username, password);
    }
}