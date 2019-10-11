package com.sky.dongqiudi.dongqiudi.utils;

import android.content.SharedPreferences;

import com.sky.dongqiudi.dongqiudi.base.BaseApp;

/**
 * 保存搜索记录
 *
 * @param keyword
 */
public class SharePreferenceUtils extends BaseApp{

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static void save(String keyword) {
        // 获取搜索框信息
        SharedPreferences mysp = BaseApp.getAppContext().getSharedPreferences("search_history", 0);
        String old_text = mysp.getString("history", "");
        // 利用StringBuilder.append新增内容，逗号便于读取内容时用逗号拆分开
        StringBuilder builder = new StringBuilder(old_text);
        builder.append(keyword + ",");

        // 判断搜索内容是否已经存在于历史文件，已存在则不重复添加
        if (!old_text.contains(keyword + ",")) {
            SharedPreferences.Editor myeditor = mysp.edit();
            myeditor.putString("history", builder.toString());
            myeditor.commit();
        }
    }

    public static String[] getHistoryList() {
        // 获取搜索记录文件内容
        SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences("search_history", 0);
        String history = sp.getString("history", "");
        // 用逗号分割内容返回数组
        String[] history_arr = history.split(",");
        // 保留前50条数据
        if (history_arr.length > 50) {
            String[] newArrays = new String[50];
            System.arraycopy(history_arr, 0, newArrays, 0, 50);
        }
        return history_arr;
    }

    /**
     * 清除搜索记录
     */
    public static void cleanHistory() {
        SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences("search_history", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }
}
