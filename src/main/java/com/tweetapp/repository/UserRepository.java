package com.tweetapp.repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.domain.Users;

public interface UserRepository extends MongoRepository<Users, String> {

	Optional<Users> findByUsername(String username);

	List<Users> findByUsernameContaining(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
