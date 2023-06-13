package com.ajayam.p26_marvelretrofit;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesViewModel extends ViewModel
{
    private MutableLiveData<List<Results>> movielist;

    public MoviesViewModel() { movielist=new MutableLiveData<>();}

    public MutableLiveData<List<Results>> getMovieObserver() { return movielist;}

    public void makeApiCall()
    {
        Api apiServices = RetrofitClient.getRetroClient().create(Api.class);
        Call<List<Results>> call=apiServices.getsuperHeroes();
        call.enqueue(new Callback<List<Results>>() {
            @Override
            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
                movielist.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Results>> call, Throwable t) {
                movielist.postValue(null);
                Log.e("error : ",t.getMessage().toString());
            }
        });
    }

}
