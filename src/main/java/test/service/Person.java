package test.service;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PERSON")
public class Person {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Name", length = 64, nullable = false)
    private String name;

    @Column(name = "Surname", length = 64, nullable = false)
    private String surname;

    @Temporal(TemporalType.DATE)
    @Column(name = "Date_Of_Birth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "Email", length = 64, nullable = false, unique = true)
    private String email;

    @Column(name = "Password", length = 64, nullable = false)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
