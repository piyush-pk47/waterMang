package com.example.watermang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class userDisplayPage extends AppCompatActivity {

    private DatabaseReference databaseUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_display_page);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        TextView waterDisplay=findViewById(R.id.userCapDisplay);
        TextView avaliDisplay=findViewById(R.id.avaliCapId);
        databaseUsers= FirebaseDatabase.getInstance().getReference("Owner");
        databaseUsers.child("RHuuxq1wgRZgB4hWim8vg6abHdl2").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    ownerInfo curOwn=task.getResult().getValue(ownerInfo.class);
                    Log.d("firebase", String.valueOf(curOwn.getTotCap1()));
//                    flatCap1.setText(String.valueOf(curOwn.getTotCap1()));
//                    flatCap2.setText(String.valueOf(curOwn.getTotCap2()));
//                    flatCap3.setText(String.valueOf(curOwn.getTotCap3()));
                    if (user != null) {
                        String userEmail = user.getEmail();
                        String flat1Email="b@gmail.com";
                        String flat2Email="c@gmail.com";
                        String flat3Email="d@gmail.com";
                        if(userEmail.equals(flat1Email)) {
                            waterDisplay.setText("Total allocated water : "+curOwn.getTotCap1()+" liters");
                            avaliDisplay.setText("Available water : "+curOwn.getAvaCap1()+" liters");
                        }
                        else if(userEmail.equals(flat2Email))
                        {
                            waterDisplay.setText("Total allocated water : "+curOwn.getTotCap2()+" liters");
                            avaliDisplay.setText("Available water : "+curOwn.getAvaCap2()+" liters");
                        }
                        else if(userEmail.equals(flat3Email))
                        {
                            waterDisplay.setText("Total allocated water : "+curOwn.getTotCap3()+" liters");
                            avaliDisplay.setText("Available water : "+curOwn.getAvaCap3()+" liters");
                        }
                        else
                        {
                            waterDisplay.setText("Total allocated water : 1000 liters");
                            avaliDisplay.setText("Available water : "+0+" liters");
                        }
                    } else {
                        // No user is signed in
                    }
                }
            }
        });

    }
}