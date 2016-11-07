package com.jzheadley.eat.ui.login.presenteres;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import com.jzheadley.eat.ui.base.presenter.BasePresenter;

public interface LoginPresenter extends BasePresenter {

    void firebaseAuthWithGoogle(GoogleSignInAccount acct);

    void signInWithEmailPassword(String email, String password);
}
