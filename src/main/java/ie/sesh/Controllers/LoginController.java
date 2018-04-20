package ie.sesh.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String getLogin(){
        return "Login/Login";
    }

    @RequestMapping("/app")
    public String getApp(){
        return "App/application";
    }

    @RequestMapping("/notifications")
    public String getNotifications(){
        return "Notifications/notifications";
    }

}
