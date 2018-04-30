package ie.sesh.Controllers;

import ie.sesh.Http.HttpHandler;
import ie.sesh.Model.Status;
import ie.sesh.Utils.CookieUtils;
import ie.sesh.Utils.StatusUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class StatusController {

    private static final Logger log = Logger.getLogger(StatusController.class);

    @Autowired
    HttpHandler http;

    @Autowired
    StatusUtils statusUtils;

    @Autowired
    CookieUtils cookieUtils;

    @PostMapping("/get/all/status")
    @ResponseBody
    public ModelAndView getAllStatus(@CookieValue(name="ul",defaultValue = "") String id, Model model){
            model.addAttribute("statuses",statusUtils.getAllStatuses(http.load(Integer.parseInt(id),"","/get/all/status")));
            return new ModelAndView("statuses :: status");
    }

    @PostMapping("/create/status")
    @ResponseBody
    public String createStatus(@RequestBody String status_data, HttpServletResponse response,
                               @CookieValue(name="ul",defaultValue = "") String id, Model model) throws Exception{
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
}
