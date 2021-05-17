package android.unipu.diario.adapter;

import android.content.Context;
import android.unipu.diario.R;
import android.unipu.diario.data.model.Entry;
import android.unipu.diario.db.EntryDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalendarViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Context context;
    public static Date currentDate;
    public ArrayList<Entry> entries;
    private final OnItemClickListener onItemClickListener;

    // Constructor
    public CalendarViewAdapter(ArrayList<Entry> entries, OnItemClickListener onItemClickListener) {
        this.currentDate = new Date();
        this.entries = entries;
        this.onItemClickListener = onItemClickListener;
    }

    // Adapter Methods
    @Override
    public int getItemCount() {
        return entries.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entry_list, parent, false);
            return new CalendarViewHolder(v);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            Entry entry = entries.get(position);
//            EntryViewHolder holder = (EntryViewHolder) viewHolder;
//            holder.entryTitle.setText(entry.title);
//            holder.entryBody.setText(entry.getBodyNoNLines());

    }

    public class CalendarViewHolder extends RecyclerView.ViewHolder {
        private CalendarView calendarView;

        public CalendarViewHolder(final ViewGroup itemView) {
            super(itemView);
//            calendarView = itemView.findViewById(R.id.calendarView);
//            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//                @Override
//                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                    refreshEntries();
//
//                }
//            });
        }
    }

    public void refreshEntries() {
        entries = EntryDatabase.getInstance(context).getEntriesOnDate(currentDate);
        notifyDataSetChanged();
        if (entries.isEmpty()) {
            SimpleDateFormat fmt = new SimpleDateFormat("dd MMM yyyy");
            Toast.makeText(context, "No entries found for " + fmt.format(currentDate), Toast.LENGTH_SHORT).show();
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
        void onLongClicked(View view, int position);
    }
}