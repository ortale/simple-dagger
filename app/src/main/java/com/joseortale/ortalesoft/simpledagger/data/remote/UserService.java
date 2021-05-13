package com.joseortale.ortalesoft.simpledagger.data.remote;

import com.joseortale.ortalesoft.simpledagger.model.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {
    @GET("users/")
    Single<List<User>> getUsers();

    @GET("users/{id}")
    Single<User> getUserById(@Path("id") Integer id);
}
