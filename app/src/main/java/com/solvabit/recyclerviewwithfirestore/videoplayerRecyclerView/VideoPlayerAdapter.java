package com.solvabit.recyclerviewwithfirestore.videoplayerRecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.solvabit.recyclerviewwithfirestore.R;
import com.solvabit.recyclerviewwithfirestore.videoplayerRecyclerView.Models.videoPlayerData;

import java.util.ArrayList;

public class VideoPlayerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private ArrayList<videoPlayerData> videoPlayerDataArrayList;
    private RequestManager requestManager;


    public VideoPlayerAdapter(ArrayList<videoPlayerData> videoPlayerDataArrayList, RequestManager requestManager) {
        this.videoPlayerDataArrayList = videoPlayerDataArrayList;
        this.requestManager = requestManager;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VideoPlayerHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_list_items, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((VideoPlayerHolder)viewHolder).onBind(videoPlayerDataArrayList.get(i), requestManager);
    }

    @Override
    public int getItemCount() {
        return videoPlayerDataArrayList.size();
    }


}
