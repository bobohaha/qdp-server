package com.tester.tester.Controller;

import com.tester.tester.Constants.JiraConstants;
import com.tester.tester.Entity.ResultEntity;
import com.tester.tester.Service.TesterService;
import com.tester.tester.Util.JiraUtil;
import com.tester.tester.Util.OkHttpUtil;
import net.rcarz.jiraclient.Project;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TesterController {

    @Autowired
    TesterService testerService;

    @CrossOrigin
    @GetMapping(value = "/api/quality/info")
    public ResultEntity getQualityTableInfo() {
        List<JSONObject> dataList = new ArrayList<>();
        String projectJql = "project = ";
        String baseJql = " AND issuetype = 故障 ORDER BY 等级 ASC";

        for (Project project : JiraUtil.getProjects()) {
            if (isNeedBusiness(project.getKey())) {
                String jql = projectJql + project + baseJql;
                JSONObject data = new JSONObject();
                data.put("project", project.getName());
                data.put("issue", JiraUtil.getProjectBugTotal(jql));
                dataList.add(data);
            }
        }
        return OkHttpUtil.success(dataList);
    }
    private boolean isNeedBusiness(String businessKey) {
        return JiraConstants.BUSINESS.contains(businessKey);
    }
}
