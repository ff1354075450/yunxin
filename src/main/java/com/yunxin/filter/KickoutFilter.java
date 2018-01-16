package com.yunxin.filter;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;


/**
 * 控制访问过滤器
 * 控制并发登录人数
 */
public class KickoutFilter extends AccessControlFilter {

    private String kickoutUrl;//踢出后到的地址
    private boolean kickoutAfter = false;//踢出之前/之后登录的用户，默认踢出之前登录的用户
    private int maxSession = 1;//同一个账号最大回话数，默认1

    private SessionManager sessionManager;
    private Cache<String,Deque<Serializable>> cache;


    public String getKickoutUrl() {
        return kickoutUrl;
    }

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro-kickout-session");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        org.apache.shiro.subject.Subject subject = getSubject(request,response);
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            //未登录
            return true;
        }
        Session session = subject.getSession();
        String username = (String) subject.getPrincipal();
        Serializable sessionId = session.getId();
        Deque<Serializable> deque = cache.get(username);
        if (deque == null) {
            deque = new LinkedList<Serializable>();
            cache.put(username,deque);
        }

        //如果队列中没有此sessionId,且用户没有被踢出，放入队列
        if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
            deque.push(sessionId);
        }

        while (deque.size() > maxSession) {
            Serializable kickoutSessionId = null;
            if (kickoutAfter) {//踢出后者
                kickoutSessionId = deque.removeFirst();
            }else {//踢出前者
                kickoutSessionId = deque.removeLast();
            }
            try {
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if(kickoutSession != null) {
                    //设置会话的kickout属性表示踢出了
                    kickoutSession.setAttribute("kickout", true);
                }
            } catch (Exception e) {//ignore exception
            }
        }
//如果被踢出了，直接退出，重定向到踢出后的地址
        if (session.getAttribute("kickout") != null) {
            //会话被踢出了
            try {
                subject.logout();
            } catch (Exception e) { //ignore
            }
            saveRequest(request);
            WebUtils.issueRedirect(request, response, kickoutUrl);
            return false;
        }
        return true;
    }
}
