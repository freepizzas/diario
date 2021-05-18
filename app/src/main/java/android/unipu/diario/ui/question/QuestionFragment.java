package android.unipu.diario.ui.question;

import android.content.Context;
import android.os.Bundle;
import android.unipu.diario.data.model.Entry;
import android.unipu.diario.db.EntryDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.unipu.diario.R;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class QuestionFragment extends Fragment {

    private TextView entryTitle;
    private EditText entryBody;
    private FloatingActionButton saveBtn;
    private ImageButton newQBtn;
    Context thisContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_question, container, false);
        thisContext = container.getContext();

        entryTitle = root.findViewById(R.id.entry_title);
        entryTitle.setText(getQuestions(false));

        entryBody = root.findViewById(R.id.entry_body);

        saveBtn = root.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EntryDatabase.getInstance(thisContext).addEntry(new Entry(UUID.randomUUID().toString(), true, entryTitle.getText().toString(), entryBody.getText().toString(), new Date()));
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.navigation_home);
            }
        });

        newQBtn = root.findViewById(R.id.newQBtn);
        newQBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entryTitle.setText(getQuestions(true));
            }
        });
        return root;
    }

    private String getQuestions(Boolean random) {
        Random rand = new Random();
        List<String> questions = new ArrayList<>();
        questions.add("What’s your biggest regret?");
        questions.add("What’s an ideal weekend for you?");
        questions.add("What is one dream you have yet to accomplish?");
        questions.add("Where would you like to live in the world?");
        questions.add("What would you change in your life?");
        questions.add("Who is your favorite historical figure?");
        questions.add("Dogs or Cats?");
        questions.add("If you could get away with anything that you do?");
        questions.add("What fictional character do you most relate to?");
        if(random) {
            return questions.get(rand.nextInt(questions.size()));
        } else return questions.get(1);
    }
}