package com.humid.router.util;

import com.humid.router.exceptions.CheckException;

import java.util.stream.Stream;

/**
 * @author qiujianping
 * @date Created in 2021/8/16 11:09
 */
public class CheckUtil {

    private static final String[] INVAILD_NAMES = {"admin", "gly"};
    /**
     * 校验名称是否正常
     * @param data
     * @return
     */
    public static String checkName(String data) {
        Stream.of(INVAILD_NAMES)
                .filter(name -> name.equalsIgnoreCase(data))
                .findAny()
                .ifPresent(name -> {
                    throw new CheckException("name", data);
                });
        return null;
    }
}
