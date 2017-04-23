package com.brokepal.nighty.sys.persist.account;


import com.brokepal.nighty.sys.model.account.Authority;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */
public interface AuthorityDao {
    Authority get(String id);
    List<Authority> findList(Authority authority);
}
