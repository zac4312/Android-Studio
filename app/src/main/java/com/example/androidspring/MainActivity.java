package com.example.androidspring;

import android.content.Intent; // ADD THIS
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.androidspring.models.User;
import com.example.androidspring.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextAge;
    private Button buttonSave, buttonViewUsers; // ADD buttonViewUsers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        buttonSave = findViewById(R.id.buttonSave);
        buttonViewUsers = findViewById(R.id.buttonViewUsers); // ADD THIS

        // Handle Save User button click
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
            }
        });

        // Handle View Users button click → Navigate to UserListActivity
        buttonViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveUser() {
        String name = editTextName.getText().toString().trim();
        String ageText = editTextAge.getText().toString().trim();

        if (name.isEmpty() || ageText.isEmpty()) {
            Toast.makeText(this, "Please enter both name and age", Toast.LENGTH_SHORT).show();
            return;
        }

        int age = Integer.parseInt(ageText);
        User newUser = new User(name, age);

        RetrofitClient.getApiService().saveUser(newUser).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(MainActivity.this, "User saved!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to save user", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("MainActivity", "Error saving user", t);
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
