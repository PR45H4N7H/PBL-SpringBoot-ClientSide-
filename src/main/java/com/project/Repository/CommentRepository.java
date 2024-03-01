package com.project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.Model.Comments;
import com.project.Model.PostProjectsModel;


public interface CommentRepository extends MongoRepository<Comments, String> {
    List<Comments> findByProjectId(String projectId);

    
    
    //added these
    @Query("SELECT a FROM Comments a WHERE a.flags > 0")
	List<Comments> findByFlaggedComments();
}

