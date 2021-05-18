package android.unipu.diario.data.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Entry implements Serializable {

    public String id;
    public Boolean question;
    public String title;
    public String body;
    public Date date;

    public String getBodyNoNLines() {
        return body.replace("\n", " ");
    }

    public Entry(String id, Boolean question, String title, String body, Date date) {
        this.id = id;
        this.question = question;
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Date: " + this.date.toString() + System.getProperty("line.separator") +
                "Title: " + this.title + System.getProperty("line.separator") +
                "Text: " + this.body;
    }
}