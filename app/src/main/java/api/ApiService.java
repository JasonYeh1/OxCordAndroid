package api;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class ApiService {
    private static final String BASE_URL = "http://oxcordplayer.com:";

    public static ApiRequest getApiInterface() {
        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .build();

        return retrofit.create(ApiRequest.class);
    }
}
