package com.solvabit.recyclerviewwithfirestore.videoplayerRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.solvabit.recyclerviewwithfirestore.R;

import java.util.ArrayList;

public class VideoPlayerAdapter extends RecyclerView.ViewHolder {
    FrameLayout media_container;
    TextView title;
    ImageView thumbnail, volumeControl;
    ProgressBar progressBar;
    View parent;
    RequestManager requestManager;

    public VideoPlayerAdapter(@NonNull View itemView) {
        super(itemView);
        parent = itemView;
        media_container = itemView.findViewById(R.id.media_container);
        thumbnail = itemView.findViewById(R.id.thumbnail);
        title = itemView.findViewById(R.id.title);
        progressBar = itemView.findViewById(R.id.progressBar);
        volumeControl = itemView.findViewById(R.id.volume_control);
    }

    public void onBind(videoPlayerData videoPlayerData, RequestManager requestManager) {
        this.requestManager = requestManager;
        parent.setTag(this);
        title.setText(videoPlayerData.getTitle());
        this.requestManager
                .load(videoPlayerData.getThumbnail())
                .into(thumbnail);
    }

}
