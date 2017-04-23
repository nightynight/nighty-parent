package com.brokepal.nighty.security.util;

import com.brokepal.nighty.core.cache.Cache;
import com.brokepal.nighty.core.util.MD5Util;
import com.brokepal.nighty.security.constant.SecurityConstant;
import com.brokepal.nighty.security.storage_object.LoginInfo;
import com.brokepal.nighty.security.storage_object.Session;

import java.util.Date;

/**
 * Created by chenchao on 17/3/29.
 */
public final class SecurityUtil {
    public static String generateToken(String username, String password){
        return MD5Util.string2MD5(username + " " + password);
    }
}
