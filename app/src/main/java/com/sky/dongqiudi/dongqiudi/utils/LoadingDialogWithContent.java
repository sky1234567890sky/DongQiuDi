package com.sky.dongqiudi.dongqiudi.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class LoadingDialogWithContent extends Dialog {

    ProgressImageView mProgressView;

    TextView mTvContent;

    String mContent;

    public LoadingDialogWithContent(Context context, String content) {
        super(context, R.style.DialogStyle);
        mContent=content;
    }
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress_content);
        mProgressView = findViewById(R.id.view_list_empty_progress);
        mTvContent = findViewById(R.id.tv_content);
//        FontUtil.replaceFont(mTvContent,"fonts/RobotoCondensed-Regular.ttf");
        mTvContent.setText(mContent);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mProgressView != null)
            mProgressView.setVisibility(View.VISIBLE);
    }
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mProgressView != null)
            mProgressView.setVisibility(View.GONE);
    }

}
