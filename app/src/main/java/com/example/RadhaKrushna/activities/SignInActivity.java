package com.example.RadhaKrushna.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.RadhaKrushna.R;
import com.example.RadhaKrushna.db.MyDbHelper;

public class SignInActivity extends AppCompatActivity {
    Button btn_signin, btn_signup;
    EditText et_username, et_password;
    Context context;
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        context = SignInActivity.this;
        inItView();
        allClickListener();


//        et_password.requestFocus();
//        et_username.requestFocus();

    }

    private void allClickListener() {
        btn_signup.setOnClickListener(v -> {
            Intent i = new Intent(context, SignUpActivity.class);
            startActivity(i);
        });

        btn_signin.setOnClickListener(v -> {
            Log.e("VAIBHAV", "onClick: ");
            login();
        });
    }


    private void inItView() {
        btn_signin = findViewById(R.id.btn_signin);
        btn_signup = findViewById(R.id.btn_signup);
        et_password = findViewById(R.id.et_password);
        et_username = findViewById(R.id.et_username);

    }

    private void login() {
        if (validation()) {
//            if (username.equals("abc") && password.equals("123")){
//                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context, DashBoardActivity.class);
//                startActivity(intent);
                 /*
             We can check user is present or not directly from database
             *
             */
            MyDbHelper myDbHelper = new MyDbHelper(context);
            boolean isUserAvailable = myDbHelper.isValidUser(username, password);
            if (isUserAvailable) {
                Intent intent1 = new Intent(context, MainActivity.class);
                startActivity(intent1);
                finish();
            } else {
                Toast.makeText(context, "Failed, User not found", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show();
            }

                /*
             We can get all values from database and then we can check user is present or not
             */
//                MyDbHelper myDbHelper = new MyDbHelper(context);
//            ArrayList<StudentDataModel> studentDataModelArrayList = myDbHelper.getAllStudent();
//            for (int i = 0; i < studentDataModelArrayList.size(); i++) {
//                StudentDataModel studentDataModel = studentDataModelArrayList.get(i);
//                if (studentDataModel.getUsername().equals(username) && studentDataModel.getPassword().equals(password)) {
//                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
//                    Intent intent1 = new Intent(context, DashBoardActivity.class);
//                    startActivity(intent1);
//                    finish();
//                    return;
//                } else {
//                    Toast.makeText(context, "Failed, User not found", Toast.LENGTH_SHORT).show();
//                }
//            }
        }
    }
    private boolean validation() {
        username = et_username.getText().toString();
        password = et_password.getText().toString();
        if (username.isEmpty()) {
            Toast.makeText(context, "Please enter username", Toast.LENGTH_SHORT).show();
            et_username.setError("Please enter username");
            et_username.requestFocus();
            return false;
        } else if (password.isEmpty()) {
            Toast.makeText(context, "Please enter password", Toast.LENGTH_SHORT).show();
            et_password.setError("Please enter password");
            et_password.requestFocus();
            return false;
        }
        return true;
    }
}