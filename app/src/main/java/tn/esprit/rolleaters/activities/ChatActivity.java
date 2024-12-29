package tn.esprit.rolleaters.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import tn.esprit.rolleaters.R;
import tn.esprit.rolleaters.adapters.ChatAdapter;
import tn.esprit.rolleaters.models.ChatMessage;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView rvChatList;
    private EditText editMessage;
    private FloatingActionButton fabSendMessage;
    private ChatAdapter chatAdapter;
    private ArrayList<ChatMessage> chatMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Initialisation des vues
        rvChatList = findViewById(R.id.rvChatList);
        editMessage = findViewById(R.id.editMessage);
        fabSendMessage = findViewById(R.id.fabSendMessage);

        // Récupération du nom du membre depuis l'Intent
        String name = getIntent().getStringExtra("friend");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(name != null ? name : "Les mangeurs du Rouleau");
        }

        // Initialisation de la liste de messages et de l'adaptateur
        chatMessages = new ArrayList<>();
        initializeDefaultConversations();
        chatAdapter = new ChatAdapter(chatMessages);

        // Configuration du RecyclerView
        rvChatList.setLayoutManager(new LinearLayoutManager(this));
        rvChatList.setAdapter(chatAdapter);

        // Action du bouton envoyer
        fabSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editMessage.getText().toString().trim();
                if (!TextUtils.isEmpty(message)) {
                    addMessage(message);
                    editMessage.setText("");
                }
            }
        });
    }

    // Ajouter un message dans la liste
    private void addMessage(String message) {
        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        chatMessages.add(new ChatMessage(message, currentTime, true)); // `true` pour un message envoyé
        chatAdapter.notifyItemInserted(chatMessages.size() - 1);
        rvChatList.smoothScrollToPosition(chatMessages.size() - 1);
    }

    // Initialiser des conversations par défaut
    private void initializeDefaultConversations() {
        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

        chatMessages.add(new ChatMessage("Salut, comment ça va ?", "12:30", false));
        chatMessages.add(new ChatMessage("Ça va bien, merci ! Et toi ?", "12:31", true));
        chatMessages.add(new ChatMessage("Je vais bien aussi, merci de demander.", "12:32", false));
        chatMessages.add(new ChatMessage("Quoi de neuf ?", "12:33", true));
        chatMessages.add(new ChatMessage("Pas grand-chose, juste en train de lire un livre.", "12:34", false));
        chatMessages.add(new ChatMessage("Oh super, quel livre ?", "12:35", true));
    }
}
