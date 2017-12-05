package com.yunxin.entity;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

/**
 * create by ff on 2017/12/5
 */
public class User {

    private Long account;

    private String password;

    private String nick;

    private String icon;

    private Timestamp gtmCreate;

    private Timestamp gtmModeified;

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 为了方便sql存储特殊字符，需要编码和转码
     * @return
     */
    public String getNick() {
        //转码，
        String temp = nick;
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            byte[] bytes = base64Decoder.decodeBuffer(temp);
            nick = new String(bytes,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nick;
    }

    public void setNick(String nick) {
        //编码
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String nic = nick;
        try {
            nic = base64Encoder.encode(nick.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.nick = nic;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Timestamp getGtmCreate() {
        return gtmCreate;
    }

    public void setGtmCreate(Timestamp gtmCreate) {
        this.gtmCreate = gtmCreate;
    }

    public Timestamp getGtmModeified() {
        return gtmModeified;
    }

    public void setGtmModeified(Timestamp gtmModeified) {
        this.gtmModeified = gtmModeified;
    }

    @Override
    public String toString() {
        return "User{" +
                "account=" + account +
                ", password='" + password + '\'' +
                ", nick='" + nick + '\'' +
                ", icon='" + icon + '\'' +
                ", gtmCreate=" + gtmCreate +
                ", gtmModeified=" + gtmModeified +
                '}';
    }
}
