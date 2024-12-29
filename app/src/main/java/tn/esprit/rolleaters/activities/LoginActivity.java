package tn.esprit.rolleaters.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import tn.esprit.rolleaters.AppDatabase;
import tn.esprit.rolleaters.R;
import tn.esprit.rolleaters.User;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
    private Button loginButton, registerButton;
    private ImageView showPasswordIcon;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.loginUsernameEditText);
        passwordEditText = findViewById(R.id.loginPasswordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        showPasswordIcon = findViewById(R.id.showPasswordIcon);

        database = AppDatabase.getInstance(this);

        // Gestion de l'affichage/masquage du mot de passe
        showPasswordIcon.setOnClickListener(v -> {
            if (passwordEditText.getTransformationMethod().equals(android.text.method.PasswordTransformationMethod.getInstance())) {
                passwordEditText.setTransformationMethod(android.text.method.HideReturnsTransformationMethod.getInstance());
                showPasswordIcon.setImageResource(R.drawable.ic_eye); // Remplacer par l'icône de l'œil ouvert
            } else {
                passwordEditText.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
                showPasswordIcon.setImageResource(R.drawable.ic_eye); // Remplacer par l'icône de l'œil fermé
            }
            passwordEditText.setSelection(passwordEditText.getText().length()); // Garde le curseur à la fin
        });

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Tous les champs sont requis", Toast.LENGTH_SHORT).show();
            } else {
                new Thread(() -> {
                    User user = database.userDao().login(username, password);
                    runOnUiThread(() -> {
                        if (user != null) {
                            Toast.makeText(this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                            // Redirection vers l'activité principale
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(this, "Identifiants incorrects", Toast.LENGTH_SHORT).show();
                        }
                    });
                }).start();
            }
        });

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
