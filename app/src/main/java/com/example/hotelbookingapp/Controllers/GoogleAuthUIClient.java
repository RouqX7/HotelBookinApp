package com.example.hotelbookingapp.Controllers;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.example.hotelbookingapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class GoogleAuthUIClient {
    private static final int RC_SIGN_IN = 9001;
    private final Activity mActivity;
    private final GoogleSignInClient mGoogleSignInClient;
    private final FirebaseAuth mAuth;
    private AuthResultCallback authResultCallback;

    public GoogleAuthUIClient(Activity activity, AuthResultCallback authResultCallback) {
        mActivity = activity;
        this.authResultCallback = authResultCallback;
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
    }

    public void createAccount() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            mActivity.startActivityForResult(signInIntent, RC_SIGN_IN);
        } else {
            // User is already signed in with Firebase, notify the callback for account creation
            authResultCallback.onAuthCompleted(true);
        }
    }

    public void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        mActivity.startActivityForResult(signInIntent, Constants.RC_SIGN_IN);
    }

    public void signIn() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            mActivity.startActivityForResult(signInIntent, Constants.RC_SIGN_IN);
        } else {
            // User is already signed in with Firebase, navigate to the next activity
            authResultCallback.onAuthCompleted(true);
        }
    }


    public void handleSignInResult(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            // Google Sign-In was successful, authenticate with Firebase
            GoogleSignInAccount account = task.getResult(ApiException.class);
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            // Google Sign-In failed, handle the error (e.g., show a message to the user)
            Log.w(TAG, "Google Sign-In failed", e);
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Firebase Sign-In was successful, update UI with the signed-in user's information.
                        //FirebaseUser user = mAuth.getCurrentUser();
                        authResultCallback.onAuthCompleted(true);
                        // You can access user information using: user.getDisplayName(), user.getEmail(), etc.
                    } else {
                        // Handle Firebase Sign-In failure here.
                        Log.e(TAG, "Firebase sign-in failed", task.getException());

                    }
                });
    }

    public void signInWithActivity(Intent data) {
        handleSignInResult(data);
    }

    public interface AuthResultCallback {
        void onAuthCompleted(boolean isSuccess);

    }



}
