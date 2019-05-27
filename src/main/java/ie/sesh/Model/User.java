package ie.sesh.Model;

import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class User {

    private int id;
    private String name;
    private int age;
    private Date dob;

    private int location;
    private String favourite_drink;
    private float rating;

    private String gender;
    private int local_spot;
    private String username;
    private String profile_pic;

    public User(){}

    public User(int id, String name, int age, Date dob, int location, String favourite_drink, float rating, String gender, int local_spot, String username, String email, String password, String profile_pic) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dob = dob;
        this.location = location;
        this.favourite_drink = favourite_drink;
        this.rating = rating;
        this.gender = gender;
        this.local_spot = local_spot;
        this.username = username;
        this.profile_pic = profile_pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String getFavourite_drink() {
        return favourite_drink;
    }

    public void setFavourite_drink(String favourite_drink) {
        this.favourite_drink = favourite_drink;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getLocal_spot() {
        return local_spot;
    }

    public void setLocal_spot(int local_spot) {
        this.local_spot = local_spot;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }
}

