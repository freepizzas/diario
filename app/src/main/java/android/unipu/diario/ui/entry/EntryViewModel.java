package android.unipu.diario.ui.entry;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EntryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EntryViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is diary fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}