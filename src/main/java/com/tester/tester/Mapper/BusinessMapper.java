package com.tester.tester.Mapper;

import com.tester.tester.Entity.BusinessEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Mapper
public interface BusinessMapper {
    List<BusinessEntity> getBusinessList();
}