package com.solvabit.recyclerviewwithfirestore.videoplayerRecyclerView;

import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.solvabit.recyclerviewwithfirestore.R;

import java.util.ArrayList;

public class VideoPlayerRecyclerView extends RecyclerView {


        private static final String TAG = "VideoPlayerRecyclerView";

        private enum VolumeState {ON, OFF};

        // ui
        private ImageView thumbnail, volumeControl;
        private ProgressBar progressBar;
        private View viewHolderParent;
        private FrameLayout frameLayout;
        private PlayerView videoPlayerView;
        private SimpleExoPlayer videoPlayer;

        // vars
        private ArrayList<videoPlayerData> videoPlayerData = new ArrayList<>();
        private int videoSurfaceDefaultHeight = 0;
        private int screenDefaultHeight = 0;
        private Context context;
        private int playPosition = -1;
        private boolean isVideoViewAdded;
        private RequestManager requestManager;

        // controlling playback state
        private VolumeState volumeState;

        public VideoPlayerRecyclerView(@NonNull Context context) {
            super(context);
            init(context);
        }

        public VideoPlayerRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            init(context);
        }



    }

