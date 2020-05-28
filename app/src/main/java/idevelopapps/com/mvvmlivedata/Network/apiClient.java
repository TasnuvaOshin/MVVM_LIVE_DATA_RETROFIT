package idevelopapps.com.mvvmlivedata.Network;

import java.util.ArrayList;
import idevelopapps.com.mvvmlivedata.Model.quranModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface apiClient {
    @GET("get_sura.php")
    Call<ArrayList<quranModel>> getSura();
}
