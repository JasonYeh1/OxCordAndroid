package ui;

import androidx.appcompat.app.AppCompatActivity;
import api.ApiRequest;
import api.ApiService;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.oxcord.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Button createRoomButton;
    private Button joinRoomButton;
    private ApiRequest apiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Song testSong = new Song("Plastic Love", "https://www.youtube.com/watch?v=3bNITQR4Uso&ab_channel=PlasticLover");

        createRoomButton = findViewById(R.id.createRoom);
        joinRoomButton = findViewById(R.id.joinRoom);

        setOnClickListeners();
        apiRequest = ApiService.getApiInterface();

        JSONObject pin = new JSONObject();
        JSONObject request = new JSONObject();

//        try {
//            pin.put("pin", 1111);
//            request.put("query", pin);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        RequestBody body = RequestBody.create(MediaType.parse("application/json"), request.toString());

        Call<ResponseBody> call = apiRequest.checkRoomExists(1111);
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

        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url ="https://www.google.com";
//
//
//        StringRequest stringRequest= new StringRequest(Request.Method.GET, "http://oxcordplayer.com/dbController/doesRoomExist", new com.android.volley.Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject JSONResponse = new JSONObject(response);
//                    Log.d("HELLO", JSONResponse.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                Log.d("HELLO", error.toString());
//
//            }
//        });
//        queue.add(stringRequest);
    }

    private void setOnClickListeners() {
        createRoomButton.setOnClickListener(View -> {
            Toast.makeText(this, "Create Room Clicked", Toast.LENGTH_SHORT);
        });

        joinRoomButton.setOnClickListener(View -> {
            Toast.makeText(this, "Join Room Clicked", Toast.LENGTH_SHORT);
        });
    }
}
