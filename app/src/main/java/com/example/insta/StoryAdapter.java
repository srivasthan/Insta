package com.example.insta;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.insta.models.Photo;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;


public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    private final List<Photo> movies;
    Context context;
    private HeartAnimation heartAnimation;
    boolean isDoubleTap = false;

    public StoryAdapter(Context applicationContext, List<Photo> movieArrayList) {
        this.context = applicationContext;
        this.movies = movieArrayList;
        heartAnimation = new HeartAnimation();
    }


    @NonNull
    @Override
    public StoryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_mainfeed_item_view, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.ViewHolder viewHolder, int i) {

        String im = movies.get(i).getUrl();

        Picasso.get()
                .load(im)
                .resize(300,300).into(viewHolder.normalImageView);

       // Glide.with(context).load(movies.get(i).getUrl()).into(viewHolder.normalImageView);
        viewHolder.body.setText(movies.get(i).getTitle());

        viewHolder.normalImageView.setOnClickListener(new DoubleClickListener() {

            @Override
            public void onSingleClick(View v) {
//                viewHolder.normalImageView.setVisibility(View.GONE);
//                viewHolder.imageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onDoubleClick(View v) {
                Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show();
                viewHolder.heartImage.setImageResource(R.drawable.like);
                String count = viewHolder.like.getText().toString();
                int increment = Integer.parseInt(count);
                increment = increment + 1;
                viewHolder.like.setText(String.valueOf(increment));
             //   Glide.with(context).load(movies.get(i).getUrl()).into(viewHolder.imageView);
                viewHolder.normalImageView.setVisibility(View.GONE);
                viewHolder.imageView.setVisibility(View.VISIBLE);
                Picasso.get()
                        .load(im)
                        .resize(300,300).into(viewHolder.imageView);
            }
        });


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView body;
        private final PhotoView imageView;
        private final ImageView normalImageView;
        private final ImageView heartImage;
        private final TextView like;

        public ViewHolder(View view) {
            super(view);
            body = (TextView) view.findViewById(R.id.body);
            imageView = (PhotoView) view.findViewById(R.id.media_post);
            normalImageView = view.findViewById(R.id.normal_img);
            heartImage = view.findViewById(R.id.heart_outline);
            like = view.findViewById(R.id.like_number);

//            // on item click
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // get position
//
//                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
//                            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//                    prepareAnimation(scaleAnimation);
//
//                    AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
//                    prepareAnimation(alphaAnimation);
//
//                    AnimationSet animation = new AnimationSet(true);
//                    animation.addAnimation(alphaAnimation);
//                    animation.addAnimation(scaleAnimation);
//                    animation.setDuration(700);
//                    animation.setFillAfter(true);
//
//                    view.startAnimation(animation);
//                }
//            });
        }
    }

    public abstract class DoubleClickListener implements View.OnClickListener {

        private static final long DOUBLE_CLICK_TIME_DELTA = 300;//milliseconds

        long lastClickTime = 0;

        @Override
        public void onClick(View v) {
            long clickTime = System.currentTimeMillis();
            if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                onDoubleClick(v);
            } else {
                onSingleClick(v);
            }
            lastClickTime = clickTime;
        }

        public abstract void onSingleClick(View v);

        public abstract void onDoubleClick(View v);
    }

    private static Animation prepareAnimation(Animation animation) {
        animation.setRepeatCount(1);
        animation.setRepeatMode(Animation.REVERSE);
        return animation;
    }

    public void clear() {
        movies.clear();
        notifyDataSetChanged();
    }

    //RecyclerView mRecycler;
    // Add a list of ites
    public void addAll(int position, List<Photo> mov) {
        movies.addAll(0, mov);
        notifyItemInserted(0);
        //mRecycler.smoothScrollToPosition(0);
        notifyDataSetChanged();
    }
}