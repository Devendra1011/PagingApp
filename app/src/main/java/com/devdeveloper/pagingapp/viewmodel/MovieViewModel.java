package com.devdeveloper.pagingapp.viewmodel;

import static com.bumptech.glide.Glide.init;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingSource;
import androidx.paging.rxjava3.PagingRx;

import com.devdeveloper.pagingapp.model.Movie;
import com.devdeveloper.pagingapp.paging.MoviePagingSource;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class MovieViewModel extends ViewModel {
    public Flowable<PagingData<Movie>> moviePagingDataFlowable;


    public MovieViewModel(){
         init();
    }

    public void init(){
        // Define Paging Source
        MoviePagingSource moviePagingSource = new MoviePagingSource();
        Pager<Integer,Movie> pager = new Pager(
                new PagingConfig(
                        20,
                        20,
                        false,
                        20,
                        20*499
                ),
                () ->moviePagingSource
        );
        // Flowable
        moviePagingDataFlowable = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(moviePagingDataFlowable,coroutineScope);


    }
}
