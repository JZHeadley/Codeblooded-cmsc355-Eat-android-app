package com.jzheadley.eat.ui.signup.presenter;

import com.google.firebase.auth.FirebaseAuth;

import com.jzheadley.eat.ui.base.presenter.BasePresenter;
import com.jzheadley.eat.ui.signup.view.SignupActivity;


public class SignupPresenter extends BasePresenter {
    private FirebaseAuth auth;
    private SignupActivity signupActivity;

    public SignupPresenter(SignupActivity signupActivity) {
        super(signupActivity);
        this.signupActivity = signupActivity;
        auth = FirebaseAuth.getInstance();
    }

    public void createUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(signupActivity, signupActivity);
    }
}
