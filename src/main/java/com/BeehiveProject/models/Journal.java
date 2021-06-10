package com.BeehiveProject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Journal {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer journalId;
    private Timestamp time;
    private String worker;
    private String text;

    public Journal() {
    }

    public Journal(Timestamp time, String worker, String text) {

        this.time = time;
        this.worker = worker;
        this.text = text;
    }

    public Integer getJournalId() {
        return journalId;
    }

    public void setJournalId(Integer journalId) {
        this.journalId = journalId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
