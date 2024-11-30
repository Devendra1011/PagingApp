package com.devdeveloper.pagingapp.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.RequestManager;
import com.devdeveloper.pagingapp.R;
import com.devdeveloper.pagingapp.adapter.MovieLoadStateAdapter;
import com.devdeveloper.pagingapp.adapter.MoviesAdapter;
import com.devdeveloper.pagingapp.databinding.ActivityMainBinding;
import com.devdeveloper.pagingapp.util.GridSpace;
import com.devdeveloper.pagingapp.util.MovieComparator;
import com.devdeveloper.pagingapp.util.Utils;
import com.devdeveloper.pagingapp.viewmodel.MovieViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {


    MovieViewModel mainActivityViewModel;
    ActivityMainBinding binding;
    MoviesAdapter moviesAdapter;


    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding  = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // setContentView(R.layout.activity_main);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (Utils.API_KEY == null || Utils.API_KEY.isEmpty()){
            Toast.makeText(this,"Error in API Key",Toast.LENGTH_SHORT).show();

        }

        moviesAdapter = new MoviesAdapter(new MovieComparator(),requestManager);
        mainActivityViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        initRecyclerViewAdapter();


        // subscribe to paging data
        mainActivityViewModel.moviePagingDataFlowable.subscribe(moviePagingData -> {
            moviesAdapter.submitData(getLifecycle(),moviePagingData);
        });


    }

    private void initRecyclerViewAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        binding.recyclerView.setLayoutManager(gridLayoutManager);
        binding.recyclerView.addItemDecoration(new GridSpace(2,20,true));
        binding.recyclerView.setAdapter(
                moviesAdapter.withLoadStateFooter(new MovieLoadStateAdapter(view -> {
                    moviesAdapter.retry();
                }))
        );

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                return moviesAdapter.getItemViewType(position) == MoviesAdapter.LOADING_ITEM ? 1:2;

            }
        });
    }
}