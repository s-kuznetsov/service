package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
public class MainController {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private PersonDAO personDAO;

    @Autowired
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    //http://localhost:8080/persons
    //curl http://localhost:8080/persons
    @GetMapping("/persons")
    public ResponseEntity<Iterable<Person>> getPersons() {
        Iterable<Person> persons = personDAO.findAll();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    //http://localhost:8080/add
    /*{
        "name": "Anton",
        "surname": "Markov",
        "dateOfBirth": "1989-01-21",
        "email": "mark@gmail.com",
        "password": "mark"
    }*/
    /*curl -i -X POST -H "Content-Type:application/json" -d "{ \"name\": \"Petya\", \"surname\": \"Melnikov\", \"password\": \"123456\", \"email\": \"melnik@gmail.com\" , \"dateOfBirth\": \"1989-01-21\"}" http://localhost:8080/add*/
    @PostMapping(value = "/add")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        String hashedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(hashedPassword);
        personDAO.save(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    //http://localhost:8080/delete/1
    //curl -X DELETE http://localhost:8080/delete/1
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Serializable> deleteCustomer(@PathVariable Long id) {
        try {
            personDAO.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("No Person found for id " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    //http://localhost:8080/get/ivanov@gmail.com
    //curl http://localhost:8080/get/ivanov@gmail.com
    @GetMapping("/get/{email}")
    public ResponseEntity<Object> getPersonByEmail(@PathVariable("email") String email) {

        Person person = personDAO.getByEmail(email);
        if (person == null) {
            return new ResponseEntity<>("No Person found for Email " + email, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}
