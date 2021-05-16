package android.unipu.diario.adapter;

import android.content.Context;
import android.unipu.diario.R;
import android.unipu.diario.data.model.Entry;
import android.unipu.diario.db.EntryDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_CALENDAR = 1;
    private static final int VIEW_TYPE_ENTRIES = 2;

    public Context context;
    public static Date currentDate;
    public ArrayList<Entry> entries;
    private final OnItemClickListener onItemClickListener;

    // Constructor
    public RecyclerViewAdapter(ArrayList<Entry> entries, OnItemClickListener onItemClickListener) {
        this.currentDate = new Date();
        this.entries = entries;
        this.onItemClickListener = onItemClickListener;
    }

    // Adapter Methods
    @Override
    public int getItemCount() {
        return entries.size() + 1;
    }
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_CALENDAR;
        } else {
            return VIEW_TYPE_ENTRIES;
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_CALENDAR) {
            ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entry_list, parent, false);
            return new CalendarViewHolder(v);
        } else {
            ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entry_list, parent, false);
            return new EntryViewHolder(v, onItemClickListener);
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_ENTRIES) {
            Entry entry = entries.get(position - 1);
            EntryViewHolder holder = (EntryViewHolder) viewHolder;
            holder.entryTitle.setText(entry.title);
            holder.entryBody.setText(entry.getBodyNoNLines());
        }
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

    public class EntryViewHolder extends RecyclerView.ViewHolder {
        private View entryItem;
        private TextView entryTitle;
        private TextView entryBody;

        public EntryViewHolder(final ViewGroup itemView, final OnItemClickListener onItemClickListener) {
            super(itemView);
            entryItem = itemView.findViewById(R.id.entry_item);
            entryTitle = itemView.findViewById(R.id.entry_title);
            entryBody = itemView.findViewById(R.id.entry_body);

            entryItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClicked(getAdapterPosition());
                }
            });
            entryItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemClickListener.onLongClicked(view, getAdapterPosition());
                    return true;
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
        void onLongClicked(View view, int position);
    }
}