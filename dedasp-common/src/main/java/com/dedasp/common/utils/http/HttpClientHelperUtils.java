package com.dedasp.common.utils.http;

import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import javax.crypto.Cipher;
import java.lang.reflect.Field;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class HttpClientHelperUtils {
    //应用公钥
    public final static String APPLICATION_RSA_PUBLIC_KEY =
            "-----BEGIN PUBLIC KEY-----" +
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC6jn6dFMAB1cPFvSjUuBdA+ygo" +
            "LGKuqVZcS+uzMy7DVKma7uMV+EP3LacwBx07l2sXGN+jZP8R5mgvrtDYP1IMBlt3" +
            "EEt5pNF4YX3x/1SjDgMCQM8uk154RBwUYb9x2SjtJsD6cQaS3NaXKOk9FhFjRrRp" +
            "LAugRiP9CW3UbVn18QIDAQAB" +
            "-----END PUBLIC KEY-----";

    //应用编码
    public final static String APPLICATION_KEY = "2679de9b-a86f-7a9d-355b-a82b0f15f876";

    /*对象转List<NameValuePair>*/
    public static List<NameValuePair> objectToNameValuePairList(Object obj) throws IllegalAccessException {
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String key = field.getName();
            Object value = field.get(obj);
            nameValuePairList.add(new BasicNameValuePair(key, String.valueOf(value)));
        }
        return nameValuePairList;
    }

    public static String getToken(String data, String publicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(spec);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }
}
