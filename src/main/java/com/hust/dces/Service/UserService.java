package com.hust.dces.Service;

import com.hust.dces.Entity.Document;
import com.hust.dces.Entity.User;

import java.util.List;

public interface UserService {

    Integer addUserInfo(User user);

    User loginuser(String username, String password);

    List<User> checkUserByUserID(Integer userID);

    User findUserByUserID(Integer userID);

    Integer updateUserByID(User user);

    Integer getFileCountByUserID(Integer userID);

    Integer getAppealCountByUserID(Integer userID);

    List<Document>findDocByUser();

}
