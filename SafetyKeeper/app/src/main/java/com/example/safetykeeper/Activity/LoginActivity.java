package com.example.safetykeeper.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.safetykeeper.R;
import com.example.safetykeeper.Util.SavedSharedPreference;


public class LoginActivity extends AppCompatActivity {

    private EditText EditText_id, EditText_password;
    private CheckBox CheckBox_autoLogin;
    private Button Button_login, Button_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewId();

        Button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(authentication(EditText_id.getText().toString(), EditText_password.getText().toString())){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this, "틀렸어요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            }
        });
    }

    private void findViewId(){
        EditText_id = findViewById(R.id.LoginActivity_EditText_id);
        EditText_password = findViewById(R.id.LoginActivity_EditText_password);
        CheckBox_autoLogin = findViewById(R.id.LoginActivity_CheckBox_autoLogin);
        Button_login = findViewById(R.id.LoginActivity_Button_login);
        Button_register = findViewById(R.id.LoginActivity_Button_register);
    }

    private boolean authentication(String id, String password){
        if(id.equals("wonsik")&&password.equals("wonsik")){
            if(CheckBox_autoLogin.isChecked()) {SavedSharedPreference.setUserName(LoginActivity.this, id); }
            return true;
        }
        else{
            return false;
        }
    }
}
