package indorse.service;

import indorse.bean.Login;
import indorse.bean.UserDTO;
import indorse.model.Friend;
import indorse.model.User;

import java.util.List;

/**
 * Created by vsantos on 7/03/2019.
 */
public interface UserService {

    public String saveUser(UserDTO userData, String user);

    public String updateUser(UserDTO userData, String user);

    public String deleteUser(UserDTO userData, String user);

    public List<UserDTO> getUserByName(String name);

    public String userLogin(Login login);

    public String validateToken(String token);
}
