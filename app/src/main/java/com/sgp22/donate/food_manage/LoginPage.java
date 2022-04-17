package com.sgp22.donate.food_manage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginPage extends AppCompatActivity {

    EditText Emaillogin,Passwordlogin;
    TextView New_here;
    Button next_btnL;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        Emaillogin = findViewById(R.id.Emaillogin);
        Passwordlogin = findViewById(R.id.Passwordlogin);
        next_btnL = findViewById(R.id.next_btnL);
        New_here=findViewById(R.id.New_here);
        mAuth = FirebaseAuth.getInstance();

        final DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users");
        New_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignupPage.class));
            }
        });
        next_btnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Emaillogin.getText().toString().trim();
                String password = Passwordlogin.getText().toString().trim();

                if (email.isEmpty()) {
                    Emaillogin.setError("Email is required");
                    Emaillogin.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Emaillogin.setError("Please enter a valid email!");
                    Emaillogin.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    Passwordlogin.setError("Password is required");
                    Passwordlogin.requestFocus();
                    return;
                }
                if (password.length() < 6){
                    Passwordlogin.setError("Please enter a valid Password!");
                    Passwordlogin.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            String uid = task.getResult().getUser().getUid();

                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                            firebaseDatabase.getReference().child("User").child(uid).child("from").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    int from = snapshot.getValue(Integer.class);
                                    if (from == 0){
                                        startActivity(new Intent(getApplicationContext(), Blank.class));
                                        finish();
                                    }
                                    if (from == 1){
                                        startActivity(new Intent(getApplicationContext(), Restarant.class));
                                        finish();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        } else {
                            Toast.makeText(LoginPage.this, "Failed to Login! please check your credential", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

    }
    
}