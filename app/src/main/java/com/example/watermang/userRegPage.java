package com.example.watermang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class userRegPage extends AppCompatActivity {

    private TextInputEditText userEmail,userPass,userName;
    private Button userLoginBtn,userRegiBtn;
    private FirebaseAuth authm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg_page);
        userEmail=findViewById(R.id.RentRegiEmail);
        userPass=findViewById(R.id.RentRegiPass);
        userName=findViewById(R.id.RentName);
        userRegiBtn=findViewById(R.id.RentRegiBtn);
        userLoginBtn=findViewById(R.id.RentLoginBtn);
        authm=FirebaseAuth.getInstance();
        userRegiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(userEmail.getText().toString()) ||
                        TextUtils.isEmpty(userPass.getText().toString()) ||
                        TextUtils.isEmpty(userName.getText().toString())
                )
                {
                    Toast.makeText(userRegPage.this,"Some fields are empty",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String OEmail=userEmail.getText().toString();
                    String OPassword=userPass.getText().toString();
                    authm.createUserWithEmailAndPassword(OEmail,OPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(userRegPage.this, "Registerd", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(getApplicationContext(),userDisplayPage.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    });
                }

            }
        });
        userLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),userLoginPage.class);
                startActivity(i);
                finish();
            }
        });
    }

}