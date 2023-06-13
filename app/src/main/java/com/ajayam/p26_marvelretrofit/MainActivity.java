package com.ajayam.p26_marvelretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//import java.net.URL;
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



//        moviesViewModel= ViewModelProviders.of(this).get(MoviesViewModel.class)
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












//

    }

    public void setAdapterData(List<Results> movieDetails) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        recyclerAdapter=new RecyclerAdapter(this,movieDetails);
        recyclerView.setAdapter(recyclerAdapter);
    }

//    private void getSuperHeroes() {
//        Call<List<Results>> call = RetrofitClient.getInstance().getMyApi().getsuperHeroes();
//        call.enqueue(new Callback<List<Results>>() {
//            @Override
//            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
//                List<Results> myheroList = response.body();
//                String[] oneHeroes = new String[myheroList.size()];
//
//                for (int i = 0; i < myheroList.size(); i++) {
//
//                    Activity context;
//                    oneHeroes[i] = "Movie:  "+myheroList.get(i).getName()
//                            +"\n Bio: "+myheroList.get(i).getBio()
//                            +"\n Publisher: "+myheroList.get(i).getPublisher()
//                            +"\n Created By: "+myheroList.get(i).getCreatedby()
//                            +"\n Team: "+myheroList.get(i).getTeam()+"\n\n\n";
//
//
//
//
//
//                }
//
//
//                superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, oneHeroes));
//            }
//
//
//
//            @Override
//            public void onFailure(Call<List<Results>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
//            }
//
//
//
//        });
//    }
}