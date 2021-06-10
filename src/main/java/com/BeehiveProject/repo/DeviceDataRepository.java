package com.BeehiveProject.repo;

import com.BeehiveProject.models.Alert;
import com.BeehiveProject.models.DeviceData;
import org.springframework.data.repository.CrudRepository;

public interface DeviceDataRepository extends CrudRepository<DeviceData, Integer> {
}
