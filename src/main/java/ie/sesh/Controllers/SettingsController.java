package ie.sesh.Controllers;

import ie.sesh.Http.HttpHandler;
import ie.sesh.Utils.CookieUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static ie.sesh.Utils.SeshConstants.SESH_COOKIE_NAME;

@Controller
public class SettingsController {

    private static final Logger log = Logger.getLogger(SettingsController.class);

    @Autowired
    HttpHandler http;

    @Autowired
    CookieUtils cookieUtils;

    @RequestMapping("/settings")
    public ModelAndView getSettings(@CookieValue(name=SESH_COOKIE_NAME, defaultValue = "") String cookie,
                                         @CookieValue(name="ul", defaultValue = "") String userId) throws Exception{
        if(cookie == null || cookie.isEmpty()){
            cookie = "";
        }
        return cookieUtils.loggedInRedirect(cookie,userId,"Settings/settings");
    }
}
