package com.aboutTime.common;

public class StringUtils {
    private final static String MAIL_AT = "@";

    public static String extractIdFromMail(String mailAddress) {
        return mailAddress.substring(0, mailAddress.indexOf(MAIL_AT));
    }

}
