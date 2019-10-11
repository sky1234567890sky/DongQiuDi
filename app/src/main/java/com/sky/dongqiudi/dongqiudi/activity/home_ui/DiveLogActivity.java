package com.sky.dongqiudi.dongqiudi.activity.home_ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sky.dongqiudi.dongqiudi.R;
import com.sky.dongqiudi.dongqiudi.activity.home_ui.fragment.DiveFreedomFragment;
import com.sky.dongqiudi.dongqiudi.activity.home_ui.fragment.ScubaDiveFragment;
import com.sky.dongqiudi.dongqiudi.adapter.DiveLogAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//潜水日志
public class DiveLogActivity extends AppCompatActivity {
    @BindView(R.id.dive_log_back)
    ImageView mDiveLogBack;
    @BindView(R.id.dive_log_tab)
    TabLayout mDiveLogTab;
    @BindView(R.id.tab_add_o2)
    TabLayout tab_add_o2;
    //    findViewById(R.id.qiping_xiaohao)
    @BindView(R.id.qiping_xiaohao)
    TextView qiping_xiaohao;

    @BindView(R.id.type_add3)
    TextView type_add;

    @BindView(R.id.qiping_xiaohao_ll)
    LinearLayout qiping_xiaohao_ll;

    @BindView(R.id.mean_rl)
    RelativeLayout mean_rl;

    @BindView(R.id.mean_rl_et)
    RelativeLayout mean_rl_et;

    //    @BindView(R.id.dive_log_vp)
//    ViewPager mVp;
    private ArrayList<Fragment> fs;
    private DiveLogAdapter diveLogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dive_log);
        ButterKnife.bind(this);
        initFragment();
        initView();
    }


    private void initFragment() {
//        fs = new ArrayList<>();
//        DiveFreedomFragment diveFreedomFragment = new DiveFreedomFragment();
//        ScubaDiveFragment scubaDiveFragment = new ScubaDiveFragment();
//        fs.add(diveFreedomFragment);
//        fs.add(scubaDiveFragment);
/**
 * tablayout切换时只有气瓶显示隐藏效果，在一个界面上
 *
 */
        mDiveLogTab.addTab(mDiveLogTab.newTab().setText("自由潜水"));
        mDiveLogTab.addTab(mDiveLogTab.newTab().setText("水肺潜水"));
/**
 * tablayout切换时
 */
        tab_add_o2.addTab(tab_add_o2.newTab().setText("空气"));
        tab_add_o2.addTab(tab_add_o2.newTab().setText("高氧"));
        tab_add_o2.addTab(tab_add_o2.newTab().setText("氦氧氮混合"));
    }
    private void initView() {
//        diveLogAdapter = new DiveLogAdapter(getSupportFragmentManager(),fs);
//        mVp.setAdapter(diveLogAdapter);
//        mDiveLogTab.setupWithViewPager(mVp);
        mDiveLogTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                mVp.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        qiping_xiaohao.setVisibility(View.VISIBLE);
                        qiping_xiaohao_ll.setVisibility(View.VISIBLE);
                        mean_rl.setVisibility(View.VISIBLE);
                        mean_rl_et.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        //复用其他的  隐藏气瓶
                        qiping_xiaohao.setVisibility(View.GONE);
                        qiping_xiaohao_ll.setVisibility(View.GONE);
                        mean_rl.setVisibility(View.GONE);
                        mean_rl_et.setVisibility(View.GONE);
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mDiveLogTab));

        tab_add_o2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        //复用相同的 添加
                        //空气添加
                        type_add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(DiveLogActivity.this, "空气添加", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case 1:
                        //高氧
                        type_add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(DiveLogActivity.this, "高氧", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case 2:
                        //氦氧氮混合
                        type_add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(DiveLogActivity.this, "氦氧氮混合", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
//    @OnClick({R.id.dive_log_back})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.dive_log_back:
//                finish();
//                break;
//        }
//    }
}
