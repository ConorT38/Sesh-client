package ie.sesh.Controllers;

import ie.sesh.Http.HttpHandler;
import ie.sesh.Utils.CookieUtils;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    HttpHandler http;

    @Autowired
    CookieUtils cookieUtils;

    @RequestMapping("/")
    public String getLogin(@CookieValue(name="sesh", defaultValue = "") String cookie) throws Exception{
        if(cookie == null || cookie.isEmpty()){
            cookie = "";
        }
        return cookieUtils.loggedInRedirect(cookie,"Login/Login");
    }
    @RequestMapping("/Logout")
    public String getLogout(@CookieValue(name="sesh", defaultValue = "") String cookie, HttpServletResponse response) throws Exception{
        if(cookie == null || cookie.isEmpty()){
            return "Login/Login";
        }
        cookieUtils.cookieLogout(response);
        return "Login/Login";
    }

    @RequestMapping("/notifications")
    public String getNotifications(){
        return "Notifications/notifications";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody String data, HttpServletResponse response) throws Exception{
        System.out.println(data);
        JSONObject obj = new JSONObject(data);

        String username = obj.getString("username");
        String password = obj.getString("password");

        return http.login(username,password,response);
    }

    @PostMapping("/check/login")
    @ResponseBody
    public String checkLogin(@RequestBody String cookie_data) throws Exception{
        System.out.println(cookie_data);
        JSONObject obj = new JSONObject(cookie_data);

        String cookie = obj.getString("sesh");

        return http.checkLogin(cookie);
    }



}
