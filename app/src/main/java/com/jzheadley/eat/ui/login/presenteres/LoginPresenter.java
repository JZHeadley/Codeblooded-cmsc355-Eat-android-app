package com.jzheadley.eat.ui.login.presenteres;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.jzheadley.eat.ui.base.presenter.BasePresenter;

public interface LoginPresenter extends BasePresenter {

    void attemptToLogInWithEmail(String email, String password);

    void attemptToLogInWithGoogle();

    void firebaseAuthWithGoogle(GoogleSignInAccount acct);

    void getAuthWithGoogle(GoogleSignInResult result);

    void signOut(GoogleApiClient googleApiClient);

    void signInWithEmailPassword(String email, String password);


}
