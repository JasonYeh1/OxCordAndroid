package api;

import android.util.JsonReader;

import org.json.JSONObject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRequest {

    @Headers({"Content-Type: application/json"})
    @POST("dbController/createRoom")
    Call<ResponseBody> createRoom(@Body RequestBody request);

    @Headers({"Content-Type: application/json"})
    @HTTP(method = "DELETE", path = "dbController/deleteRoom", hasBody = true)
    Call<ResponseBody> deleteRoom(@Body RequestBody request);

    @Headers({"Content-Type: application/json"})
    @GET("dbController/doesRoomExist")
    Call<ResponseBody> checkRoomExists(@Query("pin") int pin);

    //This endpoint should be paired with RxJava
    @Headers({"Content-Type: application/json"})
    @POST("searchController/search")
    Observable<ResponseBody> searchYoutube(@Body RequestBody request);
}
