package android.unipu.diario.data.model;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Entry {

    public Integer id;
    public Boolean question;
    public String title;
    public String body;
    public Date date;

    public String getBodyNoNLines() {
        return body.replace("\n", " ");
    }

    public Entry(Integer id, Boolean question, String title, String body, Date date) {
        this.id = id;
        this.question = question;
        this.title = title;
        this.body = body;
        this.date = date;
    }
}