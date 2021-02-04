package com.ujiuye.crmpro.common.utils;

import java.util.UUID;

public class UUIDUtil {
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-","");
    }
}
