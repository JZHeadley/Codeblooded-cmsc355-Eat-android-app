package com.jzheadley.eat.ui.userprofile.presenter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jzheadley.eat.data.models.User;
import com.jzheadley.eat.data.services.UserService;
import com.jzheadley.eat.ui.base.presenter.BasePresenterImpl;
import com.jzheadley.eat.ui.resetpassword.view.ResetPasswordActivity;
import com.jzheadley.eat.ui.userprofile.view.UserProfileActivity;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserProfilePresenter extends BasePresenterImpl {
    private static final String TAG = "UserProfilePresenter";
    private UserService userService;
    private UserProfileActivity userProfileActivity;

    public UserProfilePresenter(UserProfileActivity userProfileActivity, UserService userService) {
        super(userProfileActivity);
        this.userProfileActivity = userProfileActivity;
        this.userService = userService;
    }

    public void getUser() {
        String firebaseId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userService.getUserApi()
                .getUserByFirebaseId(firebaseId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable error) {

                    }

                    @Override
                    public void onNext(User user) {
                        Log.d(TAG, "onNext: logged in user is" + user);
                        userProfileActivity.displayUsername(user);
                    }
                });
    }

    public void sendPasswordReset() {
        userProfileActivity.startActivity(new Intent(userProfileActivity.getApplicationContext(),
                ResetPasswordActivity.class));
    }

    public void deleteUser(User user) {
        userService.getUserApi()
                .deleteUser(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Void>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: User has been deleted");
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e(TAG, "onError: Could not delete user", error);
                    }

                    @Override
                    public void onNext(Void avoid) {
                        Log.d(TAG, "onNext: Deleting user");
                    }
                });
    }

    public void modifyUser(final User user, String newUsername, String newEmail) {
        FirebaseAuth.getInstance().getCurrentUser().updateEmail(newEmail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.i(TAG, "onComplete: user's email has been changed");
                            int userId = Integer.parseInt(user.getLinks().getSelf().getHref()
                                    .replace("http://192.99.0.20:9000/users/", ""));
                            Log.d(TAG, "loadRestaurants: " + userId);
                            userService.getUserApi()
                                    .updateUser(user, userId)
                                    .subscribeOn(Schedulers.newThread())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<Void>() {
                                        @Override
                                        public void onCompleted() {
                                            Log.d(TAG, "onCompleted: User has been updated");
                                        }

                                        @Override
                                        public void onError(Throwable error) {
                                            Log.e(TAG, "onError: User could not be updated", error);
                                        }

                                        @Override
                                        public void onNext(Void avoid) {
                                            Log.d(TAG, "onNext: Updating User");
                                        }
                                    });
                        } else {
                            Log.e(TAG, "onComplete: User's email could not be changed for "
                                    + "some reason ", task.getException());
                        }
                    }
                });


    }
}
