package indorse.service.impl;

import indorse.bean.Login;
import indorse.model.Friend;
import indorse.model.User;
import indorse.model.UserToken;
import indorse.repositories.FriendRepository;
import indorse.repositories.UserRepository;
import indorse.repositories.UserTokenRepository;
import indorse.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import indorse.utils.SecurePassword;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/** Toda la logica aqui
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
     * @param usuario
     * @param user
     * @return una cadena con null si existe error
     */
    @Transactional
    @Override
    public String saveUser(User usuario, String user) {
        try {
            usuario.setCreatedBy(user);
            SecurePassword secure = new SecurePassword();
            String securePassword = secure.getSecurePassword(usuario.getPassword(), "123456789".getBytes());
            usuario.setPassword(securePassword);
            userRepository.saveAndFlush(usuario);
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Method to modify an user
     * @param usuario
     * @param user
     * @return
     */
    @Transactional
    @Override
    public String updateUser(User usuario, String user) {
        try {
            User user1 = userRepository.findOne(usuario.getId());
            if(user1 !=null){
                user1.setLastName(usuario.getLastName());
                user1.setName(usuario.getName());
                user1.setEmail(usuario.getEmail());
                user1.setBirthDate(usuario.getBirthDate());
                user1.setUpdatedBy(user);
                userRepository.save(user1);
                return null;
            }else{
                return "No se encontro el registro";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    /**
     * Method to delete an User
     * @param usuario
     * @param user
     * @return
     */
    @Transactional
    @Override
    public String deleteUser(User usuario, String user) {
        try {
            User user1 = userRepository.findOne(usuario.getId());
            if(user1 !=null){
                user1.setDisabled(true);
                user1.setUpdatedBy(user);
                userRepository.saveAndFlush(user1);
                return null;
            }else{
                return "No se encontro el registro";
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
    public List<User> getUserByName(String name) {
        try {
            //Pageable pageable = new  PageRequest(0,2);
            List<User> userList = userRepository.findAllByDisabledAndNameContains(false,name);
            return userList;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    @Override
    public String userLogin(Login login) {
        try {
            SecurePassword secure = new SecurePassword();
            String securePassword = secure.getSecurePassword(login.getPassword(), "123456789".getBytes());
            List<User> userList = userRepository.findAllByLoginAndPassword(login.getUser(),securePassword);
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
