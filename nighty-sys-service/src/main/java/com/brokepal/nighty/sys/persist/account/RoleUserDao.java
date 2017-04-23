package com.brokepal.nighty.sys.persist.account;


import com.brokepal.nighty.sys.model.account.RoleUser;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */
public interface RoleUserDao {
    List<RoleUser> findList(RoleUser roleUser);
    int createRoleUser(RoleUser roleUser);
}
