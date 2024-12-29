package tn.esprit.rolleaters.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import tn.esprit.rolleaters.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Récupération des éléments
        ImageView logo = findViewById(R.id.logoImage);
        TextView appName = findViewById(R.id.appName);

        // Chargement des animations
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Réglage des animations pour qu'elles soient infinies et plus rapides
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setDuration(2500); // Durée réduite pour accélérer l'animation
        fadeInAnimation.setRepeatCount(Animation.INFINITE);
        fadeInAnimation.setDuration(2500); // Durée réduite pour accélérer l'animation

        // Combinaison des animations
        logo.startAnimation(rotateAnimation);
        appName.startAnimation(fadeInAnimation);

        // Transition vers l’écran suivant après 3 secondes
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class); // Remplacez LoginActivity par la prochaine activité
            startActivity(intent);
            finish();
        }, 4000);
    }
}
