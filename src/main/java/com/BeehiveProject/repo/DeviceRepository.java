package com.BeehiveProject.repo;

import com.BeehiveProject.models.Alert;
import com.BeehiveProject.models.Device;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<Device, Integer> {
}
