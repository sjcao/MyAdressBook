package com.yzbz.myadressbook.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.yzbz.myadressbook.R;

import org.xutils.view.annotation.Event;
import org.xutils.x;

public class PersonInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        x.view().inject(this);
    }
    @Event({R.id.bt_back})
    private void onClick(View view){
        this.finish();
    }

}
