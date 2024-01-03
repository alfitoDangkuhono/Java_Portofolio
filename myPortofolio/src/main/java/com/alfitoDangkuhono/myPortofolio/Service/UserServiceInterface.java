package com.alfitoDangkuhono.myPortofolio.Service;

import com.alfitoDangkuhono.myPortofolio.Model.User;

import java.util.List;

public interface UserServiceInterface {
    void deleteById(Integer id);
    List<User> getAllUser();
    User createUser(User user);
    void deleteAll();
    User updateUser(Integer id, User userDetails);

}
