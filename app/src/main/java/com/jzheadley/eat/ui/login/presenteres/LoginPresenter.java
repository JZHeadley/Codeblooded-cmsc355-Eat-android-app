package com.jzheadley.eat.ui.login.presenteres;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface LoginPresenter {

    void attemptToLogInWithEmail(String email, String password);

    void attemptToLogInWithGoogle();

    void firebaseAuthWithGoogle(GoogleSignInAccount acct);

    void getAuthWithGoogle(GoogleSignInResult result);

    void signOut(GoogleApiClient googleApiClient);
}
