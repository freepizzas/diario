package android.unipu.diario.ui.diary;

import android.content.Context;
import android.os.Bundle;
import android.unipu.diario.MainActivity;
import android.unipu.diario.data.model.Entry;
import android.unipu.diario.db.EntryDatabase;
import android.unipu.diario.ui.home.HomeFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.unipu.diario.R;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DiaryFragment extends Fragment {

    private TextView entryTitle;
    private EditText entryBody;
    private Integer indexEntry;
    private Integer indexTotal;
    private FloatingActionButton saveBtn;
    Context thisContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_diary, container, false);
        thisContext = container.getContext();

        entryTitle = root.findViewById(R.id.entry_title);
        indexEntry = EntryDatabase.getInstance(thisContext).getLastIdNoQ();
        entryTitle.setText("Entry #" + indexEntry);

        entryBody = root.findViewById(R.id.entry_body);
        Log.i("body", String.valueOf(entryBody.getText()));

        indexTotal = EntryDatabase.getInstance(thisContext).getLastId();
        saveBtn = root.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EntryDatabase.getInstance(thisContext).addEntry(new Entry(indexTotal, false, entryTitle.getText().toString(), entryBody.getText().toString()));
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.navigation_home);
            }
        });
        return root;
    }
}