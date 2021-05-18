package android.unipu.diario.ui.entry;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.unipu.diario.R;
import android.unipu.diario.data.model.Entry;
import android.unipu.diario.db.EntryDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class EditEntryFragment extends Fragment {

    private TextView entryTitle;
    private EditText entryBody;
    private Entry entry;
    private String entryId;
    private FloatingActionButton editBtn;
    private ImageButton exportBtn;
    Context thisContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editentry, container, false);
        thisContext = container.getContext();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            entryId = bundle.getString("entry_id");
            entry = EntryDatabase.getInstance(getContext()).getEntryByID(entryId);
            entryTitle = root.findViewById(R.id.entry_title);
            entryTitle.setText("Editing " + entry.title);
            entryBody = root.findViewById(R.id.entry_body);
            entryBody.setText(entry.body);

            editBtn = root.findViewById(R.id.saveBtn);
            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EntryDatabase.getInstance(thisContext).editEntry(entryId, entryBody.getText().toString());
                    Toast.makeText(getActivity(), "Entry successfully saved",
                            Toast.LENGTH_LONG).show();
                }
            });
        }


        exportBtn = root.findViewById(R.id.exportBtn);
        exportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entryTitle = entry.title.replace(" ", "");
                File outFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), entryTitle + ".txt");
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter(outFile));
                    out.write(entry.toString());
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return root;
    }
}