package com.example.appespejo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

//import com.firebase.ui.auth.AuthUI;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    Context context;
    private String name="";
    private String pass="";
    public GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CallbackManager callbackManager;
//    private LoginButton loginButton;


//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
////        updateUI(currentUser);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        facebook();
        context = this;
        mAuth = FirebaseAuth.getInstance();
        createRequest();
        setupbd();
        }


        private void facebook(){
            callbackManager = CallbackManager.Factory.create();

            LoginButton loginButton = findViewById(R.id.login_button);
            loginButton.setPermissions(Arrays.asList("email,user_gender"));

            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Log.d("Demo","Login successfull");
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }

                @Override
                public void onCancel() {
                    Log.d("Demo","Login onCancel");
                }

                @Override
                public void onError(@NonNull FacebookException e) {
                    Log.d("Demo","Login onError");
                }
            });
        }

        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if(currentAccessToken == null){
                    LoginManager.getInstance().logOut();
                }
            }
        };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.startTracking();
    }

    private void createRequest(){

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail() //request to google to open a popup
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(@Nullable JSONObject object, @Nullable GraphResponse graphResponse) {
                Log.d("Demo", object.toString());
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString("fields","gender, first_name,  email");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();

//        if (RC_SIGN_IN == 123) {
//                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//                        try {
//                            // Google Sign In was successful, authenticate with Firebase
//                            GoogleSignInAccount account = task.getResult(ApiException.class);
////                                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
//                            firebaseAuthWithGoogle(account.getIdToken());
//                        } catch (ApiException e) {
//                            // Google Sign In failed, update UI appropriately
////                                Log.w(TAG, "Google sign in failed", e);
//                        }
//
//                    } else if (RC_SIGN_IN == 321) {
//                        // ToDo : Do your stuff...
//                    }
    }


        private void signIn() {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//            activityResultLaunch.launch(signInIntent);
//            startActivityForResult(signInIntent, RC_SIGN_IN);
        }


//    ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    int requestCode, resultCode;
//                    Intent data = new Intent();
//                    if (result.getResultCode() == 123) {
//                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//                        try {
//                            // Google Sign In was successful, authenticate with Firebase
//                            GoogleSignInAccount account = task.getResult(ApiException.class);
////                                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
//                            firebaseAuthWithGoogle(account.getIdToken());
//                        } catch (ApiException e) {
//                            // Google Sign In failed, update UI appropriately
////                                Log.w(TAG, "Google sign in failed", e);
//                        }
//
//                    } else if (result.getResultCode() == 321) {
//                        // ToDo : Do your stuff...
//                    }
//                }
//            });


    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LoginActivity.this, "Has entrado en tu account", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                            Intent intent = new Intent (getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        } else{
                            Toast.makeText(LoginActivity.this, "Fail auth google",Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }
    

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    private void setupbd() {

        Button signUpButton = this.findViewById(R.id.signUpButton);
        TextView register = this.findViewById(R.id.register);
        ImageButton google = this.findViewById(R.id.googleLogin);
        ImageButton facebook = this.findViewById(R.id.facebook);
        TextView recuperar = this.findViewById(R.id.Recuperar);
        TextInputEditText textLogin = this.findViewById(R.id.login);
        TextInputEditText textPassword = this.findViewById(R.id.textInputEditText);

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

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

//        facebook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RecuperarContr.class);
                startActivity(intent);
            }
        });

    }
}