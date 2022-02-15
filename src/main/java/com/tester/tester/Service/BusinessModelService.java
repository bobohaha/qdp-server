package com.tester.tester.Service;

import com.tester.tester.Entity.BusinessModelEntity;
import com.tester.tester.Mapper.BusinessModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessModelService {

    @Autowired
    BusinessModelMapper businessModelMapper;
    public List<BusinessModelEntity> getBusinessModelListByBusiness(String business) {
        return businessModelMapper.getBusinessModelListByBusiness(business);
    }

    public List<BusinessModelEntity> getBusinessModelList() {
        return businessModelMapper.getBusinessModelList();
    }
}
