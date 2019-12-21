package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * PersonRepository
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

}