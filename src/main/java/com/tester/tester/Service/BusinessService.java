package com.tester.tester.Service;

import com.tester.tester.Entity.BusinessEntity;
import com.tester.tester.Mapper.BusinessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    @Autowired
    BusinessMapper businessMapper;
    public List<BusinessEntity> getBusinessList() {
       return businessMapper.getBusinessList();
    }
}
