package com.hust.dces.Service;

import com.hust.dces.Entity.User;

public interface UserService {

    Integer addUserInfo(User user);

    User loginuser(String username, String password);


}
