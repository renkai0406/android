package com.renkai.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.renkai.R;
import com.renkai.login.LoginActivity;

/**
 * @author renkai
 */
public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        /**
         * 检查基本权限与网络连接
         */
        this.checkStart();

        /**
         * 跳转到介绍/登陆界面
         */
        this.jumpToNext();

    }

    private void checkStart(){

    }

    private void jumpToNext(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Activity startAct = getStartContext();
                Intent jumpToLogin = new Intent(startAct, LoginActivity.class);
                startAct.startActivity(jumpToLogin);
                startAct.finish();
            }
        }, 1);
    }

    private Activity getStartContext(){
        return this;
    }

}
