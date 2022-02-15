package com.tester.tester.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Mapper
public interface TesterMapper {
    String getQualityTableInfo();
}
