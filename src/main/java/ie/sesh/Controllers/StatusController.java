package ie.sesh.Controllers;

import ie.sesh.Http.HttpHandler;
import ie.sesh.Model.Status;
import ie.sesh.Utils.CommentUtils;
import ie.sesh.Utils.CookieUtils;
import ie.sesh.Utils.StatusUtils;
import ie.sesh.Utils.UserUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
public class StatusController {

    private static final Logger log = Logger.getLogger(StatusController.class);

    @Autowired
    HttpHandler http;

    @Autowired
    StatusUtils statusUtils;

    @Autowired
    CommentUtils commentUtils;

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    UserUtils userUtils;

    @GetMapping("/get/all/status")
    @ResponseBody
    public ModelAndView getAllStatus(@CookieValue(name="ul",defaultValue = "") String id, Model model){
            model.addAttribute("statuses",statusUtils.getAllStatuses(http.load(Integer.parseInt(id),"","/get/all/status"),id));
            model.addAttribute("id",Integer.parseInt(id));
            return new ModelAndView("fragments/status :: status");
    }

    @GetMapping("/get/all/user/status")
    @ResponseBody
    public ModelAndView getAllUserStatus(@CookieValue(name="ul",defaultValue = "") String id, Model model){

        model.addAttribute("statuses",statusUtils.getAllStatuses(http.load(Integer.parseInt(id),"","/get/all/user/status"),id));
        model.addAttribute("id",Integer.parseInt(id));
        return new ModelAndView("fragments/status :: status");
    }

    @GetMapping("/get/all/user/status/@{username}")
    @ResponseBody
    public ModelAndView getAllUserProfileStatus(@CookieValue(name="ul",defaultValue = "") String id,
                                                @PathVariable("username") String username, Model model){

        model.addAttribute("statuses",statusUtils.getAllStatuses(http.load(Integer.parseInt(id),"","/get/all/user/status/@"+username),id));
        model.addAttribute("id",Integer.parseInt(id));
        return new ModelAndView("User/status :: status");
    }

    @PostMapping("/create/status")
    @ResponseBody
    public String createStatus(@RequestBody String status_data,
                               @CookieValue(name="ul",defaultValue = "") String id){
        log.info("Entered Status: "+status_data);

        return http.create(Integer.parseInt(id),status_data,"/create/status");
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

    @PostMapping("/create/comment")
    @ResponseBody
    public String createComment(@RequestBody String status_data,
                               @CookieValue(name="ul",defaultValue = "") String id){
        log.info("Entered Comment: "+status_data);

        return http.create(Integer.parseInt(id),status_data,"/create/comment");
    }

    @GetMapping("/get/comments/{id}")
    @ResponseBody
    public ModelAndView getComments(@PathVariable("id") String id, Model model,
                                    @CookieValue(name="ul",defaultValue = "") String cookie){
        model.addAttribute("comments",commentUtils.getComments(http.load(Integer.parseInt(id),"","/get/comments/"+id),cookie));
        model.addAttribute("id",Integer.parseInt(id));
        return new ModelAndView("fragments/comment :: comment");
    }
}
