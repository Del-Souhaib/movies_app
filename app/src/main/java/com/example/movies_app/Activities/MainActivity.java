package com.example.movies_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.movies_app.Adapters.ItemTouchHelperAdapter;
import com.example.movies_app.Adapters.MyAdapter;
import com.example.movies_app.Adapters.SwipeHelperCallback;
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
        fs.create(new Film("Parasite", "action/drama", "this is the bst movie", 3, 200, R.drawable.poster5));
        fs.create(new Film("Squid game", "action/drama", "this is the bst movie", 3, 150, R.drawable.poster6));
        fs.create(new Film("Game of thrones", "action/drama", "this is the bst movie", 3, 136, R.drawable.poster7));
        fs.create(new Film("Peaky blinders", "action/drama", "this is the bst movie", 3, 90, R.drawable.poster8));
        fs.create(new Film("Vikings", "action/drama", "this is the bst movie", 5.5, 98, R.drawable.poster9));
        fs.create(new Film("Breaking bad", "action/drama", "this is the bst movie", 4, 102, R.drawable.poster10));

        fs.create(new Film("Rick & morty", "action/drama", "this is the bst movie", 5, 102, R.drawable.poster11));
        fs.create(new Film("Cruella", "action/drama", "this is the bst movie", 3.9, 102, R.drawable.poster12));
        fs.create(new Film("The wolf of street", "action/drama", "this is the bst movie", 4.2, 102, R.drawable.poster13));
        fs.create(new Film("World war z", "action/drama", "this is the bst movie", 5, 102, R.drawable.poster14));

//        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);
//        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        recyclerView = findViewById(R.id.recyclerView);
        myAdapter = new MyAdapter(this, fs.findall());

        ItemTouchHelper.Callback callback = new SwipeHelperCallback(myAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter.setTochHelper(itemTouchHelper);
        itemTouchHelper.attachToRecyclerView(recyclerView);



    }
//    @SuppressLint("ResourceType")
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.toolbar, menu);
//        MenuItem searchItem = menu.findItem(R.id.search_film);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Log.d("newText1",query);
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                Log.d("newText",newText);
//                myAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        return true;
//    }
}