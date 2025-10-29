package com.infinite.employee_manager.Services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.infinite.employee_manager.Models.User;

@Service
public class UsersService {
    List<User> usersDB = Arrays.asList(
        new User(1L,"testUser","test1","testPass","USER"),
        new User(2L,"Ahmad","Ahmad99","ahmad123q","USER"),
        new User(3L,"Admin","admin","admin123","ADMIN"),
        new User(4L,"Tuqa","TuqaM","123Tuqa123","USER"),
        new User(5L,"Admin2","admin2","admin123","ADMIN")
    );

    public List<User> getUsersDB() {
        return usersDB;
    }

    public void setUsersDB(List<User> usersDB) {
        this.usersDB = usersDB;
    }

    public User findByUsername(String username){
        User res = null;
        for (User user : usersDB) {
            if(user.getUsername().equals(username)){
                res =  user;
            } 
        }
        return res;
    }

    
}
