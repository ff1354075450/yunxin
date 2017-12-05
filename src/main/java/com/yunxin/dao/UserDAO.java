package com.yunxin.dao;

import org.apache.ibatis.annotations.Param;

/**
 * create by ff on 2017/12/4
 */
public interface UserDAO {

    public String getPassword(@Param("account")long account);

    public Long register(@Param("account")long account,
                            @Param("nick")String nick,
                            @Param("password") String password,
                            @Param("icon")String icon
                            );


}
