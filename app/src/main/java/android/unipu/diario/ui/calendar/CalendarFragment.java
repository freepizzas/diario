package android.unipu.diario.ui.calendar;

import android.os.Bundle;
import android.unipu.diario.ui.entry.EntryListItemFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.unipu.diario.R;
import android.widget.CalendarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarFragment extends Fragment{
    private CalendarView calendarView;
    public String selectedDate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarView = root.findViewById(R.id.calendarView);

        calendarView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar calendar = Calendar.getInstance();
                    selectedDate = df.format(calendar.getTime());
                    Bundle bundle = new Bundle();
                    bundle.putString("selectedDate", selectedDate);
                    EntryListItemFragment entryListItemFragment = new EntryListItemFragment();
                    entryListItemFragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                            entryListItemFragment).addToBackStack(null).commit();
            }
            @Override
            public void onViewDetachedFromWindow(View v) {
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                selectedDate = df.format(calendar.getTime());
                Log.i("mess", selectedDate);
                Bundle bundle = new Bundle();
                bundle.putString("selectedDate", selectedDate);
                EntryListItemFragment entryListItemFragment = new EntryListItemFragment();
                entryListItemFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                        entryListItemFragment).addToBackStack(null).commit();
            }
        });
        return root;
    }
}