package ie.sesh.Utils;

import ie.sesh.Model.Status;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class StatusUtils {
    private static final Logger log = Logger.getLogger(StatusUtils.class);

    public List<Status> getAllStatuses(String status_data){
        log.info("Status data: "+status_data);
        status_data = filterStatusResponse(status_data);

        log.info("Filtered data: "+status_data);

        JSONArray arr = new JSONArray(status_data);
        log.info("Filtered array: "+arr.toString());
        log.info("First index: "+arr.get(0).toString());
        log.info("First index message: "+new JSONObject(arr.get(0)).get("message").toString());

        List<Status> statuses = new ArrayList<Status>();

        for(int i=0; i<arr.length();i++){
            Status status = new Status();
            JSONObject obj = new JSONObject(arr.get(i));

            status.setMessage((String) obj.get("message"));
            status.setLocation((int) obj.get("location"));
            status.setLikes((int) obj.get("likes"));
            status.setDate((Date) obj.get("date"));
            status.setGoing((String) obj.get("going"));
            status.setMaybe((String) obj.get("maybe"));
            status.setNot_going((String) obj.get("not_going"));

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
    }
