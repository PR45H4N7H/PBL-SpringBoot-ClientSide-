package com.project.Repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.Model.Users;





@Repository
public interface UsersRepo extends MongoRepository<Users, String> {

	List<Users> findAll();

}