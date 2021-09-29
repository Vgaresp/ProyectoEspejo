package com.example.appespejo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Context context;

    String username = "Admin";
    String passwordd = "1234";
    boolean isValue = false;
    int counter = 5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

//        Setup
        setup();
        }


    private void setup() {

        Button signUpButton = this.findViewById(R.id.signUpButton);
        EditText textLogin = this.findViewById(R.id.editTextTextPersonName);
        EditText textPassword = this.findViewById(R.id.editTextTextPassword);
        Button register = this.findViewById(R.id.button2);

        signUpButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String inputName = textLogin.getText().toString();
                String inputPassword = textPassword.getText().toString();

                if(inputName.isEmpty() || inputPassword.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Please enter all the details correctly",Toast.LENGTH_SHORT).show();
                }else{
                    isValue = validate(inputName,inputPassword);

                    if(!isValue){
                        counter--;
                        Toast.makeText(LoginActivity.this,"Incorrect",Toast.LENGTH_SHORT).show();
                        if(counter ==0){
                            signUpButton.setEnabled(false);
                        }
                    }else{
                        Toast.makeText(LoginActivity.this,"Login successful",Toast.LENGTH_SHORT).show();

                        // add the code to go to a new activity
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }

        });

    }



    private boolean validate(String name, String password){

        if(name.equals(username) && password.equals(passwordd)){
            return true;
        }
        return false;
    }


}