package com.joseortale.ortalesoft.simpledagger.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joseortale.ortalesoft.simpledagger.BaseApplication;
import com.joseortale.ortalesoft.simpledagger.R;
import com.joseortale.ortalesoft.simpledagger.model.User;
import com.joseortale.ortalesoft.simpledagger.utils.ViewHelper;
import com.joseortale.ortalesoft.simpledagger.viewmodel.UserViewModel;

import java.util.List;

import javax.inject.Inject;

public class UsersFragment extends Fragment {
    private Context context;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private UserViewModel userViewModel;

    private List<User> users;
    private UsersAdapter usersAdapter;
    private RecyclerView rvUsers;
    private ProgressBar progressBar;

    public static UsersFragment newInstance() {
        UsersFragment fragment = new UsersFragment();

        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BaseApplication) context.getApplicationContext()).getAppComponent().injectUsersFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_users, container, false);

        rvUsers = view.findViewById(R.id.rvUsers);
        progressBar = view.findViewById(R.id.progress_circular);

        ViewHelper.setOnLoading(progressBar, rvUsers);

        userViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel.class);
        userViewModel.init();
        userViewModel.getListMutableLiveData().observe(this, users -> {
            this.users = users;
            refreshRecyclerView();
            ViewHelper.setOnFinishedLoading(progressBar, rvUsers);
        });
        userViewModel.getAllUsers();

        return view;
    }

    private void refreshRecyclerView() {
        usersAdapter = new UsersAdapter(context, users);

        rvUsers.setLayoutManager(new LinearLayoutManager(context));
        rvUsers.setAdapter(usersAdapter);
        rvUsers.setItemAnimator(new DefaultItemAnimator());
        rvUsers.setNestedScrollingEnabled(true);
        usersAdapter.notifyDataSetChanged();
    }
}

