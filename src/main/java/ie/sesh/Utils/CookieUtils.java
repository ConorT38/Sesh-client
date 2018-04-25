package ie.sesh.Utils;

import ie.sesh.Http.HttpHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Component
public class CookieUtils {

    public static final int HTTP_RESPONSE = 0;
    public static final int COOKIE_VALUE = 1;
    public static final int CONTENT_TYPE = 2;

    @Autowired
    HttpHandler http;


    public String filterCookieResponse(String cookie, int index){
        System.out.println("COOKIIE "+cookie);
        if(cookie.length() <1){
            return "false";
        }
        cookie = cookie.substring(1, cookie.length()-1);
        System.out.println(cookie);
        String[] response = cookie.split(",", 3);
        System.out.println(response[0]);
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
        System.out.println("COOKIE: "+cookie);
        System.out.println("COOKIE-Response: "+http.checkLogin(cookie));

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
        seshCookie.setMaxAge(0);

        response.addCookie(seshCookie);
    }
}
