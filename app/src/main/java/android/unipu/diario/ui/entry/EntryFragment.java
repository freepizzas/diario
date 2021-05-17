package android.unipu.diario.ui.entry;

import android.os.Bundle;
import android.unipu.diario.R;
import android.unipu.diario.adapter.ListViewAdapter;
import android.unipu.diario.data.model.Entry;
import android.unipu.diario.db.EntryDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EntryFragment extends Fragment implements ListViewAdapter.OnItemClickListener {

    private ListViewAdapter lAdapter;
    private RecyclerView recyclerView;
    public ArrayList<Entry> entries;
    public String selectedDate;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_entry, container, false);
        recyclerView = root.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            selectedDate = bundle.getString("selectedDate");
            entries = EntryDatabase.getInstance(getActivity()).getEntriesOnDate(selectedDate);
        } else {
            entries = EntryDatabase.getInstance(getActivity()).getEntries();
        }

        lAdapter = new ListViewAdapter(entries, this);
        recyclerView.setAdapter(lAdapter);

        return root;
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onLongClicked(View view, int position) {

    }

//    @Override
//    public void onItemClicked(int position) {
//        Intent intent = new Intent(getActivity(), ViewActivity.class);
//        intent.putExtra(ViewActivity.ACTION_TYPE_KEY, ViewActivity.ACTION_TYPE_EDIT);
//        intent.putExtra(ViewActivity.NOTE_ID_KEY, adapter.notes.get(position).id);
//        startActivity(intent);
//    }
//    @Override
//    public void onLongClicked(View view, final int position) {
//        final Context context = getContext();
//        PopupMenu popupMenu = new PopupMenu(context, view);
//        popupMenu.inflate(R.menu.menu_delete);
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//
//                    case R.id.note_delete:
//                        new AlertDialog.Builder(context)
//                                .setTitle("Delete entry")
//                                .setMessage("Are you sure you want to delete this entry?")
//                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        NotesDatabase.getInstance(context).deleteNoteAt(position);
//                                        adapter.notifyItemRemoved(position);
//                                        refreshLayout();
//                                    }
//                                })
//                                .setNegativeButton(android.R.string.no, null)
//                                .show();
//                        return true;
//
//                    default:
//                        return false;
//                }
//            }
//        });
//        popupMenu.show();
//    }
}