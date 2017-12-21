package com.yunxin.dao;

import com.yunxin.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * create by ff on 2017/12/4
 */
public interface UserDAO {

    public User getPassword(@Param("account")long account);

    public Long register(@Param("account")long account,
                            @Param("nick")String nick,
                            @Param("password") String password,
                            @Param("icon")String icon
                            );


}
