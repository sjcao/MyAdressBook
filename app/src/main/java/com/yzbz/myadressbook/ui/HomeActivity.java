package com.yzbz.myadressbook.ui;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yzbz.myadressbook.R;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class HomeActivity extends Activity implements HomeFragment.OnFragmentInteractionListener{
    @ViewInject(R.id.rg_home)
    private RadioGroup radioGroup;
    @ViewInject(R.id.bt_switch)
    private LinearLayout bt_switch;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.bt_personal)
    private Button bt_personal;
    @ViewInject(R.id.bt_group)
    private Button bt_group;

    private Fragment homeFragment;
    private Fragment contactsFragment;
    private Fragment meFragment;
    private Fragment groupFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        x.view().inject(this);
        homeFragment=new HomeFragment();
        contactsFragment=new ContactsFragment();
        meFragment=new MeFragment();
        groupFragment=new GroupFragment();
        getFragmentManager().beginTransaction().add(R.id.fragment_container,homeFragment).commit();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.bt_home:
                        tv_title.setVisibility(View.VISIBLE);
                        bt_switch.setVisibility(View.GONE);
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
                        break;
                    case R.id.bt_contacts:
                        tv_title.setVisibility(View.GONE);
                        bt_switch.setVisibility(View.VISIBLE);
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, contactsFragment).commit();
                        break;
                    case R.id.bt_me:
                        tv_title.setVisibility(View.GONE);
                        bt_switch.setVisibility(View.GONE);
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, meFragment).commit();
                        break;
                }
            }
        });
    }
    @Event({R.id.bt_personal,R.id.bt_group})
    private void onClick(View view){
        switch (view.getId()){
            case R.id.bt_personal:
                bt_personal.setBackgroundResource(R.mipmap.baike_btn_pink_left_f_96);
                bt_group.setBackgroundResource(R.mipmap.baike_btn_trans_right_f_96);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,contactsFragment).commit();
                break;

            case R.id.bt_group:
                bt_personal.setBackgroundResource(R.mipmap.baike_btn_trans_left_f_96);
                bt_group.setBackgroundResource(R.mipmap.baike_btn_pink_right_f_96);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,groupFragment).commit();
                break;
        }
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
