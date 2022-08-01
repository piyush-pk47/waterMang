package com.example.watermang;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ownerDisplayPage extends AppCompatActivity {

    private DatabaseReference databaseUsers;
    private FirebaseAuth authm;
    private Button updBtn;
    TextView flatCap1,flatCap2,flatCap3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_display_page);
        flatCap1=findViewById(R.id.firstCap);
        flatCap2=findViewById(R.id.secCap);
        flatCap3=findViewById(R.id.thirdCap);
        authm=FirebaseAuth.getInstance();
        databaseUsers= FirebaseDatabase.getInstance().getReference("Owner");
        updBtn=findViewById(R.id.updateLevel);
        databaseUsers.child(authm.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    ownerInfo curOwn=task.getResult().getValue(ownerInfo.class);
                    Log.d("firebase", String.valueOf(curOwn.getTotCap1()));
                    flatCap1.setText(String.valueOf(curOwn.getTotCap1())+" Liters");
                    flatCap2.setText(String.valueOf(curOwn.getTotCap2())+" Liters");
                    flatCap3.setText(String.valueOf(curOwn.getTotCap3())+" Liters");
                }
            }
        });
        updBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ownerInfoUpdatePage.class);
                startActivity(i);
                finish();
            }
        });

    }


}