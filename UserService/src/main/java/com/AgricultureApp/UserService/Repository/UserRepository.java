package com.AgricultureApp.UserService.Repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.AgricultureApp.UserService.models.UserModel;


@EnableMongoRepositories
public interface UserRepository extends  MongoRepository<UserModel,String> {
	Optional<UserModel> findByUserType(String userType);

	UserModel findByUserName(String userName);
	

}
