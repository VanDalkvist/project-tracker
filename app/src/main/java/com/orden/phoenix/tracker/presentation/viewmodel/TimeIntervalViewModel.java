package com.orden.phoenix.tracker.presentation.viewmodel;

import java.util.Date;

/**
 * Created by I_van on 03.06.2014.
 *
 * @author I_van
 */
public class TimeIntervalViewModel {

    private Date from;
    private Date to;

    public TimeIntervalViewModel() {
    }

    public long getDifference() {
        return to == null ? System.currentTimeMillis() - from.getTime() : to.getTime() - from.getTime();
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
        if (!(o instanceof TimeIntervalViewModel)) return false;

        TimeIntervalViewModel that = (TimeIntervalViewModel) o;

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