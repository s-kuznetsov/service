package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataInit implements ApplicationRunner {

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private PersonDAO personDAO;

    @Autowired
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = personDAO.count();

        if (count == 0) {
            Person p1 = new Person();
            p1.setName("Viktor");
            p1.setSurname("Petrov");
            p1.setEmail("petrov@gmail.com");
            String hashedPassword1 = passwordEncoder.encode("petrov");
            p1.setPassword(hashedPassword1);
            Date d1 = df.parse("1990-01-20");
            p1.setDateOfBirth(d1);

            Person p2 = new Person();
            p2.setName("Aleksey");
            p2.setSurname("Ivanov");
            p2.setEmail("ivanov@gmail.com");
            String hashedPassword2 = passwordEncoder.encode("ivanov");
            p2.setPassword(hashedPassword2);
            Date d2 = df.parse("1991-01-21");
            p2.setDateOfBirth(d2);

            personDAO.save(p1);
            personDAO.save(p2);
        }
    }
}
