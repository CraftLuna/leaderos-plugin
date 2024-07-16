package net.leaderos.shared.helpers;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtil {

    public static String randomString(int length) {
        // force make the string lowercase
        return RandomStringUtils.randomAlphanumeric(length).toLowerCase();
    }

}
