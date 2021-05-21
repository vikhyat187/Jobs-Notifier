package Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vikhyat.jobnotifier.JobsActivity;
import com.vikhyat.jobnotifier.MainActivity;
import com.vikhyat.jobnotifier.ProductBasedActivity;
import com.vikhyat.jobnotifier.R;
import com.vikhyat.jobnotifier.UserLoggedIn;

import Helper.InputValidation;
import SQL.DatabaseHelper;
import modal.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    String type_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        type_user= getIntent().getStringExtra("user");

        initViews();
        initListeners();
        initObjects();
    }
    private void initViews(){
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);
        textViewLinkRegister = (AppCompatTextView)findViewById(R.id.textViewLinkRegister);

    }
    private void initListeners(){
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }
    private void initObjects(){
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                //navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }
    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email)))
            return;
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail,textInputLayoutEmail,getString(R.string.error_message_email)))
            return;

        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword,textInputLayoutPassword,getString(R.string.error_message_password)))
            return;
        if(databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim(),textInputEditTextPassword.getText().toString().trim())){
            Intent accountsIntent = null;
            if (type_user.equals("student")) {
                accountsIntent = new Intent(getApplicationContext(), UserLoggedIn.class);
                accountsIntent.putExtra("EMAIL",textInputEditTextEmail.getText().toString().trim());
            }
            else if(type_user.equals("faculty")){
                if (textInputEditTextEmail.equals(getResources().getString(R.string.facultyemail))){
                    if (textInputEditTextPassword.equals(getResources().getString(R.string.facultypassword))){
                        accountsIntent = new Intent(getApplicationContext(), JobsActivity.class);
                        accountsIntent.putExtra("EMAIL",textInputEditTextEmail.getText().toString().trim());
                    }
                    else{
                        textInputLayoutEmail.setError
                    }
                }

            }
            emptyInputEditText();
            startActivity(accountsIntent);
        }
        else{
            //snackbar to show that the message that record is wrong
            Snackbar.make(nestedScrollView,getString(R.string.error_valid_email_password),Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText(){
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}