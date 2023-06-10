package com.example.networkingassignment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("users")
    Call<DataResponse> getUser(@Query("per_page") String per_page);

    @GET("users/{id}")
    Call<UserResponse> getSingleUser(@Path("id") int id);

}
