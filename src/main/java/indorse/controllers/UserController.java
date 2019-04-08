package indorse.controllers;

import indorse.bean.UserLogin;
import indorse.bean.UserDTO;
import indorse.service.UserService;
import indorse.bean.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by vsantos on 7/03/2019.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    JsonResult addUser(@RequestHeader("User") String user, @Valid @RequestBody UserDTO usuario) {
        try {
            String respuesta = userService.saveUser(usuario, user);
            if (respuesta == null) {
                return new JsonResult(true, null, "Successful Create.");
            } else {
                return new JsonResult(false, null, respuesta);
            }
        }catch (Exception ex){
            return new JsonResult(false, null, ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody
    JsonResult modificarUsuario(@RequestHeader("User") String user, @Valid @RequestBody UserDTO usuario) {
         try{
            String result = userService.updateUser(usuario,user);
            if(result == null){
                return new JsonResult(true, null, "Successful update.");
            }else {
                return new JsonResult(false, null, result);
            }
        }catch (Exception ex){
            return new JsonResult(false, null, ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody
    JsonResult deleteUser(@RequestHeader("User") String user, @Valid @RequestBody UserDTO usuario) {
        try{
            String result = userService.deleteUser(usuario,user);
            if(result == null){
                return new JsonResult(true, null, "Successful delete.");
            }else {
                return new JsonResult(false, null, result);
            }
        }catch (Exception ex){
            return new JsonResult(false, null, ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    JsonResult getUser(@RequestHeader("User") String user,@RequestHeader("Token") String token,
                       @RequestParam Map<String, Object> map) {
        try{
            if(userService.validateToken(token)==null){
                return new JsonResult(false, null, "Invalid token");
            }
            List<UserDTO> userList = userService.getUserByName(map);
            if(userList == null){
                return new JsonResult(false, null, "Error to get the users.");
            }else {
                return new JsonResult(true, userList, "");
            }
        }catch (Exception ex){
            return new JsonResult(false, null, ex.getMessage());
        }
    }

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public @ResponseBody
    JsonResult userLogin(@Valid @RequestBody UserLogin userLogin) {
        try{
            String respuesta = userService.userLogin(userLogin);
            if(respuesta != null ){
                return new JsonResult(true, respuesta, "Success");
            }else {
                return new JsonResult(false, null, "Incorrect User or Password");
            }
        }catch (Exception ex){
            return new JsonResult(false, null, ex.getMessage());
        }
    }


}
