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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ownerRegiPage extends AppCompatActivity {

    private TextInputEditText ownerEmail,ownerPass,ownerName;
    private Button ownerLoginBtn,ownerRegiBtn;
    private DatabaseReference databaseUsers;
    private FirebaseAuth authm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_regi_page);
        ownerEmail=findViewById(R.id.OwnerRegiEmail);
        ownerPass=findViewById(R.id.OwnerRegiPass);
        ownerName=findViewById(R.id.OwnerName);
        ownerRegiBtn=findViewById(R.id.ownerRegiBtn);
        ownerLoginBtn=findViewById(R.id.ownerLoginBtn);
        authm=FirebaseAuth.getInstance();

        ownerRegiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(ownerEmail.getText().toString()) ||
                        TextUtils.isEmpty(ownerPass.getText().toString()) ||
                        TextUtils.isEmpty(ownerName.getText().toString())
                )
                {
                    Toast.makeText(ownerRegiPage.this,"Some fields are empty",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String OEmail=ownerEmail.getText().toString();
                    String OPassword=ownerPass.getText().toString();
                    databaseUsers= FirebaseDatabase.getInstance().getReference("Owner");
                    authm.createUserWithEmailAndPassword(OEmail,OPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(ownerRegiPage.this, "Registerd", Toast.LENGTH_SHORT).show();
                                ownerInfo curUser=new ownerInfo(600,800,900,0,0,0);
                                databaseUsers.child(authm.getCurrentUser().getUid()).setValue(curUser);
                                databaseUsers.child(authm.getCurrentUser().getUid());
                                Intent i=new Intent(getApplicationContext(),ownerDisplayPage.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    });
                }
            }
        });
        ownerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ownerLoginPage.class);
                startActivity(i);
                finish();
            }
        });
    }
}