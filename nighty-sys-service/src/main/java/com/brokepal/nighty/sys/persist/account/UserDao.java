package com.brokepal.nighty.sys.persist.account;

import com.brokepal.nighty.sys.model.account.User;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */
public interface UserDao {
    int createUser(User user);
    int updatePassword(User user);
    User getUserByUsername(String username);
    User getUserByEmail(String email);


    /******************************** 以下为管理员对应的业务方法 ************************************/

    public List<User> findList(User filterEntity);
}
