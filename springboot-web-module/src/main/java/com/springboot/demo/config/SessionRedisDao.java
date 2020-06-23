package com.springboot.demo.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@Component
public class SessionRedisDao extends AbstractSessionDAO {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final long  SESSION_EXPIRE_SECONDS = 24*60*60;

    // 创建session，保存到数据库
    @Override
    protected Serializable doCreate(Session session) {
        try {
            Serializable sessionId = this.generateSessionId(session);
            this.assignSessionId(session, sessionId);
            redisTemplate.opsForValue().set("session_id"+sessionId.toString(),toByte(session),SESSION_EXPIRE_SECONDS);
            return sessionId;
        } catch (Exception e) {

        }
        return null;
    }

    // 获取session
    @Override
    protected Session doReadSession(final Serializable sessionId) {
        byte[] bytes = (byte[]) redisTemplate.opsForValue().get("session_id" + sessionId);
        if(bytes != null && bytes.length>0) {
            Session session = byteToSession(bytes);
            return session;
        }
        return null;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        try {
            redisTemplate.opsForValue().set("session_id"+session.getId().toString(),session,SESSION_EXPIRE_SECONDS);        } catch (Exception e) {
        }
    }

    @Override
    public void delete(Session session) {

    }

    private byte[] toByte(Session session) {
        byte[] bytes = null;
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        try {
            ObjectOutputStream ot = new ObjectOutputStream(bo);
            ot.writeObject(session);
            bytes = bo.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    private  Session byteToSession(byte[] bytes) {

        Session session = null;
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        try {
            ObjectInputStream oi = new ObjectInputStream(bi);
            session = (Session) oi.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return session;

    }

    @Override
    public Collection<Session> getActiveSessions() {
        return Collections.emptySet();
    }



}
