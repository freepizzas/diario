package android.unipu.diario.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.unipu.diario.ui.diary.DiaryFragment;
import android.unipu.diario.ui.diary.DiaryViewModel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.unipu.diario.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private CardView writeBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }
}