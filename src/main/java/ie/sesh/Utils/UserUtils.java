package ie.sesh.Utils;

import ie.sesh.Model.User;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserUtils {

    private static final Logger log = Logger.getLogger(UserUtils.class);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS+SSSS");

    public List<User> getAllUsers(String user_data){
        log.info("User data: "+user_data);
        user_data = filterUserResponse(user_data);

        log.info("Filtered data: "+user_data);

        if(user_data.equals("[]")){
            return null;
        }

        JSONArray arr = new JSONArray(user_data);
        JSONObject ob = new JSONObject(arr.get(0));
        log.info("Filtered array: "+arr.toString());
        log.info("First index: "+arr.get(0).toString());

        List<User> users = new ArrayList<User>();

        for(int i=0; i<arr.length();i++){
            User user = new User();
            JSONObject obj = new JSONObject(arr.get(i).toString());
            log.info("NAMES LENGTH: "+obj.names().length());
            for(int j = 0; j<obj.names().length(); j++){

                String key = obj.names().getString(j);
                Object value = (! obj.get(obj.names().getString(j)).toString().isEmpty() || obj.get(obj.names().getString(j))!= null ) ?  obj.get(obj.names().getString(j)) : "";

                switch (key){
                    case "id":
                        user.setId((int)  checkNullCastType(value,0));
                        break;
                    case "name":
                        user.setName((String)  checkNullCastType(value,""));
                        break;
                    case "age":
                        user.setAge((int)  checkNullCastType(value,0));
                        break;
                    case "rating":
                        user.setRating((float)  checkNullCastType(value,0.0));
                        break;
                    case "location":
                        user.setLocation((int)  checkNullCastType(value,0));
                        break;
                    case "favourite_drink":
                        user.setFavourite_drink((String)  checkNullCastType(value,""));
                        break;
                    case "dob":
                        try {
                            Long parseDate = dateFormat.parse(checkNullCastType(value, new Timestamp(new java.util.Date().getTime())).toString()).getTime();
                            user.setDob(new Date(parseDate));
                        }catch (ParseException e){
                            log.error("Couldn't parse date, something went wrong");
                        }
                        break;
                    case "username":
                        user.setUsername((String) checkNullCastType(value,""));
                        break;
                    case "gender":
                        user.setGender((String) checkNullCastType(value,""));
                        break;
                    case "local_spot":
                        user.setLocal_spot((int) checkNullCastType(value,0));
                        break;
                }
                log.info("key = " + key + " value = " + value);
            }
            users.add(user);
        }
        return users;
    }



    public String filterUserResponse(String user_data){
        log.info("User "+user_data);
        if(user_data.length() <1){
            return "false";
        }
        user_data = user_data.substring(1, user_data.length()-1);
        log.info("Filtered response: "+user_data);

        String[] responseCheck = user_data.split(",", 2);
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
            return user_data;
        }
    }

    public Object checkNullCastType(Object value, Object cast){
        return (value == null || value.toString().equals("null")) ? cast : value;
    }
}
