package com.example.appespejo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrimeraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera);
        setup();
    }


    private void setup(){
        Button login = this.findViewById(R.id.primeraLogin);
        Button regist = this.findViewById(R.id.primeraRegist);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrimeraActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrimeraActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}