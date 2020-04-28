package com.solvabit.recyclerviewwithfirestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.solvabit.recyclerviewwithfirestore.videoplayerRecyclerView.VideoPlayerRecyclerViewActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Button videoPlayerBtn= findViewById(R.id.sampleVideoBtn);
        Button eventRecyclerBtn= findViewById(R.id.eventRecyclerBtn);
        Button videoRecyclerViewBtn= findViewById(R.id.recyclerViewforVideosBtn);

        videoPlayerBtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, ExoPlayerAcitvity.class));
                    }

                    });


        eventRecyclerBtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, EventRecyclerView_Activity.class));
                    }

                });

        videoRecyclerViewBtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, VideoPlayerRecyclerViewActivity.class));
                    }

                });



    }


}
