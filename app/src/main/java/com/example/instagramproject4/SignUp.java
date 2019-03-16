package com.example.instagramproject4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtSignUpEmail,edtSignUpUserName,edtSignUpPassword;
    private Button btnSignUpSignUp,btnSignUpLogIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");

        edtSignUpEmail = findViewById(R.id.edtSignUpEmail);
        edtSignUpUserName = findViewById(R.id.edtSignUpUserName);
        edtSignUpPassword = findViewById(R.id.edtSignUpPassword);

        edtSignUpPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode ==KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){

                    onClick(btnSignUpSignUp);
                }
                return false;
            }
        });

        btnSignUpSignUp = findViewById(R.id.btnSignUpSignUp);
        btnSignUpLogIn = findViewById(R.id.btnSignUpLogIn);

        btnSignUpSignUp.setOnClickListener(this);
        btnSignUpLogIn.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null){

           // ParseUser.getCurrentUser().logOut();
        transitionToSocialMesdiaActivity();
        }


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){


            case R.id.btnSignUpSignUp:

                if (edtSignUpEmail.getText().toString().equals("") || edtSignUpUserName.getText().toString().equals("") || edtSignUpPassword.getText().toString().equals("")){
                    FancyToast.makeText(SignUp.this,  " Email, UserName and PassWord is required! ",
                            Toast.LENGTH_LONG, FancyToast.INFO, true).show();


                }else {


                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtSignUpEmail.getText().toString());
                    appUser.setUsername(edtSignUpUserName.getText().toString());
                    appUser.setPassword(edtSignUpPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing Up " + edtSignUpUserName);

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignUp.this, appUser.getUsername() + " is signed Up ",
                                        Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                                transitionToSocialMesdiaActivity();


                            } else {
                                FancyToast.makeText(SignUp.this, "There was an error: " + e.getMessage(),
                                        Toast.LENGTH_LONG, FancyToast.ERROR, true).show();


                            }
                            progressDialog.dismiss();
                        }
                    });
                }
                break;

            case R.id.btnSignUpLogIn:

                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);


                break;
        }

    }

    public void rootLayouttapped(View view){


        try {

            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);


        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void transitionToSocialMesdiaActivity() {

        Intent intent= new Intent(SignUp.this, SocialMediaActivity.class);
    startActivity(intent);
    }
}
