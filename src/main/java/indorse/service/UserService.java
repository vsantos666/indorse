package indorse.service;

import indorse.bean.UserLogin;
import indorse.bean.UserDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by vsantos on 7/03/2019.
 */
public interface UserService {

    public String saveUser(UserDTO userDTO, String user);

    public String updateUser(UserDTO userDTO, String user);

    public String deleteUser(UserDTO userDTO, String user);

    public List<UserDTO> getUserByName(Map<String, Object> map);

    public String userLogin(UserLogin userLogin);

    public String validateToken(String token);
}
