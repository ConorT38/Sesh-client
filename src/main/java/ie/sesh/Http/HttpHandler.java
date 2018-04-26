package ie.sesh.Http;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public interface HttpHandler {

    String login(String username, String password, HttpServletResponse response) throws Exception;
    String checkLogin(String cookie) throws Exception;
    String signup(String name,String username, String email, String password, HttpServletResponse response) throws Exception;
    String logout(String id, HttpServletResponse response) throws Exception;

    String create(String data,String path);
    String delete(int id,String path);
    String update(int id, String data,String path);
    String load(int id, String data,String path);





}
