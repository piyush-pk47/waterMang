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

public class ownerLoginPage extends AppCompatActivity {

    private TextInputEditText ownerEmail,ownerPass;
    private Button ownerLoginBtn;
    private FirebaseAuth authm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login_page);
        ownerEmail=findViewById(R.id.OwnerLogiEmail);
        ownerPass=findViewById(R.id.OwnerLogiPass);
        ownerLoginBtn=findViewById(R.id.ownerLoginPageBtn);
        authm=FirebaseAuth.getInstance();
        ownerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String OEmail=ownerEmail.getText().toString();
                String OPassword=ownerPass.getText().toString();
                if(TextUtils.isEmpty(ownerEmail.getText().toString()) ||
                        TextUtils.isEmpty(ownerPass.getText().toString())
                )
                {
                    Toast.makeText(ownerLoginPage.this,"Some fields are empty",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    authm.signInWithEmailAndPassword(OEmail,OPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(ownerLoginPage.this, "LOGIN Successful", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(getApplicationContext(),ownerDisplayPage.class);
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