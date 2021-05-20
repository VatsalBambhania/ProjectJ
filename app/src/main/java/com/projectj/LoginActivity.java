package com.projectj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText userIdEt;
    private EditText passwordEt;
    private Button loginBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            Toast.makeText(this, "Logging in", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, MainActivity.class);
            i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }

        userIdEt = findViewById(R.id.userIdEt);
        passwordEt = findViewById(R.id.passwordEt);
        loginBt = findViewById(R.id.checkCred);

        loginBt.setOnClickListener(butView -> {
            String userId = userIdEt.getText().toString().trim();
            String password = passwordEt.getText().toString().trim();
            if(!userId.isEmpty() && !password.isEmpty()){
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                auth.signInWithEmailAndPassword(userId, password).addOnCompleteListener(this, task->{
                    if(task.isSuccessful()) {
                        Intent i = new Intent(this, MainActivity.class);
                        i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                    else
                        Toast.makeText(this, "Wrong Creds", Toast.LENGTH_SHORT).show();
                });
            }else{
                Toast.makeText(this, "Please fill User Id and Password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}