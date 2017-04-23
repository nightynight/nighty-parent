package com.brokepal.nighty.sys.persist.account;


import com.brokepal.nighty.sys.model.account.Role;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */
public interface RoleDao {
    Role get(String id);
    List<Role> findList(Role role);

}
