package ie.sesh.Utils;

import ie.sesh.Http.HttpHandler;
import ie.sesh.Model.Notification;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationUtils {
    private static final Logger log = Logger.getLogger(NotificationUtils.class);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS+SSSS");

    @Autowired
    HttpHandler http;

    public List<Notification> getNotifications(String notification_data,String cookie){
        log.info("Notification data: "+notification_data);
        notification_data = filterNotificationResponse(notification_data);

        log.info("Filtered data: "+notification_data);

        if(notification_data.equals("[]")){
            return null;
        }

        JSONArray arr = new JSONArray(notification_data);
        JSONObject ob = new JSONObject(arr.get(0));
        log.info("Filtered array: "+arr.toString());
        log.info("First index: "+arr.get(0).toString());

        List<Notification> notifications = new ArrayList<Notification>();

        for(int i=0; i<arr.length();i++){
            Notification notification = new Notification();
            JSONObject obj = new JSONObject(arr.get(i).toString());
            log.info("NAMES LENGTH: "+obj.names().length());
            for(int j = 0; j<obj.names().length(); j++){

                String key = obj.names().getString(j);
                Object value = (! obj.get(obj.names().getString(j)).toString().isEmpty() || obj.get(obj.names().getString(j))!= null ) ?  obj.get(obj.names().getString(j)) : "";

                switch (key){
                    case "id":
                        notification.setId((int)  checkNullCastType(value,0));
                        break;
                    case "user_id":
                        notification.setUser_id((int)  checkNullCastType(value,0));
                        break;
                    case "source_users":
                        notification.setSource_users(splitStringToIntArr((String) checkNullCastType(value,"")));
                        break;
                    case "type":
                        notification.setNotification_type((String)  checkNullCastType(value,""));
                        break;
                    case "data":
                        notification.setNotification_data((String)  checkNullCastType(value,""));
                        break;
                    case "hidden":
                        notification.setHide_notification((boolean)  checkNullCastType(value,false));
                        break;
                    case "read":
                        notification.setRead_notification((boolean)  checkNullCastType(value,false));
                        break;
                    case "date":
                        try {
                            Long parseDate = dateFormat.parse(checkNullCastType(value, new Timestamp(new java.util.Date().getTime())).toString()).getTime();
                            notification.setDate(new Timestamp(parseDate));
                        }catch (ParseException e){
                            log.error("Couldn't parse date, something went wrong");
                        }
                        break;
                }

            }
            notifications.add(notification);
        }
        return notifications;
    }

    public String filterNotificationResponse(String notification_data){
        log.info("Notification: "+notification_data);
        if(notification_data.length() <1){
            return "false";
        }
        notification_data = notification_data.substring(1, notification_data.length()-1);
        log.info("Filtered response: "+notification_data);

        String[] responseCheck = notification_data.split(",", 2);
        log.info("Returned with response code: "+responseCheck[0]);

        if(responseCheck.length <1){
            return "false";
        }

        // If the cookie is a json response
        if(responseCheck[1].charAt(0) == '['){
            responseCheck[1] = responseCheck[1].replaceAll("\\]\\,(.*)","]");
            log.info("Response Data: "+ responseCheck[1]);
            return responseCheck[1];
        }
        else{
            return notification_data;
        }
    }

    public String buildNotificationData(int id, String notification_data, String notification_type){
    //TODO: Extract user_id and then the relevant id of status/follow/like etc from notification_data, and then utilise the notification type
        return "";
    }

    public Object checkNullCastType(Object value, Object cast){
        return (value == null || value.toString().equals("null")) ? cast : value;
    }

    public static int[] splitStringToIntArr(String str){
        String[] strArr = str.split(",");
        int[] numArr = new int[strArr.length];
        for(int i=0; i<strArr.length; i++){
            numArr[i] = Integer.parseInt(strArr[i]);
        }
        return numArr;
    }
}
