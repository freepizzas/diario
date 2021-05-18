package android.unipu.diario.ui.entry;

import android.content.Context;
import android.os.Bundle;
import android.unipu.diario.R;
import android.unipu.diario.data.model.Entry;
import android.unipu.diario.db.EntryDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

public class EditEntryFragment extends Fragment {

    private TextView entryTitle;
    private EditText entryBody;
    private Entry entry;
    private String entryId;
    private FloatingActionButton editBtn;
    Context thisContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_diary, container, false);
        thisContext = container.getContext();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            entryId = bundle.getString("entry_id");
            entry = EntryDatabase.getInstance(getContext()).getEntryByID(entryId);
        }
        entryTitle = root.findViewById(R.id.entry_title);
        entryTitle.setText("Editing " + entry.title);
        entryBody = root.findViewById(R.id.entry_body);
        entryBody.setText(entry.body);
        editBtn = root.findViewById(R.id.saveBtn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EntryDatabase.getInstance(thisContext).editEntry(entryId, entryBody.getText().toString());
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.navigation_home);
            }
        });
        return root;
    }
}