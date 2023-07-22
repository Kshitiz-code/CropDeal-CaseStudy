package com.AgricultureApp.UserService.Service;
import com.AgricultureApp.UserService.Repository.UserRepository;
import com.AgricultureApp.UserService.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
    private final UserRepository userRepository;

    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public UserModel findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel addUser(UserModel user) {
        return userRepository.save(user);
    }



    public Optional<UserModel> getUserById(String id) {
        return userRepository.findById(id);
    }
    
    public Optional<UserModel> getUsersByUserType(String userType) {
        return userRepository.findByUserType(userType);
    }

    public UserModel updateUser(UserModel F) {
        
        return userRepository.save(F);
    }

    public String deleteById(String Id) {

        if (Checkexits(Id)) {
            userRepository.deleteById(Id);
            return "Deleted SuccessFully";
        }
        else {
            return "UserNotFound";
        }
    }

	private boolean Checkexits(String Id) {
		// TODO Auto-generated method stub
		return userRepository.existsById(Id);
	}

    

}