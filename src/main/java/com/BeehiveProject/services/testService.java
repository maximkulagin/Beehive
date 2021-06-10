package com.BeehiveProject.services;

import com.BeehiveProject.models.Alert;
import com.BeehiveProject.models.Device;
import com.BeehiveProject.models.DeviceData;
import com.BeehiveProject.repo.AlertRepository;
import com.BeehiveProject.repo.DeviceDataRepository;
import com.BeehiveProject.repo.DeviceRepository;
import com.BeehiveProject.repo.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class testService {
    //запуск с определенной периодичностью
    private static final String CRON = "*/30 * * * * *";
    @Autowired
    private DeviceDataRepository deviceDataRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private AlertRepository alertRepository;

    public testService() {

    }

    @Scheduled(cron = CRON)
    public void testAdd() {
        Random random = new Random();
        float temp = 15 + random.nextFloat() * (40 - 15);
        float noiseLevel = 40 + random.nextFloat() * (75 - 40);
        float humidity = 20 + random.nextFloat() * (100 - 20);
        float hesitation = 0 + random.nextFloat() * (5 - 0);
        int light = random.nextInt(50000);
        float battery = 9 + random.nextFloat() * (14 - 9);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DeviceData data1 = new DeviceData(1000, temp, noiseLevel, humidity, hesitation, light, battery, timestamp);
        deviceDataRepository.save(data1);
        Optional<Device> device1 = deviceRepository.findById(1000);
        if (device1.isPresent()) {
            Device dev = device1.get();
            dev.setLastDateSeen(timestamp);
            deviceRepository.save(dev);
        }

        temp = 5 + random.nextFloat() * (40 - 5);
        noiseLevel = 40 + random.nextFloat() * (60 - 40);
        humidity = 5 + random.nextFloat() * (100 - 5);
        hesitation = 0 + random.nextFloat() * (1 - 0);
        light = random.nextInt(50000);
        battery = 10 + random.nextFloat() * (15 - 10);
        timestamp = new Timestamp(System.currentTimeMillis());
        DeviceData data2 = new DeviceData(577, temp, noiseLevel, humidity, hesitation, light, battery, timestamp);
        deviceDataRepository.save(data2);
        Optional<Device> device2 = deviceRepository.findById(577);
        if (device2.isPresent()) {
            Device dev = device2.get();
            dev.setLastDateSeen(timestamp);
            deviceRepository.save(dev);
        }

        this.clean();
        this.checkAlerts(data1);
        this.checkAlerts(data2);
    }

    public void clean() {
        alertRepository.deleteAll();
    }

    public void createAlert(Integer deviceId, String deviceName, String message) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Alert alert = new Alert(deviceId, deviceName, message, timestamp);
        alertRepository.save(alert);
    }

    public void checkAlerts(DeviceData data) {
        int id = data.getDeviceId();
        Optional<Device> device = deviceRepository.findById(id);
        if (device.isPresent()) {
            Device dev = device.get();
            //if (dev.getActive() != null)
                if (dev.getActive()) {
                    if (data.getBatteryVoltage() < 12)
                        createAlert(id, dev.getName(), "Низкий заряд аккумулятора устройства: " + data.getBatteryVoltage());
                    if (data.getTemp() < 10)
                        createAlert(id, dev.getName(), "Низкая температура улья: " + data.getTemp());
                    if (data.getTemp() > 35)
                        createAlert(id, dev.getName(), "Высокая температура улья: " + data.getTemp());
                    if (data.getNoiseLevel() > 70)
                        createAlert(id, dev.getName(), "Высокий уровень шума: " + data.getNoiseLevel());
                    if (data.getHumidity() > 95)
                        createAlert(id, dev.getName(), "Высокая влажность в улье: " + data.getHumidity());
                    if (data.getHumidity() < 10)
                        createAlert(id, dev.getName(), "Низкая влажность в улье: " + data.getHumidity());
                    if (data.getHesitation() > 1)
                        createAlert(id, dev.getName(), "Обнаружено физическое воздействие на улей");

                }
        }
    }
}
