package com.example.animations;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView star;
    ImageView ball;
    ImageView currentImage;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        star = findViewById(R.id.starView);
        ball = findViewById(R.id.ballView);
        currentImage = star;
        mp = MediaPlayer.create(this, R.raw.clapping);
    }

    public void fading(ImageView viewToFade, float alphaToAnimate, int duration) {
        viewToFade.animate().alpha(alphaToAnimate).setDuration(duration);
    }

    public void toBall(View view) {
        fading(star, 0, 1000);
        fading(ball, 1, 1000);
        currentImage = ball;
    }

    public void toStar(View view) {
        fading(ball, 0, 1000);
        fading(star, 1, 1000);
        currentImage = star;
    }

    public void spin(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(currentImage, "rotation", 0f, 720f);
        animator.setDuration(1000);
        animator.start();
    }

    public void turnAround(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(currentImage, "rotationY", 0f, 720f);
        animator.setDuration(1000);
        animator.start();
    }

    public void jump(View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, -200);
        animator.setDuration(200);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setRepeatCount(1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            currentImage.setTranslationY(value);
        });
        animator.start();
    }

    public void clap(View view) {
        if (mp.isPlaying()) mp.seekTo(0);
        else mp.start();
    }
}