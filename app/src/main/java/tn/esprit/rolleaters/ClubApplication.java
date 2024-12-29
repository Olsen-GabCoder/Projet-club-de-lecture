package tn.esprit.rolleaters;

import android.app.Application;
import androidx.appcompat.app.AppCompatDelegate;

public class ClubApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Désactive le mode nuit dans l'application
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}
