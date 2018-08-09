package ie.sesh.Model;

import java.sql.Timestamp;

public class Notification {

    private int id;
    private int user_id;
    private int[] source_users;

    private String notification_type;
    private String notification_data;
    private boolean hide_notification;
    private boolean read_notification;

    private Timestamp date;

    public Notification() {
    }

    public Notification(int id, int user_id, int[] source_users, String notification_type, String notification_data, boolean hide_notification, boolean read_notification, Timestamp date) {
        this.id = id;
        this.user_id = user_id;
        this.source_users = source_users;
        this.notification_type = notification_type;
        this.notification_data = notification_data;
        this.hide_notification = hide_notification;
        this.read_notification = read_notification;
        this.date = date;
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

    public int[] getSource_users() {
        return source_users;
    }

    public void setSource_users(int[] source_users) {
        this.source_users = source_users;
    }

    public String getNotification_type() {
        return notification_type;
    }

    public void setNotification_type(String notification_type) {
        this.notification_type = notification_type;
    }

    public String getNotification_data() {
        return notification_data;
    }

    public void setNotification_data(String notification_data) {
        this.notification_data = notification_data;
    }

    public boolean isHide_notification() {
        return hide_notification;
    }

    public void setHide_notification(boolean hide_notification) {
        this.hide_notification = hide_notification;
    }

    public boolean isRead_notification() {
        return read_notification;
    }

    public void setRead_notification(boolean read_notification) {
        this.read_notification = read_notification;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
