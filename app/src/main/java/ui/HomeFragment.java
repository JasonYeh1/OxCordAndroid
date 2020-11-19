package ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.oxcord.R;
import com.jakewharton.rxbinding4.view.RxView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Unit;
import ui.host.HostFragment;

public class HomeFragment extends Fragment {
    private Button createRoomButton;
    private Button joinRoomButton;
    private ViewGroup parent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.home_fragment, container, false);
        createRoomButton = view.findViewById(R.id.createRoom);
        joinRoomButton = view.findViewById(R.id.joinRoom);
        joinRoomButton.setVisibility(View.GONE);
        parent = container;
        setSubscribers();
        return view;
    }

    private void setSubscribers() {
        Observable<Unit> createRoomObservable = RxView.clicks(createRoomButton);
        createRoomObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {
                    Toast.makeText(getContext(), "Create room clicked", Toast.LENGTH_SHORT).show();
                    HostFragment hostFragment = new HostFragment();
                    getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, hostFragment).addToBackStack("test").commit();
                });
//        createRoomObservable.switchMap(action -> {
//            JSONObject search = new JSONObject();
//            try {
//                search.put("value", "wow");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            Log.d("SEARCH", search.toString());
//            RequestBody body = RequestBody.create(JSON, search.toString());
//            return apiRequest.searchYoutube(body);
//        }).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this::printResponse, this::onError);
    }
}
