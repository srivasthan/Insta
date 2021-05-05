package com.example.insta;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class HeartAnimation {
    private static final String TAG = "com.example.insta.HeartAnimation";
    private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
    private static final DecelerateInterpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();


    public void toggleLike(ImageView heartOutline,ImageView heartRed){

        AnimatorSet animatorSet = new AnimatorSet();
        if(heartRed.getVisibility()== View.VISIBLE){


            ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(heartRed,"scaleY",1f,0.1f);
            scaleDownY.setDuration(300);
            scaleDownY.setInterpolator(ACCELERATE_INTERPOLATOR);

            ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(heartRed,"scaleX",1f,0.1f);
            scaleDownY.setDuration(300);
            scaleDownY.setInterpolator(ACCELERATE_INTERPOLATOR);

            heartRed.setVisibility(View.GONE);
            heartOutline.setVisibility(View.VISIBLE);
            animatorSet.playTogether(scaleDownY,scaleDownX);

        }else  if(heartRed.getVisibility()== View.GONE){

            ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(heartRed,"scaleY",0.1f,1f);
            scaleDownY.setDuration(300);
            scaleDownY.setInterpolator(DECELERATE_INTERPOLATOR);

            ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(heartRed,"scaleX",0.1f,1f);
            scaleDownY.setDuration(300);
            scaleDownY.setInterpolator(DECELERATE_INTERPOLATOR);

            heartRed.setVisibility(View.VISIBLE);
            heartOutline.setVisibility(View.GONE);
            animatorSet.playTogether(scaleDownY,scaleDownX);
        }

        animatorSet.start();

    }
}