package tn.esprit.rolleaters.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import tn.esprit.rolleaters.R;

public class EventsActivity extends AppCompatActivity {

    private TextView analysisDaysRemainingText;
    private TextView debateDaysRemainingText;
    private TextView analysisProgressText;
    private TextView debateProgressText;
    private ProgressBar analysisProgressBar;
    private ProgressBar debateProgressBar;

    private CalendarView calendarView;

    private CountDownTimer analysisCountDownTimer;  // Déclaration de la minuterie pour l'analyse
    private CountDownTimer debateCountDownTimer;    // Déclaration de la minuterie pour le débat

    private static final long ANALYSIS_INTERVAL = 14 * 24 * 60 * 60 * 1000L; // 14 jours en millisecondes
    private static final long DEBATE_INTERVAL = 28 * 24 * 60 * 60 * 1000L; // 28 jours en millisecondes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        // Initialisation des vues
        analysisDaysRemainingText = findViewById(R.id.analysisRemainingText);
        debateDaysRemainingText = findViewById(R.id.debateRemainingText);
        analysisProgressBar = findViewById(R.id.analysisProgressBar);
        debateProgressBar = findViewById(R.id.debateProgressBar);
        analysisProgressText = findViewById(R.id.analysisProgressText);
        debateProgressText = findViewById(R.id.debateProgressText);
        calendarView = findViewById(R.id.eventCalendar);

        // Initialiser les valeurs maximales des ProgressBar
        analysisProgressBar.setMax(100); // 100% pour l'analyse
        debateProgressBar.setMax(100); // 100% pour le débat

        // Initialiser les minuteries pour l'analyse et le débat
        updateAnalysisTimer();
        updateDebateTimer();

        // Gérer les événements de calendrier
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // Vérifier si la date sélectionnée est un jour d'événement (analyse ou débat)
            checkForEventDays(year, month, dayOfMonth);
        });

        // Colorier les jours d'événements (analyse et débat)
        highlightEventDays();
    }

    private void updateAnalysisTimer() {
        Calendar currentDate = Calendar.getInstance();

        // Calcul de la date de la prochaine analyse (fin des deux premières semaines du mois)
        Calendar nextAnalysisDate = Calendar.getInstance();
        nextAnalysisDate.set(Calendar.DAY_OF_MONTH, 15); // Fin des deux premières semaines
        if (currentDate.after(nextAnalysisDate)) {
            nextAnalysisDate.add(Calendar.MONTH, 1);
        }

        long timeRemaining = nextAnalysisDate.getTimeInMillis() - currentDate.getTimeInMillis();
        long daysRemaining = timeRemaining / (24 * 60 * 60 * 1000);

        analysisDaysRemainingText.setText(daysRemaining + " jours pour l'analyse");
        startCountDown(timeRemaining, ANALYSIS_INTERVAL, analysisProgressBar, analysisProgressText);
    }

    private void updateDebateTimer() {
        Calendar currentDate = Calendar.getInstance();

        // Calcul de la date du prochain débat (fin du mois)
        Calendar nextDebateDate = Calendar.getInstance();
        nextDebateDate.set(Calendar.DAY_OF_MONTH, nextDebateDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        if (currentDate.after(nextDebateDate)) {
            nextDebateDate.add(Calendar.MONTH, 1);
        }

        long timeRemaining = nextDebateDate.getTimeInMillis() - currentDate.getTimeInMillis();
        long daysRemaining = timeRemaining / (24 * 60 * 60 * 1000);

        debateDaysRemainingText.setText(daysRemaining + " jours pour le débat");
        startCountDown(timeRemaining, DEBATE_INTERVAL, debateProgressBar, debateProgressText);
    }

    private void startCountDown(long timeRemaining, long interval, ProgressBar progressBar, TextView progressText) {
        CountDownTimer countDownTimer = new CountDownTimer(timeRemaining, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Calcul du pourcentage de progression
                int progress = (int) ((1 - (double) millisUntilFinished / interval) * 100);
                progressBar.setProgress(progress); // Mise à jour de la barre de progression
                progressText.setText(progress + "%"); // Mise à jour du texte pour afficher le pourcentage
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(100); // La progression atteint 100% à la fin
                progressText.setText("100%"); // Texte à afficher lorsque le temps est écoulé
            }
        };
        if (interval == ANALYSIS_INTERVAL) {
            analysisCountDownTimer = countDownTimer; // Affecter à la minuterie pour l'analyse
        } else if (interval == DEBATE_INTERVAL) {
            debateCountDownTimer = countDownTimer;   // Affecter à la minuterie pour le débat
        }
        countDownTimer.start();
    }

    // Méthode pour vérifier si la date sélectionnée est un jour de rencontre
    private void checkForEventDays(int year, int month, int dayOfMonth) {
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.set(year, month, dayOfMonth);

        // Vérifier si la date est un jour d'analyse (15 du mois)
        Calendar nextAnalysisDate = Calendar.getInstance();
        nextAnalysisDate.set(Calendar.DAY_OF_MONTH, 15); // Date de l'analyse

        // Vérifier si la date est un jour de débat (dernier jour du mois)
        Calendar nextDebateDate = Calendar.getInstance();
        nextDebateDate.set(Calendar.DAY_OF_MONTH, nextDebateDate.getActualMaximum(Calendar.DAY_OF_MONTH)); // Dernier jour du mois

        if (selectedDate.equals(nextAnalysisDate) || selectedDate.equals(nextDebateDate)) {
            // Afficher un toast pour indiquer que c'est un jour de rencontre
            Toast.makeText(this, "Jour d'événement : " + (selectedDate.equals(nextAnalysisDate) ? "Analyse" : "Débat"), Toast.LENGTH_SHORT).show();
        }
    }

    // Méthode pour surligner les jours d'événements sur le calendrier
    private void highlightEventDays() {
        Calendar currentDate = Calendar.getInstance();

        // Date d'analyse (15 du mois)
        Calendar nextAnalysisDate = Calendar.getInstance();
        nextAnalysisDate.set(Calendar.DAY_OF_MONTH, 15); // Date de l'analyse
        if (currentDate.after(nextAnalysisDate)) {
            nextAnalysisDate.add(Calendar.MONTH, 1);
        }

        // Date de débat (dernier jour du mois)
        Calendar nextDebateDate = Calendar.getInstance();
        nextDebateDate.set(Calendar.DAY_OF_MONTH, nextDebateDate.getActualMaximum(Calendar.DAY_OF_MONTH)); // Dernier jour du mois
        if (currentDate.after(nextDebateDate)) {
            nextDebateDate.add(Calendar.MONTH, 1);
        }

        // Ajouter des repères visuels (couleur) sur le calendrier
        // Vous pouvez changer la couleur de fond des dates ici
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            if (year == nextAnalysisDate.get(Calendar.YEAR) && month == nextAnalysisDate.get(Calendar.MONTH) &&
                    dayOfMonth == nextAnalysisDate.get(Calendar.DAY_OF_MONTH)) {
                view.setBackgroundColor(Color.RED); // Change la couleur de fond
            } else if (year == nextDebateDate.get(Calendar.YEAR) && month == nextDebateDate.get(Calendar.MONTH) &&
                    dayOfMonth == nextDebateDate.get(Calendar.DAY_OF_MONTH)) {
                view.setBackgroundColor(Color.GREEN); // Change la couleur de fond
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Annuler les compteurs lors de la destruction de l'activité
        if (analysisCountDownTimer != null) {
            analysisCountDownTimer.cancel();
        }
        if (debateCountDownTimer != null) {
            debateCountDownTimer.cancel();
        }
    }
}