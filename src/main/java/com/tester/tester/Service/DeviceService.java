package com.tester.tester.Service;

import com.tester.tester.Entity.DeviceEntity;
import com.tester.tester.Mapper.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    @Autowired
    DeviceMapper deviceMapper;

    public List<DeviceEntity> getDeviceList() {
        return deviceMapper.getDeviceList();
    }

    public int deleteDevice(String deviceId) {
        return deviceMapper.deleteDevice(deviceId);
    }

    public int addDevice(DeviceEntity deviceEntity) {
        return deviceMapper.addDevice(deviceEntity);
    }

    public int editDevice(DeviceEntity deviceEntity) {
        return deviceMapper.editDevice(deviceEntity);
    }

    public List<DeviceEntity> getDeviceData(int type) {
        return deviceMapper.getDeviceData(type);
    }
}
