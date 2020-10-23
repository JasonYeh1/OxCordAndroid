import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.core.Observable;
import model.Song;

import android.os.Bundle;

import com.example.oxcord.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Song testSong = new Song();
    }
}
