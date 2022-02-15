package com.tester.tester.Controller;

import com.tester.tester.Constants.JiraConstants;
import com.tester.tester.Entity.BusinessEntity;
import com.tester.tester.Entity.BusinessModelEntity;
import com.tester.tester.Entity.DeviceEntity;
import com.tester.tester.Entity.ResultEntity;
import com.tester.tester.Service.BusinessModelService;
import com.tester.tester.Service.BusinessService;
import com.tester.tester.Service.DeviceService;
import com.tester.tester.Util.JenkinsUtil;
import com.tester.tester.Util.OkHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MonitorController {

    @Autowired
    BusinessModelService businessModelService;
    @Autowired
    DeviceService deviceService;
    @Autowired
    BusinessService businessService;

    @CrossOrigin
    @GetMapping(value = "/api/monitor/business/list")
    public ResultEntity getBusinessList() {

        List<BusinessEntity> businessEntities = businessService.getBusinessList();
        return OkHttpUtil.success(businessEntities);
    }

    @CrossOrigin
    @GetMapping(value = "/api/monitor/ui/device/list")
    public ResultEntity getDeviceData(Integer type) {

        List<DeviceEntity> deviceData;

        if (type == null) {
            deviceData = deviceService.getDeviceList();
        }else {
            deviceData = deviceService.getDeviceData(type);
        }
        return OkHttpUtil.success(deviceData);
    }

    @CrossOrigin
    @GetMapping(value = "/api/monitor/ui/model/list")
    public ResultEntity getBusinessModelData(String business) {
        System.out.println(business);
        List<BusinessModelEntity> businessModelData;
        if (!business.isEmpty()) {
            businessModelData = businessModelService.getBusinessModelListByBusiness(business);
        }else {
            businessModelData = businessModelService.getBusinessModelList();
        }

        return OkHttpUtil.success(businessModelData);
    }

    @CrossOrigin
    @PostMapping(value = "/api/monitor/ui/run")
        public ResultEntity uiMonitorTest(@RequestBody Map<String, Object> params) {

        Map<String, String> info = (Map<String, String>) params.get("params");
        String data;
        String deviceType = info.get("type");
        String device = info.get("device");
        String model = info.get("model");
        String business = info.get("business");

        Map<String, String>param  = new HashMap<>();
        param.put("business", business);
        param.put("type", deviceType);
        param.put("model", model);
        device = "test";

        try {
            JenkinsUtil.triggerJenkins(device, param);
            data = "执行成功";
        } catch (Exception e) {
            data = "Time Out";
            return OkHttpUtil.fail(data);
        }
        return OkHttpUtil.success(data);
    }

    @CrossOrigin
    @PostMapping(value = "/api/monitor/monkey/run")
    public ResultEntity monkeyMonitorTest(@RequestBody Map<String, Object> params) {

        Map<String, Object> info = (Map<String, Object>) params.get("params");
        String job = "monkey_project";
        String business = (String) info.get("business");
        String model = info.get("model").toString();
        String logLevel = info.get("level").toString();
        String throttle = (String) info.get("throttle");
        String runTime = (String) info.get("time");
        String downloadUrl = (String) info.get("url");
        String testcase = (String) info.get("case");
        String unElement = (String) info.get("unElement");
        String blackList = (String) info.get("blackList");
        String slave = info.get("node").toString();
        String isAllDevice = info.get("allDevice").toString();
        String installModel = info.get("type").toString();


        Map<String, String>param  = new HashMap<>();
        param.put("project", business);
        param.put("run_mode", model);
        param.put("log_level", logLevel);
        param.put("throttle", throttle);
        param.put("running_minutes", runTime);
        param.put("apk_url", downloadUrl);
        param.put("max_xpath_actions", testcase);
        param.put("max_widget_black", unElement);
        param.put("black_list", blackList);
        param.put("slave", slave);
        param.put("run_all_devices", isAllDevice);
        param.put("install_mode", installModel);

        String data;
        String url = "https://jenkins-front.pri.ibanyu.com/";
        String username = JiraConstants.JIRA_USERNAME;
        String password = JiraConstants.JIRA_PASSWORD;
        if (JenkinsUtil.triggerJenkinsAndResult(url, username, password, job, param) != 1) {
            data = "Time Out";
        }else {
            data = "http://baidu.com";
        }
        return OkHttpUtil.success(data);
    }
}
