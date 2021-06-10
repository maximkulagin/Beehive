package com.BeehiveProject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;


//класс, описывающий аварии (устройство и тип ошибки)

    @Entity
    public class Alert {
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private Integer alertId;
        private Integer deviceId;
        private String deviceName;
        private String message;
        private Timestamp time;

        public Integer getAlertId() {
            return alertId;
        }

        public Alert() {
        }

        public Alert(Integer deviceId, String deviceName, String message, Timestamp time) {
            this.deviceId = deviceId;
            this.deviceName = deviceName;
            this.message = message;
            this.time = time;
        }

        public void setAlertId(Integer alertId) {
            this.alertId = alertId;
        }

        public Integer getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(Integer deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Timestamp getTime() {
            return time;
        }

        public void setTime(Timestamp time) {
            this.time = time;
        }
    }

