package test.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDAO extends CrudRepository<Person, Long> {
    Person getByEmail(String email);
}
