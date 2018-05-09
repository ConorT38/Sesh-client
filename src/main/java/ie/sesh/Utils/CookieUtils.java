package ie.sesh.Utils;

import ie.sesh.Http.HttpHandler;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class CookieUtils {

    private static final Logger log = Logger.getLogger(CookieUtils.class);

    public static final int HTTP_RESPONSE = 0;
    public static final int COOKIE_VALUE = 1;
    public static final int CONTENT_TYPE = 2;

    @Autowired
    HttpHandler http;

    public void addCookie(String name, String value, int lifetime, HttpServletResponse response){
        Cookie seshCookie = new Cookie(name, value);
        seshCookie.setMaxAge(lifetime);

        response.addCookie(seshCookie);

        log.info("Cookie "+value+" added");
    }


    public String filterCookieResponse(String cookie, int index){
        log.info("COOKIIE "+cookie);
        if(cookie.length() <1){
            return "false";
        }
        cookie = cookie.substring(1, cookie.length()-1);
        log.info("Filtered response: "+cookie);

        String[] responseCheck = cookie.split(",", 2);
        log.info("Returned with response code: "+responseCheck[0]);

        if(responseCheck.length <1){
            return "false";
        }

        // If the cookie is a json response
        if(responseCheck[1].charAt(0) == '{'){
            responseCheck[1] = responseCheck[1].replaceAll("\\}\\,(.*)","}");
            log.info("Response Data: "+ responseCheck[1]);
            return responseCheck[1];
        }
        else{
            // Else if it is a comma-less datatype
            String[] response = cookie.split(",", 3);
            switch (index){
                case HTTP_RESPONSE:
                    return response[HTTP_RESPONSE];
                case COOKIE_VALUE:
                    return response[COOKIE_VALUE];
                case CONTENT_TYPE:
                    return response[CONTENT_TYPE];
                default:
                    return cookie;
            }
        }
    }

    public ModelAndView loggedInRedirect(String cookie, String userId, String path) throws Exception{
        log.info("COOKIE: "+cookie);
        Map<String,String> map = new HashMap<String,String>();

        if(!cookie.isEmpty() && cookie != null) {
            if (filterCookieResponse(http.checkLogin(cookie), CookieUtils.COOKIE_VALUE).equals("false")) {
                return path.equals("Signup/Signup") ? new ModelAndView("Signup/Signup",map): new ModelAndView("Login/Login",map);
            } else {
                String response = http.load(Integer.parseInt(userId),"","/get/user");

                log.info("User details: "+filterCookieResponse(response,CookieUtils.COOKIE_VALUE)+"");
                JSONObject obj = new JSONObject(filterCookieResponse(response,CookieUtils.COOKIE_VALUE));

                for(int i = 0; i<obj.names().length(); i++){

                    String key = obj.names().getString(i);
                    String value = obj.get(obj.names().getString(i)).toString();

                    log.info("key = " + key + " value = " + value);
                    map.put( obj.names().getString(i),obj.get(obj.names().getString(i)).toString());
                }

                return new ModelAndView(path,"user",map);
            }
        }
        return path.equals("Signup/Signup") ? new ModelAndView("Signup/Signup",map): new ModelAndView("Login/Login",map);
    }

    public void cookieLogout(HttpServletResponse response){
        Cookie seshCookie = new Cookie("sesh", "");
        Cookie userIdCookie = new Cookie("ul", "");

        seshCookie.setMaxAge(0);
        userIdCookie.setMaxAge(0);

        response.addCookie(seshCookie);
        response.addCookie(userIdCookie);

        log.info("Cookies deleted");
    }

    public String getUserIdCookie(JSONArray cookieJSON){
        if(cookieJSON.length()<1){
            return "";
        }
            JSONObject jsonObject = cookieJSON.getJSONObject(0);
            Iterator<String> keys = jsonObject.keys();
            String key = keys.next();

            return key;

    }

    public String getTokenCookie(JSONArray cookieJSON){
        if(cookieJSON.length()<1){
            return "";
        }

        JSONObject jsonObject = cookieJSON.getJSONObject(0);
        Iterator<String> keys = jsonObject.keys();
        String id = keys.next();
        Object token = jsonObject.get(id);

        return token.toString();
    }
}
