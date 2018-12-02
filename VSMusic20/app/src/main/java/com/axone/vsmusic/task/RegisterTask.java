package com.axone.vsmusic.task;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import com.axone.vsmusic.activity.RegisterActivity;

import com.axone.vsmusic.socket.MyClientSocket;
import com.axone.vsmusic.translation.Type;
import com.axone.vsmusic.transmodel.RegisterModel;

/**
 * Created by 秋水 on 2017/9/9.
 */

public class RegisterTask extends AsyncTask<RegisterModel, Integer, Integer> {

    private RegisterActivity activity;
    private TextView tv_log;

    public RegisterTask(RegisterActivity activity, TextView tv_log) {
        this.activity = activity;
        tv_log = tv_log;
    }

    @Override
    protected Integer doInBackground(RegisterModel... registerModels) {
        MyClientSocket.createClient().register(registerModels[0]);
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch(integer){
            case Type.EXITED_USER:
                //用户名已经存在，要求用户重新填写用户名
                break;
            case Type.SUCCESS:
                //注册成功，服务器端为登陆状态，跳转到主界面
                Intent intent = new Intent();
                //
                activity.startActivity(intent);
                break;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }


}
