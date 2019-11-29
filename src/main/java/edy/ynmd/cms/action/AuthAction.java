package edy.ynmd.cms.action;

import edy.ynmd.cms.service.ManageService;
import edy.ynmd.cms.tools.JwtUtil;
import edy.ynmd.cms.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthAction {

    @Autowired
    private ManageService manageService;


    @GetMapping("refreshToken")
    @ResponseBody
    public HashMap<String,String> refreshToken(HttpServletRequest httpServletRequest) throws Exception{

        String userid=manageService.getCurrentUserId();
        String roleid=manageService.getCurrentUserRole();
        if(Tools.isNullOrSpace(userid)&&Tools.isNullOrSpace(roleid)){

            return new HashMap<String,String>(){{
                put("token","error");
            }};

        }
        else{
            String token= JwtUtil.generateToken(roleid,userid);

            return new HashMap<String,String>(){{
                put("token",token);
            }};

        }

    }


    @GetMapping("getRole")
    @ResponseBody
    public HashMap getRole() throws Exception{

        String role=manageService.getCurrentUserRole();
        if(Tools.isNullOrSpace(role)){

            return new HashMap(){{
                put("role","error") ;
            }};

        }
        else{
            return new HashMap(){{
                put("role",role);
            }};

        }


    }











}
