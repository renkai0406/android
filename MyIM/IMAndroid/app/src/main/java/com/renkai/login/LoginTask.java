package com.renkai.login;

import android.os.AsyncTask;

import com.renkai.R;

/**
 * Represents an asynchronous login/registration task used to authenticate
 * the user.
 */
public class LoginTask extends AsyncTask<String, Void, Boolean> {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "admin1:111111", "user01:111111"
    };

    private String mEmail;
    private String mPassword;

    private LoginActivity loginActivity;

    LoginTask(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        this.mEmail = params[0];
        this.mPassword = params[1];
        // TODO: attempt authentication against a network service.

        try {
            // Simulate network access.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            return false;
        }

        for (String credential : DUMMY_CREDENTIALS) {
            String[] pieces = credential.split(":");
            if (pieces[0].equals(mEmail)) {
                // Account exists, return true if the password matches.
                return pieces[1].equals(mPassword);
            }
        }

        // TODO: register the new account here.
        return true;
    }

    @Override
    protected void onPostExecute(final Boolean success) {
        loginActivity.onStopped();

        if (success) {
            loginActivity.jumpToNext();
        } else {
            loginActivity.error();
        }
    }

    @Override
    protected void onCancelled() {
        loginActivity.onStopped();
    }
}
