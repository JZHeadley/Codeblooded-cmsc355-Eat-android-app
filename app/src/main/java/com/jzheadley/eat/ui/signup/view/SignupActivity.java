package com.jzheadley.eat.ui.signup.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jzheadley.eat.R;
import com.jzheadley.eat.data.services.UserService;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.login.view.LoginActivity;
import com.jzheadley.eat.ui.resetpassword.view.ResetPasswordActivity;
import com.jzheadley.eat.ui.signup.presenter.SignupPresenter;


public class SignupActivity extends BaseActivity implements View.OnClickListener {
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputUsername;
    private SignupPresenter signupPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputUsername = (EditText) findViewById(R.id.username_signup);
        UserService userService = new UserService();
        signupPresenter = new SignupPresenter(this, userService);
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
            case R.id.btn_forgot_password:
                startActivity(new Intent(this, ResetPasswordActivity.class));
                break;
            default:
                break;
        }
    }

    private void signUp() {
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String username = inputUsername.getText().toString().trim();
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
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(getApplicationContext(),
                "Enter username!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(),
                "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
        signupPresenter.showProgress();
        signupPresenter.createUser(email, password, username);
        // signupPresenter.sendVerificationEmail(FirebaseAuth.getInstance().getCurrentUser());
        finish();
    }


}