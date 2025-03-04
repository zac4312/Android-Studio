package com.example.androidspring.network;

import com.example.androidspring.models.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.PUT;

public interface ApiService {
    @POST("/user/save")  // Matches Spring Boot's POST endpoint
    Call<User> saveUser(@Body User user);

    @GET("/user")  // Matches Spring Boot's GET endpoint
    Call<List<User>> getUsers();

    @DELETE("/user/{Id}")
    Call<Void> deleteUser(@Path("id") Long id);

    @PUT("/user/{Id}")
    Call<User> updateUser(@Path("id") Long id, @Body User user);
}
