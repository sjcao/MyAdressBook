package com.yzbz.myadressbook.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;

import com.mob.tools.utils.UIHandler;
import com.yzbz.myadressbook.R;

import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

public class FastLoginActivity extends Activity {

    private static final int MSG_USERID_FOUND = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_login);
        x.view().inject(this);
        ShareSDK.initSDK(this);
        initView();
    }

    @Event({R.id.bt_qqlogin, R.id.bt_sinalogin})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_qqlogin:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.SSOSetting(false);
                qq.setPlatformActionListener(paListener);
                qq.authorize();
                break;
            case R.id.bt_sinalogin:
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                weibo.SSOSetting(true);
                weibo.setPlatformActionListener(paListener);
                weibo.authorize();
                //移除授权
                //weibo.removeAccount(true);
                break;

        }

    }

    private PlatformActionListener paListener = new PlatformActionListener() {
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            if (platform == null) {
                return;
            }
//判断指定平台是否已经完成授权
            if (platform.isAuthValid()) {
                String userId = platform.getDb().getUserId();
                if (userId != null) {
                    UIHandler.sendEmptyMessage(MSG_USERID_FOUND, new Handler.Callback() {
                        @Override
                        public boolean handleMessage(Message msg) {
                            return false;
                        }
                    });
                    login(platform.getName(), userId, null);
                    return;
                }
            }
        }


        @Override
        public void onError(Platform platform, int i, Throwable throwable) {

        }

        @Override
        public void onCancel(Platform platform, int i) {

        }
    };

    private void login(String name, String userId, Object o) {
        //在这里进行数据的验证

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
