package com.example.workshopmongospring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.workshopmongospring.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
