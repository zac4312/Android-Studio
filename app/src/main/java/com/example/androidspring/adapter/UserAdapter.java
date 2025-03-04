package com.example.androidspring.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidspring.EditUserActivity;
import com.example.androidspring.R;
import com.example.androidspring.models.User;
import com.example.androidspring.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;
    private Context context;

    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.userName.setText(user.getName());
        holder.userAge.setText("Age: " + user.getAge());

        // ✅ Handle Delete
        holder.buttonDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser(user.getId(), holder.getAdapterPosition());
            }
        });

        // ✅ Handle Edit (Open EditUserActivity)
        holder.buttonEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditUserActivity.class);
                intent.putExtra("userId", user.getId());
                intent.putExtra("userName", user.getName());
                intent.putExtra("userAge", user.getAge());
                context.startActivity(intent);
            }
        });
    }

    private void deleteUser(Long userId, int position) {
        RetrofitClient.getApiService().deleteUser(userId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    userList.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(context, "User deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to delete user", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userName, userAge;
        Button buttonEditUser, buttonDeleteUser;

        public UserViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            userAge = itemView.findViewById(R.id.userAge);
            buttonEditUser = itemView.findViewById(R.id.buttonEditUser);
            buttonDeleteUser = itemView.findViewById(R.id.buttonDeleteUser);
        }
    }
}
