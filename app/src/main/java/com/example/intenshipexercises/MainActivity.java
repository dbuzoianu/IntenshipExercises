package com.example.intenshipexercises;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.widget.TextViewCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.intenshipexercises.model.Post;
import com.example.intenshipexercises.server.ServerProvider;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private int incrementValue;

    private TextView incrementTv;
    private Button incrementBtn;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("incrementValue", incrementValue);
    }

//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        incrementValue = savedInstanceState.getInt("incrementValue");
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate: Happy to be born!");

        if(savedInstanceState != null)
            incrementValue = savedInstanceState.getInt("incrementValue");
        else
            incrementValue = 0;

        initViews();

//        getPostSynchrously();

        getPostAsynchrously();
    }

    private void getPostAsynchrously() {
        ServerProvider.createPostService().getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                if(posts!=null) {
                    Log.d("", "There are " + posts.size());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    private void getPostSynchrously() {
        try {
            Response<List<Post>> response = ServerProvider.createPostService().getPosts().execute();
            List<Post> posts = response.body();
            if(posts!=null) {
                Log.d("", "There are " + posts.size());
            }
        } catch (IOException e) {
            Log.d(TAG, "error trying to get posts ");
        }
    }

    private void initViews() {
        incrementTv = findViewById(R.id.counter_value_tv);
        incrementBtn = findViewById(R.id.increment_bt);

        incrementTv.setText(String.valueOf(incrementValue));

        incrementBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                incrementValue++;
                incrementTv.setText(incrementValue + "");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart: I exit, but you cannot see me");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume: Preparing final UI changes for you master");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause: You can see me, but I don't want to interact");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop: Packing up, preparing to leave");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy: Bye Bye");
    }
}
