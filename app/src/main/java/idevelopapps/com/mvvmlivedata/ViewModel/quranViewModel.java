package idevelopapps.com.mvvmlivedata.ViewModel;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import idevelopapps.com.mvvmlivedata.Model.quranModel;
import idevelopapps.com.mvvmlivedata.Repository.quranRepository;

public class quranViewModel extends AndroidViewModel implements LifecycleObserver {

    MutableLiveData<ArrayList<quranModel>>  list;
    private quranRepository repository;
    public quranViewModel(@NonNull Application application) {
        super(application);
        if(list != null){
            return;
        }
        repository = quranRepository.getInstance();
        repository.fetchSura();
        list = repository.getSura();

    }
//    public  void init(){
//     if(list != null){
//         return;
//     }
//     repository = quranRepository.getInstance();
//     //repository.fetchSura();
//     list = repository.getSura();
//
//    }

  public  LiveData<ArrayList<quranModel>> getList(){
        return list;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void checkData(){
        repository.getSura();
    }
}
