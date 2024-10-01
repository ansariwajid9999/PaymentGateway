package com.example.Payment_Gateway.Service;

import com.example.Payment_Gateway.CustomException.addUserException;
import com.example.Payment_Gateway.Model.User;
import com.example.Payment_Gateway.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String addUser(User user) throws Exception{
        if(user.getUserId() != null){
            throw new addUserException("user is already present in Database.");
        }

        userRepository.save(user);
        return "user has been successfully saved in Database.";
    }
}
