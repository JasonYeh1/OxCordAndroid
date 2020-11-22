package ui.guest;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.oxcord.R;
import com.jakewharton.rxbinding4.view.RxView;
import com.jakewharton.rxbinding4.widget.RxTextView;

import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import api.ApiRequest;
import api.ApiService;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;

public class JoinFragment extends Fragment {
    private Button joinButton;
    private EditText pinEditText;
    private ApiRequest apiRequest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.join_fragment, container, false);
        joinButton = view.findViewById(R.id.join);
        pinEditText = view.findViewById(R.id.pin_edit_text);
        setSubscriptions();
        apiRequest = ApiService.getApiInterface();
        return view;
    }

    private void setSubscriptions() {
        Observable<String> editTextObservable = RxTextView.textChanges(pinEditText)
                .observeOn(AndroidSchedulers.mainThread())
                .map(CharSequence::toString);

        editTextObservable
                .map(text -> text != null && text.length() == 4 && TextUtils.isDigitsOnly(text))
                .subscribe(joinButton::setEnabled);

        RxView.clicks(joinButton)
                .observeOn(AndroidSchedulers.mainThread())
                .map(event -> Integer.parseInt(pinEditText.getText().toString()))
                .switchMap(pin -> apiRequest.checkRoomExists(pin))
                .subscribe(this::printResponse);
    }

    private void printResponse(ResponseBody response) {
        try {
            JSONObject json = new JSONObject(response.string());
            Log.d("SEARCH", json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
