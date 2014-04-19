package com.orden.phoenix.tracker.model;

import java.util.Date;

/**
 * Created on 4/19/14.
 */
public class NoteModel {
    private int id;
    private Date creationDate;
    private String text;

    public NoteModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NoteModel)) return false;

        NoteModel note = (NoteModel) o;

        if (id != note.id) return false;
        if (creationDate != null ? !creationDate.equals(note.creationDate) : note.creationDate != null)
            return false;
        if (text != null ? !text.equals(note.text) : note.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
