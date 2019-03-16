package com.example.instagramproject4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLoginEmail,edtLoginPassword;
    private Button btnLoginLogin,btnLoginSignUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnLoginLogin = findViewById(R.id.btnLoginLogin);
        btnLoginSignUp = findViewById(R.id.btnLoginSignUp);

        btnLoginLogin.setOnClickListener(this);
        btnLoginSignUp.setOnClickListener(this);


        if(ParseUser.getCurrentUser() != null){

            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnLoginLogin:

                ParseUser.logInInBackground(edtLoginEmail.getText().toString(),
                        edtLoginPassword.getText().toString(),
                        new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null  && e == null)
                        {

                            FancyToast.makeText(LoginActivity.this, user.getUsername() +
                                    " is logged in successfully ", FancyToast.LENGTH_LONG,
                                    FancyToast.SUCCESS, true).show();

                                transitionToSocialMesdiaActivity();
                        }
                    }
                });

                break;
            case R.id.btnLoginSignUp:
                break;

        }



    }
    private void transitionToSocialMesdiaActivity() {

        Intent intent= new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);
    }
}
