package candra.com.youtubeapiexample.View;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

import candra.com.youtubeapiexample.Model.Item;
import candra.com.youtubeapiexample.R;
import candra.com.youtubeapiexample.databinding.ListVideoBinding;

/**
 * Created by Candra Triyadi on 17/09/2017.
 */

public class ListViewHolder extends RecyclerView.ViewHolder{

    ListVideoBinding binding;

    public ListViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void onBind(final Activity activity, final Item model){

        Glide.with(activity)
                .load(model.getSnippet().getThumbnails().getDefault().getUrl()).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .dontAnimate()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        binding.imgThumbnail.setImageBitmap(resource);
                    }
                });

        binding.txtTitle.setText(model.getSnippet().getTitle());
        binding.txtDescription.setText(model.getSnippet().getDescription());

        binding.btnWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(YouTubeStandalonePlayer.createVideoIntent(activity,
                        activity.getResources().getString(R.string.YOUTUBE_API_KEY), model.getId().getVideoId()));
            }
        });

    }
}
