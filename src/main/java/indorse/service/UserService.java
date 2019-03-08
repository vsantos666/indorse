package indorse.service;

import indorse.bean.Login;
import indorse.model.Friend;
import indorse.model.User;

import java.util.List;

/**
 * Created by vsantos on 7/03/2019.
 */
public interface UserService {

    public String saveUser(User userData, String user);

    public String updateUser(User userData, String user);

    public String deleteUser(User userData, String user);

    public List<User> getUserByName(String name);

    public String userLogin(Login login);

    public String validateToken(String token);
}
