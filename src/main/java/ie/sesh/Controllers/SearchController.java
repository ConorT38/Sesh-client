package ie.sesh.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static ie.sesh.Utils.SeshConstants.SESH_COOKIE_NAME;

@Controller
public class SearchController {

//    @GetMapping("/search")
//    public ModelAndView searchResult(@CookieValue(name=SESH_COOKIE_NAME, defaultValue = "") String cookie,
//                                     @CookieValue(name="ul", defaultValue = "") String userId,
//                                     @RequestParam String searchterm) throws Exception{
//        if(cookie == null || cookie.isEmpty()){
//            cookie = "";
//        }
//
    //}
}
