package com.example.androidspring.network;

import com.example.androidspring.models.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
     @POST("/user/save")  // Matches Spring Boot's POST endpoint
        Call<User> saveUser(@Body User user);

        @GET("/user")  // Matches Spring Boot's GET endpoint
        Call<List<User>> getUsers();
    }

