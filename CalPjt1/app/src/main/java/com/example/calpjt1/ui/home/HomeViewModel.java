package com.example.calpjt1.ui.home;
        import android.widget.CalendarView;

        import androidx.annotation.NonNull;
        import androidx.lifecycle.LiveData;
        import androidx.lifecycle.MutableLiveData;
        import androidx.lifecycle.ViewModel;

        import java.text.DateFormat;
        import java.text.SimpleDateFormat;

public class HomeViewModel extends ViewModel implements CalendarView.OnDateChangeListener {

    private MutableLiveData<String> mText;
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();


    public HomeViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        mText.setValue(FORMATTER.format(view.getDate()));
    }
}