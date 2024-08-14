package ru.margarite.volkova.data;

import java.io.File;
import java.util.List;

public class Student {
    private final String firstName;
    private final String lastName;
    private String email;
    private final String gender;
    private final String phone;
    private String bithdayDate;
    private String subjects;
    private List<String> hobbies;
    private File picture;
    private String address;
    private String state;
    private String city;

    public Student(String firstName, String lastName, String email, String gender, String phone, String bithdayDate,
                   String subjects, List<String> hobbies, String picture, String address, String state, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.bithdayDate = bithdayDate;
        this.subjects = subjects;
        this.hobbies = hobbies;
        this.picture = new File("src/test/resources/pictures/" + picture);
        this.address = address;
        this.state = state;
        this.city = city;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getBithdayDate() {
        return bithdayDate;
    }

    public String getSubjects() {
        return subjects;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public File getPicture() {
        return picture;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", bithdayDate='" + bithdayDate + '\'' +
                ", subjects='" + subjects + '\'' +
                ", hobbies='" + hobbies + '\'' +
                ", picture=" + picture +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
