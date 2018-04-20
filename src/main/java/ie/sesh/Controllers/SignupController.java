package ie.sesh.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignupController {

    @RequestMapping("/Signup")
    public String getSignup(){
        return "Signup/Signup";
    }
}
