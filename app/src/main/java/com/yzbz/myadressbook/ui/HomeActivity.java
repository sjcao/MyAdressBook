package com.yzbz.myadressbook.ui;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.yzbz.myadressbook.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class HomeActivity extends Activity implements HomeFragment.OnFragmentInteractionListener{
    @ViewInject(R.id.rg_home)
    private RadioGroup radioGroup;
    @ViewInject(R.id.bt_home)
    private RadioButton bt_home;
    @ViewInject(R.id.bt_contacts)
    private RadioButton bt_contacts;
    @ViewInject(R.id.bt_me)
    private RadioButton bt_me;
    private Fragment homeFragment;
    private Fragment contactsFragment;
    private Fragment meFragment;
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
        getFragmentManager().beginTransaction().add(R.id.fragment_container,homeFragment).commit();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.bt_home:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,homeFragment).commit();
                        break;
                    case R.id.bt_contacts:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,contactsFragment).commit();
                        break;
                    case R.id.bt_me:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,meFragment).commit();
                        break;
                }
            }
        });
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
