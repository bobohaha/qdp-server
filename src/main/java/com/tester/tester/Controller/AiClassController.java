package com.tester.tester.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.tester.tester.Constants.ProductConstants;
import com.tester.tester.Entity.ResultEntity;
import com.tester.tester.Util.DateUtil;
import com.tester.tester.Util.OkHttpUtil;
import com.tester.tester.Util.UserUtil;
import net.sf.json.JSONObject;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
public class AiClassController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @CrossOrigin
    @PostMapping(value = "/api/lesson/ai/appointments", produces = "application/json; charset=UTF-8")
    public ResultEntity appointments(@RequestBody Map<String, Object> param, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String message = String.format("input info error， please try again.", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            return OkHttpUtil.fail(message);
        }
        return autoAppointmentLesson(param);
    }

    private ResultEntity autoAppointmentLesson(Map<String, Object> param) {

        Map<String, Object> info = (Map<String, Object>) param.get("data");
        boolean isTime = (boolean) param.get("delivery");
        String phone = (String) info.get("phone");
        String password = (String) info.get("password");
        int env = (int) info.get("env");

        long startTimestamp = DateUtil.getPointTimestamp(7);
        long endTimestamp = DateUtil.getPointTimestamp(22);

        if (isTime) {
            Long timestamp = (Long) param.get("time");
            timestamp = DateUtil.millisecondFormatSeconds(timestamp);
            return appointmentPointTimeLesson(startTimestamp, timestamp, endTimestamp, phone, password, env);
        }
        return appointmentAllTimeLesson(startTimestamp, endTimestamp, phone, password, env);
    }

    private ResultEntity appointmentAllTimeLesson(long startTime, long endTime, String phone, String password, int env) {

        long currentTimestamp = DateUtil.getCurrentTimestamp();

        while (endTime >= startTime) {
            startTime += 1800;
            if (startTime > currentTimestamp) {
                try {
                    JsonNode resAppointmentJson = OkHttpUtil.getResBodyJson(appointmentLesson(phone, password, startTime, env));
                    if (!OkHttpUtil.isSuccess(OkHttpUtil.getCode(resAppointmentJson))) {
                        logger.info(OkHttpUtil.getMsgJson(resAppointmentJson).asText());
                    }else {
                        Thread.sleep(5000);
                    }
                }catch (Exception e) {
                    logger.info(e.toString());
                    return OkHttpUtil.fail(e.toString());
                }
            }else {
                logger.info("class time is end , next>");
            }
        }
        return OkHttpUtil.success("已预约当天所有可约时间段，请登录App查看");
    }

    private ResultEntity appointmentPointTimeLesson(long startTime, long time, long endTime, String phone, String password, int env) {

        if (startTime < time && time < endTime) {
            try {
                JsonNode resAppointmentJson = OkHttpUtil.getResBodyJson(appointmentLesson(phone, password, time, env));

                if (OkHttpUtil.isSuccess(OkHttpUtil.getCode(resAppointmentJson))) {
                    return OkHttpUtil.success(DateUtil.getPointTimestampFormatDate(time));
                }else {
                    return OkHttpUtil.success(OkHttpUtil.getMsgJson(resAppointmentJson).asText());
                }
            }catch (Exception e) {
                e.printStackTrace();
                return OkHttpUtil.fail(e.toString());
            }

        }else {
            return OkHttpUtil.success("Current time can not appointment lesson");
        }
    }

    private Response appointmentLesson(String user, String password, Long timestamp, int env) throws Exception {

        String appointmentUrl = UserUtil.getUrl(env, "APPOINTMENT_URL");
        Long kid = ProductConstants.PRODUCT_READING_ID;
        JsonNode loginData = OkHttpUtil.getResBodyJson(UserUtil.juniorLogin(user, password, env));

        if (!OkHttpUtil.isSuccess(OkHttpUtil.getCode(loginData))) {
            throw new Exception(OkHttpUtil.getMsgJson(loginData).asText());
        }

        JSONObject userInfo = new JSONObject();
        userInfo.put("h_ts", 0);
        userInfo.put("kid", kid);
        userInfo.put("h_m", UserUtil.getJuniorUid(loginData));
        userInfo.put("token", UserUtil.getJuniorToken(loginData));
        userInfo.put("stamp", timestamp);
        Response responseAppointment = OkHttpUtil.post(appointmentUrl, userInfo.toString());

        return responseAppointment;
    }
}
