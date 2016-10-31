package com.jzheadley.eat.ui.login.presenteres;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.jzheadley.eat.ui.base.presenter.BasePresenter;
import com.jzheadley.eat.ui.login.view.LoginActivity;

public class LoginPresenterImpl extends BasePresenter implements LoginPresenter {
    private static final String TAG = "LoginPresenterImpl";
    private LoginActivity loginActivity;
    private FirebaseAuth auth;

    public LoginPresenterImpl(LoginActivity loginActivity) {
        super(loginActivity);
        this.loginActivity = loginActivity;
        auth = FirebaseAuth.getInstance();

    }


    @Override
    public void attemptToLogInWithEmail(String email, String password) {

    }

    @Override
    public void attemptToLogInWithGoogle() {

    }

    @Override
    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        this.showProgressDialog();

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(loginActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(loginActivity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        hideProgressDialog();
                    }
                });
    }

    @Override
    public void getAuthWithGoogle(GoogleSignInResult result) {

    }

    @Override
    public void signOut(GoogleApiClient googleApiClient) {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        Log.d(TAG, "onResult: " + status.getStatusMessage());
                    }
                });
    }
}
