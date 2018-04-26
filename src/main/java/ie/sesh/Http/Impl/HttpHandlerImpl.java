package ie.sesh.Http.Impl;

import ie.sesh.Http.HttpHandler;
import ie.sesh.Utils.Authentication;
import ie.sesh.Utils.CookieUtils;

import org.apache.log4j.Logger;
import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.Base64;



@Component
public class HttpHandlerImpl implements HttpHandler {

    private static final Logger log = Logger.getLogger(HttpHandlerImpl.class);

    final String SESH_COOKIE_NAME = "sesh";
    final String USER_ID_COOKIE_NAME = "ul";
    final int SESH_COOKIE_LIFETIME = 100000;

    @Autowired
    private Environment env;

    @Autowired
    CookieUtils cookieUtils;

    @Override
    public String login(String username, String password, HttpServletResponse response) throws Exception {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

        //map.add("username", Authentication.encrypt(username));
        //map.add("password", Authentication.hashPassword(password));

        map.add("username", username);
        map.add("password", password);

        String postResult = post(map,"/login");
        String loginResult = cookieUtils.filterCookieResponse(postResult,cookieUtils.COOKIE_VALUE);
        log.info("RETURN FROM API COOKIE: "+loginResult);

        JSONArray obj = new JSONArray("["+loginResult+"]");
        log.info(obj.toString());

        String useridValue = cookieUtils.getUserIdCookie(obj);
        String tokenValue = new String(Base64.getEncoder().encode(cookieUtils.getTokenCookie(obj).getBytes()));

        cookieUtils.addCookie(SESH_COOKIE_NAME, tokenValue, SESH_COOKIE_LIFETIME, response);
        cookieUtils.addCookie(USER_ID_COOKIE_NAME, useridValue, SESH_COOKIE_LIFETIME, response);

        return postResult;
    }

    @Override
    public String checkLogin(String cookie) throws Exception {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("sesh", cookie);
        log.info("CheckLogin: "+cookie);

        return post(map,"/check/login");
    }

    @Override
    public String signup(String name, String username, String email, String password, HttpServletResponse response) throws Exception {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        //map.add("username", Authentication.encrypt(username));
        //map.add("password", Authentication.hashPassword(password));
        //map.add("email", Authentication.encrypt(email));
        //map.add("name", Authentication.encrypt(name));

        map.add("username", username);
        map.add("password", password);
        map.add("email", email);
        map.add("name", name);

        return post(map,"/register/user");
    }

    @Override
    public String logout(String id, HttpServletResponse response) throws Exception {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("id", id);

        cookieUtils.cookieLogout(response);
        post(map,"/logout");

        return "Login/Login";
    }

    @Override
    public String create(String data, String path) {
        return "yo";
    }

    @Override
    public String delete(int id, String path) {
        return "yo";
    }

    @Override
    public String update(int id, String data, String path) {
        return "yo";
    }

    @Override
    public String load(int id, String data, String path) {
        return "yo";
    }

    public String post(MultiValueMap<String,String> data, String path){
        String url = env.getProperty("sesh.api.host")+":"+env.getProperty("sesh.api.port")+path;
        log.info(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(data, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request , String.class );
        return response.toString();
    }

}
