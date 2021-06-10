package com.BeehiveProject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Device {
    @Id
    private Integer id;

    private String name;

    private Boolean active;

    private Timestamp lastDateSeen;

    public Device(Integer id, String name, Boolean active, Timestamp lastDateSeen) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.lastDateSeen = lastDateSeen;
    }

    public Device() {
    }

    public Device(Integer id, String name, Boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Timestamp getLastDateSeen() {
        return lastDateSeen;
    }

    public void setLastDateSeen(Timestamp lastDateSeen) {
        this.lastDateSeen = lastDateSeen;
    }
}
