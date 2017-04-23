package com.brokepal.nighty.security.storage_object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenchao on 17/3/30.
 */

/**
 * 登录失败时缓存中需要存的对象
 * key为username
 * value为该对象
 */
public class LoginInfo implements Serializable {
    private static final long serialVersionUID = -201703302146000L;

    //登录失败时需要记录的信息
    private int failCount;
    private Date lastLockTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    //登录成功时需要记录的信息
    private Session currentSession = null;
    private List<Session> sessions = new ArrayList<Session>();

    public LoginInfo() {}

    public static LoginInfo buildFailInfo(){
        LoginInfo info = new LoginInfo();
        info.failCount = 0;
        return info;
    }

    public static LoginInfo buildSuccessInfo(String sessionId, String token){
        LoginInfo info = new LoginInfo();
        Session session = new Session(sessionId, token);
        info.currentSession = session;
        info.sessions.add(session);
        return info;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public Date getLastLockTime() {
        return lastLockTime;
    }

    public void setLastLockTime(Date lastLockTime) {
        this.lastLockTime = lastLockTime;
    }

    public void removeCurrentSession(String sessionId){
        if (sessionId == null)
            throw new IllegalArgumentException("sessionId can not be null");
        this.currentSession = null;
        for (int i = 0; i < this.sessions.size(); i++){
            if (sessionId.equals(this.sessions.get(i).getSessionId())){
                this.sessions.remove(i);
                return;
            }
        }
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
}
