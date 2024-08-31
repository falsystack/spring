package jp.co.falsystack.ssiach11prac.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class GenerateCodeUtil {

    private GenerateCodeUtil() {
    }

    public static String generateCode() {
        String code;

        try {
            var random = SecureRandom.getInstanceStrong();
            code = String.valueOf(random.nextInt(9000) + 1000);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Problem when generating the random code", e);
        }

        return code;
    }
}
