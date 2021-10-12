package com.example.appespejo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class VerificarActivity extends AppCompatActivity {

    private final static int RC_SIGN_IN = 123;

    EditText email;
    Button verificar;
    FirebaseUser usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar);
        login();
//        setup();
        email = findViewById(R.id.editTextTextPersonName);
        email.setText(usuario.getEmail());


    }


//    Al pinchar el btoton compruebe si el correo ya esta verificado o no.
    private void setup(){
        usuario = FirebaseAuth.getInstance().getCurrentUser();
        verificar = this.findViewById(R.id.button2);
        verificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usuario.isEmailVerified()) {
                    Intent i = new Intent(VerificarActivity.this, HomeActivity.class);
                    startActivity(i);
                }
                Toast.makeText(VerificarActivity.this, "No se verifica el correo", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void login() {

        usuario = FirebaseAuth.getInstance().getCurrentUser();
        if (usuario.isEmailVerified() && usuario != null) {
            Toast.makeText(this, "inicia sesión: "+usuario.getDisplayName()+
                    " - "+ usuario.getEmail(),Toast.LENGTH_LONG).show();
            Intent i = new Intent(VerificarActivity.this, HomeActivity.class);
            startActivity(i);
        } else {

            if (usuario != null) {
                usuario.sendEmailVerification();
                Toast.makeText(this, "Se ha enviado un correo de confirmación", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                login();
            } else {

                IdpResponse response = IdpResponse.fromResultIntent(data);
                if (response == null) {
                    Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
                    return;
                } else if (response.getError().getErrorCode() ==
                        ErrorCodes.NO_NETWORK) {
                    Toast.makeText(this, "Sin conexión a Internet", Toast.LENGTH_LONG).show();
                    return;
                } else if (response.getError().getErrorCode() ==
                        ErrorCodes.UNKNOWN_ERROR) {
                    Toast.makeText(this, "Error desconocido", Toast.LENGTH_LONG).show();
                    return;
                }

            }

        }
    }
}