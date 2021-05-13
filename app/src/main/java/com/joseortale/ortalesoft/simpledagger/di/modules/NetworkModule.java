package com.joseortale.ortalesoft.simpledagger.di.modules;

import com.joseortale.ortalesoft.simpledagger.data.remote.UserService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public abstract class NetworkModule {
    private final static String APP_BASE_URL = "https://jsonplaceholder.typicode.com/";

    @Provides
    @Singleton
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(APP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    static UserService provideUserService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }
}
