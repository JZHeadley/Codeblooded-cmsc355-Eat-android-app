package com.jzheadley.eat.ui.signup.presenter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.jzheadley.eat.data.models.User;
import com.jzheadley.eat.data.services.UserService;
import com.jzheadley.eat.ui.base.presenter.BasePresenterImpl;
import com.jzheadley.eat.ui.signup.view.SignupActivity;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class SignupPresenter extends BasePresenterImpl implements OnCompleteListener<AuthResult> {
    private static final String TAG = "SignupPresenter";
    private FirebaseAuth auth;
    private SignupActivity signupActivity;
    private UserService userService;
    private String username;

    public SignupPresenter(SignupActivity signupActivity, UserService userService) {
        super(signupActivity);
        this.userService = userService;
        this.signupActivity = signupActivity;
        auth = FirebaseAuth.getInstance();
    }

    public void createUser(String email, String password, String username) {
        this.username = username;
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this);
        Log.d(TAG, "createUser: User has been added to FireBase");
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

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        Toast.makeText(signupActivity, "createUserWithEmail:onComplete:"
                + task.isSuccessful(), Toast.LENGTH_SHORT).show();
        hideProgress();
        // If sign in fails, display a message to the user. If sign in succeeds
        // the auth state listener will be notified and logic to handle the
        // signed in user can be handled in the listener.
        Log.d(TAG, "onComplete: " + task.toString());
        if (!task.isSuccessful()) {
            Toast.makeText(signupActivity, "Authentication failed." + task.getException(),
                    Toast.LENGTH_SHORT).show();

        } else {
            signupActivity.startActivity(new Intent(signupActivity.getApplicationContext(),
                    SignupActivity.class));
            User user = new User(username, task.getResult().getUser().getUid());
            userService.getUserApi()
                    .createUser(user)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Void>() {
                        @Override
                        public void onCompleted() {
                            Log.d(TAG, "onCompleted: ");
                        }

                        @Override
                        public void onError(Throwable exception) {

                        }

                        @Override
                        public void onNext(Void avoid) {

                        }
                    });
            signupActivity.finish();
        }
    }
}
