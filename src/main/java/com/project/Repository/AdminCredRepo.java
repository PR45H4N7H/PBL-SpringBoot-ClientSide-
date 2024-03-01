package com.project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.Model.AdminCred;
import com.project.Model.Bearcat;





@Repository
public interface AdminCredRepo extends MongoRepository<AdminCred, String> {

	 AdminCred findByUsrName(String usrName);
}