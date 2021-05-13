package com.joseortale.ortalesoft.simpledagger.di.components;

import com.joseortale.ortalesoft.simpledagger.view.MainActivity;
import com.joseortale.ortalesoft.simpledagger.di.modules.ContextModule;
import com.joseortale.ortalesoft.simpledagger.di.modules.NetworkModule;
import com.joseortale.ortalesoft.simpledagger.view.UserDetailFragment;
import com.joseortale.ortalesoft.simpledagger.view.UsersFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ContextModule.class})
public interface AppComponent {
    void injectMainActivity(MainActivity mainActivity);
    void injectUsersFragment(UsersFragment usersFragment);
    void injectMainFragment(UserDetailFragment userDetailFragment);
}
