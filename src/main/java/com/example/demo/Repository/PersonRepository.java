package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.modal.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {}
