package com.tweetapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.domain.Tweets;

public interface TweetsRepository extends MongoRepository<Tweets, String> {

}
