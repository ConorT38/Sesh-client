package ie.sesh.Utils;

import ie.sesh.Http.HttpHandler;
import ie.sesh.Model.Status;

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
public class StatusUtils {

    private static final Logger log = Logger.getLogger(StatusUtils.class);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS+SSSS");

    @Autowired
    HttpHandler http;

    public List<Status> getAllStatuses(String status_data, String cookie){
        log.info("Status data: "+status_data);
        status_data = filterStatusResponse(status_data);

        log.info("Filtered data: "+status_data);

        if(status_data.equals("[]")){
            return null;
        }

        JSONArray arr = new JSONArray(status_data);
        JSONObject ob = new JSONObject(arr.get(0));
        log.info("Filtered array: "+arr.toString());
        log.info("First index: "+arr.get(0).toString());

        List<Status> statuses = new ArrayList<Status>();

        for(int i=0; i<arr.length();i++){
            Status status = new Status();
            JSONObject obj = new JSONObject(arr.get(i).toString());
           log.info("NAMES LENGTH: "+obj.names().length());
            for(int j = 0; j<obj.names().length(); j++){

                String key = obj.names().getString(j);
                Object value = (! obj.get(obj.names().getString(j)).toString().isEmpty() || obj.get(obj.names().getString(j))!= null ) ?  obj.get(obj.names().getString(j)) : "";

                switch (key){
                    case "id":
                        status.setId((int)  checkNullCastType(value,0));
                        break;
                    case "user_id":
                        status.setUser_id((int)  checkNullCastType(value,0));
                        break;
                    case "name":
                        status.setName((String)  checkNullCastType(value,""));
                        break;
                    case "username":
                        status.setUsername((String)  checkNullCastType(value,""));
                        break;
                    case "message":
                        status.setMessage((String)  checkNullCastType(value,""));
                        break;
                    case "location":
                        status.setLocation((int) value);
                        break;
                    case "likes":
                        status.setLikes((int)value);
                        break;
                    case "liked":
                        status.setLiked((boolean) checkNullCastType(value,false));
                        break;
                    case "date":
                        try {
                            Long parseDate = dateFormat.parse(checkNullCastType(value, new Timestamp(new java.util.Date().getTime())).toString()).getTime();
                            status.setDate(new Timestamp(parseDate));
                        }catch (ParseException e){
                            log.error("Couldn't parse date, something went wrong");
                        }
                        break;
                    case "going":
                        status.setGoing((String) checkNullCastType(value,""));
                        break;
                    case "maybe":
                        status.setMaybe((String) checkNullCastType(value,""));
                        break;
                    case "not_going":
                        status.setNot_going((String) checkNullCastType(value,""));
                        break;
                }
                log.info("key = " + key + " value = " + value);
            }

            statuses.add(status);
        }
        return statuses;
    }



    public String filterStatusResponse(String status_data){
        log.info("Status "+status_data);
        if(status_data.length() <1){
            return "false";
        }
        status_data = status_data.substring(1, status_data.length()-1);
        log.info("Filtered response: "+status_data);

        String[] responseCheck = status_data.split(",", 2);
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
            return status_data;
            }
        }

        public Object checkNullCastType(Object value, Object cast){
            return (value == null || value.toString().equals("null")) ? cast : value;
        }
    }
