package com.vikhyat.jobnotifier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;

import Activities.LoginActivity;

public class MainActivity extends AppCompatActivity {
    Button Faculty;
    Button User;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Faculty = (Button)findViewById(R.id.Faculty);
        User = (Button)findViewById(R.id.User);
        Faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Faculty clicked",Snackbar.LENGTH_LONG).show();
                Intent faculty = new Intent(getApplicationContext(), LoginActivity.class);
                faculty.putExtra("user","faculty");
                startActivity(faculty);
            }
        });
        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Student clicked",Snackbar.LENGTH_LONG).show();
                Intent user = new Intent(getApplicationContext(), LoginActivity.class);
                user.putExtra("user","student");
                startActivity(user);
            }
        });
    }
}