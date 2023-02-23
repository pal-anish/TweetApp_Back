package com.tweetapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.domain.Comments;


public interface CommentRepository extends MongoRepository<Comments, String>{

}
