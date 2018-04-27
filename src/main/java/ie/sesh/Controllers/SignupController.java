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

@Controller
public class SignupController {

    private static final Logger log = Logger.getLogger(SignupController.class);

    @Autowired
    HttpHandler http;

    @Autowired
    CookieUtils cookieUtils;

    @RequestMapping("/Signup")
    public ModelAndView getSignup(@CookieValue(name="sesh", defaultValue = "") String cookie,
                                  @CookieValue(name="ul",defaultValue = "") String id) throws Exception{
        return cookieUtils.loggedInRedirect(cookie,id,"Signup/Signup");
    }

    @PostMapping("/register/user")
    @ResponseBody
    public String registerUser(@RequestBody String user_data, HttpServletResponse response) throws Exception{
        log.info(user_data);
        JSONObject obj = new JSONObject(user_data);

        String username = obj.getString("username");
        String password = obj.getString("password");
        String name = obj.getString("name");
        String email = obj.getString("email");

        return http.signup(name,username,email,password,response);
    }
}
