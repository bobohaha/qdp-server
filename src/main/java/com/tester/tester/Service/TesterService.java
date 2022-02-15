package com.tester.tester.Service;

import com.tester.tester.Mapper.TesterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TesterService {

    @Autowired
    TesterMapper testerMapper;
    public String getQualityTableInfo() {
        return testerMapper.getQualityTableInfo();
    }
}
