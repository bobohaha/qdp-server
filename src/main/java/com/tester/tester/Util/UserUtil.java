package com.tester.tester.Util;
import com.fasterxml.jackson.databind.JsonNode;
import com.tester.tester.Service.UrlEnumServiceImp;
import net.sf.json.JSONObject;
import okhttp3.Response;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {

    public static Response juniorLogin(String user, String password, int env) {
        JSONObject param = new JSONObject();
        String pwd = MD5Util.string2MD5(password).substring(0, 16);
        param.put("phone", user);
        param.put("pw", pwd);
        param.put("area", "86");
        param.put("cate", 1);

        String loginUrl = getUrl(env, "APP_LOGIN_URL");

        return login(loginUrl, param);
    }

    public static Long getJuniorUid(JsonNode data) {
        return FastJsonUtil.getJsonNode(data, "data", "mid").asLong();
    }

    public static String getJuniorToken(JsonNode data) {
        return FastJsonUtil.getJsonNode(data, "data", "token").asText();
    }

    public Response opLogin(int env) {
        JSONObject param = new JSONObject();
        param.put("utype", "op");
        param.put("phone", "18810809286");
        param.put("ttype", "phone");
        param.put("ticket", "");
        param.put("mtype", "salessystem");

        String loginUrl = getUrl(env, "OP_LOGIN_URL");
        return login(loginUrl, param);
    }

    private static Response crmLogin(int env) {
        JSONObject param = new JSONObject();
        param.put("utype", "op");
        param.put("phone", "18810809286");
        param.put("ttype", "phone");
        param.put("ticket", "");
        param.put("mtype", "salessystem");

        String loginUrl = getUrl(env, "CRM_LOGIN_URL");
        return login(loginUrl, param);
    }

    public static String getCrmLoginCookies(int env) {
        String cookies = null;
        try {
            cookies = OkHttpUtil.getResCookies(crmLogin(env));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookies;
    }

    public static String getUrl(int env, String urlType) {
        return UrlEnumServiceImp.valueOf(urlType).getUrl(env);
    }

    private static Response login(String url, JSONObject param) {
        Response response = null;
        try {
            response = OkHttpUtil.post(url, param.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
