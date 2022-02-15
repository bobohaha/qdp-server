package com.tester.tester.Controller;
import com.tester.tester.Entity.ResultEntity;
import com.tester.tester.Util.OkHttpUtil;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @CrossOrigin
    @GetMapping(value = "api/user/info", produces = "application/json; charset=UTF-8")
    public ResultEntity getUserInfo(){
        JSONObject resData = new JSONObject();
        resData.put("roles","admin");
        resData.put("name","Super admin");
        resData.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        resData.put("token", "token");
        return OkHttpUtil.success(resData);
    }
}
