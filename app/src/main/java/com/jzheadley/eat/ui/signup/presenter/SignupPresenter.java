package com.jzheadley.eat.ui.signup.presenter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.support.annotation.NonNull;
import android.util.Log;

import com.jzheadley.eat.ui.base.presenter.BasePresenterImpl;
import com.jzheadley.eat.ui.signup.view.SignupActivity;


public class SignupPresenter extends BasePresenterImpl {
    private static final String TAG = "SignupPresenter";
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

    public void sendVerificationEmail(FirebaseUser currentUser) {
        currentUser.sendEmailVerification()
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "onComplete: Verification Email sent");
                    }
                }
            });
    }
}
