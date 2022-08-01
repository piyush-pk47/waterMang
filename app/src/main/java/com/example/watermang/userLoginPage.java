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

public class userLoginPage extends AppCompatActivity {

    private TextInputEditText userEmail,userPass;
    private Button userLoginBtn;
    private FirebaseAuth authm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login_page);
        userEmail=findViewById(R.id.RentLogiEmail);
        userPass=findViewById(R.id.RentLogiPass);
        userLoginBtn=findViewById(R.id.RentLoginPageBtn);
        authm=FirebaseAuth.getInstance();
        userLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String OEmail=userEmail.getText().toString();
                String OPassword=userPass.getText().toString();
                if(TextUtils.isEmpty(userEmail.getText().toString()) ||
                        TextUtils.isEmpty(userPass.getText().toString())
                )
                {
                    Toast.makeText(userLoginPage.this,"Some fields are empty",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    authm.signInWithEmailAndPassword(OEmail,OPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                //Toast.makeText(userLoginPage.this, "LOGIN In Successful", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(getApplicationContext(),userDisplayPage.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    });
                }

            }
        });
    }
}