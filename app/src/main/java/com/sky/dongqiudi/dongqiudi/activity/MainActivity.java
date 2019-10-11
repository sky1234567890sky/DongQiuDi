package com.sky.dongqiudi.dongqiudi.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sky.dongqiudi.dongqiudi.R;
import com.sky.dongqiudi.dongqiudi.base.BaseActivity;
import com.sky.dongqiudi.dongqiudi.custom.ZFlowLayout;
import com.sky.dongqiudi.dongqiudi.utils.SharePreferenceUtils;

import butterknife.OnClick;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    public static String[] searchWord = {"净水器", "手机", "电动车", "洗衣机", "沙发", "冰箱", "瓷砖", "空调", "床垫", "卫浴", "热水器", "床", "家具", "手表", "电视", "集成灶", "领带", "保温杯", "童装", "自行车", "空气净化器", "地板", "硅藻泥", "油烟机", "智能家居"};

    private ImageView mBackIv;
    private AutoCompleteTextView mAutoSearch;
    private TextView mTvSearch;
    private ZFlowLayout mKeywordFl;
    private ImageView mClearIv;
    private ZFlowLayout mHistoryFl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    protected void initView() {

        mBackIv = findViewById(R.id.back_iv);
        mAutoSearch = findViewById(R.id.auto_search);
        mTvSearch = findViewById(R.id.tv_search);
        mKeywordFl = findViewById(R.id.keyword_fl);
        mClearIv = findViewById(R.id.clear_iv);
        mHistoryFl = findViewById(R.id.history_fl);

        initKeyWord(searchWord);
        initHistory();
        String[] data = SharePreferenceUtils.getHistoryList();
        ArrayAdapter<String> autoComplteAdapter = new ArrayAdapter<String>(this, R.layout.view_mw_textview, data);
        mAutoSearch.setAdapter(autoComplteAdapter);

        mAutoSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    String s = charSequence.toString();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        initListener();
    }
    private void initListener() {
        mBackIv.setOnClickListener(this);
        mClearIv.setOnClickListener(this);
    }
    private void initHistory() {
        final String[] data = SharePreferenceUtils.getHistoryList();
        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 10);
        mHistoryFl.removeAllViews();
        for (int i = 0; i < data.length; i++) {
            if (data[i].isEmpty()) {
                return;
            }
            final int j = i;
            //添加分类块
            View paramItemView = getLayoutInflater().inflate(R.layout.adapter_search_keyword, null);
            TextView keyWordTv = paramItemView.findViewById(R.id.tv_content);
            keyWordTv.setText(data[j]);
            keyWordTv.setBackgroundResource(R.drawable.whitebg_radius3);
            mHistoryFl.addView(paramItemView, layoutParams);
            keyWordTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mAutoSearch.setText(data[j]);
                }
            });
        }
    }
    private void initKeyWord(final String[] keyword) {
        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 10);
        mKeywordFl.removeAllViews();
        for (int i = 0; i < keyword.length; i++) {
            final int j = i;
            //添加分类块
            View paramItemView = getLayoutInflater().inflate(R.layout.adapter_search_keyword, null);
            TextView keyWordTv = paramItemView.findViewById(R.id.tv_content);
            keyWordTv.setText(keyword[j]);
            keyWordTv.setBackgroundResource(R.drawable.whitebg_radius3);
            mKeywordFl.addView(paramItemView, layoutParams);

            keyWordTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAutoSearch.setText(keyword[j]);
                }
            });
        }
    }
    private boolean isNullorEmpty(String str) {
        return str == null || "".equals(str);
    }

    private void showToastShort(Context context, String data) {
        Toast toast = Toast.makeText(context, data, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                initHistory();
            }
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                String searchKey = mAutoSearch.getText().toString();
                if (!searchKey.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, SearchResultActivity.class);
                    intent.putExtra("key", searchKey);
                    startActivityForResult(intent, 0);
                    String keyWord = mAutoSearch.getText().toString();
                    if (!keyWord.isEmpty()) {
                        SharePreferenceUtils.save(mAutoSearch.getText().toString());
                    }
                } else {
                    showToast("搜索内容为空！");
                }
                break;
            case R.id.clear_iv:
                SharePreferenceUtils.cleanHistory();
                showToastShort(this, "已清除历史记录！");
                initHistory();
                break;
            default:
                break;
        }
    }
}
