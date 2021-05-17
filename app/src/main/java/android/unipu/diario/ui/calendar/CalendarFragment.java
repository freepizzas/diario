package android.unipu.diario.ui.calendar;

import android.os.Bundle;
import android.unipu.diario.adapter.ListViewAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.unipu.diario.R;
import android.widget.CalendarView;

import java.util.Date;

public class CalendarFragment extends Fragment implements ListViewAdapter.OnItemClickListener {
    private CalendarView calendarView;
    private long currentDate;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarView = root.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    currentDate = calendarView.getDate();
                }


        });
        return root;
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onLongClicked(View view, int position) {

    }
}