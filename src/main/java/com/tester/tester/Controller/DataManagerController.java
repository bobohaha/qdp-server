package com.tester.tester.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.tester.tester.Constants.UrlConstants;
import com.tester.tester.Constants.ServerInfoConstants;
import com.tester.tester.Entity.ResultEntity;
import com.tester.tester.Entity.UserEntity;
import com.tester.tester.Util.DateUtil;
import com.tester.tester.Util.FastJsonUtil;
import com.tester.tester.Util.OkHttpUtil;
import com.tester.tester.Util.UserUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
public class DataManagerController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    OkHttpUtil okHttpUtil;

    @CrossOrigin
    @PostMapping(value = "/api/data/select/uid", produces = "application/json; charset=UTF-8")
    public ResultEntity getUserUid(@RequestBody UserEntity userEntity) {

        String phone = userEntity.getPhone();
        String pwd = userEntity.getPassword();
        int env = userEntity.getEnv();
        return OkHttpUtil.response(UserUtil.juniorLogin(phone, pwd, env), "data", "mid");
    }

    @CrossOrigin
    @GetMapping(value = "/api/data/select/did")
    public ResultEntity getUserDId(String uid) throws IOException {
        Map<String, String> param = new HashMap<>();

        String filter = "h_m,int," + uid;
        param.put("filter", filter);
        param.put("cluster", "STAT");
        param.put("sortkey", "-ut");
        param.put("ctkey", "ct");
        param.put("table", "device_info_1");

        String cookies = UserUtil.getCrmLoginCookies(0);
        JsonNode resNode = OkHttpUtil.getResBodyJson(OkHttpUtil.get(UrlConstants.TEST_DEVICE_INFO_URL, param, "Cookie", cookies));

        if (OkHttpUtil.isSuccess(OkHttpUtil.getCode(resNode))) {
            if (FastJsonUtil.getJsonNode(resNode, "data", "total").asInt() != 0) {
                System.out.println(FastJsonUtil.getJsonNode(resNode, "data", "total").asInt());
                JsonNode info = FastJsonUtil.getJsonNode(resNode, "data", "infos", "h_did");
                return OkHttpUtil.success(info);
            }else {
                return OkHttpUtil.success(ServerInfoConstants.SERVER_DATA_EMPTY_INFO);
            }
        }
        return OkHttpUtil.fail();
    }

    @CrossOrigin
    @PostMapping(value = "/api/data/select/roomid")
    public ResultEntity getLessonRoomId(@RequestBody UserEntity userEntity) {
        JSONObject param = new JSONObject();

        param.put("isrevers", false);
        param.put("kid", 173108934578178L);
        param.put("offset", 0);
        param.put("onlyabnormal", false);
        param.put("onlyremark", false);
        param.put("rank", 1);
        param.put("shownewteaclass", false);
        param.put("showsign", false);
        param.put("stamp", 1);
        param.put("stuid", userEntity.getUid());
        param.put("uid", 0);

        String cookies = UserUtil.getCrmLoginCookies(0);
        JsonNode resNode = OkHttpUtil.getResBodyJson(OkHttpUtil.post(UrlConstants.TEST_LESSON_INFO_URL, param.toString(), "Cookie", cookies));

        if (OkHttpUtil.isSuccess(OkHttpUtil.getCode(resNode))) {
            JsonNode dataList = resNode.findPath("data").findPath("ent").findPath("items");
            JSONArray newData = new JSONArray();
            for (JsonNode data : dataList) {
                JSONObject newInfo = new JSONObject();
                newInfo.put("lessonId", data.get("lessonid").asText());
                newInfo.put("stamp", DateUtil.getPointTimestampFormatDate(data.get("stamp").asLong()));
                newInfo.put("roomId", data.get("roomid").asText());
                newData.add(newInfo);
            }
            return OkHttpUtil.success(newData.toString());
        }
        return OkHttpUtil.fail();
    }
}
