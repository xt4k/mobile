package com.academy.mobile.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "subscriber")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subscriber {
    private long id;
    private String firstName;
    private String lastName;
    private int age;
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
}
