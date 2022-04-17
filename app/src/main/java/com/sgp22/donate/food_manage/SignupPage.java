package com.sgp22.donate.food_manage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.lang.reflect.Member;
import java.util.jar.Attributes;

public class SignupPage extends AppCompatActivity {
    private TextInputLayout name,mobile_num,emailRegister,passwordRegister,cpassword,address;
    Button next_btnR;
    TextView Already;
    FirebaseAuth fAuth;
    UserHelperClass userHelperClass;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    int i = 0;
    CheckBox checkBox,checkbox2;
    RadioButton NGO,REST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        userHelperClass = new UserHelperClass();

        mobile_num = findViewById(R.id.Mobile_num);
        emailRegister = findViewById(R.id.EmailRegister);
        name = findViewById(R.id.Name);
        passwordRegister = findViewById(R.id.PasswordRegister);
        cpassword = findViewById(R.id.Cpassword);
        next_btnR=findViewById(R.id.next_btnR);
        address=findViewById(R.id.Address);
        Already=findViewById(R.id.Already);
        NGO = findViewById(R.id.Ngo);
        REST = findViewById(R.id.restaurant);
        fAuth = FirebaseAuth.getInstance();


        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("User");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    i=(int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                ///
            }
        });


        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),LoginPage.class));
        }
        Already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginPage.class));
            }
        });

        next_btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Name = name.getEditText().getText().toString();
                String Mobilenumber = mobile_num.getEditText().getText().toString();
                String Email = emailRegister.getEditText().getText().toString();
                String Address = address.getEditText().getText().toString();
                String Password = passwordRegister.getEditText().getText().toString();
                String Confirmpasword = cpassword.getEditText().getText().toString();




                if(Name.isEmpty())
                {
                    name.setError("Name is required");
                    return;
                }

                if(Mobilenumber.isEmpty())
                {
                    mobile_num.setError("Mobile is required");
                    return;
                }
                if(Email.isEmpty())
                {
                    emailRegister.setError("Email is required");
                    return;
                }
                if(Password.isEmpty())
                {
                    passwordRegister.setError("Password is required");
                    return;
                }
                if(Address.isEmpty())
                {
                    address.setError("Password is required");
                    return;
                }
                if(Password.length()<6){
                  passwordRegister.setError("Password must be more than 6 characters");
                }

                if(Confirmpasword.isEmpty())
                {
                    cpassword.setError("Confirm Password is required");
                    return;
                }
                if(!Password.equals(Confirmpasword))
                {
                    cpassword.setError("Password do not match");
                    return;
                }
                // data is validated
               fAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           String uid = task.getResult().getUser().getUid();
                           userHelperClass = new UserHelperClass(uid,Name,Mobilenumber,Email,Password,Address);

                           if (NGO.isChecked()){
                               userHelperClass.setfrom(0);
                               reference.child(uid).setValue(userHelperClass);
                           }else{
                               userHelperClass.setfrom(1);
                               reference.child(uid).setValue(userHelperClass);
                           }
                           Toast.makeText(SignupPage.this, "User Created",Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(),LoginPage.class));
                       }
                       else
                       {
                           Toast.makeText(SignupPage.this,"Error !!"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                       }
                   }
               });
            }
        });

    }
}