package com.example.androidspring;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidspring.adapter.UserAdapter;
import com.example.androidspring.models.User;
import com.example.androidspring.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button buttonViewForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonViewForm = findViewById(R.id.buttonViewForm);
        buttonViewForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        fetchUsers(); // Load users from API
    }

    private void fetchUsers() {
        RetrofitClient.getApiService().getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> users = response.body();
                    recyclerView.setAdapter(new UserAdapter(users, UserListActivity.this)); // ✅ Pass Context
                } else {
                    Toast.makeText(UserListActivity.this, "Failed to load users", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("UserListActivity", "Error fetching users", t);
                Toast.makeText(UserListActivity.this, "API request failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
