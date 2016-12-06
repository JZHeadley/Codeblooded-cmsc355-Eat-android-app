package com.jzheadley.eat.ui.login.view;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.login.presenteres.LoginPresenter;
import com.jzheadley.eat.ui.login.presenteres.LoginPresenterImpl;
import com.jzheadley.eat.ui.nearbyrestaurants.view.NearbyRestaurantActivity;
import com.jzheadley.eat.ui.resetpassword.view.ResetPasswordActivity;
import com.jzheadley.eat.ui.signup.view.SignupActivity;

public class LoginActivity extends BaseActivity implements LoginView,
    GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    private static final int REQUEST_SIGN_GOOGLE = 9001;
    private static final String TAG = "LoginActivity";
    private GoogleApiClient googleApiClient;
    private LoginPresenter loginPresenter;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenterImpl(this);
        SignInButton signInButton = (SignInButton) findViewById(R.id.google_sign_in_btn);
        signInButton.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build();
        googleApiClient = new GoogleApiClient.Builder(this)
            .enableAutoManage(this /* FragmentActivity */,
                this /* OnConnectionFailedListener */)
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
        googleApiClient.disconnect();
        googleApiClient.stopAutoManage(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        Log.d(TAG, "onClick: SomeButtonGotClicked");

        switch (viewId) {
            case R.id.btn_login:
                signIn();
                break;
            case R.id.btn_signup:
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                break;
            case R.id.google_sign_in_btn:
                Log.d(TAG, "onClick: GoogleSignIn");
                googleSignIn();
                break;
            case R.id.btn_forgot_password:
                startActivity(new Intent(getApplicationContext(), ResetPasswordActivity.class));
                break;
            default:
                break;
        }
    }


    private void googleSignIn() {
        Log.d(TAG, "googleSignIn: Made it to the sign in method");
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQUEST_SIGN_GOOGLE);
    }

    private void signIn() {
        EditText inputEmail = ((EditText) findViewById(R.id.email));
        String email = inputEmail.getText().toString();
        final EditText inputPassword = ((EditText) findViewById(R.id.password));
        final String password = inputPassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!",
                Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            ((EditText) findViewById(R.id.password)).setError("You must enter a password");
        } else if (password.length() < 6) {
            ((EditText) findViewById(R.id.password))
                .setError(getString(R.string.minimum_password));
        } else {
            //authenticate user
            loginPresenter.signInWithEmailPassword(email, password);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "LoginActivity Connection Failed: " + connectionResult.getErrorMessage());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // google
        if (requestCode == REQUEST_SIGN_GOOGLE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.d(TAG, "onActivityResult: " + result.isSuccess());
            Log.d(TAG, "onActivityResult: " + result.getSignInAccount());
            loginPresenter.firebaseAuthWithGoogle(result.getSignInAccount());
            startActivity(new Intent(getApplicationContext(), NearbyRestaurantActivity.class));
        }

    }


}
