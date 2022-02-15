package com.tester.tester.Util;

import com.fasterxml.jackson.databind.JsonNode;
import com.tester.tester.Entity.ResultEntity;
import com.tester.tester.Common.ResultFactory;
import okhttp3.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class OkHttpUtil {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String TAG = "";
    private static OkHttpClient client = new OkHttpClient();
    private static Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);

    /**
     * post request
     * @param url 请求的url
     * @param postParam 请求参数 String类型
     * @return Response
     */

    public static Response post(String url, String postParam) {
        return postFormBody(url, postParam);
    }

    /**
     * post request
     * @param url 请求的url
     * @param formParamsMap 请求参数 Map类型
     * @return Response
     */

    public static Response post(String url,Map<String,String> formParamsMap){
        String mapToString = formParamsMap.toString();
        return postFormBody(url, mapToString);
    }

    /**
     * point header post request
     * @param url
     * @param postParam String 类型
     * @param headerKey
     * @param headerValue
     * @return Response
     */
    public static Response post(String url, String postParam, String headerKey, String headerValue) {

        RequestBody body = RequestBody.create(JSON, postParam);
        Request request = new Request.Builder()
                .url(url).addHeader(headerKey, headerValue)
                .post(body)
                .build();
        return execute(request);
    }

    /**
     * point MediaType post request
     * @param url
     * @param urlParams
     * @param postBody
     * @param mediaType
     * @return response
     */
    public static Response post(String url, String urlParams, String postBody, MediaType mediaType) {

        Response response = postCommon(url, urlParams, postBody, mediaType);
        return response;
    }

    private static Response postFormBody(String url, String postParam) {

        RequestBody requestBody = RequestBody.create(JSON, postParam);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return execute(request);
    }

    private static Response postCommon(String url, String urlParams, String postBody, MediaType mediaType) {
        postBody = StringUtils.isEmpty(postBody) ? "" : postBody;
        RequestBody body = RequestBody.create(mediaType, postBody);

        Request request = new Request.Builder().url(url + urlParams)
                .post(body)
                .build();
        return execute(request);
    }

    /**
     * get请求
     * @param url
     * @param urlParams
     * @return Response
     */
    public static Response get(String url, Map<String, String> urlParams) {
        Request request = new Request.Builder()
                .url(url + formatParam(urlParams))
                .build();
        return execute(request);
    }

    /**
     * get请求
     * @param url
     * @param urlParams Map类型
     * @param headerKey
     * @param headerValue
     * @return Response
     */
    public static Response get(String url, Map<String, String> urlParams, String headerKey, String headerValue) {
        Request request = new Request.Builder().addHeader(headerKey,headerValue).url(url + formatParam(urlParams)).build();
        return execute(request);
    }

    private static Response execute(Request request) {
        Response response = null;
        try {
            response = client.newCall(request).execute();
            logger.info(String.valueOf(response));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(String.valueOf(e));
        }
        return response;
    }

    private static String formatParam(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        if (params != null && params.keySet().size() > 0) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                sb.append(param.getKey()).append("=").append(param.getValue()).append("&");
            }
        }
        return sb.insert(0,"?").toString();
    }

    public static String builderURL(String host, String port, String path) {
        return host + ":" + port + path;
    }

    public static JsonNode getRetJson(Response response) {
        String resString = getResponseString(response);
        JsonNode resNode = FastJsonUtil.toJsonNode(resString);
        return resNode.path("ret");
    }

    public static int getCode(JsonNode resNode) {
        return resNode.path("ret").asInt();
    }

    public static JsonNode getDataJson(JsonNode resNode) {
        return resNode.path("data");
    }

    public static JsonNode getMsgJson(JsonNode resNode) {
        return resNode.path("msg");
    }

    public static JsonNode getResBodyJson(Response response) {
        String resString = getResponseString(response);
        JsonNode resNode = FastJsonUtil.toJsonNode(resString);
        logger.info(resNode.toString());
        return resNode;
    }

    private static String getResponseString(Response response) {
        String resString = null;
        try {
            resString = response.body().string();
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return resString;
    }

    public static boolean isSuccess(int code) {
        return code == 1 || code == 200;
    }

    public static ResultEntity response(Response response) {
        JsonNode resNode = getResBodyJson(response);
        if (isSuccess(getCode(resNode))){
            return ResultFactory.executeSuccess(getDataJson(resNode));
        }else {
            return ResultFactory.executeFail();
        }
    }

    public static ResultEntity success(JsonNode data) {
        return ResultFactory.executeSuccess(data.asText());
    }

    public static ResultEntity success(Object info) {
        return ResultFactory.executeSuccess(info);
    }

    public static ResultEntity fail() {
        return ResultFactory.executeFail();
    }

    public static ResultEntity fail(String message) {
        return ResultFactory.executeFail(message);
    }

    public static ResultEntity error() {
        return ResultFactory.executeError();
    }

    public static ResultEntity response(Response response, String path, String key) {
        JsonNode resNode = getResBodyJson(response);
        if (isSuccess(getCode(resNode))){
            return ResultFactory.executeSuccess(FastJsonUtil.getJsonNode(resNode, path, key));
        }else {
            return ResultFactory.executeFail();
        }
    }

    public static String getResCookies(Response response) {

        Headers headers = response.headers();
        List<String> cookiesList = headers.values("Set-Cookie");
        List<String> newCookies = new ArrayList<>();
        for (String cookie : cookiesList) {
            newCookies.add(cookie.split(";")[0]);
        }
        return StringUtil.listToString(newCookies, ";");
    }
}
