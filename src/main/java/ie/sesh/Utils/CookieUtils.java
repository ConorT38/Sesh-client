package ie.sesh.Utils;

import ie.sesh.Http.HttpHandler;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

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
        log.info(cookie);

        String[] response = cookie.split(",", 3);
        log.info(response[0]);

        if(response.length <1){
            return "false";
        }
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

    public String loggedInRedirect(String cookie, String path) throws Exception{
        log.info("COOKIE: "+cookie);

        if(!cookie.isEmpty() && cookie != null) {
            if (filterCookieResponse(http.checkLogin(cookie), CookieUtils.COOKIE_VALUE).equals("false")) {
                return path;
            } else {
                return "App/application";
            }
        }
        return path;
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
