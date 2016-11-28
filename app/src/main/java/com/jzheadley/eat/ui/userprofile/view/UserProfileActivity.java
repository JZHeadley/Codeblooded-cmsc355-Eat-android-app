package com.jzheadley.eat.ui.userprofile.view;

import com.google.firebase.auth.FirebaseAuth;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.User;
import com.jzheadley.eat.data.services.UserService;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.nearbyrestaurants.view.NearbyRestaurantActivity;
import com.jzheadley.eat.ui.userprofile.presenter.UserProfilePresenter;

public class UserProfileActivity extends BaseActivity {
    private static final String TAG = "UserProfileActivity";
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
        String newEmail;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newEmail = null;
            } else {
                newEmail = extras.getString("newEmail");
                ((EditText) findViewById(R.id.email_profile_et))
                    .setHint(newEmail);
            }
        } else {
            displayEmail();

        }

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
                    EditText usernameEditText = ((EditText) findViewById(R.id.user_profile_et));
                    EditText emailEditText = ((EditText) findViewById(R.id.email_profile_et));
                    userProfilePresenter.modifyUser(user, usernameEditText.getText().toString(),
                        emailEditText.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), NearbyRestaurantActivity.class);
                    startActivity(intent);
                }
            })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
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
                    startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
                }
            })
            .create();
        alertDialog.show();
    }


}

