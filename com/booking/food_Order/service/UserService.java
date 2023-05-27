package com.booking.food_Order.service;

import com.booking.food_Order.entity.Login;
import com.booking.food_Order.entity.User;
import com.booking.food_Order.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public User save(User user){

        String userName = user.getUserName();
        // String status = "saved";
        User getUser = userRepository.findByUserName(userName);
        if(getUser!=null){
        String getUserName = getUser.getUserName();
            if(!userName.equals(getUserName)){
                userRepository.save(user);
            }else{
                // status = "unsaved";
                return null;
            }
        }else{
            userRepository.save(user);
        }
        
        // return status;
        return user;
    }
    
    public String validateUser(Login login){

        String loginStatus = null;
        String userName=login.getUserName();
        User user = userRepository.findByUserName(userName);
        if(user!=null){
            if(user.getPassword().equals(login.getPassword())){
                loginStatus = user.getRole();
            }else{
                loginStatus = "passwordIsIncorrect";
            }
        }else{
            loginStatus = "userNotExists";
        } 

        return loginStatus;
    }

    public User getUserByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public void deleteByRestaurantId(long userId){
        userRepository.deleteByUserId(userId);
    }

    public long countByRole(String role){
        return userRepository.countByRole(role);
    }

    public List<User> findAllByRole(String role){
        return userRepository.findAllByRole(role);
    }

    public User findByUserId(long userId){
        return userRepository.findByUserId(userId);
    }
    
}
