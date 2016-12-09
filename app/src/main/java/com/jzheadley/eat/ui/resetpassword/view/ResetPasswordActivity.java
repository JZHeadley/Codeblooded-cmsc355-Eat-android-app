package com.jzheadley.eat.ui.resetpassword.view;

import static com.jzheadley.eat.utils.Utilities.isValidEmail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.login.view.LoginActivity;
import com.jzheadley.eat.ui.resetpassword.presenter.ResetPasswordPresenter;


public class ResetPasswordActivity extends BaseActivity implements View.OnClickListener {
    private ResetPasswordPresenter resetPasswordPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        resetPasswordPresenter = new ResetPasswordPresenter(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_forgot_password:
                EditText newPasswordEditText = (EditText) findViewById(R.id.reset_email);
                if (TextUtils.isEmpty(newPasswordEditText.getText())) {
                    newPasswordEditText.setError("You must enter your email.");
                } else if (!isValidEmail(newPasswordEditText.getText())) {
                    newPasswordEditText.setError("Please enter a valid email.");
                } else {
                    resetPasswordPresenter.resetPassword(
                        newPasswordEditText.getText().toString().trim());
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
                break;
            case R.id.btn_back:
                finish();
                break;
            default:
                break;

        }
    }
}
