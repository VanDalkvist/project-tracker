package com.orden.phoenix.tracker.model;

import java.util.Date;

/**
 * Created on 4/19/14.
 */
public class TimeIntervalModel {
    private Date from;
    private Date to;

    public TimeIntervalModel() {
    }

    public long getDifference() {
        return to == null ? System.currentTimeMillis() - from.getTime() : to.getTime() - from.getTime();
    }

    public static void foo() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeIntervalModel)) return false;

        TimeIntervalModel that = (TimeIntervalModel) o;

        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        if (to != null ? !to.equals(that.to) : that.to != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }
}
