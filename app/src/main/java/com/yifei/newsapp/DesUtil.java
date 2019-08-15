package com.yifei.newsapp;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class DesUtil {
    private static final String TAG = "MainActivity-1";
    //private final static String TRANSFORMATION = "DES/CBC/PKCS5Padding";//DES是加密方式 CBC是工作模式
    private final static String TRANSFORMATION = "AES/ECB/ZeroBytePadding";//DES是加密方式 CBC是工作模式

    // PKCS5Padding是填充模式
    //private final static String IVPARAMETERSPEC = "01020304";////初始化向量参数，AES 为16bytes. DES
    // 为8bytes.
    private final static String IVPARAMETERSPEC = "00000000";////初始化向量参数，AES 为16bytes. DES 为8bytes.

    private final static String ALGORITHM = "AES";//DES是加密方式

    /**
     * DES算法，加密
     *
     * @param data 待加密字符串
     * @param key  加密私钥，长度不能够小于8位
     * @return 加密后的字节数组，一般结合Base64编码使用
     */
    public static String encode(String key, String data) {
        return encode(key, data.getBytes());
    }

    public static String encrypt1(String key, String data) {
// 将传过来的key和data转换为byte型

        byte[] bKey = new byte[0];
        try {
            bKey = key.getBytes("gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        byte[] bData = new byte[0];
        try {
            bData = data.getBytes("gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String ds = "";
// 创建一个DESKeySpec对象
        SecretKeySpec spec = new SecretKeySpec(bKey, "DES");
// Cipher对象实际完成加密操作
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/ZeroBytePadding");
// 实例化cipher
            cipher.init(Cipher.ENCRYPT_MODE, spec);
// 真正的开始加密
            byte[] cData = cipher.doFinal(bData);
// 将加密后的数据，转化为16进制
            //yte[] bytes = cipher.doFinal(cData);
            ds = Base64.encodeToString(cData, Base64.NO_WRAP);
            //ds = new String(cData);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return ds;
    }

    /**
     * DES算法，加密
     *
     * @param data 待加密字符串
     * @param key  加密私钥，长度不能够小于8位
     * @return 加密后的字节数组，一般结合Base64编码使用
     */
    public static String encode(String key, byte[] data) {
        try {

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);

            //IvParameterSpec iv = new IvParameterSpec(IVPARAMETERSPEC.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, getRawKey(key));
            byte[] bytes = cipher.doFinal(data);
            return Base64.encodeToString(bytes, Base64.NO_WRAP);
        } catch (Exception e) {
            Log.d(TAG, "encode: " + e.getMessage());
            return null;
        }
    }

    /**
     * 获取编码后的值
     *
     * @param key
     * @param data
     * @return
     */
    public static String decode(String key, String data) {
        return decode(key, Base64.decode(data, Base64.NO_WRAP));
    }

    /**
     * DES算法，解密
     *
     * @param data 待解密字符串
     * @param key  解密私钥，长度不能够小于8位
     * @return 解密后的字节数组
     */
    public static String decode(String key, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            //IvParameterSpec iv = new IvParameterSpec(IVPARAMETERSPEC.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, getRawKey(key));
            byte[] original = cipher.doFinal(data);
            String originalString = new String(original);
            return originalString;
        } catch (Exception e) {
            return null;
        }
    }


//    private static Key getRawKey(String key) throws Exception {
//        KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
//        //for android
//        SecureRandom sr = null;
//        // 在4.2以上版本中，SecureRandom获取方式发生了改变
//        if (android.os.Build.VERSION.SDK_INT >= 17) {
//            sr = SecureRandom.getInstance(SHA1PRNG, "Crypto");
//        } else {
//            sr = SecureRandom.getInstance(SHA1PRNG);
//        }
//        // for Java
//        // secureRandom = SecureRandom.getInstance(SHA1PRNG);
//        sr.setSeed(key.getBytes());
//        kgen.init(64, sr); //DES固定格式为64bits，即8bytes。
//        SecretKey skey = kgen.generateKey();
//        byte[] raw = skey.getEncoded();
//        return new SecretKeySpec(raw, ALGORITHM);
//    }

    // 对密钥进行处理
    private static Key getRawKey(String key) throws Exception {
        DESKeySpec dks = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return keyFactory.generateSecret(dks);
    }
}



