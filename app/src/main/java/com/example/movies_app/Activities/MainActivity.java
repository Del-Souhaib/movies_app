package com.example.movies_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.movies_app.Adapters.MyAdapter;
import com.example.movies_app.Models.Film;
import com.example.movies_app.R;
import com.example.movies_app.Services.FilmService;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private FilmService fs = FilmService.getInstance();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fs.create(new Film("dark kighy", "action , adventure", "this is the bst movie", 3.5, 120, R.drawable.poster1));
        fs.create(new Film("Jocker", "action , adventure", "this is the bst movie", 4, 90, R.drawable.poster2));
        fs.create(new Film("Spider man", "action/drama", "this is the bst movie", 4.5, 140, R.drawable.poster3));
        fs.create(new Film("dark kighy", "action/drama", "this is the bst movie", 3, 200, R.drawable.poster1));
        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        recyclerView = findViewById(R.id.recyclerView);
        myAdapter = new MyAdapter(this, fs.findall());
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}