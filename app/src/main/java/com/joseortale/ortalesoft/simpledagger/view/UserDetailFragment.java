package com.joseortale.ortalesoft.simpledagger.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.joseortale.ortalesoft.simpledagger.BaseApplication;
import com.joseortale.ortalesoft.simpledagger.R;
import com.joseortale.ortalesoft.simpledagger.databinding.FragmentUserDetailBinding;
import com.joseortale.ortalesoft.simpledagger.utils.ViewHelper;
import com.joseortale.ortalesoft.simpledagger.viewmodel.UserViewModel;

import javax.inject.Inject;

public class UserDetailFragment extends Fragment {
    private Context context;

    private static final String KEY_USER = "KEY_USER";

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private UserViewModel userViewModel;
    private FragmentUserDetailBinding fragmentUserDetailBinding;

    private Integer userId;
    private LinearLayout llRoot;
    private ProgressBar progressBar;

    public static UserDetailFragment newInstance(Integer userId) {
        UserDetailFragment fragment = new UserDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(KEY_USER, userId);

        fragment.setArguments(bundle);

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

        Bundle bundle = getArguments();
        if (bundle != null) {
            userId = bundle.getInt(KEY_USER);
        }

        ((BaseApplication) context.getApplicationContext()).getAppComponent().injectMainFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_user_detail, container, false);

        fragmentUserDetailBinding = DataBindingUtil.setContentView((MainActivity) context, R.layout.fragment_user_detail);

        llRoot = view.findViewById(R.id.ll_root);
        progressBar = view.findViewById(R.id.progress_circular);

        ViewHelper.setOnLoading(progressBar, llRoot);

        userViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel.class);
        userViewModel.init();
        userViewModel.getSingleMutableLiveData().observe(this, user -> {
            fragmentUserDetailBinding.setUser(user);
            ViewHelper.setOnFinishedLoading(progressBar, llRoot);
        });
        userViewModel.getUserDetail(userId);

        return view;
    }
}
