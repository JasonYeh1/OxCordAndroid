import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.core.Observable;
import model.Song;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.oxcord.R;

public class MainActivity extends AppCompatActivity {
    private Button createRoomButton;
    private Button joinRoomButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Song testSong = new Song("Plastic Love", "https://www.youtube.com/watch?v=3bNITQR4Uso&ab_channel=PlasticLover");

        createRoomButton = findViewById(R.id.createRoom);
        joinRoomButton = findViewById(R.id.joinRoom);

        setOnClickListeners();
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
