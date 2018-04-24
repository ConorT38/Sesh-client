package ie.sesh.Http.Impl;

import ie.sesh.Http.HttpHandler;
import ie.sesh.Utils.Authentication;

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



@Component
public class HttpHandlerImpl implements HttpHandler {

    @Autowired
    private Environment env;

    @Override
    public String login(String username, String password) throws Exception {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("username", Authentication.encrypt(username));
        map.add("password", Authentication.hashPassword(password));

        return post(map,"/login");
    }

    @Override
    public String checkLogin(String cookie) throws Exception {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("sesh", cookie);
        System.out.println(cookie);

        return post(map,"/check/login");
    }

    @Override
    public String signup(String username, String email, String password) throws Exception {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("username", Authentication.encrypt(username));
        map.add("password", Authentication.hashPassword(password));

        return post(map,"/login");
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
        System.out.println(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(data, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request , String.class );
        return response.toString();
    }

}
