package ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.oxcord.R;
import com.jakewharton.rxbinding4.view.RxView;

import org.json.JSONArray;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import api.ApiRequest;
import api.ApiService;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Unit;
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

        Observable<Unit> createRoomObservable =  RxView.clicks(createRoomButton);
        createRoomObservable.switchMap(action -> {
            JSONObject search = new JSONObject();
            try {
                search.put("value", "wow");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("SEARCH", search.toString());
            RequestBody body = RequestBody.create(JSON, search.toString());
            return apiRequest.searchYoutube(body);
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::printResponse, this::onError);
    }

    private void setOnClickListeners() {
//        joinRoomButton.setOnClickListener(View -> {
//          JSONObject search = new JSONObject();
//            try {
//                search.put("value", "wow");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            Log.d("SEARCH", search.toString());
//            RequestBody body = RequestBody.create(JSON, search.toString());
//            Observable<ResponseBody> searchRequest = apiRequest.searchYoutube(body);
//            searchRequest
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(this::printResponse, this::onError);
//        });
    }

    private void printResponse(ResponseBody response) {
        try {
            JSONArray json = new JSONArray(response.string());
            Log.d("SEARCH", json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
    }
}
