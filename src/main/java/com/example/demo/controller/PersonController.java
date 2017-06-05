package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Repository.PersonRepository;
import com.example.demo.modal.Person;

@RestController
@RequestMapping("/people")
public class PersonController {
    @Autowired private PersonRepository personRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Person> listPeople() {
        return personRepository.findAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable("id") Long id) {
        return personRepository.findOne(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createPerson(@RequestBody Person person) {
        personRepository.save(new Person(person.getFirstName(), person.getLastName()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,  produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePerson(@PathVariable("id") Long id, @RequestBody Person person) {
        Person existingPerson = personRepository.findOne(id);
        existingPerson.setFirstName(person.getFirstName());
        existingPerson.setLastName(person.getLastName());
        personRepository.save(existingPerson);
    }
}