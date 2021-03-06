package android.unipu.diario.ui.entry;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.unipu.diario.R;
import android.unipu.diario.adapter.EntryListViewAdapter;
import android.unipu.diario.data.model.Entry;
import android.unipu.diario.db.EntryDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EntryListItemFragment extends Fragment implements EntryListViewAdapter.OnItemClickListener {

    private EntryListViewAdapter lAdapter;
    private RecyclerView recyclerView;
    public ArrayList<Entry> entries;
    public String selectedDate;
    View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_entrylistitem, container, false);
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

        lAdapter = new EntryListViewAdapter(entries, this);
        recyclerView.setAdapter(lAdapter);

        return root;
    }

    @Override
    public void onItemClicked(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("entry_id", lAdapter.entries.get(position).id);
        EditEntryFragment editEntryFragment = new EditEntryFragment();
        editEntryFragment.setArguments(bundle);
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.navigation_editentry, bundle);

    }

    @Override
    public void onLongClicked(View view, final int position) {
        if (getParentFragment() != null) {
            final Context context = getContext();
            new AlertDialog.Builder(context)
                    .setTitle("Delete entry")
                    .setMessage("Are you sure you want to delete this entry?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            EntryDatabase.getInstance(context).deleteEntryAt(position);
                            lAdapter.notifyItemRemoved(position);
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        }
    }
}