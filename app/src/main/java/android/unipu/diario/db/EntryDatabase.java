package android.unipu.diario.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.unipu.diario.data.model.Entry;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class EntryDatabase {

    private static final String TABLE_ENTRIES = "entries_table";
    private static final String LIST_ENTRIES = "entries_list";

    private Gson gson;
    private Context context;
    private int index = 2;

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
            String json = preferences.getString(LIST_ENTRIES, "");
            entries = gson.fromJson(json, EntryWrapper.class).getEntries();
        }
    }

    private ArrayList<Entry> getDummyEntries() {
        entries = new ArrayList<>();
        entries.add(new Entry(UUID.randomUUID().toString(), false, "Entry #1", "This is a sample diary entry. Hehe!", new Date()));
        return entries;
    }

    private ArrayList<Entry> entries;

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public ArrayList<Entry> getEntriesOnDate(String date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Entry> entriesOnDate = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            boolean isSameDay = date.equals(df.format(entries.get(i).date));
            if (isSameDay) {
                entriesOnDate.add(entries.get(i));
            }
        }
        return entriesOnDate;
    }

    public Integer augmentEntryN(){
        return index++;
    }

    public Integer getEntryN(){
        return index;
    }

    public Entry getEntryByID(String entryId) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).id.equals(entryId)) {
                return entries.get(i);
            }
        }
        return null;
    }

    public void addEntry(Entry entry) {
        entries.add(0, entry);
        commit();
    }

    public void editEntry(String entryId, String body) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).id.equals(entryId)) {
                entries.get(i).setBody(body);
            }
        }
        commit();
    }

    public void deleteEntryAt(int position) {
        entries.remove(position);
        commit();
    }
    
    public void commit() {
        String json = gson.toJson(new EntryWrapper(entries));
        SharedPreferences.Editor editor = context.getSharedPreferences(TABLE_ENTRIES, Context.MODE_PRIVATE).edit();
        editor.putString(LIST_ENTRIES, json);
        editor.commit();
    }
}