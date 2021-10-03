package com.example.appespejo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private String name = "";
    private String pass = "";

    FirebaseAuth mAuth;
    DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setup();

        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();
    }

    private void setup(){

        Button register = this.findViewById(R.id.registrarmeButton);
        EditText usuario = this.findViewById(R.id.EditTextRegUsername);
        EditText contrasena = this.findViewById(R.id.editTextRegContras);
        EditText nombre = this.findViewById(R.id.Nombre);
        EditText apellido = this.findViewById(R.id.Apellido);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                name = usuario.getText().toString();
                pass = contrasena.getText().toString();

                if(!name.isEmpty() || !pass.isEmpty()){
                    if(pass.length() < 6){
                        Toast.makeText(RegisterActivity.this, "La contrasena debe consistir al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }else{
                        registerUser();
                    }

                }else{
                    Toast.makeText(RegisterActivity.this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registerUser(){
        mAuth.createUserWithEmailAndPassword(name,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

/*                    Map<String,Object> map = new HashMap<>();
                    map.put("Usuario",name);
                    map.put("Contrasena",pass);

                    String id = mAuth.getCurrentUser().getUid();

                    mDataBase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> taskZ) {
                            if(taskZ.isSuccessful()){
*/
                    Intent intent2 = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(intent2);
                    //finish(); //Para evitar que vuelva a la pantalla del registro

  /*                          }
                            else{
                                Toast.makeText(RegisterActivity.this, "Habia un error en crear nuevos datos", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
*/
                }else{
                    Toast.makeText(RegisterActivity.this,"No se pudo registrar el usuario "
                            +task.getException().getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}