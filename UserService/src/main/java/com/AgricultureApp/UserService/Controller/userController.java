package com.AgricultureApp.UserService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.AgricultureApp.UserService.Service.UserService;
import com.AgricultureApp.UserService.models.UserModel;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class userController {
     
	@Autowired
    private  UserService userService;

    
	@GetMapping("/Authenticate/{userName}")
	public UserModel authenticate(@PathVariable String userName) {
		
		return userService.findByUserName(userName);
		
	}
    
    

    @GetMapping("/getall")
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/adduser")
    public UserModel addUser(@RequestBody UserModel user) {
        return userService.addUser(user);
    }

    
    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<String> deleteUser(@PathVariable String Id) {
        try {
            return new ResponseEntity<String>(userService.deleteById(Id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/getbyid/{id}")
    public Optional<UserModel> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }
@GetMapping("/getbyusertype/{userType}")
public Optional<UserModel> getUsersByUserType(@PathVariable String userType) {
	return userService.getUsersByUserType(userType);
}





@PutMapping("/update")
public ResponseEntity<UserModel> updateFarmer(@RequestBody UserModel s) {
    try {
        return new ResponseEntity<>(userService.updateUser(s), HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}


    

}
