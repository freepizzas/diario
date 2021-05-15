package android.unipu.diario.data.model;

import java.util.Calendar;
import java.util.Locale;

public class Entry {

    private String id;
    private String title;
    private String body;
    private Calendar calendar;

    public String getHourAndMin() {
        String hour = Integer.toString(calendar.get(Calendar.HOUR));
        if (hour.length() == 1) {
            hour = "0" + hour;
        }
        String min = Integer.toString(calendar.get(Calendar.MINUTE));
        if (min.length() == 1) {
            min = "0" + min;
        }
        return hour + ":" + min;
    }

    public String getDayOfMonth() {
        String dayOfMon = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        if (dayOfMon.length() == 1) {
            return "0" + dayOfMon;
        } else {
            return dayOfMon;
        }
    }

    public String getDayOfWeek() {
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    }

    public String getMonthAndYear() {
        return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + calendar.get(Calendar.YEAR);

    }

    public Entry(String id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.calendar = Calendar.getInstance();
    }

    public Entry(String id, String title, String body, Calendar calendar) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.calendar = calendar;
    }
}