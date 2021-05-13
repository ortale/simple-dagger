package com.joseortale.ortalesoft.simpledagger.data.local;

import com.joseortale.ortalesoft.simpledagger.data.remote.UserService;
import com.joseortale.ortalesoft.simpledagger.model.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class UserRepository {
    private UserService userService;

    @Inject
    public UserRepository(UserService userService) {
        this.userService = userService;
    }

    public Single<List<User>> modelUsers() {
        return userService.getUsers();
    }

    public Single<User> modelUserDetail(Integer userId) {
        return userService.getUserById(userId);
    }
}
