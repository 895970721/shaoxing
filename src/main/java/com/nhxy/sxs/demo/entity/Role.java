package com.nhxy.sxs.demo.entity;

/**
 * <p>Interface: Role</p>
 * 继承此接口的类表明 他是用户角色类
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/7/18 15:42
 */

public interface Role {
    public Integer getId();

    public String getUsername();

    public String getPassword();

    public void setId(Integer id);

    public void setUsername(String username);

    public void setPassword(String password);
}
