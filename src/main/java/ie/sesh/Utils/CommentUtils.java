package ie.sesh.Utils;

import ie.sesh.Model.Comment;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommentUtils {

    private static final Logger log = Logger.getLogger(CommentUtils.class);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS+SSSS");

    public List<Comment> getComments(String comment_data){
        log.info("Comment data: "+comment_data);
        comment_data = filterCommentResponse(comment_data);

        log.info("Filtered data: "+comment_data);

        if(comment_data.equals("[]")){
            return null;
        }

        JSONArray arr = new JSONArray(comment_data);
        JSONObject ob = new JSONObject(arr.get(0));
        log.info("Filtered array: "+arr.toString());
        log.info("First index: "+arr.get(0).toString());

        List<Comment> comments = new ArrayList<Comment>();

        for(int i=0; i<arr.length();i++){
            Comment comment = new Comment();
            JSONObject obj = new JSONObject(arr.get(i).toString());
            log.info("NAMES LENGTH: "+obj.names().length());
            for(int j = 0; j<obj.names().length(); j++){

                String key = obj.names().getString(j);
                Object value = (! obj.get(obj.names().getString(j)).toString().isEmpty() || obj.get(obj.names().getString(j))!= null ) ?  obj.get(obj.names().getString(j)) : "";

                switch (key){
                    case "id":
                        comment.setId((int)  checkNullCastType(value,0));
                        break;
                    case "user_id":
                        comment.setUser_id((int)  checkNullCastType(value,0));
                        break;
                    case "status_id":
                        comment.setStatus_id((int)  checkNullCastType(value,0));
                        break;
                    case "name":
                        comment.setName((String)  checkNullCastType(value,""));
                        break;
                    case "username":
                        comment.setUsername((String)  checkNullCastType(value,""));
                        break;
                    case "message":
                        comment.setMessage((String)  checkNullCastType(value,""));
                        break;
                    case "likes":
                        comment.setLikes((int)value);
                        break;
                    case "date":
                        try {
                            Long parseDate = dateFormat.parse(checkNullCastType(value, new Timestamp(new java.util.Date().getTime())).toString()).getTime();
                            comment.setDate(new Timestamp(parseDate));
                        }catch (ParseException e){
                            log.error("Couldn't parse date, something went wrong");
                        }
                        break;
                }
                log.info("key = " + key + " value = " + value);
            }
            comments.add(comment);
        }
        return comments;
    }

    public String filterCommentResponse(String comment_data){
        log.info("Comment "+comment_data);
        if(comment_data.length() <1){
            return "false";
        }
        comment_data = comment_data.substring(1, comment_data.length()-1);
        log.info("Filtered response: "+comment_data);

        String[] responseCheck = comment_data.split(",", 2);
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
            return comment_data;
        }
    }

    public Object checkNullCastType(Object value, Object cast){
        return (value == null || value.toString().equals("null")) ? cast : value;
    }
}
