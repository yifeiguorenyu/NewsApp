package com.bluetooth.imooc_music.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataUtils {
    private static final String TAG = "DataUtils-1";
    /**
     * 读取资源文件中的数据
     * @return
     */
    
    public static String getJsonFromAssets(Context context,String fileName){
        /**
         * StringBuilder 存放读取出的数据
         * AssetManager资源管理器 Open方法 返回InputStream
         * InputStreamReader 字节到字符的桥接器  bufferReader 存放读取字符的缓冲区
         * 循环利用BufferedReader 的readerLine
         * 返回读取出来的所有数据
         */
        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;

            while ((line=bufferedReader.readLine())!=null){
                stringBuilder.append(line);

            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return stringBuilder.toString();
    }
}
