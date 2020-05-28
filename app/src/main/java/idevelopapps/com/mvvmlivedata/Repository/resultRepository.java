package idevelopapps.com.mvvmlivedata.Repository;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import idevelopapps.com.mvvmlivedata.Connection.apiConnection;
import idevelopapps.com.mvvmlivedata.Model.resultModel;
import idevelopapps.com.mvvmlivedata.Network.apiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class resultRepository {

    apiClient apiClient;
    private static resultRepository repository;
    MutableLiveData<ArrayList<resultModel>> data = new MutableLiveData<>();


    public static resultRepository getInstance() {
        if (repository == null) {
            repository = new resultRepository();

        }
        return repository;
    }

    public MutableLiveData<ArrayList<resultModel>> getResult() {
        return data;
    }


    public void fetchresult() {
        apiClient = apiConnection.cteateService(apiClient.class);
        apiClient.getResult().enqueue(new Callback<ArrayList<resultModel>>() {
            @Override
            public void onResponse(Call<ArrayList<resultModel>> call, Response<ArrayList<resultModel>> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<resultModel>> call, Throwable t) {

            }
        });
    }
}
