package com.example.appespejo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    Context context;
    private String name="";
    private String pass="";

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
//        setup();  //con prueba automatica sin bd
        setupbd();
        }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    private void setupbd() {

        Button signUpButton = this.findViewById(R.id.signUpButton);
        EditText textLogin = this.findViewById(R.id.editTextTextPersonName);
        EditText textPassword = this.findViewById(R.id.editTextTextPassword);
        Button register = this.findViewById(R.id.button2);

        signUpButton.setOnClickListener(new View.OnClickListener(){ //para logear

            @Override
            public void onClick(View v) {
                String inputName = textLogin.getText().toString();
                String inputPassword = textPassword.getText().toString();

                if(!inputName.isEmpty() || !inputPassword.isEmpty()){
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(inputName,inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(LoginActivity.this, "Incorrecto usuario o/y contrasena", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                if(inputName.isEmpty() || inputPassword.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                }
                if(!isEmailValid(inputName)){
                    Toast.makeText(LoginActivity.this, "Incierta tu email por favor", Toast.LENGTH_SHORT).show();
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

    private void setup() {
        Button signUpButton = this.findViewById(R.id.signUpButton);
        EditText textLogin = this.findViewById(R.id.editTextTextPersonName);
        EditText textPassword = this.findViewById(R.id.editTextTextPassword);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = textLogin.getText().toString();
                String inputPassword = textPassword.getText().toString();

                if (inputName.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter all the details correctly", Toast.LENGTH_SHORT).show();
                } else {
                    isValue = validate(inputName, inputPassword);
                }

                if (!isValue) {
                    counter--;
                    Toast.makeText(LoginActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();

                    if (counter == 0) {
                        signUpButton.setEnabled(false);
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });


        signUpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String inputName = textLogin.getText().toString();
                String inputPassword = textPassword.getText().toString();

                if (inputName.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter all the details correctly", Toast.LENGTH_SHORT).show();
                } else {
                    isValue = validate(inputName, inputPassword);
                }

                if (!isValue) {
                    counter--;
                    Toast.makeText(LoginActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
//                        if(counter ==0){
//                            signUpButton.setEnabled(false);
//                        }
                } else {
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                    // add the code to go to a new activity
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validate(String name, String pass){

        if(name.equals(username) && pass.equals(passwordd)){
            return true;
        }
        return false;
    }
}