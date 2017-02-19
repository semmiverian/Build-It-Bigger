package id.semmi.showjokes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class JokeActivity extends AppCompatActivity {

    private static final String TAG = "loha";
    private RecyclerView recyclerView;
    private JokeAdapter jokeAdapter;
    private List<String> jokes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Log.d(TAG, "onCreate: start");

        if (getIntent().getExtras().getStringArrayList("jokes") != null) {
            jokes = getIntent().getExtras().getStringArrayList("jokes");
            for (String joke : jokes) {
                Log.d(TAG, "onCreate: " + joke);
            }
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            jokeAdapter = new JokeAdapter(this, jokes);
            recyclerView.setAdapter(jokeAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));        }
    }
}
