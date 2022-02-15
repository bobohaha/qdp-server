package com.tester.tester.Controller;

import com.tester.tester.Entity.ReportEntity;
import com.tester.tester.Entity.ResultEntity;
import com.tester.tester.Service.ReportService;
import com.tester.tester.Util.OkHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/quality/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    @GetMapping(value = "/ui")
    public ResultEntity getReportList() {
        List<ReportEntity> reportList= reportService.getReportList();
        System.out.println(reportList);
        return OkHttpUtil.success(reportList);
    }
}
