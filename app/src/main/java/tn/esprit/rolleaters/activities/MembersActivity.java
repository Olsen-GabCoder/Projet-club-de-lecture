package tn.esprit.rolleaters.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.rolleaters.R;
import tn.esprit.rolleaters.adapters.MembersRecyclerAdapter;
import tn.esprit.rolleaters.models.Member;

public class MembersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fabChat, fabActus, fabCommunautes, fabAppels;
    private TextView textDiscussion, textActus, textCommunautes, textAppels;
    private MembersRecyclerAdapter membersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        // Initialisation des vues
        initializeViews();

        // Configuration du RecyclerView
        setupRecyclerView();

        // Configuration des FloatingActionButtons
        setupFloatingActionButtons();
    }

    /**
     * Méthode pour initialiser les vues
     */
    private void initializeViews() {
        recyclerView = findViewById(R.id.recyclerView);
        fabChat = findViewById(R.id.fabChat);
        fabActus = findViewById(R.id.fabActus);
        fabCommunautes = findViewById(R.id.fabCommunautes);
        fabAppels = findViewById(R.id.fabAppels);

        textDiscussion = findViewById(R.id.textDiscussion);
        textActus = findViewById(R.id.textActus);
        textCommunautes = findViewById(R.id.textCommunautes);
        textAppels = findViewById(R.id.textAppels);
    }

    /**
     * Méthode pour configurer le RecyclerView
     */
    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialisation de la liste des membres
        List<Member> membersList = generateSampleMembers();

        // Configuration de l'adaptateur avec les données
        membersAdapter = new MembersRecyclerAdapter(this, membersList);
        recyclerView.setAdapter(membersAdapter);
    }

    /**
     * Méthode pour configurer les FloatingActionButtons
     */
    private void setupFloatingActionButtons() {
        fabChat.setOnClickListener(view -> handleChatButtonClick());
        fabActus.setOnClickListener(view -> handleActusButtonClick());
        fabCommunautes.setOnClickListener(view -> handleCommunautesButtonClick());
        fabAppels.setOnClickListener(view -> handleAppelsButtonClick());
    }

    /**
     * Gestion des clics pour le bouton "Discussion"
     */
    private void handleChatButtonClick() {
        showMessage("Discussion button clicked");
    }

    /**
     * Gestion des clics pour le bouton "Actus"
     */
    private void handleActusButtonClick() {
        showMessage("Actus button clicked");
    }

    /**
     * Gestion des clics pour le bouton "Communautés"
     */
    private void handleCommunautesButtonClick() {
        showMessage("Communautés button clicked");
    }

    /**
     * Gestion des clics pour le bouton "Appels"
     */
    private void handleAppelsButtonClick() {
        showMessage("Appels button clicked");
    }

    /**
     * Méthode pour afficher un Toast
     *
     * @param message Le message à afficher
     */
    private void showMessage(String message) {
        android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show();
    }

    /**
     * Méthode pour générer une liste d'exemple de membres
     *
     * @return Liste fictive de membres
     */
    private List<Member> generateSampleMembers() {
        List<Member> membersList = new ArrayList<>();
        membersList.add(new Member("Alice", "Salut, comment ça va ?", "15:30", R.drawable.avatar1));
        membersList.add(new Member("Bob", "À bientôt !", "14:00", R.drawable.avatar2));
        membersList.add(new Member("Charlie", "Rendez-vous demain.", "13:15", R.drawable.avatar3));
        membersList.add(new Member("Diane", "Super idée !", "12:45", R.drawable.avatar4));
        membersList.add(new Member("Ethan", "On se voit ce week-end ?", "11:20", R.drawable.avatar5));
        return membersList;
    }
}
