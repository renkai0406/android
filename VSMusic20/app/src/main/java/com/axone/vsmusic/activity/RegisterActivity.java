package com.axone.vsmusic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.axone.vsmusic.R;

import com.axone.vsmusic.task.RegisterTask;
import com.axone.vsmusic.translation.Type;
import com.axone.vsmusic.transmodel.RegisterModel;

import java.util.Date;


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final RegisterTask registerTask = new RegisterTask(this, null);
        final EditText et_username = (EditText) findViewById(R.id.reg_username);
        final EditText et_password = (EditText) findViewById(R.id.reg_password);
        EditText et_rePassword = (EditText) findViewById(R.id.reg_rePassword);
        final RadioGroup rg_sex = (RadioGroup) findViewById(R.id.reg_sex);
        final EditText et_address = (EditText) findViewById(R.id.reg_address);
        final EditText et_birth = (EditText) findViewById(R.id.reg_birthday);
        final RadioGroup rg_blood = (RadioGroup) findViewById(R.id.reg_blood);
        Button bt_register = (Button) findViewById(R.id.reg_register);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sex = 0;
                switch(rg_sex.getCheckedRadioButtonId()){
                    case R.id.reg_sex_man:
                        sex = Type.SEX_MAN;
                        break;
                    case R.id.reg_sex_woman:
                        sex = Type.SEX_WOMAN;
                        break;
                    case R.id.reg_sex_secrecy:
                        sex = Type.SEX_SECRECY;
                        break;
                    default:
                        break;
                }
                int blood = 0;
                switch (rg_blood.getCheckedRadioButtonId()){
                    case R.id.reg_blood_a:
                        blood = Type.BLOOD_A;
                        break;
                    case R.id.reg_blood_b:
                        blood = Type.BLOOD_B;
                        break;
                    case R.id.reg_blood_ab:
                        blood = Type.BLOOD_AB;
                        break;
                    case R.id.reg_blood_o:
                        blood = Type.BLOOD_O;
                        break;
                    case R.id.reg_blood_other:
                        blood = Type.BLOOD_OTHER;
                        break;
                    default:
                        break;
                }

                if(checkValues(et_username.getText() + "", sex, et_address.getText() + "", et_birth.getText() + "", blood)){
                    RegisterModel reg_model = new RegisterModel(et_username.getText() + "", et_password.getText() + "", sex, new Date(et_birth.getText() + ""), null, et_address.getText() + "", blood);
                    registerTask.execute(reg_model);
                }

            }
        });


    }

    private boolean checkValues(String username, int sex, String address, String birthday, int blood){
        return true;
    }
}
