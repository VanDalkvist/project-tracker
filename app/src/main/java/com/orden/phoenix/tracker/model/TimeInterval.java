package com.orden.phoenix.tracker.model;

import com.orden.phoenix.tracker.storage.Storable;

import java.util.Date;

/**
 * Created on 4/19/14.
 */
public class TimeInterval implements Storable {

    private String id;
    private Date from;
    private Date to;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}