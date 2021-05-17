package android.unipu.diario.adapter;

import android.content.Context;
import android.unipu.diario.R;
import android.unipu.diario.data.model.Entry;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Context context;
    public ArrayList<Entry> entries;
    private final OnItemClickListener onItemClickListener;

    public ListViewAdapter(ArrayList<Entry> entries, OnItemClickListener onItemClickListener) {
        this.entries = entries;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entry_list, parent, false);
            return new EntryViewHolder(v, onItemClickListener);

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            Entry entry = entries.get(position);
            EntryViewHolder holder = (EntryViewHolder) viewHolder;
            holder.entryTitle.setText(entry.title);
            holder.entryBody.setText(entry.getBodyNoNLines());

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