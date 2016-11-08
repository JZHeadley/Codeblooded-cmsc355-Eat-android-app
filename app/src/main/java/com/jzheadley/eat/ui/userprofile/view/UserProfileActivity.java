package com.jzheadley.eat.ui.userprofile.view;

import com.google.firebase.auth.FirebaseAuth;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.User;
import com.jzheadley.eat.data.services.UserService;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.userprofile.presenter.UserProfilePresenter;

public class UserProfileActivity extends BaseActivity {
    private UserProfilePresenter userProfilePresenter;
    private UserService userService;


    private User user;

    public void displayUsername(User user) {
        ((EditText) findViewById(R.id.user_profile_et))
                .setHint(user.getUsername());
        this.user = user;
    }

    public void displayEmail() {
        ((EditText) findViewById(R.id.email_profile_et))
                .setHint(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userService = new UserService();
        userProfilePresenter = new UserProfilePresenter(this, userService);
        userProfilePresenter.getUser();
        displayEmail();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sending_pass_reset_button:
                userProfilePresenter.sendPasswordReset();
                break;
            case R.id.remove_user_button:
                promptUserRemoval();
                break;
            case R.id.user_profile_update_submit_btn:
                promptUserUpdate();
                finish();
                break;
            default:
                break;
        }
    }

    private void promptUserUpdate() {
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this)
                .setMessage("Save Changes?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        userProfilePresenter.modifyUser(user,
                                ((EditText) findViewById(R.id.user_profile_et))
                                        .getText().toString(),
                                ((EditText) findViewById(R.id.email_profile_et))
                                        .getText().toString());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .create();
        alertDialog.show();

    }

    private void promptUserRemoval() {
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to delete your account?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        userProfilePresenter.deleteUser(user);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .create();
        alertDialog.show();
    }


}

