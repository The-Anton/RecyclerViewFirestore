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


        private void init(Context context){
            this.context = context.getApplicationContext();
            Display display = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            videoSurfaceDefaultHeight = point.x;
            screenDefaultHeight = point.y;

            videoPlayerView = new PlayerView(this.context);
            videoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM);

            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

            // 2. Create the player
            videoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
            // Bind the player to the view.
            videoPlayerView.setUseController(false);
            videoPlayerView.setPlayer(videoPlayer);
            setVolumeControl(VolumeState.ON);

            addOnScrollListener(new OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);

                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        Log.d(TAG, "onScrollStateChanged: called.");
                        if(thumbnail != null){ // show the old thumbnail
                            thumbnail.setVisibility(VISIBLE);
                        }

                        // There's a special case when the end of the list has been reached.
                        // Need to handle that with this bit of logic
                        if(!recyclerView.canScrollVertically(1)){
                            playVideo(true);
                        }
                        else{
                            playVideo(false);
                        }
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                }
            });

            addOnChildAttachStateChangeListener(new OnChildAttachStateChangeListener() {
                @Override
                public void onChildViewAttachedToWindow(View view) {

                }

                @Override
                public void onChildViewDetachedFromWindow(View view) {
                    if (viewHolderParent != null && viewHolderParent.equals(view)) {
                        resetVideoView();
                    }

                }
            });

            videoPlayer.addListener(new Player.EventListener() {
                @Override
                public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {

                }

                @Override
                public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

                }

                @Override
                public void onLoadingChanged(boolean isLoading) {

                }

                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                    switch (playbackState) {

                        case Player.STATE_BUFFERING:
                            Log.e(TAG, "onPlayerStateChanged: Buffering video.");
                            if (progressBar != null) {
                                progressBar.setVisibility(VISIBLE);
                            }

                            break;
                        case Player.STATE_ENDED:
                            Log.d(TAG, "onPlayerStateChanged: Video ended.");
                            videoPlayer.seekTo(0);
                            break;
                        case Player.STATE_IDLE:

                            break;
                        case Player.STATE_READY:
                            Log.e(TAG, "onPlayerStateChanged: Ready to play.");
                            if (progressBar != null) {
                                progressBar.setVisibility(GONE);
                            }
                            if(!isVideoViewAdded){
                                addVideoView();
                            }
                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void onRepeatModeChanged(int repeatMode) {

                }

                @Override
                public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

                }

                @Override
                public void onPlayerError(ExoPlaybackException error) {

                }

                @Override
                public void onPositionDiscontinuity(int reason) {

                }

                @Override
                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

                }

                @Override
                public void onSeekProcessed() {

                }
            });
        }


    }

