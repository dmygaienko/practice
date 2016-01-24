package com.mygaienko.practice.cassandra.model;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/*import lombok.Data;
import lombok.NoArgsConstructor;*/

/**
 * Created by enda1n on 24.01.2016.
 */
/*@Data
@NoArgsConstructor*/
@Table(value = "users")
public class Person {

    private String firstName;
    private String lastName;

    @PrimaryKey("user_id")
    private Long id;

    @Column("uname")
    private String username;
    @Column("fname")
    private String firstname;
    @Column("lname")
    private String lastname;

    public Person() {
    }

    public Person(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
