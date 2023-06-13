package com.ajayam.p26_marvelretrofit.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//import java.net.URL;
import com.ajayam.p26_marvelretrofit.R;
import com.ajayam.p26_marvelretrofit.adapter.RecyclerAdapter;
import com.ajayam.p26_marvelretrofit.model.Results;
import com.ajayam.p26_marvelretrofit.viewModel.MoviesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
//    ListView superListView;

    RecyclerView recyclerView;
    List<Results> movieDetails;
    MoviesViewModel moviesViewModel;
    RecyclerAdapter recyclerAdapter;
    TextView noresfound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recylerView);
        noresfound=findViewById(R.id.noresfound);



        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.getMovieObserver().observe(this, new Observer<List<Results>>() {
            @Override
            public void onChanged(List<Results> results) {
                if (results!=null) {
                    movieDetails = results;
//                    recyclerAdapter.updatemovielist(results);
                    noresfound.setVisibility(View.GONE);
                    setAdapterData(movieDetails);
                }
                if(results==null)
                {
                    recyclerView.setVisibility(View.GONE);
                    noresfound.setVisibility(View.VISIBLE);
                }
            }
        });
        moviesViewModel.makeApiCall();



    }

    public void setAdapterData(List<Results> movieDetails) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        recyclerAdapter=new RecyclerAdapter(this,movieDetails);
        recyclerView.setAdapter(recyclerAdapter);
    }

}