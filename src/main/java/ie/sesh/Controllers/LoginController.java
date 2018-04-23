package ie.sesh.Controllers;

import ie.sesh.Http.HttpHandler;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    HttpHandler http;

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

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody String data) throws Exception{
        System.out.println(data);
        JSONObject obj = new JSONObject(data);

        String username = obj.getString("username");
        String password = obj.getString("password");

        return http.login(username,password);
    }



}
