package android.unipu.diario.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.unipu.diario.data.model.Entry;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EntryDatabase {

    private static final String TABLE_ENTRIES = "entries_table";
    private static final String LIST_ENTRIES = "entries_list";

    private Gson gson;
    private Context context;

    private static EntryDatabase instance;

    public static EntryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new EntryDatabase(context);
        }
        return instance;
    }

    private EntryDatabase(Context context) {
        this.context = context;
        SharedPreferences preferences = context.getSharedPreferences(TABLE_ENTRIES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (gson == null) {
            gson = new Gson();
        }
        if (!preferences.contains(LIST_ENTRIES)) {
            entries = getDummyEntries();
            String json = gson.toJson(new EntryWrapper(entries));
            editor.putString(LIST_ENTRIES, json);
            editor.apply();
        } else {
            // Load notes from storage
            String json = preferences.getString(LIST_ENTRIES, "");
            entries = gson.fromJson(json, EntryWrapper.class).getEntries();
        }
    }

    private ArrayList<Entry> getDummyEntries() {
        entries = new ArrayList<>();
        entries.add(new Entry(1, false, "Entry #1", "ahhhhhhhhhhhh"));
        return entries;
    }

    private ArrayList<Entry> entries;

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public ArrayList<Entry> getEntriesOnDate(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        ArrayList<Entry> entriesOnDate = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            boolean isSameDay = fmt.format(date).equals(fmt.format(entries.get(i).calendar.getTime()));
            if (isSameDay) {
                entriesOnDate.add(entries.get(i));
            }
        }
        return entriesOnDate;
    }

    public Integer getLastIdNoQ() {
        int index = 1;
        for (int i = 0; i < entries.size(); i++) {
            if (!entries.get(i).question) {
                index++;
            }
        }
        return index;
    }

    public Integer getLastId() {
        int lastIndex = entries.size() - 1;
        return entries.get(lastIndex).id;
    }

    public Entry getEntryByID(Integer entryId) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).id == entryId) {
                return entries.get(i);
            }
        }
        return null;
    }

    public void addEntry(Entry entry) {
        entries.add(0, entry);
        commit();
    }

    public void deleteEntryAt(int position) {
        entries.remove(position);
        commit();
    }

    public void deleteEntryWithId(Integer entryId) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).id == entryId) {
                entries.remove(i);
                break;
            }
        }
        commit();
    }

    public void commit() {
        String json = gson.toJson(new EntryWrapper(entries));
        SharedPreferences.Editor editor = context.getSharedPreferences(TABLE_ENTRIES, Context.MODE_PRIVATE).edit();
        editor.putString(LIST_ENTRIES, json);
        editor.commit();
    }
}