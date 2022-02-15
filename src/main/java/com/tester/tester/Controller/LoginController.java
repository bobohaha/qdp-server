package com.tester.tester.Controller;

import com.tester.tester.Constants.JenkinsConstants;
import com.tester.tester.Entity.ResultEntity;
import com.tester.tester.Entity.UserEntity;
import com.tester.tester.Service.TokenService;
import com.tester.tester.Util.LdapUtil;
import com.tester.tester.Util.OkHttpUtil;
import com.tester.tester.Util.RedisUtil;
import com.tester.tester.Util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")

public class LoginController {

    @Autowired
    TokenService tokenService;
    @Resource
    private RedisUtil redisUtil;

    @CrossOrigin
    @PostMapping(value = "/ldaplogin", produces = "application/json; charset=UTF-8")
    public ResultEntity ldapLogin(@Valid @RequestBody UserEntity userEntity) {
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();
        JSONObject res = new JSONObject();

        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            return OkHttpUtil.fail( "账号或密码不能为空");
        }

        if (LdapUtil.isConnLdapSuccess(username, password, JenkinsConstants.LDAP_URL)) {
            String token = tokenService.getToken(userEntity);
            res.put("msg", "success");
            res.put("token", token);
            return OkHttpUtil.success(res);
        }
        return OkHttpUtil.fail("账号用户名或密码错误");
    }

    @CrossOrigin
    @PostMapping(value = "/login", produces = "application/json; charset=UTF-8")
    public ResultEntity Login(@Valid @RequestBody UserEntity userEntity) {
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();
        JSONObject res = new JSONObject();

        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            return OkHttpUtil.fail( "账号或密码不能为空");
        }

        String token = tokenService.getToken(userEntity);
        res.put("msg", "success");
        res.put("token", token);
        return OkHttpUtil.success(res);
    }

    @CrossOrigin
    @PostMapping(value = "/logout", produces = "application/json; charset=UTF-8")
    public ResultEntity logout(@Valid @RequestBody UserEntity userEntity) {
        return OkHttpUtil.fail();
    }
}

