package ie.sesh.Controllers;

import ie.sesh.Http.HttpHandler;
import ie.sesh.Utils.CommentUtils;
import ie.sesh.Utils.CookieUtils;
import ie.sesh.Utils.StatusUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CommentController {
    private static final Logger log = Logger.getLogger(CommentController.class);

    @Autowired
    HttpHandler http;

    @Autowired
    StatusUtils statusUtils;

    @Autowired
    CommentUtils commentUtils;

    @Autowired
    CookieUtils cookieUtils;

    @PostMapping("/create/comment")
    @ResponseBody
    public String createComment(@RequestBody String status_data,
                                @CookieValue(name="ul",defaultValue = "") String id){
        log.info("Create Comment: "+status_data);

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
