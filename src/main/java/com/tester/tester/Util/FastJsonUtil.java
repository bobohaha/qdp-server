package com.tester.tester.Util;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class FastJsonUtil {

    public static Map toInstance(String jsonString) {
        return JSONObject.parseObject(jsonString, Map.class);
    }

    public static JSONObject stringFormatJson(String string) {
        return JSONObject.parseObject(string);
    }

    public static JsonNode toJsonNode(String jsonString) {
        JsonNode jsonNode = null;
        try {
            jsonNode = new ObjectMapper().readTree(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }

    public static JsonNode getJsonNode(JsonNode resNode , String path, String key) {
        return resNode.findPath(path).get(key);
    }

    public static JsonNode getJsonNode(JsonNode resNode , String path1,  String path2, String key) {
        JsonNode path = resNode.findPath(path1);
        JsonNode sonPath = path.findPath(path2);
        if (sonPath.isArray()) {
            for (JsonNode p : sonPath) {
                return p.get(key);
            }
        }
        return sonPath.get(key);
    }

    public static JsonNode getJsonNode(JsonNode resNode , String path1,  String path2, String path3, String key) {
        JsonNode path = resNode.findPath(path1);
        JsonNode sonPath = path.findPath(path2);
        JsonNode sonSonPath = sonPath.findPath(path3);
        if (sonSonPath.isArray()) {
            for (JsonNode p : sonSonPath) {
                return p.get(key);
            }
        }
        return sonPath.get(key);
    }
}
