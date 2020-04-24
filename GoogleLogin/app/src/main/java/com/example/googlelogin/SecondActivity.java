package com.example.googlelogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SecondActivity extends AppCompatActivity {
    private ImageView userPhoto;
    private Button signOut;
    private TextView userName,userID,userEmail;
    private GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient= GoogleSignIn.getClient(this, gso);
        userPhoto=findViewById(R.id.userPhoto);
        userName=findViewById(R.id.userName);
        userEmail=findViewById(R.id.userEmail);
        userID=findViewById(R.id.userID);
        signOut=findViewById(R.id.signOut);
        GoogleSignInAccount acc=GoogleSignIn.getLastSignedInAccount(SecondActivity.this);
        if(acc!=null)
        {
            userName.setText(acc.getDisplayName());
            userEmail.setText(acc.getEmail());
            userID.setText(acc.getId());
            Uri uri=acc.getPhotoUrl();
            Glide.with(this).load(uri).into(userPhoto);

        }
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

    }
    private void signOut(){
        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(SecondActivity.this, "Succesfully SignOut", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SecondActivity.this,MainActivity.class));
                finish();
            }
        });


    }
}
