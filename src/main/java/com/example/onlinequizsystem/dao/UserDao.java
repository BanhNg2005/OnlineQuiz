package com.example.onlinequizsystem.dao;

import com.example.onlinequizsystem.model.User;
import java.util.List;
public interface UserDao {

    //find user base on id
    // return the user or null if not found
    User findById(int id);

    // get all user
    // return list of user
    List<User> findAll();

    //save user information
    // return true if successful
    boolean save(User user);

    // delete user
    // return true if successful
    boolean delete(int id);

}
