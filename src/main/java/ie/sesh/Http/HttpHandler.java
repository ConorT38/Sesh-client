package ie.sesh.Http;

import org.springframework.stereotype.Component;

@Component
public interface HttpHandler {

    String login(String username, String password) throws Exception;
    String signup(String username, String email, String password);

    String create(String data,String path);
    String delete(int id,String path);
    String update(int id, String data,String path);
    String load(int id, String data,String path);





}
