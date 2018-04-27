package ie.sesh.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StatusController {

    @PostMapping("/get/all/status")
    @ResponseBody
    public String getAllStatus(@CookieValue(name="ul",defaultValue = "") String id, Model model){
        return "";
    }

    @PostMapping("/create/status")
    @ResponseBody
    public String createStatus(@CookieValue(name="ul",defaultValue = "") String id, Model model){
        return "";
    }

    @PostMapping("/delete/status")
    @ResponseBody
    public String deleteStatus(@CookieValue(name="ul",defaultValue = "") String id, Model model){
        return "";
    }

    @PostMapping("/update/status")
    @ResponseBody
    public String updateStatus(@CookieValue(name="ul",defaultValue = "") String id, Model model){
        return "";
    }
}
