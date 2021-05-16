package android.unipu.diario.db;

import android.unipu.diario.data.model.Entry;

import java.util.ArrayList;

public class EntryWrapper {

    private ArrayList<Entry> entries;

    public EntryWrapper(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }
}