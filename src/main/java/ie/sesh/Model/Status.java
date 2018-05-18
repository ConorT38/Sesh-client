package ie.sesh.Model;

import java.sql.Timestamp;

public class Status {

    private int id;
    private int user_id;
    private String name;
    private String username;

    private String message;
    private int location;
    private int likes;
    private boolean liked;

    private Timestamp date;

    // uses User ID
    private String going;
    private String maybe;
    private String not_going;

    public Status() {}

    public Status(int user_id, String name, String username, String message, int location, int likes, boolean liked, Timestamp date, String going,String maybe, String not_going) {
        this.user_id = user_id;
        this.name = name;
        this.username = username;
        this.message = message;
        this.location = location;
        this.likes = likes;
        this.liked = liked;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
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


