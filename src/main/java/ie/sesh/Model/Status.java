package ie.sesh.Model;

import java.sql.Date;

public class Status {

    private int id;
    private int user_id;

    private String message;
    private int location;
    private int likes;

    private Date date;

    // uses User ID
    private String going;
    private String maybe;
    private String not_going;

    public Status() {}

    public Status(int user_id, String message, int location, int likes, Date date, String going,String maybe, String not_going) {
        this.user_id = user_id;
        this.message = message;
        this.location = location;
        this.likes = likes;
        this.date = date;
        this.going = going;
        this.maybe = maybe;
        this.not_going = not_going;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGoing() {
        return going;
    }

    public void setGoing(String going) {
        this.going = going;
    }

    public String getMaybe() {
        return maybe;
    }

    public void setMaybe(String maybe) {
        this.maybe = maybe;
    }

    public String getNot_going() {
        return not_going;
    }

    public void setNot_going(String not_going) {
        this.not_going = not_going;
    }
}


