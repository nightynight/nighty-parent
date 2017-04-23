package com.brokepal.nighty.security.service;

import com.brokepal.nighty.core.cache.Cache;
import com.brokepal.nighty.core.util.MD5Util;
import com.brokepal.nighty.security.constant.SecurityConstant;
import com.brokepal.nighty.security.storage_object.LoginInfo;
import com.brokepal.nighty.security.storage_object.Session;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by chenchao on 17/4/16.
 */
@Service
public class SecurityService {
    public void putPrivateKey(String sessionId, String privateKey){
        Cache.put(sessionId, privateKey);
    }

    public String getPrivateKey(String sessionId){
        return Cache.get(sessionId);
    }

    public Session getCurrentSession(String username){
        Session session = null;
        LoginInfo info = Cache.get(username);
        do {
            if (info == null)
                break;
            session = info.getCurrentSession();
        } while (false);
        return session;
    }


    public boolean isLocked(String username){
        LoginInfo info = Cache.get(username);
        if (info == null){
            info = new LoginInfo();
            Cache.put(username, info);
        }
        if (info.getFailCount() <= SecurityConstant.TRY_COUNT)
            return false;

        if (new Date().getTime() - info.getLastLockTime().getTime() > SecurityConstant.LOCK_TIME * 60 * 1000){ //已经过了锁定时间
            info.setFailCount(0);
            return false;
        }

        return true;
    }

    public void addFailCount(String username){
        LoginInfo info = Cache.get(username);
        if (info == null){
            info = new LoginInfo();
            Cache.put(username, info);
        }
        int failCount = info.getFailCount() + 1;
        info.setFailCount(failCount);
        if (failCount == SecurityConstant.TRY_COUNT){
            info.setLastLockTime(new Date());
        }
    }

    public void clearFailInfo(String username){
        LoginInfo info = Cache.get(username);
        info.setFailCount(0);
        info.setLastLockTime(null);
    }


    public void login(String username, String sessionId, String token, boolean keepPassword){
        LoginInfo info = Cache.get(username);
        if (info == null || info.getSessions().size() == 0){
            info = LoginInfo.buildSuccessInfo(sessionId, token);
            if (keepPassword)
                Cache.longPut(username, info);
            else
                Cache.put(username, info);
        }
        else {
            Session session = new Session(sessionId,token);
            info.setCurrentSession(session);
            boolean hasSession = false;
            for (Session s : info.getSessions()){
                if (s.getSessionId().equals(sessionId)){
                    hasSession = true;
                    break;
                }
            }
            if (!hasSession)
                info.getSessions().add(session);
        }
    }

    public void logout(String username, String sessionId){
        LoginInfo info = Cache.get(username);
        info.removeCurrentSession(sessionId);
        if (info.getSessions().size() == 0)
            Cache.remove(username);
    }

    public void resetLoginExpires(String username){
        LoginInfo info = Cache.get(username);
        Cache.put(username, info);
    }

    public String MD5Encrypt(String password, String salt){
        return MD5Util.string2MD5(MD5Util.string2MD5(password) + salt);
    }
}
