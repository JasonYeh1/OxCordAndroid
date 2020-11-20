package api;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

public class ApiService {
    private static final String BASE_URL = "http://192.168.2.171:8080/";

    public static ApiRequest getApiInterface() {
        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit.create(ApiRequest.class);
    }
}
