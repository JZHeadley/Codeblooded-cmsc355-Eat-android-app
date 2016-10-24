package com.jzheadley.eat.ui.login.view;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.login.presenteres.LoginPresenter;
import com.jzheadley.eat.ui.login.presenteres.LoginPresenterImpl;

public class LoginActivity extends BaseActivity implements LoginView, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    private static final int REQUEST_SIGN_GOOGLE = 9001;
    private static final String TAG = "LoginActivity";
    private SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    private LoginPresenter loginPresenter;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenterImpl(this);
        signInButton = (SignInButton) findViewById(R.id.google_sign_in_btn);
        signInButton.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        auth = FirebaseAuth.getInstance();
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
        auth.addAuthStateListener(authListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        Log.d(TAG, "onClick: SomeButtonGotClicked");
        if (viewId == R.id.sign_in_btn) {
            signIn();
        } else if (viewId == R.id.sign_up_btn) {
            signUp();
        } else if (viewId == R.id.google_sign_in_btn) {
            Log.d(TAG, "onClick: GoogleSignIn");
            googleSignIn();
        } else if (viewId == R.id.btn_sign_out) {
            signOut();
        }
    }

    private void signUp() {

    }

    private void googleSignIn() {
        Log.d(TAG, "googleSignIn: Made it to the sign in method");
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQUEST_SIGN_GOOGLE);
    }

    private void signOut() {
        loginPresenter.signOut(googleApiClient);
    }

    private void signIn() {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "LoginActivity Connection Failed: " + connectionResult.getErrorMessage());
    }


    public void showLoading(boolean loading) {
        progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // google
        if (requestCode == REQUEST_SIGN_GOOGLE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.d(TAG, "onActivityResult: " + result.isSuccess());
            Log.d(TAG, "onActivityResult: " + result.getSignInAccount());
            firebaseAuthWithGoogle(result.getSignInAccount());
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        showLoading(true);
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [START_EXCLUDE]
                        showLoading(false);
                        // [END_EXCLUDE]
                    }
                });
    }
}
