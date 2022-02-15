package com.tester.tester.Controller;

import com.tester.tester.Entity.DeviceEntity;
import com.tester.tester.Entity.ResultEntity;
import com.tester.tester.Service.DeviceService;
import com.tester.tester.Util.OkHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @CrossOrigin
    @GetMapping("/api/data/device")
    public ResultEntity getDeviceInfoData() {
        List<DeviceEntity> deviceData = deviceService.getDeviceList();
        return OkHttpUtil.success(deviceData);
    }

    @CrossOrigin
    @PostMapping("/api/data/device/delete")
    public ResultEntity deleteDeviceInfo(@RequestBody DeviceEntity deviceEntity) {
        String deviceId = deviceEntity.getDeviceId();
        int result = deviceService.deleteDevice(deviceId);
        return OkHttpUtil.success(result);
    }

    @CrossOrigin
    @PostMapping("/api/data/device/add")
    public ResultEntity addDeviceInfo(@RequestBody DeviceEntity deviceEntity) {
        int result = deviceService.addDevice(deviceEntity);
        return OkHttpUtil.success(result);
    }

    @CrossOrigin
    @PostMapping("/api/data/device/edit")
    public ResultEntity editDeviceInfo(@RequestBody DeviceEntity deviceEntity) {
        int result = deviceService.editDevice(deviceEntity);
        return OkHttpUtil.success(result);
    }
}
