package com.brokepal.nighty.sys.persist.account;

import com.brokepal.nighty.sys.model.account.RoleAuthority;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */
public interface RoleAuthorityDao {
    List<RoleAuthority> findList(RoleAuthority roleAuthority);
}
