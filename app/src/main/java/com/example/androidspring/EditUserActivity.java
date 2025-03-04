package com.example.androidspring;

import  android.content.Intent;
import android.os.Bundle;
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

public class EditUserActivity extends AppCompatActivity {

    private EditText editTextName, editTextAge;
    private Button buttonUpdateUser;
    private Long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        buttonUpdateUser = findViewById(R.id.buttonUpdateUser);

        // Get user data from intent
        Intent intent = getIntent();
        userId = intent.getLongExtra("userId", -1);
        editTextName.setText(intent.getStringExtra("userName"));
        editTextAge.setText(String.valueOf(intent.getIntExtra("userAge", 0)));

        // Handle Update
        buttonUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
    }

    private void updateUser() {
        String updatedName = editTextName.getText().toString().trim();
        int updatedAge = Integer.parseInt(editTextAge.getText().toString().trim());

        User updatedUser = new User(updatedName, updatedAge);

        RetrofitClient.getApiService().updateUser(userId, updatedUser).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EditUserActivity.this, "User updated!", Toast.LENGTH_SHORT).show();
                    finish(); // Close activity after update
                } else {
                    Toast.makeText(EditUserActivity.this, "Failed to update user", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(EditUserActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
