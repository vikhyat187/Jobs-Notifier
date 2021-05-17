package com.vikhyat.jobnotifier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;

import Activities.LoginActivity;
import modal.User;

public class UserLoggedIn extends Activity {
    Button Prepare;
    Button Apply;
    View fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_logged_in);
        Prepare = (Button)findViewById(R.id.Prepare);
        Apply = (Button)findViewById(R.id.Apply);

        Prepare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Prepare clicked",Snackbar.LENGTH_LONG).show();
                Intent faculty = new Intent(getApplicationContext(), ServiceBasedActivity.class);
                startActivity(faculty);
            }
        });
        Apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Apply clicked",Snackbar.LENGTH_LONG).show();
                Intent user = new Intent(getApplicationContext(), JobsActivity.class);
                startActivity(user);
            }
        });
    }


}
