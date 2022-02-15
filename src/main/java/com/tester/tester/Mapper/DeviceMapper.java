package com.tester.tester.Mapper;


import com.tester.tester.Entity.DeviceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Mapper
public interface DeviceMapper {
    List<DeviceEntity> getDeviceList();
    int deleteDevice(String deviceId);
    int addDevice(DeviceEntity deviceEntity);
    int editDevice(DeviceEntity deviceEntity);
    List<DeviceEntity> getDeviceData(int type);
}
