package dev.jay.asmovie.adapter;


import static android.view.View.TEXT_ALIGNMENT_CENTER;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import dev.jay.asmovie.R;
import dev.jay.asmovie.activities.DetailsActivity;
import dev.jay.asmovie.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageThumbnail;
        public TextView textTitle;


        public MovieViewHolder(View view) {
            super(view);
            imageThumbnail = view.findViewById(R.id.imageThumbnail);
            textTitle = view.findViewById(R.id.textTitle);
        }
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.textTitle.setText(movie.getTitle());
        holder.textTitle.setTextColor(Color.rgb(255,255,255));
        Picasso.get().load(movie.getThumbnail()).into(holder.imageThumbnail);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("title", movie.getTitle());
            intent.putExtra("description", movie.getDescription());
            intent.putExtra("video_url", movie.getVideo_url());
            intent.putExtra("poster",movie.getThumbnail());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

}