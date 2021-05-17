package android.unipu.diario.ui.entry;

import android.os.Bundle;
import android.unipu.diario.R;
import android.unipu.diario.adapter.CalendarViewAdapter;
import android.unipu.diario.adapter.ListViewAdapter;
import android.unipu.diario.db.EntryDatabase;
import android.unipu.diario.ui.calendar.CalendarFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EntryFragment extends Fragment implements ListViewAdapter.OnItemClickListener, CalendarViewAdapter.OnItemClickListener {

    private ListViewAdapter lAdapter;
    private CalendarViewAdapter cAdapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_entry, container, false);
        recyclerView = root.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        Fragment parent = getParentFragment();
        if (parent.getClass().equals("class android.unipu.diario.ui.calendar.CalendarFragment")) {
            cAdapter = new CalendarViewAdapter(EntryDatabase.getInstance(getActivity()).getEntries(), this);
            recyclerView.setAdapter(cAdapter);
        } else {
            lAdapter = new ListViewAdapter(EntryDatabase.getInstance(getActivity()).getEntries(), this);
            recyclerView.setAdapter(lAdapter);
        }

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