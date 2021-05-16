package android.unipu.diario.ui.entry;

import android.os.Bundle;
import android.unipu.diario.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class EntryFragment extends Fragment {

    private android.unipu.diario.ui.entry.EntryViewModel entryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        entryViewModel =
                new ViewModelProvider(this).get(android.unipu.diario.ui.entry.EntryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_entry, container, false);
        return root;
    }
}