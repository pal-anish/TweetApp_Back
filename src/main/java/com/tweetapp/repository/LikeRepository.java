package com.tweetapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.domain.Likes;

public interface LikeRepository extends MongoRepository<Likes, String> {

}
