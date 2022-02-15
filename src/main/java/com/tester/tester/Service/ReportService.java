package com.tester.tester.Service;

import com.tester.tester.Entity.ReportEntity;
import com.tester.tester.Mapper.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    ReportMapper reportMapper;
    public List<ReportEntity> getReportList() {
        return reportMapper.getReportList();
    }
}