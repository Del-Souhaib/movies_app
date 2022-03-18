package com.example.movies_app.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_app.Models.Film;
import com.example.movies_app.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<Film> films;

    public MyAdapter(Context context, List<Film> films) {
        this.context = context;
        this.films = films;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.activity_list_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
//        holder.title.setText("helllo");

        holder.title.setText(films.get(position).getNom());
        holder.type.setText(films.get(position).getType());
        holder.image.setImageResource(films.get(position).getImage());
        holder.ratingBar.setRating((float) films.get(position).getRating());
        String descsub=films.get(position).getDesc();
        if(descsub.length()>30){
            descsub=descsub.substring(0,30)+"...";
        }
        holder.desc.setText(descsub);
        holder.duration.setText(films.get(position).getCreated_at()+" min");

    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, type, desc;
        private ImageView image;
        private RatingBar ratingBar;
        private Button duration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.card_title);
            type = itemView.findViewById(R.id.card_type);
            image = itemView.findViewById(R.id.card_image);
            ratingBar = itemView.findViewById(R.id.card_rating);
            duration = itemView.findViewById(R.id.card_duration);
            desc = itemView.findViewById(R.id.card_desc);

        }
    }
}
