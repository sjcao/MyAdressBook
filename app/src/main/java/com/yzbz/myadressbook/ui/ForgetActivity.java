package com.yzbz.myadressbook.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yzbz.myadressbook.R;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class ForgetActivity extends Activity {
    @ViewInject(R.id.et_phone)
    private EditText et_phone;
    @ViewInject(R.id.et_newpwd)
    private EditText et_newpwd;
    @ViewInject(R.id.et_comfirpwd)
    private EditText et_comfirpwd;
    @ViewInject(R.id.bt_send)
    private Button bt_send;
    @ViewInject(R.id.et_code)
    private EditText et_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        x.view().inject(this);
        initView();
        SMSSDK.registerEventHandler(new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        handler.sendEmptyMessage(1);
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        handler.sendEmptyMessage(2);
                        //获取验证码成功
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                    handler.sendEmptyMessage(3);
                }
            }
        });
    }

    @Event({R.id.bt_commit,R.id.bt_send})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_send:
                String phone = et_phone.getText().toString();
                SMSSDK.getSupportedCountries();
                SMSSDK.getVerificationCode("86", phone);
                break;
            case R.id.bt_commit:
                SMSSDK.submitVerificationCode("86",et_phone.getText().toString(),et_code.getText().toString());
                //下面提交数据库的操作
                break;
        }
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Toast.makeText(ForgetActivity.this, "验证码验证成功", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    bt_send.setClickable(false);
                    new CountDownTimer(60 * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            bt_send.setText(String.valueOf(millisUntilFinished / 1000));
                        }

                        @Override
                        public void onFinish() {
                            bt_send.setText(R.string.txt_seed);
                            bt_send.setClickable(true);
                        }
                    }.start();
                    Toast.makeText(ForgetActivity.this, "验证码已经发送", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(ForgetActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
    private void initView() {
        ActionBar actionBar=getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle("返回");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
