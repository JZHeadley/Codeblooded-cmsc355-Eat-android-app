package com.jzheadley.eat.ui.userprofile.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.User;
import com.jzheadley.eat.data.services.UserService;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.userprofile.presenter.UserProfilePresenter;

public class UserProfileActivity extends BaseActivity {
    private UserProfilePresenter userProfilePresenter;
    private UserService userService;


    private EditText oldEmail, newEmail, password, newPassword;
    private ProgressBar progressBar;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    public void displayUsername(User user) {
        ((EditText) findViewById(R.id.user_profile_et))
                .setHint(user.getUsername());
    }

    public void displayEmail() {
        ((EditText) findViewById(R.id.email_profile_et))
                .setHint(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userService = new UserService();
        userProfilePresenter = new UserProfilePresenter(this, userService);
        userProfilePresenter.getUser();
        displayEmail();
    }


    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}

