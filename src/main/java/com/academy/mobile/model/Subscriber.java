package com.academy.mobile.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;

@JacksonXmlRootElement(localName = "subscriber")
@Table
@Entity(name="subscriber")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable=false)
    private String firstName;

    @Column(nullable=false)
    private String lastName;

    @Column(nullable=false)
    private int age;

    @Column(nullable=false)
    @Convert(converter=GenderConverter.class)
    // TODO
//    @Check(constraints = "gender in ('m', 'f')")
    private Gender gender;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    // Builder
    public Subscriber withId(long id) {
        this.id = id;
        return this;
    }

    public Subscriber withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Subscriber withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Subscriber withGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public Subscriber withAge(int age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
