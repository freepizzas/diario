package android.unipu.diario.ui.diary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.unipu.diario.R;

public class DiaryFragment extends Fragment {

    private android.unipu.diario.ui.diary.DiaryViewModel diaryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        diaryViewModel =
                new ViewModelProvider(this).get(android.unipu.diario.ui.diary.DiaryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_diary, container, false);
        final TextView textView = root.findViewById(R.id.text_diary);
        diaryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}