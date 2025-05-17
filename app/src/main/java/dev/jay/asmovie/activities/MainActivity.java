package dev.jay.asmovie.activities;
import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dev.jay.asmovie.R;
import dev.jay.asmovie.adapter.MovieAdapter;
import dev.jay.asmovie.model.Movie;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import static dev.jay.asmovie.config.Constants.API_URL_GET_ALL;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private ArrayList<Movie> movieList = new ArrayList<>();

//    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerMovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchMovies();
    }

    private void fetchMovies() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, API_URL_GET_ALL, null,
                response -> {
                    Gson gson = new Gson();
                    Movie[] movies = gson.fromJson(response.toString(), Movie[].class);
                    movieList.addAll(Arrays.asList(movies));
                    adapter = new MovieAdapter(this, movieList);
                    recyclerView.setAdapter(adapter);
                },
                error -> {
                    error.printStackTrace();
                });

        Volley.newRequestQueue(this).add(request);
    }
}