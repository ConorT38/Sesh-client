package ie.sesh.Controllers;

import ie.sesh.Http.HttpHandler;
import ie.sesh.Utils.NotificationUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class NotificationController {
    private static final Logger log = Logger.getLogger(NotificationController.class);

    @Autowired
    HttpHandler http;

    @Autowired
    NotificationUtils notificationUtils;

    @GetMapping("/get/notifications")
    @ResponseBody
    public ModelAndView getNotification(@CookieValue(name="ul",defaultValue = "") String id, Model model){
        model.addAttribute("notifications",notificationUtils.getNotifications(http.load(Integer.parseInt(id),"","/get/notifications"),id));
        model.addAttribute("id",Integer.parseInt(id));
        return new ModelAndView("Notifications/notification :: notifications");
    }

    @GetMapping("/get/all/notifications")
    @ResponseBody
    public ModelAndView getAllNotifications(@CookieValue(name="ul",defaultValue = "") String id, Model model){

        model.addAttribute("notifications",notificationUtils.getNotifications(http.load(Integer.parseInt(id),"","/get/all/user/status"),id));
        model.addAttribute("id",Integer.parseInt(id));
        return new ModelAndView("Notifications/notification :: notifications");
    }

    @PostMapping("/create/notification")
    @ResponseBody
    public String createNotification(@RequestBody String notification_data,
                               @CookieValue(name="ul",defaultValue = "") String id){
        log.info("Notification created: "+notification_data);

        return http.create(Integer.parseInt(id),notification_data,"/create/notification");
    }
}
