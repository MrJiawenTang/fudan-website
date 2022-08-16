package com.cloud.cqc.util;

import com.cloud.cqc.autoconfig.cache.CacheTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class AESUtil {

    protected static final Logger logger = LoggerFactory.getLogger(CacheTools.class);


    public final static String ENCODE_RULE = "";

    public final static String SECRET_KEY = "7zmP5QVIBIKhrZju";

    /**
     * 加密算法
     *
     * @param secretKey
     * @param content
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String AESEncode(String secretKey, String content) {
        String key_content_base64 = null;
        try {
//            //1.构造秘钥生成器，指定秘钥算法为AES算法，这里不区分大小写
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//            //2.根据encodeRules初始化秘钥生成器，生成一个128位随机源
//            //这里分为多种的方式生成秘钥生成器，这里我使用的是：使用用户提供的随机源初始化此密钥生成器，使其具有确定的密钥大小。
//            keyGenerator.init(128, new SecureRandom(encodeRules.getBytes()));
//            //3.生成原始对称秘钥，这是一个接口类，
//            SecretKey secretKey = keyGenerator.generateKey();
//            //4.获得原始对称秘钥的字节数组
//            byte[] secretKeyByte = secretKey.getEncoded();

            //使用16长度字符串生成秘钥字节数组
            byte[] secretKeyByte = secretKey.getBytes();
            //5.根据字节数组生成AES秘钥
            SecretKey key = new SecretKeySpec(secretKeyByte, "AES");
            //6.根据指定算法AES制成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的key
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] contentByte = content.getBytes("UTF-8");
            //9.使用密码器进行加密操作
            byte[] byte_AES = cipher.doFinal(contentByte);
            //10.将加密后的字符数组转成Base64的字符格式
            key_content_base64 = Base64.getEncoder().encodeToString(byte_AES);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        } catch (InvalidKeyException e) {
            logger.error(e.getMessage());
        } catch (NoSuchPaddingException e) {
            logger.error(e.getMessage());
        } catch (BadPaddingException e) {
            logger.error(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        } catch (IllegalBlockSizeException e) {
            logger.error(e.getMessage());
        }
        return key_content_base64;
    }

    /**
     * 解密算法
     *
     * @param secretKey
     * @param content
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws UnsupportedEncodingException
     */
    public static String AESDecode(String secretKey, String content) {
        String str = null;
        try {
//            //构造秘钥生成器
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//            //初始化秘钥生成器
//            keyGenerator.init(128, new SecureRandom(encodeRules.getBytes()));
//            //生成原始对称秘钥
//            SecretKey secretKey = keyGenerator.generateKey();
//            //将原始对称秘钥转成byte数组
//            byte[] secretKeyByte = secretKey.getEncoded();

            //使用16长度字符串生成秘钥字节数组
            byte[] secretKeyByte = secretKey.getBytes();
            //根据指定算法AES和数组生成AES秘钥
            SecretKey key = new SecretKeySpec(secretKeyByte, "AES");
            //指定AES算法生成密码器      Cipher
            Cipher cipher = Cipher.getInstance("AES");
            //初始化密码器
            cipher.init(Cipher.DECRYPT_MODE, key);
            //获取Base64的字符串，进行Base64反编译
            byte[] base_Decrypt = Base64.getDecoder().decode(content);
            //使用密码器进行解密
            byte[] decrypt_byte = cipher.doFinal(base_Decrypt);
            //把数组转成string字符串
            str = new String(decrypt_byte, "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        } catch (InvalidKeyException e) {
            logger.error(e.getMessage());
        } catch (NoSuchPaddingException e) {
            logger.error(e.getMessage());
        } catch (BadPaddingException e) {
            logger.error(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        } catch (IllegalBlockSizeException e) {
            logger.error(e.getMessage());
        }
        return str;
    }

    public static String AESEncode(String content) {
        return AESEncode(SECRET_KEY, content);
    }

    public static String AESDecode(String content) {
        String result;
        if (content.length() % 4 != 0) {
            return content;
        }
        try {
            result = AESDecode(SECRET_KEY, content);
        } catch (Exception e) {
            return content;
        }
        if (StringUtils.isEmpty(result)) {
            return content;
        }
        return result;
    }

}
