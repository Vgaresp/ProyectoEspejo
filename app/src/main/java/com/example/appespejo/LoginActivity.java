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

public class LoginActivity extends AppCompatActivity {

    Context context;
    private String name="";
    private String pass="";

    String username = "Admin";
    String passwordd = "1234";
    boolean isValue = false;
    int counter = 5;

    FirebaseAuth mAuth;
    DatabaseReference mDataBase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

//        Setup
        setup();
//        setupbd(); //con prueba automatica sin bd
        }


    private void setup() {

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
                                Toast.makeText(LoginActivity.this, "Habia un error el acceder a tu perfil", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


//                    mAuth.signInWithEmailAndPassword(name,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if(task.isSuccessful()){
//
//
//                                Map<String,Object> map = new HashMap<>();
//                                map.put("Usuario",name);
//                                map.put("Contrasena",pass);
//
//                                String id = mAuth.getCurrentUser().getUid();
//
//                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                                startActivity(intent);
////
////                                mDataBase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
////                                    @Override
////                                    public void onComplete(@NonNull Task<Void> taskZ) {
////                                        if(taskZ.isSuccessful()){
////                                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
////                                            startActivity(intent);
//////                                finish(); //Para evitar que vuelva a la pantalla del registro
////
////                                        }
////                                        else{
////                                            Toast.makeText(LoginActivity.this, "Habia un error en crear nuevos datos", Toast.LENGTH_SHORT).show();
////                                        }
////                                    }
////                                });
//
//                            }else{
//                                Toast.makeText(LoginActivity.this,"No se pudo acceder al perfil",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
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

    private void setupbd(){
        Button signUpButton = this.findViewById(R.id.signUpButton);
        EditText textLogin = this.findViewById(R.id.editTextTextPersonName);
        EditText textPassword = this.findViewById(R.id.editTextTextPassword);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputName = textLogin.getText().toString();
                String inputPassword = textPassword.getText().toString();

                if(!inputName.isEmpty() || !inputPassword.isEmpty()){

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