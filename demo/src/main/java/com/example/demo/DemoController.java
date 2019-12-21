package com.example.demo;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoController
 */
@CrossOrigin
@RestController("/person")
public class DemoController {

    @Autowired
    PersonRepository repository;

    AtomicInteger counter = new AtomicInteger();

    @RequestMapping(method = RequestMethod.GET, path="/sayHi")
    public String sayHi(@RequestParam(name = "id") final Integer id) {
        Person person = getPersonByNameOrThrow(id);
        return "Hello " + person.getName();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Person addPerson(@RequestBody final Person person) {
        person.setId(counter.getAndIncrement());
        repository.save(person);
        return person;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Person getPerson(@RequestParam(name = "id") Integer id) {
        return getPersonByNameOrThrow(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public Iterable<Person> getAllPeople(){
        return repository.findAll();
    }

    private Person getPersonByNameOrThrow(final Integer id) {
        Optional<Person> person = repository.findById(id);
        if (!person.isPresent())
            throw new PersonNotFoundException();
        return person.get();
    }
}