package com.hust.dces.ServiceImpl;

import com.hust.dces.Entity.Document;
import com.hust.dces.Entity.User;
import com.hust.dces.Mapper.UserMapper;
import com.hust.dces.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<User> checkUserByUserID(Integer userID) {
        return userMapper.checkUserByUserID(userID);
    }

    @Override
    public User findUserByUserID(Integer userID) {
        return userMapper.findUserByUserID(userID);
    }

    @Override
    public Integer updateUserByID(User user) {
        return userMapper.updateUserByID(user);
    }

    @Override
    public Integer getFileCountByUserID(Integer userID) {
        return userMapper.getFileCountByUserID(userID);
    }

    @Override
    public Integer getAppealCountByUserID(Integer userID) {
        return userMapper.getAppealCountByUserID(userID);
    }

    @Override
    public List<Document> findDocByUser() {
        return userMapper.findDocByUser();
    }

}
