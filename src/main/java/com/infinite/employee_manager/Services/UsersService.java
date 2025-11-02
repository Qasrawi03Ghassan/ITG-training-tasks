package com.infinite.employee_manager.Services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.infinite.employee_manager.Models.User;
import com.infinite.employee_manager.Repositories.UserRepository;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    public UsersService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find user with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole()))
        );
    }

    List<User> usersDB = Arrays.asList(

        /*
        new User(1L,"TestUser","test1","testPass","USER"),
        new User(2L,"Ahmad","Ahmad99","ahmad123q","USER"),
        new User(3L,"Administrator","admin","admin123","ADMIN"),
        new User(4L,"Tuqa","TuqaM","123Tuqa123","USER"),
        new User(5L,"Admin2","admin2","admin123","ADMIN")
        */
        
    );

    public List<User> getUsersDB() {
        return usersDB;
    }

    public void setUsersDB(List<User> usersDB) {
        this.usersDB = usersDB;
    }

    public User findByUsername(String username){
       /* User res = null;
        for (User user : usersDB) {
            if(user.getUsername().equals(username)){
                res =  user;
            } 
        }
        return res;*/

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Could not find user with username: " + username));
    }

    
}
