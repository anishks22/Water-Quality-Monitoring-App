package com.example.water_quality_monitoring_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    MaterialButton btn;
    TextView textView;
    FirebaseUser user;
    Button temp;
    Button ph;
    Button turbidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth=FirebaseAuth.getInstance();
        btn=findViewById(R.id.logout);
        textView=findViewById(R.id.user_details);
        user=auth.getCurrentUser();
        if(user==null){
            Intent intent=new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
            finish();
        }else{
            textView.setText(user.getEmail());
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });

        temp=findViewById(R.id.temp);
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Temperature.class);
                startActivity(intent);
                finish();
            }
        });

        ph=findViewById(R.id.ph);
        ph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Ph.class);
                startActivity(intent);
                finish();
            }
        });

        turbidity=findViewById(R.id.turb);
        turbidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Turbidity.class);
                startActivity(intent);
                finish();
            }
        });



    }
}