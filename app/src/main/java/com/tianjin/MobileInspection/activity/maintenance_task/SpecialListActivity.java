package com.tianjin.MobileInspection.activity.maintenance_task;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tianjin.MobileInspection.R;
import com.tianjin.MobileInspection.fragment.SpecialHasFinishedFragment;
import com.tianjin.MobileInspection.fragment.SpecialNotFinishedFragment;
import com.tianjin.MobileInspection.main.BaseActivity;

/**
 * 专项列表
 * Created by wuchang on 2016-11-21.
 */
public class SpecialListActivity extends BaseActivity{

    private String TAG="InspectionListActivity";
    private TextView mtvTitle;
    private LinearLayout mlinearBack;
    private LinearLayout mlinearAdd;

    private FragmentManager fragmentManager;
    private SpecialNotFinishedFragment doingFragment;
    private SpecialHasFinishedFragment finishedFragment;
    private RadioGroup mrgGroup;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);
        initView();
        initData();
    }

    private void initData() {
        fragmentManager=getSupportFragmentManager();
        mlinearBack.setOnClickListener(this);
        mlinearAdd.setOnClickListener(this);
//        mrgGroup.setVisibility(View.GONE);
        mrgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_spectial_doing:
                        if (doingFragment==null) {
                            doingFragment = new SpecialNotFinishedFragment();
                            doingFragment.setContext(SpecialListActivity.this);
                        }
                        changeFragment(doingFragment);
                        break;
                    case R.id.rb_spectial_finished:
                        if(finishedFragment==null){
                            finishedFragment=new SpecialHasFinishedFragment();
                            finishedFragment.setContext(SpecialListActivity.this);
                        }
                        changeFragment(finishedFragment);
                        break;
                }
            }
        });

        mrgGroup.check(R.id.rb_spectial_doing);
    }

    private void initView() {
        mtvTitle=(TextView) findViewById(R.id.tv_activity_title);
        mlinearBack=(LinearLayout)findViewById(R.id.linear_return_back);
        mlinearAdd=(LinearLayout)findViewById(R.id.linear_add);
        mrgGroup=(RadioGroup)findViewById(R.id.rg_type_bottom);

        mtvTitle.setText("计划列表");
//        mlinearAdd.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_return_back:
                this.finish();
                break;
            case R.id.linear_add:
                Intent in=new Intent("android.intent.action.NEWSPECIALTASKACTIVITY");
                startActivity(in);
                break;
        }
    }

    private void changeFragment(Fragment fragment){
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_inspection_list,fragment);
        transaction.commit();
    }

}
