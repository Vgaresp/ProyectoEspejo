package com.example.espejoversion2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button signUpButton = findViewById(R.id.signUpButton);
    EditText textLogin = findViewById(R.id.editTextTextPersonName);
    EditText textPassword = findViewById(R.id.editTextTextPassword);
    String username = "Admin";
    String passwordd = "1234";
    boolean isValue = false;
    int counter = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Setup
        setup();
    }


    private void setup() {

        signUpButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String inputName = textLogin.getText().toString();
                String inputPassword = textPassword.getText().toString();

                if(inputName.isEmpty() || inputPassword.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter all the details correctly",Toast.LENGTH_SHORT).show();
                }else{
                    isValue = validate(inputName,inputPassword);

                    if(!isValue){
                        counter--;
                        Toast.makeText(MainActivity.this,"Incorrect",Toast.LENGTH_SHORT).show();
                        if(counter ==0){
                            signUpButton.setEnabled(false);
                        }
                    }else{
                        Toast.makeText(MainActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
                        // add the code to go to a new activity
                        Intent intent = new Intent(MainActivity.this, HomePage.class);
                        startActivity(intent);
                    }
                }
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