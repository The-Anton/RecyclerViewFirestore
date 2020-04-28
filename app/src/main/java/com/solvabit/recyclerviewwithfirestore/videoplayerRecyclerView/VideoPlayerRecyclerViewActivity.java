package com.solvabit.recyclerviewwithfirestore.videoplayerRecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.solvabit.recyclerviewwithfirestore.R;
import com.solvabit.recyclerviewwithfirestore.videoplayerRecyclerView.Models.videoPlayerData;
import com.solvabit.recyclerviewwithfirestore.videoplayerRecyclerView.Util.Resources;
import com.solvabit.recyclerviewwithfirestore.videoplayerRecyclerView.Util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.Arrays;

public class VideoPlayerRecyclerViewActivity extends AppCompatActivity {

    private VideoPlayerRecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player_recycler_view);


        mRecyclerView = findViewById(R.id.recycler_view);

        initRecyclerView();
    }




    private void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
       VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);

        ArrayList<videoPlayerData> videoPlayerDataArrayList = new ArrayList<videoPlayerData>(Arrays.asList(Resources.PLAYER_DATA));
       mRecyclerView.setMediaObjects(videoPlayerDataArrayList);
        VideoPlayerAdapter adapter = new VideoPlayerAdapter(videoPlayerDataArrayList, initGlide());
        mRecyclerView.setAdapter(adapter);
    }

    private RequestManager initGlide(){
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.white_background)
                .error(R.drawable.white_background);

        return Glide.with(this)
                .setDefaultRequestOptions(options);
    }


    @Override
    protected void onDestroy() {
        if(mRecyclerView!=null)
            mRecyclerView.releasePlayer();
        super.onDestroy();
    }
}
