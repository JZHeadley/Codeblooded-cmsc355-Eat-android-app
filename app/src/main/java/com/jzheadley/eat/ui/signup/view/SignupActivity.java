package com.jzheadley.eat.ui.signup.view;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.login.view.LoginActivity;
import com.jzheadley.eat.ui.resetpassword.view.ResetPasswordActivity;
import com.jzheadley.eat.ui.signup.presenter.SignupPresenter;


public class SignupActivity extends BaseActivity implements View.OnClickListener,
    OnCompleteListener<AuthResult> {

    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressBar progressBar;
    private SignupPresenter signupPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        signupPresenter = new SignupPresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.sign_up_button:
                signUp();
                break;
            case R.id.btn_reset_password:
                startActivity(new Intent(this, ResetPasswordActivity.class));
                break;
            default:
                break;
        }
    }

    private void signUp() {
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(),
                "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        signupPresenter.createUser(email, password);
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:"
            + task.isSuccessful(), Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        // If sign in fails, display a message to the user. If sign in succeeds
        // the auth state listener will be notified and logic to handle the
        // signed in user can be handled in the listener.
        if (!task.isSuccessful()) {
            Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(this, SignupActivity.class));
            finish();
        }
    }
}