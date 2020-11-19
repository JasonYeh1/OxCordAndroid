package ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.oxcord.R;
import com.jakewharton.rxbinding4.view.RxView;

import org.json.JSONArray;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import api.ApiRequest;
import api.ApiService;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Unit;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    private ApiRequest apiRequest;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiRequest = ApiService.getApiInterface();
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, homeFragment).commit();

//        Call<ResponseBody> call = apiRequest.checkRoomExists(8549);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if(response.isSuccessful()) {
//                    try {
//                        JSONObject json = new JSONObject(response.body().string());
//                        Log.d("HELLO", json.getString("resString"));
//                        Log.d("HELLO", json.getString("exists"));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d("HELLO", "FAIL");
//                Log.d("HELLO", t.toString());
//            }
//        });
    }

//    private void printResponse(ResponseBody response) {
//        try {
//            JSONArray json = new JSONArray(response.string());
//            Log.d("SEARCH", json.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
    }
}
