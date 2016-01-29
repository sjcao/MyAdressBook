package com.yzbz.myadressbook.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yzbz.myadressbook.R;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.ContactsPage;
import cn.smssdk.gui.RegisterPage;

public class LoginActivity extends Activity {
    @ViewInject(R.id.et_phone)
    private EditText et_phone;
    @ViewInject(R.id.et_pwd)
    private EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        x.view().inject(this);

    }
    @Event(value = {R.id.bt_login,R.id.tv_forget,R.id.tv_reg,R.id.tv_fast},type = View.OnClickListener.class)
    private void Click(View view){
        switch (view.getId()){
            //登录按钮
            case R.id.bt_login:
//                //打开通信录好友列表页面
//                Log.d("login_touch","login_touch");
//                ContactsPage contactsPage = new ContactsPage();
//                contactsPage.show(this);

                break;
            //忘记密码
            case R.id.tv_forget:
                Intent intent=new Intent(LoginActivity.this,ForgetActivity.class);
                startActivity(intent);
                break;
            //注册
            case  R.id.tv_reg:
                //打开注册页面
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
                        // 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            @SuppressWarnings("unchecked")
                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");
                            // 提交用户信息
                            registerUser(phone);
                        }
                    }
                });
                registerPage.show(this);

                break;
            //快速登录
            case R.id.tv_fast:
                Intent intent1=new Intent(LoginActivity.this,FastLoginActivity.class);
                startActivity(intent1);
                break;
        }
    }

    private void registerUser(String phone) {
        Intent intent=new Intent(LoginActivity.this,RegActivity.class);
        startActivity(intent);
    }



}
