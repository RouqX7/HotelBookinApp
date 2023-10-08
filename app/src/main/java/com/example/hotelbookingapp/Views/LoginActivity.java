package com.example.hotelbookingapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hotelbookingapp.Controllers.GoogleAuthUIClient;
import com.example.hotelbookingapp.R;
import com.example.hotelbookingapp.Views.AccountCreation.LogIn.LoginWithEmail;
import com.google.android.gms.common.SignInButton;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity  implements GoogleAuthUIClient.AuthResultCallback{
    private GoogleAuthUIClient mGoogleAuthUIClient;
    MaterialButton emailBtn;
    TextView createAccountTextBtn;
    SignInButton btnGoogleSignIn;

    boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mGoogleAuthUIClient = new GoogleAuthUIClient(this,(GoogleAuthUIClient.AuthResultCallback)this);

        emailBtn = findViewById(R.id.btn_log_in_with_email);
        createAccountTextBtn = findViewById(R.id.create_account_text_view_btn);
        btnGoogleSignIn = findViewById(R.id.btn_google_sign_in);
        emailBtn.setOnClickListener((v) -> startActivity(new Intent(this, LoginWithEmail.class)));
        btnGoogleSignIn.setOnClickListener(view -> mGoogleAuthUIClient.signIn());




    }

    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the result to the GoogleAuthUiClient for handling
        mGoogleAuthUIClient.handleSignInResult(data);
    }
    @Override
    public void onAuthCompleted(boolean isSuccess) {

    }
}