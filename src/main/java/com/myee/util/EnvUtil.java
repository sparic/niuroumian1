package com.myee.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class EnvUtil {

    public static final BigDecimal ZERO = BigDecimal.ZERO;

    public static Integer getUser() {
        return 100;
    }

    static SimpleDateFormat fullFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String currentTimestamp() {
        return fullFormat.format(new Date());
    }

    public static Timestamp current() {
        return new Timestamp(System.currentTimeMillis());
    }

}
