package com.joseortale.ortalesoft.simpledagger.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.joseortale.ortalesoft.simpledagger.data.local.UserRepository;
import com.joseortale.ortalesoft.simpledagger.model.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class UserViewModel extends ViewModel {
    private UserRepository userRepository;
    private CompositeDisposable disposable;
    private MutableLiveData<List<User>> mutableListLiveData;
    private MutableLiveData<User> mutableSingleLiveData;

    @Inject
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void init() {
        disposable = new CompositeDisposable();
        mutableListLiveData = new MutableLiveData<>();
        mutableSingleLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<User>> getListMutableLiveData() {
        return mutableListLiveData;
    }

    public MutableLiveData<User> getSingleMutableLiveData() {
        return mutableSingleLiveData;
    }

    public void getAllUsers() {
        disposable.add(userRepository.modelUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<User>>() {
                    @Override
                    public void onSuccess(@NonNull List<User> users) {
                        getListMutableLiveData().setValue(users);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                }));
    }

    public void getUserDetail(Integer userId) {
        disposable.add(userRepository.modelUserDetail(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<User>() {
                    @Override
                    public void onSuccess(@NonNull User user) {
                        getSingleMutableLiveData().setValue(user);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (disposable != null) {
            disposable.clear();
        }
    }
}
