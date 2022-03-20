package com.example.movies_app.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_app.Activities.ListItemActivity;
import com.example.movies_app.Activities.MainActivity;
import com.example.movies_app.Models.Film;
import com.example.movies_app.R;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements ItemTouchHelperAdapter,
        Filterable {

    private Context context;
    private List<Film> dataSet;
    private List<Film> films;
    ItemTouchHelper itemTouchHelper;

    public MyAdapter(Context context, List<Film> films) {
        this.context = context;
        this.films = films;
        this.dataSet = films;
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
        String descsub = films.get(position).getDesc();
        if (descsub.length() > 30) {
            descsub = descsub.substring(0, 30) + "...";
        }
        holder.desc.setText(descsub);
        holder.duration.setText(films.get(position).getCreated_at() + " min");

    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Film frm_film = films.get(fromPosition);
        films.remove(frm_film);
        films.add(toPosition, frm_film);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemSwiped(int position, int direction) {
        if (direction == ItemTouchHelper.RIGHT) {
            films.remove(position);
            notifyItemRemoved(position);
        } else if (direction == ItemTouchHelper.LEFT) {
            Film film=films.get(position);
            View dialoguedesign =LayoutInflater.from(context).inflate(R.layout.rating_pop_layout,null);

            TextView title=dialoguedesign.findViewById(R.id.pop_title);
            ImageView image=dialoguedesign.findViewById(R.id.pop_image);
            RatingBar ratingbar=dialoguedesign.findViewById(R.id.pop_rating);

            title.setText(film.getNom().toString());
            image.setImageResource(film.getImage());
            ratingbar.setRating((float)film.getRating());

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.context);
            alertDialog
//                    .setMessage("Rate this movie").setTitle("Rate movie")
                    .setView(dialoguedesign)
                    .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            film.setRating(ratingbar.getRating());
                            notifyDataSetChanged();
                        }
                    });
//            TextView textView=findViewById R.id.pop_title;

            alertDialog.show();
            notifyItemChanged(position);
//            Toast.makeText(this.context,direction+"",Toast.LENGTH_SHORT).show();
        }
    }

    public void setTochHelper(ItemTouchHelper itemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener, View.OnTouchListener, GestureDetector.OnGestureListener {
        private TextView title, type, desc;
        private ImageView image;
        private RatingBar ratingBar;
        private Button duration;
        GestureDetector gestureDetector;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.card_title);
            type = itemView.findViewById(R.id.card_type);
            image = itemView.findViewById(R.id.card_image);
            ratingBar = itemView.findViewById(R.id.card_rating);
            duration = itemView.findViewById(R.id.card_duration);
            desc = itemView.findViewById(R.id.card_desc);
            gestureDetector = new GestureDetector(itemView.getContext(), this);
        }

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {
            itemTouchHelper.startDrag(this);
        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onClick(View view) {

        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            gestureDetector.onTouchEvent(motionEvent);
            return false;
        }
    }

    @Override
    public Filter getFilter() {
        return Searched_Filter;
    }

    private Filter Searched_Filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Film> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(films);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Film item : films) {
                    if (item.getNom().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataSet.clear();
            dataSet.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

}
