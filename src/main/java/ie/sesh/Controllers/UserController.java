package ie.sesh.Controllers;

import ie.sesh.Http.HttpHandler;
import ie.sesh.Utils.CookieUtils;
import ie.sesh.Utils.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

import static ie.sesh.Utils.SeshConstants.SESH_COOKIE_NAME;

@Controller
public class UserController {

    private static final Logger log = Logger.getLogger(LoginController.class);

    @Autowired
    HttpHandler http;

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    UserUtils userUtils;

    @RequestMapping("/profile")
    public ModelAndView getProfile(@CookieValue(name=SESH_COOKIE_NAME, defaultValue = "") String cookie,
                                 @CookieValue(name="ul", defaultValue = "") String userId) throws Exception{
        if(cookie == null || cookie.isEmpty()){
            cookie = "";
        }
        return cookieUtils.loggedInRedirect(cookie,userId,"Profile/profile");
    }

    @GetMapping("/get/recommended/users")
    public ModelAndView getRecommendedUsers(@CookieValue(name="ul", defaultValue = "") String userId,
                                            Model model) throws Exception{
        model.addAttribute("recommendedUsers",userUtils.getAllUsers(http.load(Integer.parseInt(userId),"","/get/recommended/users")));
        return new ModelAndView("fragments/recommendedContainer :: recommendedContainer");
    }

    @PostMapping("/follow/user")
    @ResponseBody
    public String followUser(@RequestBody String user_data,
                               @CookieValue(name="ul",defaultValue = "") String id){
        log.info("Following User: "+user_data);

        return http.create(Integer.parseInt(id),user_data,"/follow/user");
    }
}
