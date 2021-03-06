package android.unipu.diario.ui.entry;

import android.content.Context;
import android.os.Bundle;
import android.unipu.diario.data.model.Entry;
import android.unipu.diario.db.EntryDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.unipu.diario.R;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.UUID;

public class NewEntryFragment extends Fragment {

    private TextView entryTitle;
    private EditText entryBody;
    private Integer indexEntry;
    private FloatingActionButton saveBtn;
    Context thisContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_newentry, container, false);
        thisContext = container.getContext();
        entryTitle = root.findViewById(R.id.entry_title);
        indexEntry = EntryDatabase.getInstance(thisContext).getEntryN();
        entryTitle.setText("Entry #" + indexEntry);
        entryBody = root.findViewById(R.id.entry_body);
        saveBtn = root.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EntryDatabase.getInstance(thisContext).addEntry(new Entry(UUID.randomUUID().toString(), false, entryTitle.getText().toString(), entryBody.getText().toString(), new Date()));
                Toast.makeText(getActivity(), "Created new entry",
                        Toast.LENGTH_LONG).show();
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.navigation_home);
            }
        });
        return root;
    }
}