package tn.esprit.rolleaters.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.rolleaters.R;
import tn.esprit.rolleaters.adapters.MenuAdapter;
import tn.esprit.rolleaters.models.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.menuRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Membres", R.drawable.ic_members, MembersActivity.class));
        menuItems.add(new MenuItem("Livres", R.drawable.ic_library, BibliothequeActivity.class));
        menuItems.add(new MenuItem("Objectifs", R.drawable.ic_challenges, ChallengesActivity.class));
        menuItems.add(new MenuItem("Débat", R.drawable.ic_debate, DebateActivity.class));
        menuItems.add(new MenuItem("Création", R.drawable.ic_creation, CreationActivity.class));
        menuItems.add(new MenuItem("Archives", R.drawable.ic_archives, ArchivesActivity.class));
        menuItems.add(new MenuItem("Rencontre", R.drawable.ic_events, EventsActivity.class));
        menuItems.add(new MenuItem("Vidéos", R.drawable.ic_podcasts, PodcastsActivity.class));
        menuItems.add(new MenuItem("Quiz", R.drawable.ic_quiz, QuizActivity.class));
        menuItems.add(new MenuItem("Marché", R.drawable.ic_market, MarketActivity.class));
        menuItems.add(new MenuItem("Sponsors", R.drawable.ic_sponsors, SponsorsActivity.class));
        menuItems.add(new MenuItem("Sponsors", R.drawable.ic_sponsors, SponsorsActivity.class));

        MenuAdapter adapter = new MenuAdapter(this, menuItems);
        recyclerView.setAdapter(adapter);
    }
}


