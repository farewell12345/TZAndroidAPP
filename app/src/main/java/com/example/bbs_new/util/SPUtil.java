package com.example.bbs_new.util;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtil {
    // 存储 token 数据
    public static void saveToken(String token, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("token",
                MODE_PRIVATE).edit();
        editor.putString("token", token);
        editor.apply();
    }

    // 读取 token 数据
    public static String loadToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("token", MODE_PRIVATE);
        return sharedPreferences.getString("token", "");
    }
}
