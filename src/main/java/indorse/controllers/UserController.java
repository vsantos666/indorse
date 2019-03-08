package indorse.controllers;

import indorse.bean.Login;
import indorse.model.Friend;
import indorse.model.User;
import indorse.service.UserService;
import indorse.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    JsonResult adicionarUsuario(@RequestHeader("User") String user, @Valid @RequestBody User usuario) {
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
    JsonResult modificarUsuario(@RequestHeader("User") String user, @Valid @RequestBody User usuario) {
         try{
            String respuesta = userService.updateUser(usuario,user);
            if(respuesta == null){
                return new JsonResult(true, null, "Successful update.");
            }else {
                return new JsonResult(false, null, respuesta);
            }
        }catch (Exception ex){
            return new JsonResult(false, null, ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody
    JsonResult eliminarUsuario(@RequestHeader("User") String user, @Valid @RequestBody User usuario) {
        try{
            String respuesta = userService.deleteUser(usuario,user);
            if(respuesta == null){
                return new JsonResult(true, null, "Successful delete.");
            }else {
                return new JsonResult(false, null, respuesta);
            }
        }catch (Exception ex){
            return new JsonResult(false, null, ex.getMessage());
        }
    }

    @RequestMapping(path = "/{name}",method = RequestMethod.GET)
    public @ResponseBody
    JsonResult otenerUsuarioS(@RequestHeader("User") String user,@RequestHeader("Token") String token,
                              @PathVariable String name) {
        try{
            if(userService.validateToken(token)==null){
                return new JsonResult(false, null, "Invalid token");
            }
            List<User> userList = userService.getUserByName(name);
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
    JsonResult userLogin(@Valid @RequestBody Login login) {
        try{
            String respuesta = userService.userLogin(login);
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
