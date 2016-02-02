package com.yzbz.myadressbook.ui;

import android.app.Activity;
import android.os.Bundle;

import com.yzbz.myadressbook.R;

import org.xutils.x;

public class GroupInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);
        x.view().inject(this);
        initView();
    }

    private void initView() {

    }

}
