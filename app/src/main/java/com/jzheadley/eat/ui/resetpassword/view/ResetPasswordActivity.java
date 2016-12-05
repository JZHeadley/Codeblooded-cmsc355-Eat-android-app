package com.jzheadley.eat.ui.resetpassword.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
              resetPasswordPresenter.resetPassword(
                  ((EditText) findViewById(R.id.reset_email)).getText().toString().trim());
              startActivity(new Intent(getApplicationContext(), LoginActivity.class));
              break;
          case R.id.btn_back:
              finish();
              break;
          default:
              break;

        }
    }


}
