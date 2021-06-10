package com.BeehiveProject.controllers;
import com.BeehiveProject.models.Alert;
import com.BeehiveProject.models.Device;
import com.BeehiveProject.models.DeviceData;
import com.BeehiveProject.models.Journal;
import com.BeehiveProject.repo.AlertRepository;
import com.BeehiveProject.repo.DeviceDataRepository;
import com.BeehiveProject.repo.DeviceRepository;
import com.BeehiveProject.repo.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private DeviceDataRepository deviceDataRepository;
    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        Iterable<Alert> alerts = alertRepository.findAll();
        Iterable<Device> devices= deviceRepository.findAll();
        ArrayList<Device> active = new ArrayList<Device>();
        ArrayList<Alert> activeAlerts = new ArrayList<Alert>();
        for (Device device:
             devices) {
            if(device.getActive())
                active.add(device);
        }
        model.addAttribute("devices", active);
        model.addAttribute("alerts", alerts);
        return "home";
    }

    @GetMapping("/data")
    public String data(Model model) {
        model.addAttribute("title", "Данные с устройств");
        Iterable<DeviceData> deviceData = deviceDataRepository.findAll();
        model.addAttribute("deviceData", deviceData);
        return "data";
    }
}