package com.jzheadley.eat.ui.login.presenteres;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.base.presenter.BasePresenterImpl;
import com.jzheadley.eat.ui.login.view.LoginActivity;

public class LoginPresenterImpl extends BasePresenterImpl implements LoginPresenter {
    private static final String TAG = "LoginPresenterImpl";
    private LoginActivity loginActivity;
    private FirebaseAuth auth;

    public LoginPresenterImpl(LoginActivity loginActivity) {
        super(loginActivity);
        this.loginActivity = loginActivity;
        auth = FirebaseAuth.getInstance();

    }

    @Override
    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        this.showProgress();

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
                        hideProgress();
                    }
                });

    }

    @Override
    public void signInWithEmailPassword(String email, final String password) {
        showProgress();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(loginActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            if (password.length() < 6) {
                                ((EditText) loginActivity.findViewById(R.id.password))
                                        .setError(loginActivity
                                                .getString(R.string.minimum_password));
                            } else {
                                Toast.makeText(loginActivity,
                                        loginActivity.getString(R.string.auth_failed),
                                        Toast.LENGTH_LONG).show();
                            }
                        } else {
                            // Intent intent = new Intent(loginActivity.getApplicationContext(),
                            //     NearbyRestaurantActivity.class);
                            // loginActivity.startActivity(intent);
                            loginActivity.finish();
                        }
                    }
                });
        hideProgress();
    }
}
