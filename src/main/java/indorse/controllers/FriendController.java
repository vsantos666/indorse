package indorse.controllers;

import indorse.bean.FriendDTO;
import indorse.service.FriendService;
import indorse.service.UserService;
import indorse.bean.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult addFriend(@RequestHeader("User") String user,@RequestHeader("Token") String token,
                         @Valid @RequestBody FriendDTO friend) {
        try{
            if(userService.validateToken(token)==null){
                return new JsonResult(false, null, "Invalid Token");
            }
            String result = friendService.addFriend(friend,user);
            if(result == null ){
                return new JsonResult(true, null, "Successful Create.");
            }else {
                return new JsonResult(false, null, result);
            }
        }catch (Exception ex){
            return new JsonResult(false, null, ex.getMessage());
        }
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public @ResponseBody
    JsonResult disableFriend(@RequestHeader("User") String user, @RequestHeader("Token") String token,
                             @Valid @RequestBody FriendDTO friend) {
        try{
            if(userService.validateToken(token)==null){
                return new JsonResult(false, null, "Invalid Token");
            }
            String result = friendService.disableFriend(friend,user);
            if(result == null ){
                return new JsonResult(true, null, "Successful Delete.");
            }else {
                return new JsonResult(false, null, result);
            }
        }catch (Exception ex){
            return new JsonResult(false, null, ex.getMessage());
        }
    }
}
