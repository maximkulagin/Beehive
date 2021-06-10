package com.BeehiveProject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class DeviceData {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer packetId;
    private Integer deviceId;
    private Float temp;
    private Float noiseLevel;
    private Float humidity;
    private Float hesitation;
    private Integer light;
    private Float batteryVoltage;
    private Timestamp time;

    public DeviceData() {
    }

    public DeviceData(Integer deviceId, Float temp, Float noiseLevel, Float humidity, Float hesitation, Integer light, Float batteryVoltage, Timestamp time) {
        this.deviceId = deviceId;
        this.temp = temp;
        this.noiseLevel = noiseLevel;
        this.humidity = humidity;
        this.hesitation = hesitation;
        this.light = light;
        this.batteryVoltage = batteryVoltage;
        this.time = time;
    }

    public Integer getPacketId() {
        return packetId;
    }

    public void setPacketId(Integer packetId) {
        this.packetId = packetId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Float getTemp() {
        return temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }

    public Float getNoiseLevel() {
        return noiseLevel;
    }

    public void setNoiseLevel(Float noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    public Float getHumidity() {
        return humidity;
    }

    public void setHumidity(Float humidity) {
        this.humidity = humidity;
    }

    public Float getHesitation() {
        return hesitation;
    }

    public void setHesitation(Float hesitation) {
        this.hesitation = hesitation;
    }

    public Integer getLight() {
        return light;
    }

    public void setLight(Integer light) {
        this.light = light;
    }

    public Float getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(Float batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
