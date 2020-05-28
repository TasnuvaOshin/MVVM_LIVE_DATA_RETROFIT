package idevelopapps.com.mvvmlivedata.ViewModel;

import android.app.Application;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import idevelopapps.com.mvvmlivedata.Model.resultModel;
import idevelopapps.com.mvvmlivedata.Repository.resultRepository;

public class resultViewModel extends AndroidViewModel implements LifecycleObserver {
    MutableLiveData<ArrayList<resultModel>> list;
    resultRepository repository;

    public resultViewModel(@NonNull Application application) {
        super(application);

        if(list != null){
            return;
        }
        repository = repository.getInstance();
        repository.fetchresult();
        list = repository.getResult();
    }

    public MutableLiveData<ArrayList<resultModel>> getList() {
        return list;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void checkData(){
        repository.getResult();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void checkDataResume(){
        repository.getResult();
    }
}
