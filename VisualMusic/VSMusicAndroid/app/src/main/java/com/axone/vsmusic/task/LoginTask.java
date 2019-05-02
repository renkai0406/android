package com.axone.vsmusic.task;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import com.axone.vsmusic.activity.LoginActivity;
import com.axone.vsmusic.socket.MyClientSocket;
import com.axone.vsmusic.translation.Type;
import com.axone.vsmusic.util.Config;

/**
 * Created by 秋水 on 2017/9/9.
 */

public class LoginTask extends AsyncTask<String, Integer, Integer> {

    private TextView tv_log;
    private LoginActivity loginActivity;
    public LoginTask(LoginActivity activity, TextView tv_log){
    this.tv_log = tv_log;
        loginActivity = activity;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        System.out.println("开始执行登录任务");
        int result = MyClientSocket.createClient().login(strings[0], strings[1]);
        return new Integer(result);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer){
            case Type.EMPTY_USER:
                tv_log.setText(Config.EMPTY_USER_MESSAGE);
                break;
            case Type.WRONG_PASSWORD:
                tv_log.setText(Config.WRONG_PASSWORD_MESSAGE);
                break;
            case Type.SUCCESS:
                //登陆成功，跳转到下一个界面
                tv_log.setText("登陆成功");
                Intent intent = new Intent();
                //intent.setClass(loginActivity, SlideActivity.class);
                //loginActivity.startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }


}
