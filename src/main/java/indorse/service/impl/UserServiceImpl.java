package indorse.service.impl;

import indorse.bean.UserLogin;
import indorse.bean.UserDTO;
import indorse.model.User;
import indorse.model.UserToken;
import indorse.repositories.UserRepository;
import indorse.repositories.UserTokenRepository;
import indorse.service.UserService;
import indorse.utils.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import indorse.utils.SecurePassword;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by vsantos on 7/03/2019.
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTokenRepository userTokenRepository;

    /**
     * Method to add a new user
     * @param userDTO
     * @param user
     * @return una cadena con null si existe error
     */
    @Transactional
    @Override
    public String saveUser(UserDTO userDTO, String user) {
        try {
            User user1 = MapperUtil.mapObject(userDTO, User.class);
            user1.setCreatedBy(user);
            SecurePassword secure = new SecurePassword();
            String securePassword = secure.getSecurePassword(user1.getPassword(), "123456789".getBytes());
            user1.setPassword(securePassword);
            userRepository.saveAndFlush(user1);
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Method to modify an user
     * @param userDTO
     * @param user
     * @return
     */
    @Transactional
    @Override
    public String updateUser(UserDTO userDTO, String user) {
        try {
            User usuario = MapperUtil.mapObject(userDTO, User.class);
            User user1 = userRepository.findById((long)usuario.getId());
            if(user1 !=null){
                user1.setLastName(usuario.getLastName());
                user1.setName(usuario.getName());
                user1.setEmail(usuario.getEmail());
                user1.setBirthDate(usuario.getBirthDate());
                user1.setUpdatedBy(user);
                userRepository.save(user1);
                return null;
            }else{
                return "There is no User";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    /**
     * Method to delete an User
     * @param userDTO
     * @param user
     * @return
     */
    @Transactional
    @Override
    public String deleteUser(UserDTO userDTO, String user) {
        try {
            User user1 = userRepository.findById((long)userDTO.getId());
            if(user1 !=null){
                user1.setDisabled(true);
                user1.setUpdatedBy(user);
                userRepository.saveAndFlush(user1);
                return null;
            }else{
                return "There is no User";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Method to get all users
     * @return List<User> lista de usuarios
     */
    @Override
    public List<UserDTO> getUserByName(Map<String, Object> map) {
        try {
            Pageable pageable = new PageRequest(Integer.parseInt(map.get("page").toString()),Integer.parseInt(map.get("size").toString()));
            List<User> userList = userRepository.findAllByDisabledAndNameContains(false,map.get("name").toString(),pageable);
            List<UserDTO> userDTOList = userList
                    .stream()
                    .map(e -> MapperUtil.mapObject(e, UserDTO.class))
                    .collect(Collectors.toList());
            return userDTOList;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Method to get the secure token
     * @param userLogin
     * @return
     */
    @Transactional
    @Override
    public String userLogin(UserLogin userLogin) {
        try {
            SecurePassword secure = new SecurePassword();
            String securePassword = secure.getSecurePassword(userLogin.getPassword(), "123456789".getBytes());
            List<User> userList = userRepository.findAllByLoginAndPassword(userLogin.getUser(),securePassword);
            if(userList!= null && userList.size()>0){
                String token = UUID.randomUUID().toString();
             for(User user:userList){
                 UserToken userToken= new UserToken();
                 Calendar cal = Calendar.getInstance();
                 cal.setTime(new Date());
                 cal.set(Calendar.HOUR, cal.get(Calendar.HOUR)+ 2);
                 userToken.setEndDate(cal.getTime());
                 userToken.setUserId(user);
                 userToken.setToken(token);
                 userTokenRepository.saveAndFlush(userToken);
             }
                return token;
            }else {
                return null;
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Method to validate if the toke is alive
     * @param token
     * @return
     */
    @Transactional
    @Override
    public String validateToken(String token) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            UserToken userToken = userTokenRepository.findByTokenAndEndDateAfter(token,cal.getTime());
            if(userToken!= null){
                return "Conected";
            }else {
                return null;
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
