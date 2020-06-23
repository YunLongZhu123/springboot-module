package com.springboot.demo.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordUtils {

    private static final String algorithmName = "md5";

    private static final int COUNT = 2;

    public static String getPassword(String password) {

        String hex = getSalt();
        SimpleHash simpleHash = new SimpleHash(algorithmName, password, ByteSource.Util.bytes(hex), COUNT);
        simpleHash.toHex();
        return simpleHash.toHex();
    }

    public static String getSalt() {
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        return salt;
    }

    public static void main(String[] args) {
        String str = getSalt();
        System.out.println(str);

    }
}
