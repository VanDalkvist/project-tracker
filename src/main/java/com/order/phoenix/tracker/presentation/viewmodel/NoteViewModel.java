package com.order.phoenix.tracker.presentation.viewmodel;

import java.util.Date;

/**
 * Created on 4/19/14.
 */
public class NoteViewModel extends AbstractViewModel {
    private Date creationDate;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        setIfChanged(text, "text");
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        setIfChanged(creationDate, "creationDate");
    }
}
