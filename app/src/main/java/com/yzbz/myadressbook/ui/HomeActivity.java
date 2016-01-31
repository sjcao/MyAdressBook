package com.yzbz.myadressbook.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;
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
    @ViewInject(R.id.bt_right_menu)
    private Button bt_right_menu;

    private Fragment homeFragment;
    private Fragment contactsFragment;
    private Fragment meFragment;
    private Fragment groupFragment;

    private ResideMenu resideMenu;
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
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                        break;
                    case R.id.bt_contacts:
                        tv_title.setVisibility(View.GONE);
                        bt_switch.setVisibility(View.VISIBLE);
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, contactsFragment).setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                        break;
                    case R.id.bt_me:
                        tv_title.setVisibility(View.GONE);
                        bt_switch.setVisibility(View.GONE);
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, meFragment).setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                        break;
                }
            }
        });

        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.mipmap.bg1);
        resideMenu.attachToActivity(this);
        //resideMenu.openMenu(ResideMenu.DIRECTION_LEFT); // or ResideMenu.DIRECTION_RIGHT
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        //resideMenu.closeMenu();

        // create menu items;
        String titles[] = { "Home", "Profile", "Calendar", "Settings" };
        int icon[] = { R.drawable.ssdk_oks_logo_qq, R.drawable.ssdk_oks_logo_sinaweibo, R.drawable.ssdk_oks_logo_wechat, R.drawable.ssdk_oks_logo_wechatmoments };

        for (int i = 0; i < titles.length; i++){
            ResideMenuItem item = new ResideMenuItem(this, icon[i], titles[i]);
            //item.setOnClickListener(this);
            resideMenu.addMenuItem(item,  ResideMenu.DIRECTION_LEFT); // or  ResideMenu.DIRECTION_RIGHT
        }

    }
    @Event({R.id.bt_personal,R.id.bt_group,R.id.bt_right_menu})
    private void onClick(View view){
        switch (view.getId()){
            case R.id.bt_personal:
                bt_personal.setBackgroundResource(R.mipmap.baike_btn_pink_left_f_96);
                bt_group.setBackgroundResource(R.mipmap.baike_btn_trans_right_f_96);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,contactsFragment).setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                break;

            case R.id.bt_group:
                bt_personal.setBackgroundResource(R.mipmap.baike_btn_trans_left_f_96);
                bt_group.setBackgroundResource(R.mipmap.baike_btn_pink_right_f_96);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,groupFragment).setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                break;
            case R.id.bt_right_menu:
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
                break;
        }
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    //侧滑菜单
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }
}
