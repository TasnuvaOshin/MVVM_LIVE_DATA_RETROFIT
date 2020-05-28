package idevelopapps.com.mvvmlivedata.Connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiConnection {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://apzo.xyz/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
