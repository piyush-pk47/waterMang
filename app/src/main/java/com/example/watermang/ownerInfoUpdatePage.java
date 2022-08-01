package com.example.watermang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ownerInfoUpdatePage extends AppCompatActivity {

    private TextInputEditText firstInput,secInput,thirdInput;
    private Button finalUpdate;
    private DatabaseReference databaseUsers;
    private FirebaseAuth authm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_info_update_page);
        firstInput=findViewById(R.id.FirstCapUpd);
        secInput=findViewById(R.id.SecCapUpd);
        thirdInput=findViewById(R.id.thirdCapUpd);
        finalUpdate=findViewById(R.id.finalUpdBtn);
        databaseUsers= FirebaseDatabase.getInstance().getReference("Owner");
        finalUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(firstInput.getText().toString()) ||
                        TextUtils.isEmpty(secInput.getText().toString()) ||
                        TextUtils.isEmpty(thirdInput.getText().toString())
                )
                {
                    Toast.makeText(ownerInfoUpdatePage.this, "Some fields are empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int firCap=Integer.parseInt(firstInput.getText().toString());
                    int secCap=Integer.parseInt(secInput.getText().toString());
                    int thirCap=Integer.parseInt(thirdInput.getText().toString());
                    ownerInfo curUser=new ownerInfo(firCap,secCap,thirCap,0,0,0);
                    databaseUsers.child("RHuuxq1wgRZgB4hWim8vg6abHdl2").setValue(curUser);
                    Intent i=new Intent(getApplicationContext(),ownerDisplayPage.class);
                    startActivity(i);
                    finish();
                }

            }
        });

    }
}