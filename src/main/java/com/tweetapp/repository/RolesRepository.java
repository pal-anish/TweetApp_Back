package com.tweetapp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.domain.ERole;
import com.tweetapp.domain.Role;

public interface RolesRepository extends MongoRepository<Role, String> {

	Optional<Role> findByName(ERole name);

}
