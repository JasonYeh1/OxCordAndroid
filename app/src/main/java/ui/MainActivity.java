package ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.oxcord.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import api.ApiRequest;
import api.ApiService;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import model.Song;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button createRoomButton;
    private Button joinRoomButton;
    private ApiRequest apiRequest;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Song testSong = new Song("Plastic Love", "https://www.youtube.com/watch?v=3bNITQR4Uso&ab_channel=PlasticLover");

        createRoomButton = findViewById(R.id.createRoom);
        joinRoomButton = findViewById(R.id.joinRoom);

        setOnClickListeners();
        apiRequest = ApiService.getApiInterface();

        Call<ResponseBody> call = apiRequest.checkRoomExists(8549);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    try {
                        JSONObject json = new JSONObject(response.body().string());
                        Log.d("HELLO", json.getString("resString"));
                        Log.d("HELLO", json.getString("exists"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("HELLO", "FAIL");
                Log.d("HELLO", t.toString());
            }
        });

//        JSONObject pin = new JSONObject();
//        try {
//            pin.put("pin", 1111);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Log.d("HELLO", pin.toString());
//        RequestBody body = RequestBody.create(JSON, pin.toString());
//        Call<ResponseBody> deleteRoomCall = apiRequest.deleteRoom(body);
//        deleteRoomCall.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if(response.isSuccessful()) {
//                    try {
//                        JSONObject json = new JSONObject(response.body().string());
//                        Log.d("deleteRoom", json.toString());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d("deleteRoom", "FAIL");
//                Log.d("deleteRoom", t.toString());
//            }
//        });
    }

    private void setOnClickListeners() {
//        createRoomButton.setOnClickListener(View -> {
////            Toast.makeText(this, "Create Room Clicked", Toast.LENGTH_SHORT).show();
//            JSONObject pin = new JSONObject();
//            try {
//                pin.put("pin", 1111);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            Log.d("HELLO", pin.toString());
//            RequestBody body = RequestBody.create(JSON, pin.toString());
//            Call<ResponseBody> deleteRoomCall = apiRequest.deleteRoom(body);
//            deleteRoomCall.enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                    if(response.isSuccessful()) {
//                        try {
//                            JSONObject json = new JSONObject(response.body().string());
//                            Log.d("deleteRoom", json.toString());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        Log.d("deleteRoom", response.toString());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    Log.d("deleteRoom", "FAIL");
//                    Log.d("deleteRoom", t.toString());
//                }
//            });
//        });
//
//        joinRoomButton.setOnClickListener(View -> {
//            Toast.makeText(this, "Join Room Clicked", Toast.LENGTH_SHORT).show();
//        });

        createRoomButton.setOnClickListener(View -> {
//            Toast.makeText(this, "Create Room Clicked", Toast.LENGTH_SHORT).show();
            JSONObject search = new JSONObject();
            try {
                search.put("value", "wow");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("SEARCH", search.toString());
            RequestBody body = RequestBody.create(JSON, search.toString());
            Observable<ResponseBody> searchYoutubeCall = apiRequest.searchYoutube(body);
            searchYoutubeCall
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(new Consumer<ResponseBody>() {
                        @Override
                        public void accept(ResponseBody responseBody) throws Throwable {
                            try {
                                JSONArray json = new JSONArray(responseBody.string());
                                Log.d("SEARCH", json.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }).doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            Log.d("SEARCH", "ERROR");
                        }
                    })
                    .subscribe();
        });

        joinRoomButton.setOnClickListener(View -> {
            Toast.makeText(this, "Join Room Clicked", Toast.LENGTH_SHORT).show();
        });
    }
}
