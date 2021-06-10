package com.BeehiveProject.controllers;

import com.BeehiveProject.models.Device;
import com.BeehiveProject.repo.AlertRepository;
import com.BeehiveProject.repo.DeviceDataRepository;
import com.BeehiveProject.repo.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeviceController {
    @Autowired
    private DeviceDataRepository deviceDataRepository;
    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @GetMapping("/devices")
    public String devices(Model model) {
        model.addAttribute("title", "Устройства");
        Iterable<Device> devices= deviceRepository.findAll();
        model.addAttribute("devices", devices);
        return "devices";
    }
    @GetMapping("/devices/add")
    public String deviceAdd(Model model) {
        model.addAttribute("title", "Новое устройство");
        return "deviceAdd";
    }
    @PostMapping("/devices/add")
    public String devicePostAdd(@RequestParam Integer deviceId, @RequestParam String name, @RequestParam(defaultValue = "false") Boolean active, Model model) {
        Device device = new Device(deviceId, name, active);
        deviceRepository.save(device);
        return "redirect:/devices";
    }
    @PostMapping("/devices/{id}/delete")
    public String deleteDevice(@PathVariable(value = "id") int id, Model model) {
        // Находим нужную запись по ID
        Device device = deviceRepository.findById(id).orElseThrow();
        deviceRepository.delete(device); // Выполняем удаление

        // Возвращаем на главную страницу
        return "redirect:/devices";
    }
    @PostMapping("/devices/{id}/edit")
    public String editDevice(@PathVariable(value = "id") int id, Model model) {
        // Находим нужную запись по ID
        Device device = deviceRepository.findById(id).orElseThrow();
        model.addAttribute("device", device);
        return "deviceEdit";
    }
    @PostMapping("/devices/edit/post")
    public String editDevicePost(@RequestParam Integer deviceId, @RequestParam String name, @RequestParam(defaultValue = "false") Boolean active, Model model) {
        Device device = deviceRepository.findById(deviceId).orElseThrow();
        device.setName(name);
        device.setActive(active);
        deviceRepository.save(device);
        return "redirect:/devices";
    }
}
