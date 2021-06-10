package com.BeehiveProject.services;

import com.BeehiveProject.models.Alert;
import com.BeehiveProject.models.Device;
import com.BeehiveProject.models.DeviceData;
import com.BeehiveProject.repo.AlertRepository;
import com.BeehiveProject.repo.DeviceDataRepository;
import com.BeehiveProject.repo.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Random;

@Service
public class alertService {
    //private static final String CRON = "*/5 * * * * *";
    @Autowired
    private DeviceDataRepository deviceDataRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private AlertRepository alertRepository;

    public void clean(){
        alertRepository.deleteAll();
    }
    public void createAlert(Integer deviceId, String deviceName, String message){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Alert alert = new Alert(deviceId, deviceName, message, timestamp);
        alertRepository.save(alert);
    }
    //@Scheduled(cron = CRON)
    public void checkAlerts(DeviceData data) {
        int id = data.getDeviceId();
        Optional<Device> device = deviceRepository.findById(id);
        if(device.isPresent()){
            Device dev = device.get();
            if (dev.getActive() != null && dev.getActive() != false){
                if(data.getBatteryVoltage() < 12 )
                    createAlert(id, dev.getName(), "Низкий заряд аккумулятора устройства");
                if(data.getTemp() < 10 )
                    createAlert(id, dev.getName(), "Низкая температура улья");
            }
        }
        /*alertRepository.deleteAll();
        Iterable<DeviceData> data = deviceDataRepository.findAll();
        Iterable<Device> devices = deviceRepository.findAll();
        for (Device device:
             devices) {
            if (device.getActive() != null && device.getActive() != false)
            {
                boolean flag = false;
                DeviceData data = deviceDataRepository.findAll();
            }
        }*/

    }
}
