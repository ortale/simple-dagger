package com.joseortale.ortalesoft.simpledagger.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.joseortale.ortalesoft.simpledagger.di.ViewModelKey;
import com.joseortale.ortalesoft.simpledagger.viewmodel.UserViewModel;
import com.joseortale.ortalesoft.simpledagger.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    abstract ViewModel bindViewModel(UserViewModel userViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindFactory(ViewModelFactory factory);
}
