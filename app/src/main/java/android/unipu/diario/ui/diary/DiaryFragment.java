package android.unipu.diario.ui.diary;

import android.content.Context;
import android.os.Bundle;
import android.unipu.diario.db.EntryDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.unipu.diario.R;
import android.widget.EditText;
import android.widget.TextView;

public class DiaryFragment extends Fragment {

    private TextView entryTitle;
    private EditText entryBody;
    private Integer index;
    Context thisContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_diary, container, false);
        thisContext = container.getContext();

        entryTitle = root.findViewById(R.id.entry_title);
        index = EntryDatabase.getInstance(thisContext).getLastIdNoQ();
        entryTitle.setText("Entry #" + index);

        entryBody = root.findViewById(R.id.entry_body);

        return root;
    }
}