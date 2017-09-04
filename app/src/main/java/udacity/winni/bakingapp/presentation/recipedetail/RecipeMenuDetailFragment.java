package udacity.winni.bakingapp.presentation.recipedetail;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import com.squareup.picasso.Picasso;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import udacity.winni.bakingapp.R;
import udacity.winni.bakingapp.presentation.model.StepVM;

/**
 * Created by winniseptiani on 7/8/17.
 */

public class RecipeMenuDetailFragment extends Fragment {

    private boolean playWhenReady;

    private int currentWindow;

    private long playbackPosition;

    private SimpleExoPlayer player;

    @BindView(R.id.textview_instruction)
    TextView textviewInstruction;

    @BindView(R.id.view_exoplayer)
    SimpleExoPlayerView exoPlayerView;

    @BindView(R.id.iv_recipe_step)
    ImageView ivRecipeStep;

    private String videoUrl = "";

    private String thumbnailUrl = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_menu_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void displayInstruction(StepVM stepVM) {
        textviewInstruction.setText(stepVM.getDescription());
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    // Prefer to play video first then image. If video path is empty then image will be
// display. If the image path is also empty then it will display placeholder no content.
    public void displayStepVideoOrImage() {
        if (TextUtils.isEmpty(videoUrl)) {
            exoPlayerView.setVisibility(View.GONE);
            ivRecipeStep.setVisibility(View.VISIBLE);
            releasePlayer();
            displayThumbnail();
        } else {
            exoPlayerView.setVisibility(View.VISIBLE);
            ivRecipeStep.setVisibility(View.GONE);
            restartPlayer();
        }
    }

    private void displayThumbnail() {
        Picasso.with(this.getActivity())
            .load(thumbnailUrl)
            .placeholder(R.drawable.no_image)
            .into(ivRecipeStep);
    }

    private void setUpExoPlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
            new DefaultRenderersFactory(getContext()),
            new DefaultTrackSelector(), new DefaultLoadControl());
        exoPlayerView.setPlayer(player);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        exoPlayerView.requestFocus();
        Uri uri = Uri.parse(videoUrl);
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource(uri,
            new DefaultHttpDataSourceFactory("ua"),
            new DefaultExtractorsFactory(), null, null);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            setUpExoPlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || player == null)) {
            setUpExoPlayer();
        }
    }

    public void restartPlayer() {
        releasePlayer();
        player = ExoPlayerFactory.newSimpleInstance(
            new DefaultRenderersFactory(getContext()),
            new DefaultTrackSelector(), new DefaultLoadControl());
        exoPlayerView.setPlayer(player);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(0, 0);
        exoPlayerView.requestFocus();
        Uri uri = Uri.parse(videoUrl);
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, true);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }
}
