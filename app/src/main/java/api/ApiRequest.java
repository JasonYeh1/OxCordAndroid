package api;

import android.util.JsonReader;

import org.json.JSONObject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRequest {

    @Headers({"Content-Type: application/json"})
    @POST("dbController/createRoom")
    Call<ResponseBody> createRoom(@Body RequestBody request);

    @Headers({"Content-Type: application/json"})
    @DELETE("dbController/deleteRoom")
    Call<ResponseBody> deleteRoom(@Body RequestBody request);

    @Headers({"Content-Type: application/json"})
    @GET("dbController/doesRoomExist")
    Call<ResponseBody> checkRoomExists(@Query("pin") int pin);

    @Headers({"Content-Type: application/json"})
    @POST("searchController/search")
    Call<ResponseBody> searchYoutube(@Body RequestBody request);
}
