package com.example.water_quality_monitoring_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        EditText emails=(EditText) findViewById(R.id.email);
        EditText pass=(EditText) findViewById(R.id.password);
        EditText repass=(EditText) findViewById(R.id.repassword);
        MaterialButton signbtn=(MaterialButton) findViewById(R.id.signupbtn);
        progressBar=findViewById(R.id.progressBar);
        TextView textView=(TextView) findViewById(R.id.loginNow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email,password,repassword;
                email=String.valueOf(emails.getText());
                password=String.valueOf(pass.getText());
                repassword=String.valueOf(repass.getText());
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.equals(password,repassword)) {
//                    Toast.makeText(Register.this, "Incorrect repassword", Toast.LENGTH_SHORT).show();


                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(Register.this, "Account Created", Toast.LENGTH_SHORT).show();
//                                    FirebaseUser user = mAuth.getCurrentUser();

                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                        finish();


                                    } else {
                                        // If sign in fails, display a message to the user.
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Register.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(Register.this, "Incorrect repassword", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}