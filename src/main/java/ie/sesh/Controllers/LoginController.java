package ie.sesh.Controllers;

import ie.sesh.Http.HttpHandler;
import ie.sesh.Utils.CookieUtils;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

import static ie.sesh.Utils.SeshConstants.SESH_COOKIE_NAME;

@Controller
public class LoginController {

    private static final Logger log = Logger.getLogger(LoginController.class);

    @Autowired
    HttpHandler http;

    @Autowired
    CookieUtils cookieUtils;

    @RequestMapping("/")
    public ModelAndView getLogin(@CookieValue(name=SESH_COOKIE_NAME, defaultValue = "") String cookie,
                                 @CookieValue(name="ul", defaultValue = "") String userId) throws Exception{
        if(cookie == null || cookie.isEmpty()){
            cookie = "";
        }
        return cookieUtils.loggedInRedirect(cookie,userId,"App/application");
    }
    @RequestMapping("/Logout")
    public String getLogout(@CookieValue(name=SESH_COOKIE_NAME, defaultValue = "") String cookie, HttpServletResponse response) throws Exception{
        if(cookie == null || cookie.isEmpty()){
            return "Login/Login";
        }
        cookieUtils.cookieLogout(response);
        return "Login/Login";
    }

    @RequestMapping("/notifications")
    public ModelAndView getNotifications(@CookieValue(name=SESH_COOKIE_NAME, defaultValue = "") String cookie,
                                   @CookieValue(name="ul", defaultValue = "") String userId) throws Exception{
        if(cookie == null || cookie.isEmpty()){
            cookie = "";
        }
        return cookieUtils.loggedInRedirect(cookie,userId,"Notifications/notifications");
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody String data, HttpServletResponse response) throws Exception{
        log.info(data);
        JSONObject obj = new JSONObject(data);

        String username = obj.getString("username");
        String password = obj.getString("password");

        return http.login(username,password,response);
    }

    @PostMapping("/check/login")
    @ResponseBody
    public String checkLogin(@RequestBody String cookie_data) throws Exception{
        log.info(cookie_data);
        JSONObject obj = new JSONObject(cookie_data);

        String cookie = obj.getString(SESH_COOKIE_NAME);

        return http.checkLogin(cookie);
    }



}
