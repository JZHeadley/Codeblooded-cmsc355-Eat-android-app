package com.jzheadley.eat.ui.resetpassword.presenter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.jzheadley.eat.ui.base.presenter.BasePresenterImpl;
import com.jzheadley.eat.ui.resetpassword.view.ResetPasswordActivity;


public class ResetPasswordPresenter extends BasePresenterImpl {
    private ResetPasswordActivity resetPasswordActivity;

    public ResetPasswordPresenter(ResetPasswordActivity resetPasswordActivity) {
        super(resetPasswordActivity);
        this.resetPasswordActivity = resetPasswordActivity;
    }


    public void resetPassword(String email) {
        this.showProgress();
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener(resetPasswordActivity, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(resetPasswordActivity,
                            "We have sent you instructions to reset your password!",
                            Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(resetPasswordActivity, "Failed to send reset email!",
                            Toast.LENGTH_SHORT).show();
                    }
                    hideProgress();
                }
            });
    }
}
