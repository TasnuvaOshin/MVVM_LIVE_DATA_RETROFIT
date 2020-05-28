package idevelopapps.com.mvvmlivedata.Repository;

import android.util.Log;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import idevelopapps.com.mvvmlivedata.Connection.apiConnection;
import idevelopapps.com.mvvmlivedata.Model.quranModel;
import idevelopapps.com.mvvmlivedata.Network.apiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class quranRepository {
   private static quranRepository repository;
   private ArrayList<quranModel> list = new ArrayList<>();
   apiClient apiClient;
    MutableLiveData<ArrayList<quranModel>> data = new MutableLiveData<>();
   public static quranRepository getInstance(){

       if(repository == null){
           repository = new quranRepository();

       }
       return repository;
   }
/*
Get date from a web service
 */
   public MutableLiveData<ArrayList<quranModel>> getSura(){
    return data;
   }


   public void fetchSura(){
       apiClient = apiConnection.cteateService(apiClient.class);
       apiClient.getSura().enqueue(new Callback<ArrayList<quranModel>>() {
           @Override
           public void onResponse(Call<ArrayList<quranModel>> call, Response<ArrayList<quranModel>> response) {

               list = response.body();
               data.postValue(response.body());
               Log.d("size", String.valueOf(list.size()));
           }

           @Override
           public void onFailure(Call<ArrayList<quranModel>> call, Throwable t) {

           }
       });
   }

}
