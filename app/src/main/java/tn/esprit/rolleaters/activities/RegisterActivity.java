package tn.esprit.rolleaters.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import tn.esprit.rolleaters.AppDatabase;
import tn.esprit.rolleaters.R;
import tn.esprit.rolleaters.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private Button registerButton  ;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.registerUsernameEditText);
        emailEditText = findViewById(R.id.registerEmailEditText);
        passwordEditText = findViewById(R.id.registerPasswordEditText);
        confirmPasswordEditText = findViewById(R.id.registerConfirmPasswordEditText);
        registerButton = findViewById(R.id.registerButton);

        database = AppDatabase.getInstance(this);

        registerButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                Toast.makeText(this, "Tous les champs sont requis", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
            } else {
                new Thread(() -> {
                    if (database.userDao().checkUser(username) != null) {
                        runOnUiThread(() -> Toast.makeText(this, "Nom d'utilisateur déjà pris", Toast.LENGTH_SHORT).show());
                    } else {
                        User user = new User();
                        user.setUsername(username);
                        user.setEmail(email);
                        user.setPassword(password);
                        database.userDao().insert(user);
                        runOnUiThread(() -> {
                            Toast.makeText(this, "Inscription réussie", Toast.LENGTH_SHORT).show();
                            finish();
                        });
                    }
                }).start();
            }
        });
    }
}


