package com.example.aexpress.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aexpress.R;
import com.example.aexpress.dataModel.StudentDataModel;
import com.example.aexpress.db.MyDbHelper;
import com.example.aexpress.utils.SignUpFormValidation;


public class SignUpActivity extends AppCompatActivity {

    Button btn_signup,btn_signin;
    EditText et_first_name,et_last_name,et_email,et_mobile,et_username,et_password;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        context = SignUpActivity.this;
        allIds();
        allClickListener();

    }

    private void allClickListener() {
        btn_signin.setOnClickListener(v -> {
            Intent i = new Intent(context, SignInActivity.class);
            startActivity(i);
            finish();
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registration();
            }
        });
    }

    private void registration() {
        if (validate()){
            StudentDataModel studentDataModel = new StudentDataModel();

            studentDataModel.setFirstName(et_first_name.getText().toString());
            studentDataModel.setLastName(et_last_name.getText().toString());
            studentDataModel.setEmail(et_email.getText().toString());
            studentDataModel.setMobile(et_mobile.getText().toString());
            studentDataModel.setUsername(et_username.getText().toString());
            studentDataModel.setPassword(et_password.getText().toString());

            MyDbHelper myDbHelper = new MyDbHelper(context);
            if (myDbHelper.saveStudent(studentDataModel)){
                Toast.makeText(context, "Customer Saved Successfully",Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validate(){
        if(et_first_name.getText().toString().isEmpty()){
            et_first_name.setError(getResources().getString(R.string.enter_first_name));
            et_first_name.requestFocus();
            return false;
        } else if (et_last_name.getText().toString().isEmpty()) {
            et_last_name.setError(getResources().getString(R.string.enter_last_name));
            et_last_name.requestFocus();
            return false;
        } else if (et_email.getText().toString().isEmpty() && SignUpFormValidation.isEmailValid(et_email.getText().toString())){
            et_email.setError(getResources().getString(R.string.enter_email));
            et_email.requestFocus();
            return false;
        } else if (et_mobile.getText().toString().isEmpty()) {
            et_mobile.setError(getResources().getString(R.string.enter_mobile_number));
            et_mobile.requestFocus();
            return false;
        } else if (et_username.getText().toString().isEmpty()){
            et_username.setError(getResources().getString(R.string.enter_username));
            et_username.requestFocus();
            return false;
        } else if (et_password.getText().toString().isEmpty()){
            et_password.setError(getResources().getString(R.string.enter_password));
            et_password.requestFocus();
            return false;
        }
        return true;
    }

    private void allIds() {
        btn_signup  = findViewById(R.id.btn_signup);
        btn_signin  =findViewById(R.id.btn_signin);
        et_first_name = findViewById(R.id.et_first_name);
        et_last_name = findViewById(R.id.et_last_name);
        et_email = findViewById(R.id.et_email);
        et_mobile = findViewById(R.id.et_mobile);
        et_username = findViewById(R.id.et_username);
        et_password= findViewById(R.id.et_password);

    }
}