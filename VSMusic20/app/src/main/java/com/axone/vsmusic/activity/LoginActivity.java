package com.axone.vsmusic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.axone.vsmusic.R;
import com.axone.vsmusic.task.LoginTask;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText et_username = (EditText) findViewById(R.id.login_et_username);
        final EditText et_password = (EditText) findViewById(R.id.login_et_password);
        final LoginTask loginTask = new LoginTask(LoginActivity.this, (TextView)findViewById(R.id.login_tv_log));
        Button bt_login = (Button) findViewById(R.id.login_bt_login);
        Button bt_register = (Button) findViewById(R.id.login_bt_register);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginTask.execute(et_username.getText() + "", et_password.getText() + "");
            }
        });

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(intent);
                finish();
            }
        });
    }
}
